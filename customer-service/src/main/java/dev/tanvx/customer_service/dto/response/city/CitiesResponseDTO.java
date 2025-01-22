package dev.tanvx.customer_service.dto.response.city;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CitiesResponseDTO {

  private Integer cityId;
  private String name;
  private CountryDTO country;
  @Getter
  @Builder
  @ToString
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class CountryDTO {
    private Integer countryId;
    private String countryName;
  }
}
