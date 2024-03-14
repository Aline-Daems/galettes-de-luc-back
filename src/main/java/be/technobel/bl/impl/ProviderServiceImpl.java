package be.technobel.bl.impl;

import be.technobel.bl.ProviderService;
import be.technobel.dal.models.entities.Provider;
import be.technobel.dal.repositories.ProviderRepository;
import be.technobel.pl.forms.ProviderForm;
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
        return providerRepository.findAll();
    }

    @Override
    public void delete(Long id) {

        providerRepository.deleteById(id);
    }
}
