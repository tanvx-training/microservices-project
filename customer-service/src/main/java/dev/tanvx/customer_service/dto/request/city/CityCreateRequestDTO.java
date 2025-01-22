package dev.tanvx.customer_service.dto.request.city;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CityCreateRequestDTO {

  @NotNull(message = "City name is required")
  private String name;

  @NotNull(message = "Country ID is required")
  private Integer countryId;
}
