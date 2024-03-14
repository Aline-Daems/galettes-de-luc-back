package be.technobel.bl.impl;

import be.technobel.bl.MaterialService;
import be.technobel.dal.models.entities.Material;
import be.technobel.dal.repositories.MaterialRepository;
import be.technobel.pl.forms.MaterialForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    public MateriaServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public void create(MaterialForm materialForm) {
        if(materialForm == null){
            throw new IllegalArgumentException("Le formulaire doit être rempli");
        }

        Material material = new Material();

        material.setName(materialForm.name());
        material.setBio(materialForm.bio());
        material.setFresh(materialForm.fresh());

        materialRepository.save(material);
    }

    @Override
    public List<Material> getAll() {
        return materialRepository.findAll();
    }
}
