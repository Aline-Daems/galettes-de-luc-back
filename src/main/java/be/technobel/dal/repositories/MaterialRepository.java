package be.technobel.dal.repositories;

import be.technobel.dal.models.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
