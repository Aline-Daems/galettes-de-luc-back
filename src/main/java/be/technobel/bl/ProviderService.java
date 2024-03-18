package be.technobel.bl;

import be.technobel.dal.models.entities.Material;
import be.technobel.dal.models.entities.Provider;
import be.technobel.pl.forms.ProviderForm;

import java.util.List;

public interface ProviderService {

    void create(ProviderForm providerForm);

    List<Provider> getAll();

    void delete(Long id);

   Provider getOne(Long id);

   void update(ProviderForm providerForm, Long id);
}
