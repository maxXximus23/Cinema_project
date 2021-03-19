package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.domain.Role;
import com.dut.CinemaProject.dao.domain.Status;
import com.dut.CinemaProject.dao.domain.User;
import com.dut.CinemaProject.dao.repos.RoleRepository;
import com.dut.CinemaProject.dao.repos.UserRepository;
import com.dut.CinemaProject.dto.User.AuthenticationRequestDto;
import com.dut.CinemaProject.dto.User.UserDto;
import com.dut.CinemaProject.dto.User.UserRegisterData;
import com.dut.CinemaProject.exceptions.EmailAlreadyExistsException;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.exceptions.JwtAuthenticationException;
import com.dut.CinemaProject.exceptions.UserNotFoundException;
import com.dut.CinemaProject.exceptions.ValidationException;
import com.dut.CinemaProject.security.jwt.JwtTokenProvider;
import com.dut.CinemaProject.services.interfaces.IUserService;
import com.dut.CinemaProject.services.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public String login(AuthenticationRequestDto requestDto) {

        String email = requestDto.getEmail();

        User user = findByEmail(email);

        if(user.getStatus().name().equals("ACTIVE"))
            throw new JwtAuthenticationException("User is already logged in");

        if(user.getStatus().name().equals("BLOCKED"))
            throw new JwtAuthenticationException("User is blocked");

        user.setStatus(Status.ACTIVE);
        userRepository.save(user);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));

        return jwtTokenProvider.createToken(email, user.getRoles());


    }

    @Override
    public UserDto register(UserRegisterData userRegisterData) {

        isEmailFree(userRegisterData.getEmail());


        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        User user = UserMapper.userFromRegisterData(userRegisterData);

        user.setRoles(userRoles);
        user.setStatus(Status.NOT_ACTIVE);
        user.setCreated(new Date());

        return new UserDto(userRepository.save(user));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> new ItemNotFoundException("User with email " + email + " not found"));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No such user in database"));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public boolean isEmailFree(String email){
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if(user.isPresent())
            throw new EmailAlreadyExistsException("This email is already registered");

        return true;
    }

    @Override
    public String changeUserPasswordById(Long userId, String newPassword, String oldPassword) {
        User user = userRepository.findById(userId).orElseThrow(ItemNotFoundException::new);

        if(!oldPassword.equals(user.getPassword())){
            throw new ValidationException("Password doesn't match");
        }

        if(newPassword.length() > 32 || newPassword.length() < 4){
            throw new ValidationException("Password length must not be more than 32 or less 4");
        }

        if(newPassword.contains(" ") || containsIllegals(newPassword)){
            throw new ValidationException("Password must not have spaces and invalid characters");
        }

        if(newPassword.equals(oldPassword)){
            throw new ValidationException("New password is the same!");
        }

        user.setPassword(newPassword);

        userRepository.save(user);

        return "Password has been successfully changed!";
    }

    public boolean containsIllegals(String toExamine) {
        Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^,.'?/=-]");
        Matcher matcher = pattern.matcher(toExamine);
        return matcher.find();
    }
}

