package be.technobel.bl;

import be.technobel.dal.models.entities.Receipt;
import be.technobel.pl.forms.ReceiptForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface ReceiptService {

    void create(ReceiptForm receiptForm);
    Optional<Receipt> getOne(Long id);

    ResponseEntity<String> dataImage(MultipartFile file, Long id) throws IOException;
}
