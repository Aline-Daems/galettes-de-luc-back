package be.technobel.bl.impl;

import be.technobel.bl.MailService;
import be.technobel.dal.models.entities.Email;
import be.technobel.dal.models.entities.Material;
import be.technobel.dal.models.entities.Provider;
import be.technobel.pl.forms.ReceiptForm;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class MailServiceImpl implements MailService {


    private final  JavaMailSender emailSender;
    private final SpringTemplateEngine springTemplateEngine;

    public MailServiceImpl(JavaMailSender emailSender, SpringTemplateEngine springTemplateEngine) {
        this.emailSender = emailSender;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    public void sendEmailMessage(ReceiptForm receiptForm, Provider provider, Material material) throws MessagingException {

        sendMail(receiptForm, "email", provider, material);
    }

    public String getHtmlContent(Email email){

        Context context = new Context();
        context.setVariables(email.getHtmlTemplate().getProps());
        return springTemplateEngine.process(email.getHtmlTemplate().getTemplate(), context);
    }
    @Override
    public void sendMail(ReceiptForm receiptForm, String templateName, Provider provider, Material material) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Map<String, Object> properties = new HashMap<>();
        properties.put("reception", receiptForm);
        properties.put("provider", provider);
        properties.put("material", material);

        Email email = new Email(receiptForm.email(), "daems.aline90@gmail.com", "Formulaire de réception" + LocalDate.now(), new Email.HtmlTemplate(templateName, properties));


        String html = getHtmlContent(email);


        helper.setFrom(email.getFrom());
        helper.setTo(receiptForm.email());
        helper.setSubject(email.getSubject());
        helper.setText(html,true);


        emailSender.send(message);
    }
}
