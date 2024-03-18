package be.technobel.bl.impl;

import be.technobel.bl.UserService;
import be.technobel.dal.models.entities.User;
import be.technobel.dal.repositories.UserRepository;
import be.technobel.pl.config.JWTProvider;
import be.technobel.pl.dtos.AuthDTO;
import be.technobel.pl.forms.LoginForm;
import be.technobel.pl.forms.UserForm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final JWTProvider jwtProvider;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public AuthDTO login(LoginForm form) {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword()));
      User user = userRepository.findByEmail(form.getEmail()).get();

      String token = jwtProvider.generateToken(user.getUsername(),  user.getEmail(), user.getFirstname() , user.getRoles());

      AuthDTO authDTO = AuthDTO.create(token, user.getFirstname(), user.getEmail(),user.getRoles());

      return authDTO;

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
        user.setRoles(form.role());

        userRepository.save(user);

    }
}
