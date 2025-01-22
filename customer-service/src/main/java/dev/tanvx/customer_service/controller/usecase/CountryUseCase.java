package dev.tanvx.customer_service.controller.usecase;

import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.customer_service.dto.request.country.CountriesRequestDTO;
import dev.tanvx.customer_service.dto.request.country.CountryCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.country.CountryUpdateRequestDTO;
import dev.tanvx.customer_service.dto.response.country.CountriesResponseDTO;
import dev.tanvx.customer_service.dto.response.country.CountryByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.country.CountryCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.country.CountryUpdateResponseDTO;
import org.springframework.data.domain.Page;

public class CountryUseCase {

  public ApiResponse<Page<CountriesResponseDTO>> getCountries(CountriesRequestDTO build) {
    return null;
  }

  public ApiResponse<CountryByIdResponseDTO> getCountryById(Integer countryId) {
    return null;
  }

  public ApiResponse<CountryCreateResponseDTO> createCountry(CountryCreateRequestDTO requestDTO) {
    return null;
  }

  public ApiResponse<CountryUpdateResponseDTO> updateCountry(Integer countryId, CountryUpdateRequestDTO requestDTO) {
    return null;
  }

  public ApiResponse<Void> deleteCountry(Integer countryId) {
    return null;
  }
}
