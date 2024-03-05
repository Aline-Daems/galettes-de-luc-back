package be.technobel.bl.impl;

import be.technobel.bl.UserService;
import be.technobel.dal.models.entities.User;
import be.technobel.dal.repositories.UserRepository;
import be.technobel.pl.dtos.AuthDTO;
import be.technobel.pl.forms.LoginForm;
import be.technobel.pl.forms.UserForm;
import org.springframework.stereotype.Service;

@Service
public class UserServicImpl implements UserService {

    private final UserRepository userRepository;

    public UserServicImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AuthDTO login(LoginForm form) {
        return null;
    }

    @Override
    public void create(UserForm form) {

        if(form == null){
            throw new IllegalArgumentException("Le formulaire ne peut pas être vide");
        }

        User user = new User();
        user.setLastname(form.lastname());
        user.setEmail(form.email());
        user.setPassword(form.password());
        user.setRoles(form.roles());

        userRepository.save(user);

    }
}
