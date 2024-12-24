package dev.tanvx.addressservice.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AddressByIdRequestDTO {
    private final Integer addressId;
}
