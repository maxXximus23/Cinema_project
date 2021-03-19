package com.dut.CinemaProject.dto.User;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String email;
    private String password;
}
