package be.technobel.bll;

import be.technobel.bl.impl.UserServiceImpl;
import be.technobel.dal.models.entities.User;
import be.technobel.dal.models.enums.enums.Roles;
import be.technobel.dal.repositories.UserRepository;
import be.technobel.pl.forms.UserForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private User user;
    private UserForm userForm;

    @BeforeEach
    void setUp(){

        userForm = new UserForm("Jane", "Doe", "Test1234", "jane@doe.be", Roles.admin);
        userForm = new UserForm("Jane", "Doe", "Test1234", "jane@doe.be", Roles.admin);


    }
   @Test
    void when_create_ok(){
       when(userRepository.save(any(User.class))).thenReturn(user);

       userService.create(userForm);

       verify(userRepository, times(1)).save(any(User.class));
   }

   @Test
    void when_create_ko(){

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.create(null));

        String exeptedMessage = "Le formulaire doit être rempli";
        String actualMessage = exception.getMessage();

        assertEquals(exeptedMessage, actualMessage);
   }


}
