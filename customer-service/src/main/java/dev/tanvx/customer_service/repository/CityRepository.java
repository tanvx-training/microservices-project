package dev.tanvx.customer_service.repository;

import dev.tanvx.customer_service.entity.City;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>,
    JpaSpecificationExecutor<City> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<City> findCityByCityId(Integer cityId);
}
