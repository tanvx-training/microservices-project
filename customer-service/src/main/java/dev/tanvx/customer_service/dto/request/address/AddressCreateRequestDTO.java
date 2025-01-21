package dev.tanvx.customer_service.dto.request.address;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressCreateRequestDTO {

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
