package dev.tanvx.customer_service.repository;

import dev.tanvx.customer_service.entity.Language;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer>,
    JpaSpecificationExecutor<Language> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Language> findLanguageByLanguageId(Integer languageId);

  Optional<Language> findLanguageByName(String name);
}
