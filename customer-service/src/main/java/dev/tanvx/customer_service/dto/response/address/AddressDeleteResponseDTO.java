package dev.tanvx.customer_service.dto.response.address;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressDeleteResponseDTO {
  private final Integer addressId;
  private final boolean deleteFlg;
}
