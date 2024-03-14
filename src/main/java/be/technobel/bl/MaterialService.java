package be.technobel.bl;

import be.technobel.dal.models.entities.Material;
import be.technobel.pl.forms.MaterialForm;

import java.util.List;

public interface MaterialService {

    void create(MaterialForm materialForm);

    List<Material> getAll();
}
