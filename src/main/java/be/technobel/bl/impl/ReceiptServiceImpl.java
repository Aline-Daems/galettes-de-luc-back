package be.technobel.bl.impl;

import be.technobel.bl.ReceiptService;
import be.technobel.dal.models.entities.Receipt;
import be.technobel.dal.repositories.ReceiptRepository;
import be.technobel.pl.forms.ReceiptForm;
import org.springframework.stereotype.Service;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;

    public ReceiptServiceImpl(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }


    @Override
    public void create(ReceiptForm receiptForm) {

        if(receiptForm == null){
            throw new IllegalArgumentException("Le formulaire doit être rempli");
        }

        Receipt receipt = new Receipt();

        receipt.setReceiptDate(receiptForm.receiptDate().now());
        receipt.setQuantity(receiptForm.quantity());
        receipt.setProviderNumber(receiptForm.providerNumber());
        receipt.setExpirationDate(receiptForm.expirationDate());
        receipt.setTemperature(receiptForm.temperature());
        receipt.setFrozen(receiptForm.frozen());
        receipt.setFrozenDate(receiptForm.frozenDate());
        receipt.setLabelling(receiptForm.labelling());
        receipt.setLabelComment(receiptForm.labelComment());
        receipt.setPackaging(receiptForm.packaging());
        receipt.setPackagingComment(receiptForm.packagingComment());
        receipt.setHygiene(receiptForm.hygiene());
        receipt.setHygienComment(receiptForm.hygienComment());
        receipt.setEmail(receiptForm.email());

        receiptRepository.save(receipt);


    }
}
