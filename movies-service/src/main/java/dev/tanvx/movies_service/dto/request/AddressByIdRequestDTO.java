package dev.tanvx.movies_service.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AddressByIdRequestDTO {
    private final Integer addressId;
}
