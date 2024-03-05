package be.technobel.pl.controllers;

import be.technobel.bl.UserService;
import be.technobel.pl.dtos.AuthDTO;
import be.technobel.pl.forms.LoginForm;
import be.technobel.pl.forms.UserForm;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

   // @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public AuthDTO login(@RequestBody LoginForm form){
        return userService.login(form);
    }

  //  @PreAuthorize("hasRole('admin')")
    @PostMapping("/create")
    public void create(@RequestBody UserForm form){
        userService.create(form);
    }
}
