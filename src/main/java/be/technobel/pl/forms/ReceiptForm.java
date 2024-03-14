package be.technobel.pl.forms;

import be.technobel.dal.models.entities.User;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReceiptForm(

        LocalDate receiptDate,
        double quantity,
        String providerNumber,
        LocalDate expirationDate,
        double temperature,
        boolean frozen,
        LocalDate frozenDate,
        LocalDate thawedDate,
        LocalDate frozenExpirationDate,
        long frozenDays,
        boolean labelling,
        String labelComment,
        boolean packaging,
        String packagingComment,
        boolean hygiene,
        String hygieneComment,
        String comment,
        String email,
        Long providerId,
        Long materialId,
        byte[] imageData
) {

}
