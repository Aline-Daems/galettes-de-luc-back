package be.technobel.bl.impl;

import be.technobel.bl.MailService;
import be.technobel.dal.models.entities.Email;
import be.technobel.dal.models.entities.Material;
import be.technobel.dal.models.entities.Provider;
import be.technobel.dal.models.entities.Receipt;
import be.technobel.pl.config.DocumentGenerator;
import be.technobel.pl.forms.ReceiptForm;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {


    private final  JavaMailSender emailSender;
    private final SpringTemplateEngine springTemplateEngine;
    private final DocumentGenerator documentGenerator;

    public MailServiceImpl(JavaMailSender emailSender, SpringTemplateEngine springTemplateEngine, DocumentGenerator documentGenerator) {
        this.emailSender = emailSender;
        this.springTemplateEngine = springTemplateEngine;
        this.documentGenerator = documentGenerator;
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

        MimeMessageHelper helper = new MimeMessageHelper(message,  MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        String imageBase64 = Base64.getEncoder().encodeToString(receiptForm.getImageData());

        receiptForm.setImageBase64(imageBase64);




        Map<String, Object> properties = new HashMap<>();
        properties.put("reception", receiptForm);
        properties.put("provider", provider);
        properties.put("material", material);



        Email email = new Email(receiptForm.getEmail(), receiptForm.getEmail(), "Formulaire de réception "+ " " + LocalDate.now() +" " +provider.getName(), new Email.HtmlTemplate(templateName, properties));


        String html = getHtmlContent(email);
        byte[] pdfBytes = documentGenerator.htmlToPdf(html);
        ByteArrayResource pdfAttachement = new ByteArrayResource(pdfBytes);




        helper.setFrom(email.getFrom());
        helper.setTo(receiptForm.getEmail());
        helper.setSubject(email.getSubject());
        helper.setText(html,true);
        helper.addAttachment("formulaire-reception "+ LocalDate.now() +".pdf" , pdfAttachement  );


        emailSender.send(message);
    }
}
