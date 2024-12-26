package dev.tanvx.catalogservice.entity.id;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmActorId {
    @Column(name = "actor_id")
    private Integer actorId;

    @Column(name = "film_id")
    private Integer filmId;
}
