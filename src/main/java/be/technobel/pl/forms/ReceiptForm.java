package be.technobel.pl.forms;

import be.technobel.dal.models.entities.User;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ReceiptForm{
    LocalDate receiptDate;
    double quantity;
    String providerNumber;
    LocalDate expirationDate;
    double temperature;
    boolean frozen;
    int frozenTemp;
    LocalDate frozenDate;
    LocalDate thawedDate;
    LocalDate frozenExpirationDate;
    long frozenDays;
    boolean labelling;
    String labelComment;
    boolean packaging;
    String packagingComment;
    boolean hygiene;
    String hygieneComment;
    String comment;
    String email;
    Long providerId;
    Long materialId;
    byte[] imageData;

    String imageBase64;

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

    public int getFrozenTemp() {
        return frozenTemp;
    }

    public void setFrozenTemp(int frozenTemp) {
        this.frozenTemp = frozenTemp;
    }

    public LocalDate getFrozenDate() {
        return frozenDate;
    }

    public void setFrozenDate(LocalDate frozenDate) {
        this.frozenDate = frozenDate;
    }

    public LocalDate getThawedDate() {
        return thawedDate;
    }

    public void setThawedDate(LocalDate thawedDate) {
        this.thawedDate = thawedDate;
    }

    public LocalDate getFrozenExpirationDate() {
        return frozenExpirationDate;
    }

    public void setFrozenExpirationDate(LocalDate frozenExpirationDate) {
        this.frozenExpirationDate = frozenExpirationDate;
    }

    public long getFrozenDays() {
        return frozenDays;
    }

    public void setFrozenDays(long frozenDays) {
        this.frozenDays = frozenDays;
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

    public String getHygieneComment() {
        return hygieneComment;
    }

    public void setHygieneComment(String hygieneComment) {
        this.hygieneComment = hygieneComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}


