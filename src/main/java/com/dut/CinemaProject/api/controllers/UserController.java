package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dto.Ticket.PurchaseTicket;
import com.dut.CinemaProject.dto.User.UserDto;
import com.dut.CinemaProject.services.interfaces.IAccountService;
import com.dut.CinemaProject.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
    private final IAccountService accountService;
    private final IUserService userService;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto showAccount(@PathVariable Long id){
        return (accountService.getUserAccount(id));
    }

    @PostMapping("{userId}/changePassword")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@PathVariable Long userId, String newPassword, String oldPassword){
        userService.changeUserPasswordById(userId, newPassword, oldPassword);
    }
}
