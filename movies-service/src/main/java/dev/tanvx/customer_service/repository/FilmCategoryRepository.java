package dev.tanvx.customer_service.repository;

import dev.tanvx.customer_service.entity.Film;
import dev.tanvx.customer_service.entity.FilmCategory;
import dev.tanvx.customer_service.entity.id.FilmCategoryId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmCategoryRepository extends JpaRepository<FilmCategory, FilmCategoryId> {

  List<FilmCategory> findAllByIdFilm(Film film);

  void deleteAllByIdFilm(Film film);
}
