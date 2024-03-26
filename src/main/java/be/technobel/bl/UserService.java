package be.technobel.bl;

import be.technobel.dal.models.entities.User;
import be.technobel.pl.dtos.AuthDTO;
import be.technobel.pl.forms.LoginForm;
import be.technobel.pl.forms.UserForm;

import java.util.Optional;

public interface UserService {


   AuthDTO login(LoginForm form);

   void create(UserForm form);




}
