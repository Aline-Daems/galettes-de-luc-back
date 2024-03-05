package be.technobel.pl.dtos;

import be.technobel.dal.models.entities.enums.Roles;

public record AuthDTO(
        String Token,
        String email,
        Roles roles


) {
    public AuthDTO(String Token, String email, Roles roles) {
        this.Token = Token;
        this.email = email;
        this.roles = roles;
    }
}
