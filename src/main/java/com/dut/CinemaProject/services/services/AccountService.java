package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.repos.UserRepository;
import com.dut.CinemaProject.dto.User.UserDto;
import com.dut.CinemaProject.services.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDto getUserAccount(Long id) {
        return new UserDto(userRepository.findById(id).orElseThrow(ItemNotFoundException::new));
    }
}
