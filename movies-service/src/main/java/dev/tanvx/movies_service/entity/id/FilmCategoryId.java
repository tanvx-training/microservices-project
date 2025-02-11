package dev.tanvx.movies_service.entity.id;

import dev.tanvx.movies_service.entity.Category;
import dev.tanvx.movies_service.entity.Film;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class FilmCategoryId implements Serializable {

  @ManyToOne
  @JoinColumn(name = "film_id")
  private Film film;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
}
