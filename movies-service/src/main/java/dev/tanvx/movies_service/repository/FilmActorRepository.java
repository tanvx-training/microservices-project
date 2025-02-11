package dev.tanvx.movies_service.repository;

import dev.tanvx.movies_service.entity.Actor;
import dev.tanvx.movies_service.entity.Film;
import dev.tanvx.movies_service.entity.FilmActor;
import dev.tanvx.movies_service.entity.id.FilmActorId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId> {

  List<FilmActor> findAllByIdFilm(Film film);

  void deleteAllByIdActor(Actor actor);

  void deleteAllByIdFilm(Film film);
}
