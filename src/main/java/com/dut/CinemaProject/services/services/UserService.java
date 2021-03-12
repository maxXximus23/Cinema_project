package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.domain.User;
import com.dut.CinemaProject.dao.repos.UserRepository;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.exceptions.ValidationException;
import com.dut.CinemaProject.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public void changeUserPasswordById(Long userId, String newPassword, String oldPassword) {
        User user = userRepository.findById(userId).orElseThrow(ItemNotFoundException::new);

        if(oldPassword != user.getPassword()){
            throw new ValidationException("Password doesn't match");
        }

        if(newPassword.length() > 32){
            throw new ValidationException("Password length is more than can be");
        }

        user.setPassword(newPassword);

        userRepository.save(user);

    }
}
