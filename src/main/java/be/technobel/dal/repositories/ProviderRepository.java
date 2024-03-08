package be.technobel.dal.repositories;

import be.technobel.dal.models.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
