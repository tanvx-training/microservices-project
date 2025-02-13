package dev.tanvx.customer_service.client.store;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StoreByIdResponseDTO {
  private Integer storeId;
  private StaffDTO manager;
  private AddressDTO address;

  @Getter
  @Builder
  @ToString
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class StaffDTO {
    private Integer staffId;
    private String firstName;
    private String lastName;
    private AddressDTO address;
    private String email;
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
