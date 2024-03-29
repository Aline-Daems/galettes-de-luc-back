package be.technobel.bl;

import be.technobel.dal.models.entities.Receipt;
import be.technobel.pl.dtos.ReceiptDTOMinus;
import be.technobel.pl.forms.ReceiptForm;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ReceiptService {

    Long create(ReceiptForm receiptForm) throws MessagingException;
    Optional<Receipt> getOne(Long id);

   void dataImage(byte[] file, Long id);

   byte[] getImageData(Long id);

   List<ReceiptDTOMinus> getAll();

   void delete(Long id);


}
