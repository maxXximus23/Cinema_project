package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dto.User.UserDto;
import com.dut.CinemaProject.services.interfaces.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
    private IAccountService accountService;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto showAccount(@PathVariable Long id){
        return (accountService.getUserAccount(id));
    }
}
