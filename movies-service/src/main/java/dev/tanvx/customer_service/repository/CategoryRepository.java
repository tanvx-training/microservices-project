package dev.tanvx.customer_service.repository;

import dev.tanvx.customer_service.entity.Category;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>,
    JpaSpecificationExecutor<Category> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Category> findCategoryByCategoryId(Integer categoryId);

  Optional<Category> findCategoryByName(String name);
}
