package be.technobel.bl.impl;

import be.technobel.bl.MailService;
import be.technobel.bl.ReceiptService;
import be.technobel.dal.models.entities.Email;
import be.technobel.dal.models.entities.Provider;
import be.technobel.dal.models.entities.Receipt;
import be.technobel.dal.repositories.MaterialRepository;
import be.technobel.dal.repositories.ProviderRepository;
import be.technobel.dal.repositories.ReceiptRepository;
import be.technobel.pl.forms.ReceiptForm;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import be.technobel.dal.models.entities.Email;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ProviderRepository providerRepository;
    private final JavaMailSender emailSender;
    private final MailService mailService;
    private final MaterialRepository materialRepository;
    private final SpringTemplateEngine springTemplateEngine;

    public ReceiptServiceImpl(ReceiptRepository receiptRepository, ProviderRepository providerRepository, JavaMailSender emailSender, MailService mailService, MaterialRepository materialRepository1, SpringTemplateEngine springTemplateEngine) {
        this.receiptRepository = receiptRepository;
        this.providerRepository = providerRepository;
        this.emailSender = emailSender;
        this.mailService = mailService;
        this.materialRepository = materialRepository1;

        this.springTemplateEngine = springTemplateEngine;
    }


    @Override
    public Long create(ReceiptForm receiptForm) throws MessagingException {

        if(receiptForm == null){
            throw new IllegalArgumentException("Form can't be null");
        }

        Receipt receipt = new Receipt();

        receipt.setReceiptDate(LocalDate.now());
        receipt.setQuantity(receiptForm.quantity());
        receipt.setProviderNumber(receiptForm.providerNumber());
        receipt.setExpirationDate(receiptForm.expirationDate());
        receipt.setTemperature(receiptForm.temperature());
        receipt.setFrozen(receiptForm.frozen());
        receipt.setFrozenTemp(receiptForm.frozenTemp());
        receipt.setFrozenDate(receiptForm.frozenDate());
        receipt.setThawedDate(receiptForm.thawedDate());
        receipt.setFrozenExpirationDate(receiptForm.frozenExpirationDate());
        receipt.setLabelling(receiptForm.labelling());
        receipt.setLabelComment(receiptForm.labelComment());
        receipt.setPackaging(receiptForm.packaging());
        receipt.setPackagingComment(receiptForm.packagingComment());
        receipt.setHygiene(receiptForm.hygiene());
        receipt.setHygienComment(receiptForm.hygieneComment());
        receipt.setEmail(receiptForm.email());
        receipt.setProvider(providerRepository.findById(receiptForm.providerId()).orElseThrow(()-> new EntityNotFoundException("Provider not found")));
        receipt.setMaterial(materialRepository.findById(receiptForm.materialId()).orElseThrow(() -> new EntityNotFoundException("Material not found ")));

        mailService.sendEmailMessage(receiptForm, receipt.getProvider(), receipt.getMaterial());
        receiptRepository.save(receipt);
        return receipt.getId();


    }




    @Override
    public Optional<Receipt> getOne(Long id) {
        return receiptRepository.findById(id);
    }

    @Override
    public void dataImage(byte[] file, Long id)  {
          Receipt receipt = getOne(id).orElseThrow(()-> new EntityNotFoundException("Id not found"));
          receipt.setImageData(file);



          receiptRepository.save(receipt);

    }

    @Override
    public Receipt getLast() {
        return receiptRepository.findFirstByOrderByIdDesc();
    }
}
