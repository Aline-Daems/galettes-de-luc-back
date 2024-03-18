package be.technobel.pl.controllers;

import be.technobel.bl.MailService;
import be.technobel.dal.models.entities.MailStructure;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {

    private MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send/{mail}")
    public String sendMail(@PathVariable String mail, @RequestBody MailStructure mailStructure){

        mailService.sendMail(mail, mailStructure);

        return "success";
    }


}
