package be.technobel.pl.dtos;

import be.technobel.dal.models.entities.Provider;

public record ProviderDTO (
        Long id,
        String name,
        String description
){

    public static ProviderDTO fromEntity(Provider provider){

        return  new ProviderDTO(provider.getId(), provider.getName(), provider.getDescription());
    }
}
