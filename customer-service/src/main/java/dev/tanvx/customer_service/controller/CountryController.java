package dev.tanvx.customer_service.controller;

import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.customer_service.controller.usecase.CountryUseCase;
import dev.tanvx.customer_service.dto.response.country.CountriesResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/countries")
public class CountryController {

  private final CountryUseCase countryUseCase;

  @GetMapping
  public ResponseEntity<ApiResponse<Page<CountriesResponseDTO>>> getCountries(
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "sort", defaultValue = "countryId,asc") String sort) {

    return ResponseEntity.ok(countryUseCase.getCountries(
        CountriesRequestDTO.builder()
            .page(page)
            .size(size)
            .sort(sort)
            .build()
    ));
  }

  @GetMapping("/{countryId}/")
  public ResponseEntity<ApiResponse<CountryByIdResponseDTO>> getCountryById(
      @PathVariable("countryId") Integer countryId) {
    return ResponseEntity.ok(countryUseCase.getCountryById(countryId));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<CountryByIdResponseDTO>> createCountry(
      @RequestBody CountryCreateRequestDTO requestDTO) {
    return ResponseEntity.ok(countryUseCase.createCountry(requestDTO));
  }

  @PutMapping("/{countryId}/")
  public ResponseEntity<ApiResponse<CountryByIdResponseDTO>> updateCountry(
      @PathVariable("countryId") Integer countryId,
      @RequestBody CountryUpdateRequestDTO requestDTO) {
    return ResponseEntity.ok(countryUseCase.updateCountry(countryId, requestDTO
    ))
  }

  @DeleteMapping("/{countryId}/")
  public ResponseEntity<ApiResponse<CountryByIdResponseDTO>> deleteCountry(
      @PathVariable("countryId") Integer countryId) {
    return ResponseEntity.ok(countryUseCase.deleteCountry(countryId));
  }
}
