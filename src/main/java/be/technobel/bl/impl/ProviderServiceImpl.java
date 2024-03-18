package be.technobel.bl.impl;

import be.technobel.bl.ProviderService;
import be.technobel.dal.models.entities.Material;
import be.technobel.dal.models.entities.Provider;
import be.technobel.dal.repositories.ProviderRepository;
import be.technobel.pl.forms.ProviderForm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService {

    private  final ProviderRepository providerRepository;

    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public void create(ProviderForm providerForm) {
        if(providerForm == null){
            throw new IllegalArgumentException("Le formulaire doit être rempli");
        }

        Provider provider = new Provider();

        provider.setName(providerForm.name());
        provider.setDescription(providerForm.description());

        providerRepository.save(provider);

    }

    @Override
    public List<Provider> getAll() {
        return providerRepository.findAllByActiveTrue();
    }

    @Override
    public Provider getOne(Long id) {
       return providerRepository.findById(id).orElseThrow(EntityNotFoundException::new);

    }
    @Override
    public void delete(Long id) {
        Provider provider = getOne(id);

        provider.setActive(false);
        providerRepository.save(provider);
    }

    @Override
    public void update(ProviderForm providerForm, Long id) {
        if(providerForm == null){
            throw new IllegalArgumentException("Le formulaire doit être rempli");
        }

        Provider provider = getOne(id);

        provider.setName(providerForm.name());
        provider.setDescription(providerForm.description());

        providerRepository.save(provider);
    }


}
