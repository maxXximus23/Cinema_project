package com.dut.CinemaProject.services.mapper;

import com.dut.CinemaProject.dao.domain.User;
import com.dut.CinemaProject.dto.User.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserMapper {


    public static User userFromDto(UserDto userDto){

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        return user;
    }
}
