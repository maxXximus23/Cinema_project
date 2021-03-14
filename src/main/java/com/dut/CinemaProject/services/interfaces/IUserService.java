package com.dut.CinemaProject.services.interfaces;

import com.dut.CinemaProject.dao.domain.User;
import com.dut.CinemaProject.dto.User.AuthenticationRequestDto;
import com.dut.CinemaProject.dto.User.UserDto;

import java.util.List;
import java.util.Map;

public interface IUserService {

    Map<Object, Object> login(AuthenticationRequestDto requestDto);

    User register(UserDto userDto);

    User findByEmail(String email);

    List<User> getAll();

    User findById(Long id);

    void delete(Long id);
}
