package dev.tanvx.customer_service.repository;

import dev.tanvx.customer_service.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>,
    JpaSpecificationExecutor<Address> {
}
