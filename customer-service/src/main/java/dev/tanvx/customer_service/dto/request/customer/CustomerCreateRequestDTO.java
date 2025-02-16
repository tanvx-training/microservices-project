package dev.tanvx.customer_service.dto.request.customer;

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
public class CustomerCreateRequestDTO {
  @NotNull(message = "Store id is required")
  private Integer storeId;
  @NotNull(message = "First name is required")
  private String firstName;
  @NotNull(message = "Last name is required")
  private String lastName;
  @NotNull(message = "Email is required")
  private String email;
  @NotNull(message = "Address id is required")
  private Integer addressId;
}
