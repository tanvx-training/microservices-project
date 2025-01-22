package dev.tanvx.customer_service.controller;

import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.customer_service.controller.usecase.CityUseCase;
import dev.tanvx.customer_service.dto.request.city.CitiesRequestDTO;
import dev.tanvx.customer_service.dto.request.city.CitiesByCountryRequestDTO;
import dev.tanvx.customer_service.dto.request.city.CityCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.city.CityUpdateRequestDTO;
import dev.tanvx.customer_service.dto.response.city.CitiesResponseDTO;
import dev.tanvx.customer_service.dto.response.city.CityByCountryResponseDTO;
import dev.tanvx.customer_service.dto.response.city.CityByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.city.CityCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.city.CityUpdateResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
@RequestMapping("/api/v1/cities")
public class CityController {

  private final CityUseCase cityUseCase;

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

    return ResponseEntity.ok(cityUseCase.getCitiesByCountry(
        CitiesByCountryRequestDTO.builder()
            .countryId(countryId)
            .page(page)
            .size(size)
            .sort(sort)
            .build()
    ));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<CityCreateResponseDTO>> createCity(
      @RequestBody CityCreateRequestDTO requestDTO) {
    return ResponseEntity.ok(cityUseCase.createCity(requestDTO));
  }

  @PutMapping("/{cityId}/")
  public ResponseEntity<ApiResponse<CityUpdateResponseDTO>> updateCity(
      @PathVariable("cityId") Integer cityId,
      @RequestBody CityUpdateRequestDTO requestDTO) {
    return ResponseEntity.ok(cityUseCase.updateCity(cityId, requestDTO));
  }

  @DeleteMapping("/{cityId}/")
  public ResponseEntity<ApiResponse<Void>> deleteCity(
      @PathVariable("cityId") Integer cityId) {
    return ResponseEntity.ok(cityUseCase.deleteCity(cityId));
  }
}
