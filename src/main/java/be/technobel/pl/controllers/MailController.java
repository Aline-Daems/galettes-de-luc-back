package be.technobel.pl.controllers;

import be.technobel.bl.MailService;
import be.technobel.dal.models.entities.Email;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;


    public MailController(MailService mailService) {
        this.mailService = mailService;
    }



    @PostMapping("/email/send/html")
    public void sendHtmlMessage(@RequestBody Email email) throws MessagingException {
    //  mailService.sendMail();
    }




}
