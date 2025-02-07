package dev.tanvx.customer_service.dto.request.film;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FilmCreateRequestDTO {

  @NotBlank(message = "Title cannot be blank")
  @Size(max = 255, message = "Title must not exceed 255 characters")
  private String title;
  private String description;
  @NotNull(message = "Release year is required")
  @Min(value = 1900, message = "Release year must be at least 1900")
  @Max(value = 2100, message = "Release year must not exceed 2100")
  private Integer releaseYear;
  @NotNull(message = "Language ID is required")
  private Integer languageId;
  private Integer originalLanguageId;
  @NotNull(message = "Rental duration is required")
  @Min(value = 1, message = "Rental duration must be at least 1 day")
  @Max(value = 30, message = "Rental duration must not exceed 30 days")
  private Integer rentalDuration;
  @NotNull(message = "Rental rate is required")
  @DecimalMin(value = "0.0", inclusive = false, message = "Rental rate must be greater than 0")
  private BigDecimal rentalRate;
  @NotNull(message = "Length is required")
  @Min(value = 1, message = "Length must be at least 1 minute")
  private Integer length;
  @NotNull(message = "Replacement cost is required")
  @DecimalMin(value = "0.0", inclusive = false, message = "Replacement cost must be greater than 0")
  private BigDecimal replacementCost;
  @Pattern(regexp = "G|PG|PG-13|R|NC-17", message = "Rating must be one of G, PG, PG-13, R, or NC-17")
  private String rating;
  @NotEmpty(message = "At least one actor is required")
  private List<@NotNull(message = "Actor ID cannot be null") Integer> actorIds;
  @NotEmpty(message = "At least one category is required")
  private List<@NotNull(message = "Category ID cannot be null") Integer> categoryIds;
}
