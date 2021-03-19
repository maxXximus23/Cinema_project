package com.dut.CinemaProject.dto.User;

import com.dut.CinemaProject.dao.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    public UserDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
}
