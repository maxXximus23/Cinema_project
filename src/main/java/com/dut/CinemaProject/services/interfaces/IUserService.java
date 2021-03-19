package com.dut.CinemaProject.services.interfaces;

import com.dut.CinemaProject.dto.User.AuthenticationRequestDto;
import com.dut.CinemaProject.dto.User.UserDto;
import com.dut.CinemaProject.dto.User.UserRegisterData;

import java.util.List;

public interface IUserService {

    String login(AuthenticationRequestDto requestDto);

    UserDto register(UserRegisterData userRegisterData);

    List<UserDto> getAll();

    UserDto findById(Long id);

    void delete(Long id);

    String changeUserPasswordById(Long userId, String newPassword, String oldPassword);
}
