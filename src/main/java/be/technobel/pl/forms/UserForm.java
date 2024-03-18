package be.technobel.pl.forms;

import be.technobel.dal.models.enums.enums.Roles;

public record UserForm(
        String firstname,
        String lastname,

        String password,

        String email,

        Roles role



) {
}
