package dev.tanvx.customer_service.entity.id;

import dev.tanvx.customer_service.entity.Actor;
import dev.tanvx.customer_service.entity.Film;
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
public class FilmActorId implements Serializable {

  @ManyToOne
  @JoinColumn(name = "actor_id")
  private Actor actor;

  @ManyToOne
  @JoinColumn(name = "film_id")
  private Film film;
}
