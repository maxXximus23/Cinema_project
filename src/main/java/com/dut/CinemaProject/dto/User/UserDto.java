package com.dut.CinemaProject.dto.User;

import com.dut.CinemaProject.dao.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;

    public UserDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

}
