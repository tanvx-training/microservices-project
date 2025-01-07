package dev.tanvx.movies_service.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressByCityResponseDTO {
    private Integer addressId;
    private String address;
    private String address2;
    private String district;
    private String postalCode;
    private String phone;
    private CityDTO city;

    @Getter
    @Builder
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class CityDTO {
        private Integer cityId;
        private String cityName;
    }
}
