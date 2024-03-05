package be.technobel.bl;

import be.technobel.pl.dtos.AuthDTO;
import be.technobel.pl.forms.LoginForm;
import be.technobel.pl.forms.UserForm;

public interface UserService {


   AuthDTO login(LoginForm form);

   void create(UserForm form);


}
