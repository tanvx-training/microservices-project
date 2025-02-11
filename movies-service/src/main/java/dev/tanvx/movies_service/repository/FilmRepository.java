package dev.tanvx.movies_service.repository;

import dev.tanvx.movies_service.entity.Film;
import dev.tanvx.movies_service.entity.Language;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer>,
    JpaSpecificationExecutor<Film> {

  Optional<Film> findByTitle(String title);

  List<Film> findAllByLanguageOrOriginalLanguage(Language language, Language originalLanguage);
}
