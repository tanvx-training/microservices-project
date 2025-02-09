package dev.tanvx.customer_service.service;

import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.customer_service.dto.request.city.CitiesRequestDTO;
import dev.tanvx.customer_service.dto.request.city.CitiesByCountryRequestDTO;
import dev.tanvx.customer_service.dto.request.city.CityByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.city.CityCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.city.CityUpdateRequestDTO;
import dev.tanvx.customer_service.dto.response.city.CitiesResponseDTO;
import dev.tanvx.customer_service.dto.response.city.CityByCountryResponseDTO;
import dev.tanvx.customer_service.dto.response.city.CityByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.city.CityCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.city.CityDeleteResponseDTO;
import dev.tanvx.customer_service.dto.response.city.CityUpdateResponseDTO;
import org.springframework.data.domain.Page;

public interface CityService {

    String CITY_NOT_FOUND = "CITY_NOT_FOUND";

    void checkCityById(Integer id) throws ServiceException;

    Page<CitiesResponseDTO> getCities(CitiesRequestDTO requestDTO);

    CityByIdResponseDTO getCityById(CityByIdRequestDTO requestDTO) throws ServiceException;

    Page<CityByCountryResponseDTO> getCitiesByCountry(CitiesByCountryRequestDTO requestDTO);

    CityCreateResponseDTO createCity(CityCreateRequestDTO requestDTO) throws ServiceException;

    CityUpdateResponseDTO updateCity(Integer cityId, CityUpdateRequestDTO requestDTO)
        throws ServiceException;

    CityDeleteResponseDTO deleteCity(Integer cityId) throws ServiceException;
}
