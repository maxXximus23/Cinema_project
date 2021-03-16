package com.dut.CinemaProject.services.services;

import com.dut.CinemaProject.dao.domain.User;
import com.dut.CinemaProject.dao.repos.UserRepository;
import com.dut.CinemaProject.exceptions.ItemNotFoundException;
import com.dut.CinemaProject.exceptions.ValidationException;
import com.dut.CinemaProject.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public String changeUserPasswordById(Long userId, String newPassword, String oldPassword) {
        User user = userRepository.findById(userId).orElseThrow(ItemNotFoundException::new);

        if(!oldPassword.equals(user.getPassword())){
            throw new ValidationException("Password doesn't match");
        }

        if(newPassword.length() > 32 || newPassword.length() < 4){
            throw new ValidationException("Password length must not be more than 32 or less 4");
        }

        if(newPassword.contains(" ") || containsIllegals(newPassword)){
            throw new ValidationException("Password must not have spaces and invalid characters");
        }

        if(newPassword.equals(oldPassword)){
            throw new ValidationException("New password is the same!");
        }

        user.setPassword(newPassword);

        userRepository.save(user);

        return "Password has been successfully changed!";
    }

    public boolean containsIllegals(String toExamine) {
        Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^,.'?/=-]");
        Matcher matcher = pattern.matcher(toExamine);
        return matcher.find();
    }

}
