package dev.tanvx.customer_service.dto.response.film;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
  private Integer languageId;
  private Integer originalLanguageId;
  private Integer rentalDuration;
  private String rentalRate;
  private Integer length;
  private String replacementCost;
  private String rating;
  private ZonedDateTime lastUpdate;
}
