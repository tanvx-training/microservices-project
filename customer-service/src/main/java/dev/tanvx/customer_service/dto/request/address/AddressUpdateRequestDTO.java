package dev.tanvx.customer_service.dto.request.address;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressUpdateRequestDTO {
  @NotNull(message = "Address cannot be null.")
  private final String address;
  private final String address2;
  @NotNull(message = "District cannot be null.")
  private final String district;
  @NotNull(message = "City cannot be null.")
  private final Integer cityId;
  private final String postalCode;
  @NotNull(message = "Phone cannot be null.")
  private final String phone;
}
