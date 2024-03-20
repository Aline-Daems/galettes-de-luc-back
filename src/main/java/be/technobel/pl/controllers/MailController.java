package be.technobel.pl.controllers;

import be.technobel.bl.MailService;
import be.technobel.bl.MaterialService;
import be.technobel.bl.ProviderService;
import be.technobel.bl.ReceiptService;
import be.technobel.dal.models.entities.Material;
import be.technobel.dal.models.entities.Provider;
import be.technobel.pl.forms.ReceiptForm;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {

    private MailService mailService;
    private MaterialService materialService;

    private  ProviderService providerService;

    public MailController(MailService mailService, MaterialService materialService, ProviderService providerService) {
        this.mailService = mailService;
        this.materialService = materialService;
        this.providerService = providerService;
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody ReceiptForm  receiptForm, @RequestParam String templateName, @RequestParam Long providerId, @RequestParam Long materialId){

        try{
            Provider provider = providerService.getOne(providerId);
            Material material = materialService.getOne(materialId);

            mailService.sendMail(receiptForm, templateName, provider, material);

            return ResponseEntity.ok("Email sent successfully");
        }catch (MessagingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email");
        }
    }
}
