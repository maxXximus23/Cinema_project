package com.dut.CinemaProject.services.interfaces;

public interface IUserService {
    void changeUserPasswordById(Long userId, String newPassword, String oldPassword);
}
