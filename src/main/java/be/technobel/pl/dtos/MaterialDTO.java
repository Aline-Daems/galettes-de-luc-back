package be.technobel.pl.dtos;

import be.technobel.dal.models.entities.Material;

public record MaterialDTO(

        Long id,
        String name,
        boolean bio,
        boolean fresh
) {

    public static MaterialDTO fromEntity(Material material){

        return  new MaterialDTO(material.getId(), material.getName(), material.isBio(), material.isFresh());
    }
}
