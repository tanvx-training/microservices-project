package dev.tanvx.catalogservice.entity;

import dev.tanvx.catalogservice.entity.id.FilmCategoryId;
import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "film_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmCategory {
    @EmbeddedId
    private FilmCategoryId id;

    @ManyToOne
    @MapsId("filmId")
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "last_update", nullable = false)
    private OffsetDateTime lastUpdate;
}
