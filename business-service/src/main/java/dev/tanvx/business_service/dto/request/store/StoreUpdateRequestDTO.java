package dev.tanvx.business_service.dto.request.store;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StoreUpdateRequestDTO {
  @NotBlank(message = "Name is required")
  @Size(max = 256, message = "Name must be less than or equal to 256 characters")
  private String name;
  private Integer managerStaffId;
  @NotNull(message = "AddressId must not be null.")
  private Integer addressId;
}
