package dev.tanvx.addressservice.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressByCountryRequestDTO {
    private final Integer countryId;
    private final Integer page;
    private final Integer size;
    private final String sort;
}
