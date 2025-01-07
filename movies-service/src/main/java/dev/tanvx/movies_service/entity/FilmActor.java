package dev.tanvx.movies_service.entity;

import dev.tanvx.movies_service.entity.id.FilmActorId;
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
@Table(name = "film_actor")
public class FilmActor {

  @EmbeddedId
  private FilmActorId id;

  @Column(name = "last_update", nullable = false)
  private ZonedDateTime lastUpdate;
}
