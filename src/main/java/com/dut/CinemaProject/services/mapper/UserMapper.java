package com.dut.CinemaProject.services.mapper;

import com.dut.CinemaProject.dao.domain.User;
import com.dut.CinemaProject.dto.User.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static User userFromDto(UserDto userDto){

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return user;
    }
}
