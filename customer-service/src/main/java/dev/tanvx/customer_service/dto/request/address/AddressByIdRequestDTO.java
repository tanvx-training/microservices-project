package dev.tanvx.customer_service.dto.request.address;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressByIdRequestDTO {
    private final Integer addressId;
}
