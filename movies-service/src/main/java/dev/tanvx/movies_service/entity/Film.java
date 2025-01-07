package dev.tanvx.movies_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
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
@Table(name = "film")
public class Film {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "film_id")
  private Integer filmId;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "release_year")
  private Integer releaseYear;

  @ManyToOne
  @JoinColumn(name = "language_id", nullable = false)
  private Language language;

  @ManyToOne
  @JoinColumn(name = "original_language_id")
  private Language originalLanguage;

  @Column(name = "rental_duration", nullable = false)
  private Integer rentalDuration;

  @Column(name = "rental_rate",nullable = false)
  private BigDecimal rentalRate;

  @Column(name = "length")
  private Integer length;

  @Column(name = "replacement_cost",nullable = false)
  private BigDecimal replacementCost;

  @Column(name = "rating", nullable = false, length = 10)
  private String rating;

  @Column(name = "last_update", nullable = false)
  private ZonedDateTime lastUpdate;
}
