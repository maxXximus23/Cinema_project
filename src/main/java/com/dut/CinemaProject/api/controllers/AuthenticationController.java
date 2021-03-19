package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dao.domain.User;
import com.dut.CinemaProject.dto.User.AuthenticationRequestDto;
import com.dut.CinemaProject.dto.User.UserDto;
import com.dut.CinemaProject.dto.User.UserRegisterData;
import com.dut.CinemaProject.services.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/users/")
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody AuthenticationRequestDto requestDto){
        return userService.login(requestDto);
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.OK)
    public UserDto register(@RequestBody UserRegisterData userRegisterData){
        return userService.register(userRegisterData);
    }

    @GetMapping("check-{email}")
    @ResponseStatus(HttpStatus.OK)
    public void isEmailFree(@PathVariable String email){
        userService.isEmailFree(email);
    }


}
