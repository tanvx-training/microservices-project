package dev.tanvx.customer_service.entity;

import dev.tanvx.customer_service.entity.id.FilmCategoryId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "film_category")
public class FilmCategory {

  @EmbeddedId
  private FilmCategoryId id;

  @Column(name = "last_update", nullable = false)
  private ZonedDateTime lastUpdate;
}
