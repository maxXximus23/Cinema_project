package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dto.User.AuthenticationRequestDto;
import com.dut.CinemaProject.services.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto){

        return ResponseEntity.ok(userService.login(requestDto));
    }


}
