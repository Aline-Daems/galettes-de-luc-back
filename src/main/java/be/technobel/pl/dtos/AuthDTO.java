package be.technobel.pl.dtos;

import be.technobel.dal.models.entities.enums.Roles;

public record AuthDTO(
        String token,
        String firstname,
        String email,
        Roles roles


) {

    public static AuthDTO create(String token, String firstname, String email, Roles roles){
        return new AuthDTO(token, firstname,  email, roles);
    }
}
