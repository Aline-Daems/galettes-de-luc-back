package be.technobel.dal.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private boolean bio =false;
    @NotNull
    private boolean fresh =false;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBio() {
        return bio;
    }

    public void setBio(boolean bio) {
        this.bio = bio;
    }

    public boolean isFresh() {
        return fresh;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }
}
