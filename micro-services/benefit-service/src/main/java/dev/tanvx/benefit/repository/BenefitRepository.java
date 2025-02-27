package dev.tanvx.benefit.repository;

import dev.tanvx.benefit.entity.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Benefit Repository
 *
 * Repository interface for handling database operations related to Benefits.
 * Extends JpaRepository to inherit basic CRUD operations and custom query methods.
 */
@Repository
public interface BenefitRepository extends JpaRepository<Benefit, Long> {
}
