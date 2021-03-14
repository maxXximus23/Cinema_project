package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dao.domain.User;
import com.dut.CinemaProject.dto.User.AuthenticationRequestDto;
import com.dut.CinemaProject.dto.User.UserDto;
import com.dut.CinemaProject.services.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth/")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public Map<Object, Object> login(@RequestBody AuthenticationRequestDto requestDto){
        return userService.login(requestDto);
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.OK)
    public User register(@RequestBody UserDto userDto){
        return userService.register(userDto);
    }


}
