package dev.tanvx.customer_service.controller;

import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.customer_service.controller.usecase.AddressUseCase;
import dev.tanvx.customer_service.dto.request.address.AddressByCityRequestDTO;
import dev.tanvx.customer_service.dto.request.address.AddressByCountryRequestDTO;
import dev.tanvx.customer_service.dto.request.address.AddressCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.address.AddressUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.address.AddressesRequestDTO;
import dev.tanvx.customer_service.dto.response.address.AddressByCityResponseDTO;
import dev.tanvx.customer_service.dto.response.address.AddressByCountryResponseDTO;
import dev.tanvx.customer_service.dto.response.address.AddressByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.address.AddressCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.address.AddressUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.address.AddressesResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/addresses")
public class AddressController {

  private final AddressUseCase addressUseCase;

  @GetMapping
  public ResponseEntity<ApiResponse<Page<AddressesResponseDTO>>> getAddresses(
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "sort", defaultValue = "addressId,asc") String sort) {

    return ResponseEntity.ok(addressUseCase.getAddresses(
        AddressesRequestDTO.builder()
            .page(page)
            .size(size)
            .sort(sort)
            .build()
    ));
  }

  @GetMapping("/{addressId}/")
  public ResponseEntity<ApiResponse<AddressByIdResponseDTO>> getAddressById(
      @PathVariable("addressId") Integer addressId) {
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

  @PostMapping
  public ResponseEntity<ApiResponse<AddressCreateResponseDTO>> createAddress(
      @RequestBody AddressCreateRequestDTO requestDTO) {

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(addressUseCase.createAddress(requestDTO));
  }

  @PutMapping("/{addressId}")
  public ResponseEntity<ApiResponse<AddressUpdateResponseDTO>> updateAddress(
      @PathVariable("addressId") Integer addressId,
      @RequestBody AddressUpdateRequestDTO requestDTO) {
    return ResponseEntity.ok(addressUseCase.updateAddress(addressId, requestDTO));
  }

  @DeleteMapping("/{addressId}")
  public ResponseEntity<ApiResponse<Void>> deleteAddress(
      @PathVariable("addressId") Integer addressId) {
    return ResponseEntity.ok(addressUseCase.deleteAddress(addressId));
  }
}
