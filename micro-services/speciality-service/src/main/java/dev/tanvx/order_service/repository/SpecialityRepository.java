package dev.tanvx.order_service.repository;

import dev.tanvx.order_service.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
}
