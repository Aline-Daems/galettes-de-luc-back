package be.technobel.dal.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Entity
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private LocalDate receiptDate;
    @NotNull
    private double quantity;
    @NotNull
    private String providerNumber;
    @NotNull
    @Future(message = "La date d'expiration ne peut pas être inférieure ou égale à la date du jour.")
    private LocalDate expirationDate;
    private double temperature;
    @NotNull
    private boolean frozen =false;

    private int frozenTemp;
    private LocalDate frozenDate;

    private LocalDate frozenExpirationDate;
    @NotNull
    private boolean labelling =false;
    private String labelComment;
    @NotNull
    private boolean packaging=false;
    private String packagingComment;
    @NotNull
    private boolean hygiene=false;
    private String hygieneComment;

    private String comment;

    private String email;

    //The content of the document, stored as a large object (LOB)
    @Lob
    private byte[] imageData;

    private String imageBase64;
    @ManyToOne
    private Provider provider;


    @ManyToOne
    Material material;

    private Boolean active = true;




    public Receipt(Long id, LocalDate receiptDate, double quantity, String providerNumber, LocalDate expirationDate, double temperature, boolean frozen, int frozenTemp, LocalDate frozenDate, LocalDate frozenExpirationDate, boolean labelling, String labelComment, boolean packaging, String packagingComment, boolean hygiene, String hygieneComment, String comment, String email, byte[] imageData, String imageBase64, Provider provider, Material material, Boolean active) {
        this.id = id;
        this.receiptDate = receiptDate;
        this.quantity = quantity;
        this.providerNumber = providerNumber;
        this.expirationDate = expirationDate;
        this.temperature = temperature;
        this.frozen = frozen;
        this.frozenTemp = frozenTemp;
        this.frozenDate = frozenDate;
        this.frozenExpirationDate = frozenExpirationDate;
        this.labelling = labelling;
        this.labelComment = labelComment;
        this.packaging = packaging;
        this.packagingComment = packagingComment;
        this.hygiene = hygiene;
        this.hygieneComment = hygieneComment;
        this.comment = comment;
        this.email = email;
        this.imageData = imageData;
        this.imageBase64 = imageBase64;
        this.provider = provider;
        this.material = material;
        this.active = active;
    }



    public Receipt() {

    }

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

        if(quantity < 0 ){
            throw new IllegalStateException("La quantité doit être supérieur à 0");

        }
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
        if(expirationDate.isBefore(LocalDate.now())) {

            throw new IllegalStateException("La date d'expiration ne peut pas être inférieure ou égale à la date du jour.");

        }

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



    public LocalDate getFrozenExpirationDate() {
        return frozenExpirationDate;
    }

    public void setFrozenExpirationDate(LocalDate frozenExpirationDate) {
        this.frozenExpirationDate = frozenExpirationDate;
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
        return hygieneComment;
    }

    public void setHygienComment(String hygienComment) {
        this.hygieneComment = hygienComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public Provider getProvider() {
        return provider;
    }
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getFrozenTemp() {
        return frozenTemp;
    }

    public void setFrozenTemp(int frozenTemp) {
        this.frozenTemp = frozenTemp;
    }


    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
