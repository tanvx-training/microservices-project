package dev.tanvx.movies_service.repository;

import dev.tanvx.movies_service.entity.Actor;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer>,
    JpaSpecificationExecutor<Actor> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Actor> findActorByActorId(Integer actorId);
}
