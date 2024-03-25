package be.technobel.dal.repositories;

import be.technobel.dal.models.entities.Receipt;
import be.technobel.pl.dtos.ReceiptDTOMinus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

   List<ReceiptDTOMinus> findByActiveTrue();
}
