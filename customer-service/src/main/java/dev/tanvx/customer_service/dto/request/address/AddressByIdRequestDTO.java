package dev.tanvx.customer_service.dto.request.address;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AddressByIdRequestDTO {
    private final Integer addressId;
}
