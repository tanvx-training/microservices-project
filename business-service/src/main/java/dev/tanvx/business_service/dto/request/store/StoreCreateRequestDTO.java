package dev.tanvx.business_service.dto.request.store;

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
public class StoreCreateRequestDTO {

  private Integer managerStaffId;
  @NotNull(message = "AddressId must not be null.")
  private Integer addressId;
}
