package dev.tanvx.movies_service.dto.response.film;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FilmUpdateResponseDTO {
  private Integer filmId;
  private String title;
  private String description;
  private Integer releaseYear;
  private FilmCreateResponseDTO.LanguageDTO language;
  private FilmCreateResponseDTO.LanguageDTO originalLanguage;
  private Integer rentalDuration;
  private BigDecimal rentalRate;
  private Integer length;
  private BigDecimal replacementCost;
  private String rating;
  private ZonedDateTime lastUpdate;
  private List<FilmCreateResponseDTO.ActorDTO> actors;
  private List<FilmCreateResponseDTO.CategoryDTO> categories;

  @Getter
  @Builder
  @ToString
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class LanguageDTO {
    private Integer languageId;
    private String name;
  }

  @Getter
  @Builder
  @ToString
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class ActorDTO {
    private Integer actorId;
    private String firstName;
    private String lastName;
  }

  @Getter
  @Builder
  @ToString
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class CategoryDTO {
    private Integer categoryId;
    private String name;
  }
}
