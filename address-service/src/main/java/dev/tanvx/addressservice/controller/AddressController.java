package dev.tanvx.addressservice.controller;

import dev.tanvx.addressservice.common.ApiResponse;
import dev.tanvx.addressservice.controller.usecase.AddressUseCase;
import dev.tanvx.addressservice.dto.request.AddressByCountryRequestDTO;
import dev.tanvx.addressservice.dto.response.AddressByCountryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private final AddressUseCase addressUseCase;

    @GetMapping("/{countryId}/countries")
    public ResponseEntity<ApiResponse<Page<AddressByCountryResponseDTO>>> getAddressByCountry(
            @PathVariable("countryId") Integer countryId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "addressId,asc") String sort) {

        return ResponseEntity.ok(addressUseCase.getAddressByCountry(
                AddressByCountryRequestDTO.builder()
                        .countryId(countryId)
                        .page(page)
                        .size(size)
                        .sort(sort)
                        .build()
        ));
    }
}
