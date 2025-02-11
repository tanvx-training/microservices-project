package dev.tanvx.movies_service.dto.response.film;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FilmsResponseDTO {
  private Integer filmId;
  private String title;
  private String description;
  private Integer releaseYear;
  private LanguageDTO language;
  private LanguageDTO originalLanguage;
  private Integer rentalDuration;
  private BigDecimal rentalRate;
  private Integer length;
  private BigDecimal replacementCost;
  private String rating;
  private ZonedDateTime lastUpdate;

  @Getter
  @Builder
  @ToString
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class LanguageDTO {
    private Integer languageId;
    private String name;
  }
}
