package com.dut.CinemaProject.api.controllers;

import com.dut.CinemaProject.dto.User.UserDto;
import com.dut.CinemaProject.services.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private IAccountService accountService;
    @GetMapping("{id}")
    public ResponseEntity<UserDto> showAccount(@PathVariable Long id){
        try {
            return ResponseEntity.ok(accountService.getUserAccount(id));
        } catch (ItemNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }

}
