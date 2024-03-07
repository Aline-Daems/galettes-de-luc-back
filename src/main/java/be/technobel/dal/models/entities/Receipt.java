package be.technobel.dal.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private LocalDate receiptDate;
    @NotNull
    private double quantity;
    @NotNull
    private String providerNumber;
    @NotNull
    private LocalDate expirationDate;

    private double temperature;
    @NotNull
    private boolean frozen;

    private LocalDate frozenDate;
    @NotNull
    private boolean labelling;
    private String labelComment;
    @NotNull
    private boolean packaging;
    private String packagingComment;
    @NotNull
    private boolean hygiene;
    private String hygienComment;

    private String comment;
    @ManyToOne
    private User user;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getProviderNumber() {
        return providerNumber;
    }

    public void setProviderNumber(String providerNumber) {
        this.providerNumber = providerNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public LocalDate getFrozenDate() {
        return frozenDate;
    }

    public void setFrozenDate(LocalDate frozenDate) {
        this.frozenDate = frozenDate;
    }

    public boolean isLabelling() {
        return labelling;
    }

    public void setLabelling(boolean labelling) {
        this.labelling = labelling;
    }

    public String getLabelComment() {
        return labelComment;
    }

    public void setLabelComment(String labelComment) {
        this.labelComment = labelComment;
    }

    public boolean isPackaging() {
        return packaging;
    }

    public void setPackaging(boolean packaging) {
        this.packaging = packaging;
    }

    public String getPackagingComment() {
        return packagingComment;
    }

    public void setPackagingComment(String packagingComment) {
        this.packagingComment = packagingComment;
    }

    public boolean isHygiene() {
        return hygiene;
    }

    public void setHygiene(boolean hygiene) {
        this.hygiene = hygiene;
    }

    public String getHygienComment() {
        return hygienComment;
    }

    public void setHygienComment(String hygienComment) {
        this.hygienComment = hygienComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
