package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.domain.Role;
import com.dut.CinemaProject.dao.domain.Status;
import com.dut.CinemaProject.dao.domain.User;
import com.dut.CinemaProject.dao.repos.RoleRepository;
import com.dut.CinemaProject.dao.repos.UserRepository;
import com.dut.CinemaProject.dto.User.AuthenticationRequestDto;
import com.dut.CinemaProject.dto.User.UserDto;
import com.dut.CinemaProject.exceptions.EmailAlreadyExistsException;
import com.dut.CinemaProject.exceptions.UserNotFoundException;
import com.dut.CinemaProject.security.jwt.JwtTokenProvider;
import com.dut.CinemaProject.services.interfaces.IUserService;
import com.dut.CinemaProject.services.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       AuthenticationManager authenticationManager,
                       JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Map<Object, Object> login(AuthenticationRequestDto requestDto) {
        try{
            String email = requestDto.getEmail();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));

            User user = findByEmail(email);

            if(user == null){
                throw new UsernameNotFoundException("User with username: " + email + "not found");
            }

            String token = jwtTokenProvider.createToken(email, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", email);
            response.put("token", token);

            return response;

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
    }

    @Override
    public User register(UserDto userDto) {

        if(!isEmailFree(userDto))
            throw new EmailAlreadyExistsException("This email is already registered");


        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        User user = UserMapper.userFromDto(userDto);

        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        user.setCreated(new Date());

        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
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

    private boolean isEmailFree(UserDto userDto){
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
        return user.isEmpty();
    }
}

