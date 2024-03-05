package be.technobel.pl.forms;

import lombok.Data;


public record LoginForm(

        String email,
        String password


) {


    @Override
    public String email() {
        return email;
    }

    @Override
    public String password() {
        return password;
    }
}
