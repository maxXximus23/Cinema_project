package com.dut.CinemaProject.dto.User;

import com.dut.CinemaProject.dao.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePassword {
    public ChangePassword(String oldPassword, String newPassword){
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    private String oldPassword;
    private String newPassword;
}
