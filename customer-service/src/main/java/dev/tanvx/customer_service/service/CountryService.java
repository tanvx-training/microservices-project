package dev.tanvx.customer_service.service;

import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.customer_service.dto.request.country.CountriesRequestDTO;
import dev.tanvx.customer_service.dto.request.country.CountryByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.country.CountryCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.country.CountryUpdateRequestDTO;
import dev.tanvx.customer_service.dto.response.country.CountriesResponseDTO;
import dev.tanvx.customer_service.dto.response.country.CountryByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.country.CountryCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.country.CountryUpdateResponseDTO;
import org.springframework.data.domain.Page;

public interface CountryService {

    String COUNTRY_NOT_FOUND = "COUNTRY_NOT_FOUND";

    String COUNTRY_ALREADY_EXISTS = "COUNTRY_ALREADY_EXISTS";

    void checkCountryById(Integer id) throws ServiceException;

    Page<CountriesResponseDTO> getCountries(CountriesRequestDTO requestDTO);

    CountryByIdResponseDTO getCountryById(CountryByIdRequestDTO requestDTO) throws ServiceException;

    CountryCreateResponseDTO createCountry(CountryCreateRequestDTO requestDTO)
        throws ServiceException;

    CountryUpdateResponseDTO updateCountry(Integer countryId, CountryUpdateRequestDTO requestDTO)
        throws ServiceException;

    void deleteCountry(Integer countryId) throws ServiceException;
}
