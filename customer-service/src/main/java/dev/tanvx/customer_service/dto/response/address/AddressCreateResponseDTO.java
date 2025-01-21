package dev.tanvx.customer_service.dto.response.address;

import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressCreateResponseDTO {

  private final Integer addressId;

  private final OffsetDateTime lastUpdate;
}
