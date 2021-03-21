package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.domain.JwtBlacklist;
import com.dut.CinemaProject.dao.domain.Role;
import com.dut.CinemaProject.dao.domain.Status;
import com.dut.CinemaProject.dao.domain.User;
import com.dut.CinemaProject.dao.repos.JwtBlacklistRepository;
import com.dut.CinemaProject.dao.repos.RoleRepository;
import com.dut.CinemaProject.dao.repos.UserRepository;
import com.dut.CinemaProject.dto.User.AuthenticationRequestDto;
import com.dut.CinemaProject.dto.User.UserDto;
import com.dut.CinemaProject.dto.User.UserRegisterData;
import com.dut.CinemaProject.exceptions.EmailAlreadyExistsException;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.exceptions.JwtAuthenticationException;
import com.dut.CinemaProject.exceptions.UserNotFoundException;
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

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtBlacklistService jwtBlacklistService;


    @Override
    public Map<String, String> login(AuthenticationRequestDto requestDto) {

            String email = requestDto.getEmail();
            User user = findByEmail(email);

            checkUserStatus(user);

            user.setStatus(Status.ACTIVE);
            userRepository.save(user);

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));

            Map<String, String> response = new HashMap<>();
            response.put("id", user.getId().toString());
            response.put("token", jwtTokenProvider.createToken(email, user.getRoles()));

            return response;


    }

    @Override
    public void logout(Map<String, String> json) {
        String token = json.get("token");
        JwtBlacklist jwtBlacklist = new JwtBlacklist();
        jwtBlacklist.setToken(token);

        User user = userRepository.findById(Long.parseLong(json.get("id")))
                .orElseThrow(() -> new UserNotFoundException("No such user in database"));

        user.setStatus(Status.NOT_ACTIVE);
        userRepository.save(user);

        jwtBlacklistService.saveTokenToBlacklist(jwtBlacklist);
    }

    private void checkUserStatus(User user) {
        if(user.getStatus().name().equals("ACTIVE"))
            throw new JwtAuthenticationException("User is already logged in");

        if(user.getStatus().name().equals("BLOCKED"))
            throw new JwtAuthenticationException("User is blocked");
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
}

