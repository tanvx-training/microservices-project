package dev.tanvx.customer_service.dto.request.address;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AddressesRequestDTO {
  @Min(value = 0, message = "Page number must be greater than zero.")
  private final Integer page;
  @Min(value = 1, message = "Size number must be greater than one.")
  @Max(value = 100, message = "Size number must be equal or less than 100.")
  private final Integer size;
  private final String sort;
}
