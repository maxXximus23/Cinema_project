package com.dut.CinemaProject.dto.User;

import com.dut.CinemaProject.dao.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;

    public UserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
    }

}
