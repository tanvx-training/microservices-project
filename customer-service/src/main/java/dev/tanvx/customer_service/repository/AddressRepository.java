package dev.tanvx.customer_service.repository;

import dev.tanvx.customer_service.entity.Address;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>,
    JpaSpecificationExecutor<Address> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Address> findAddressByAddressId(Integer addressId);
}
