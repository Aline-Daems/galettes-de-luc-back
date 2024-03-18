package be.technobel.bl;

import be.technobel.dal.models.entities.MailStructure;

public interface MailService {

    void sendMail(String mail, MailStructure mailStructure);
}
