package dev.tanvx.customer_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cities")
public class CityController {

  @GetMapping
  public ResponseEntity<ApiResponse<Page<CitiesResponseDTO>>> getCities(
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "sort", defaultValue = "cityId,asc") String sort) {

    return ResponseEntity.ok(cityUseCase.getCities(
        CitiesRequestDTO.builder()
            .page(page)
            .size(size)
            .sort(sort)
            .build()
    ));
  }

  @GetMapping("/{cityId}/")
  public ResponseEntity<ApiResponse<CityByIdResponseDTO>> getCityById(
      @PathVariable("cityId") Integer cityId) {
    return ResponseEntity.ok(cityUseCase.getCityById(cityId));
  }

  @GetMapping("/{countryId}/countries")
  public ResponseEntity<ApiResponse<Page<CityByCountryResponseDTO>>> getCityByCountry(
      @PathVariable("countryId") Integer countryId,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "sort", defaultValue = "cityId,asc") String sort) {
    return ResponseEntity.ok(cityUseCase.getCityByCountry(
        CityByCountryRequestDTO.builder()
            .countryId(countryId)
            .page(page)
            .size(size)
            .sort(sort)
            .build()
    ));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<CityByIdResponseDTO>> createCity(
      @RequestBody CityCreateRequestDTO requestDTO) {
    return ResponseEntity.ok(cityUseCase.createCity(requestDTO));
  }

  @PutMapping("/{cityId}/")
  public ResponseEntity<ApiResponse<CityByIdResponseDTO>> updateCity(
      @PathVariable("cityId") Integer cityId,
      @RequestBody CityUpdateRequestDTO requestDTO) {
    return ResponseEntity.ok(cityUseCase.updateCity(cityId, requestDTO));
  }

  @DeleteMapping
  public ResponseEntity<ApiResponse<CityByIdResponseDTO>> deleteCity(
      @PathVariable("cityId") Integer cityId) {
    return ResponseEntity.ok(cityUseCase.deleteCity(cityId));
  }
}
