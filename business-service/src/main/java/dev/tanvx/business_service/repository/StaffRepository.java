package dev.tanvx.business_service.repository;

import dev.tanvx.business_service.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer>,
    JpaSpecificationExecutor<Staff> {

}
