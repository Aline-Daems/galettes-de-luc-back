package be.technobel.pl.dtos;

import be.technobel.dal.models.entities.User;
import be.technobel.dal.models.enums.enums.Roles;

public record UserDTO(

        Long id,
        String lastname,

        String fristname,

        String password,

        String email,

        Roles roles
) {

    public static UserDTO fromEntity (User user){

        return new UserDTO(user.getId(), user.getLastname(), user.getFirstname(), user.getPassword(), user.getEmail(), user.getRoles());
    }
}
