package be.technobel.bl;

import be.technobel.dal.models.entities.Email;
import be.technobel.dal.models.entities.Material;
import be.technobel.dal.models.entities.Provider;
import be.technobel.pl.forms.ReceiptForm;
import jakarta.mail.MessagingException;

public interface MailService {

    void sendMail(ReceiptForm receiptForm, String templateName, Provider provider, Material material) throws MessagingException;
    void sendEmailMessage(ReceiptForm receiptForm, Provider provider, Material material) throws  MessagingException;
}
