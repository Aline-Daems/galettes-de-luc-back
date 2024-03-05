package be.technobel.bl.impl;

import be.technobel.bl.UserService;
import be.technobel.dal.models.entities.User;
import be.technobel.dal.repositories.UserRepository;
import be.technobel.pl.dtos.AuthDTO;
import be.technobel.pl.forms.LoginForm;
import be.technobel.pl.forms.UserForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthDTO login(LoginForm form) {
        return null;
    }

    @Override
    public void create(UserForm form) {

        if(form == null){
            throw new IllegalArgumentException("Le formulaire doit être rempli");
        }

        User user = new User();
        user.setFirstname(form.firstname());
        user.setLastname(form.lastname());
        user.setEmail(form.email());
        user.setPassword(passwordEncoder.encode(form.password()));
        user.setRoles(form.roles());

        userRepository.save(user);

    }
}
