package dev.tanvx.customer_service.repository;

import dev.tanvx.customer_service.entity.FilmActor;
import dev.tanvx.customer_service.entity.id.FilmActorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId> {

}
