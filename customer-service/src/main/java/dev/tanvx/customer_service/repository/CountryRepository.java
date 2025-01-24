package dev.tanvx.customer_service.repository;

import dev.tanvx.customer_service.entity.Country;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>,
    JpaSpecificationExecutor<Country> {

  Optional<Country> findByName(String name);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Country> findCountryByCountryId(Integer countryId);
}
