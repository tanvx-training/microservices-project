package dev.tanvx.business_service.dto.response.staff;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StaffByIdResponseDTO {

  private Integer staffId;
  private String firstName;
  private String lastName;
  private String email;
  private Boolean isActive;
  private StoreDTO store;
  private AddressDTO address;

  @Getter
  @Builder
  @ToString
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class StoreDTO {
    private Integer storeId;
    private AddressDTO address;
  }

  @Getter
  @Builder
  @ToString
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class AddressDTO {
    private Integer addressId;
    private String address;
    private String address2;
    private String district;
    private String postalCode;
    private String phone;
    private CountryDTO country;
    private CityDTO city;
  }

  @Getter
  @Builder
  @ToString
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class CityDTO {
    private Integer cityId;
    private String cityName;
  }

  @Getter
  @Builder
  @ToString
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class CountryDTO {
    private Integer countryId;
    private String countryName;
  }
}
