package dev.tanvx.movies_service.dto.request.inventory;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class InventoryRequestDTO {
  @Min(value = 0, message = "Page number must be greater than zero.")
  private final Integer page;
  @Min(value = 1, message = "Size number must be greater than one.")
  @Max(value = 100, message = "Size number must be equal or less than 100.")
  private final Integer size;
  private final String sort;
}
