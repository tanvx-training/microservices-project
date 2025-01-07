package dev.tanvx.movies_service.controller;

import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.movies_service.controller.usecase.AddressUseCase;
import dev.tanvx.movies_service.dto.request.AddressByCityRequestDTO;
import dev.tanvx.movies_service.dto.request.AddressByCountryRequestDTO;
import dev.tanvx.movies_service.dto.response.AddressByCityResponseDTO;
import dev.tanvx.movies_service.dto.response.AddressByCountryResponseDTO;
import dev.tanvx.movies_service.dto.response.AddressByIdResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private final AddressUseCase addressUseCase;

    @GetMapping("/{addressId}/")
    public ResponseEntity<ApiResponse<AddressByIdResponseDTO>> getAddressById(@PathVariable("addressId") Integer addressId) {
        return ResponseEntity.ok(addressUseCase.getAddressById(addressId));
    }

    @GetMapping("/{countryId}/countries")
    public ResponseEntity<ApiResponse<Page<AddressByCountryResponseDTO>>> getAddressByCountry(
            @PathVariable("countryId") Integer countryId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
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

    @GetMapping("/{cityId}/cities")
    public ResponseEntity<ApiResponse<Page<AddressByCityResponseDTO>>> getAddressByCity(
            @PathVariable("cityId") Integer cityId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "sort", defaultValue = "addressId,asc") String sort) {

        return ResponseEntity.ok(addressUseCase.getAddressByCity(
                AddressByCityRequestDTO.builder()
                        .cityId(cityId)
                        .page(page)
                        .size(size)
                        .sort(sort)
                        .build()
        ));
    }
}
