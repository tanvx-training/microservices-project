package dev.tanvx.movies_service.dto.response.inventory;

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
public class InventoriesResponseDTO {

  private Integer inventoryId;
  private FilmDTO film;
  private StoreDTO store;

  @Getter
  @Builder
  @ToString
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class FilmDTO {
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

  @Getter
  @Builder
  @ToString
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class StoreDTO {

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
}
