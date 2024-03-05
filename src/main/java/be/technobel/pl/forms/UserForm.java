package be.technobel.pl.forms;

import be.technobel.dal.models.entities.enums.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record UserForm(
        String firstname,
        String lastname,

        String password,

        String email,

        Roles roles



) {
}
