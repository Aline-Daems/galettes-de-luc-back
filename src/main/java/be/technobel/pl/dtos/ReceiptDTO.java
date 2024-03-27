package be.technobel.pl.dtos;

import be.technobel.dal.models.entities.Material;
import be.technobel.dal.models.entities.Provider;
import be.technobel.dal.models.entities.Receipt;

import java.time.LocalDate;

public record ReceiptDTO(
        Long id,
        LocalDate receiptDate,

        String email,

        double quantity,

        String providerNumber,

        LocalDate expirationDate,

        double temperature,

        boolean frozen,

        LocalDate frozenDate,

        int frozenTemp,

        LocalDate FrozenExpirationDate,

        boolean labelling,

        String labelComment,

        boolean packaging,

        String packagingComment,

        boolean hygiene,

        String hygieneComment,

        String comment,

        Provider provider,

        Material material,

        byte[] imageData,

        Boolean active) {



        public static ReceiptDTO fromEntity (Receipt receipt){
        return new ReceiptDTO(receipt.getId(), receipt.getReceiptDate(), receipt.getEmail(), receipt.getQuantity(), receipt.getProviderNumber(), receipt.getExpirationDate(), receipt.getTemperature(), receipt.isFrozen(),receipt.getFrozenDate() ,receipt.getFrozenTemp(),  receipt.getFrozenExpirationDate(), receipt.isLabelling(), receipt.getLabelComment(), receipt.isPackaging(), receipt.getPackagingComment(), receipt.isHygiene(), receipt.getHygieneComment(), receipt.getComment(), receipt.getProvider(), receipt.getMaterial(), receipt.getImageData(), receipt.getActive());
    }
}

