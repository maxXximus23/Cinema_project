package com.dut.CinemaProject.services.interfaces;

import com.dut.CinemaProject.dao.domain.JwtBlacklist;
import com.dut.CinemaProject.dao.domain.User;
import com.dut.CinemaProject.dto.User.AuthenticationRequestDto;
import com.dut.CinemaProject.dto.User.UserDto;
import com.dut.CinemaProject.dto.User.UserRegisterData;

import java.util.List;
import java.util.Map;

public interface IUserService {

    Map<String, String> login(AuthenticationRequestDto requestDto);

    JwtBlacklist logout(Map<String, String> token);

    UserDto register(UserRegisterData userRegisterData);

    User findByEmail(String email);

    List<User> getAll();

    User findById(Long id);

    void delete(Long id);
}
