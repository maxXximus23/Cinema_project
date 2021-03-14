package com.dut.CinemaProject.services.interfaces;

public interface IUserService {
    String changeUserPasswordById(Long userId, String newPassword, String oldPassword);
}
