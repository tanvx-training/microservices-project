package dev.tanvx.customer_service.controller.usecase;

import dev.tanvx.common_library.exception.BusinessException;
import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.common_library.model.MessageProperties;
import dev.tanvx.common_library.model.ResponseConstants;
import dev.tanvx.common_library.util.MessageUtils;
import dev.tanvx.common_library.util.ValidationUtils;
import dev.tanvx.customer_service.dto.request.city.CitiesRequestDTO;
import dev.tanvx.customer_service.dto.request.city.CitiesByCountryRequestDTO;
import dev.tanvx.customer_service.dto.request.city.CityByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.city.CityCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.city.CityUpdateRequestDTO;
import dev.tanvx.customer_service.dto.response.city.CitiesResponseDTO;
import dev.tanvx.customer_service.dto.response.city.CityByCountryResponseDTO;
import dev.tanvx.customer_service.dto.response.city.CityByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.city.CityCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.city.CityUpdateResponseDTO;
import dev.tanvx.customer_service.entity.City;
import dev.tanvx.customer_service.service.CityService;
import dev.tanvx.customer_service.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CityUseCase {

  private final CityService cityService;

  private final CountryService countryService;

  private final ValidationUtils validationUtils;

  private final MessageUtils messageUtils;

  @Transactional(readOnly = true)
  public ApiResponse<Page<CitiesResponseDTO>> getCities(CitiesRequestDTO requestDTO) {
    try {
      // Validate page and size parameter
      validationUtils.validateRequest(requestDTO);

      // Validate sort parameter
      validationUtils.validateSortParam(requestDTO.getSort(), City.class);

      Page<CitiesResponseDTO> citiesResponseDTOPage = cityService.getCities(requestDTO);

      return ApiResponse.<Page<CitiesResponseDTO>>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(citiesResponseDTOPage)
          .build();
    } catch (Exception e) {
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional(readOnly = true)
  public ApiResponse<CityByIdResponseDTO> getCityById(Integer cityId) {
    try {
      CityByIdRequestDTO requestDTO = CityByIdRequestDTO.builder()
          .cityId(cityId)
          .build();
      CityByIdResponseDTO cityByIdResponseDTO = cityService.getCityById(requestDTO);
      return ApiResponse.<CityByIdResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(cityByIdResponseDTO)
          .build();

    } catch (ServiceException e) {
      if (CityService.CITY_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }


  @Transactional(readOnly = true)
  public ApiResponse<Page<CityByCountryResponseDTO>> getCitiesByCountry(
      CitiesByCountryRequestDTO requestDTO) {
    try {
      // Validate page and size parameter
      validationUtils.validateRequest(requestDTO);

      // Validate sort parameter
      validationUtils.validateSortParam(requestDTO.getSort(), City.class);

      countryService.checkCountryById(requestDTO.getCountryId());

      Page<CityByCountryResponseDTO> citiesByCountryResponseDTOPage = cityService.getCitiesByCountry(
          requestDTO);

      return ApiResponse.<Page<CityByCountryResponseDTO>>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(citiesByCountryResponseDTOPage)
          .build();
    } catch (ServiceException e) {
      if (CountryService.COUNTRY_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<CityCreateResponseDTO> createCity(CityCreateRequestDTO requestDTO) {
    try {
      validationUtils.validateRequest(requestDTO);

      countryService.checkCountryById(requestDTO.getCountryId());

      CityCreateResponseDTO cityCreateResponseDTO = cityService.createCity(requestDTO);

      return ApiResponse.<CityCreateResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(cityCreateResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (CountryService.COUNTRY_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<CityUpdateResponseDTO> updateCity(Integer cityId,
      CityUpdateRequestDTO requestDTO) {
    try {
      validationUtils.validateRequest(requestDTO);

      cityService.checkCityById(cityId);

      CityUpdateResponseDTO cityUpdateResponseDTO = cityService.updateCity(cityId, requestDTO);

      return ApiResponse.<CityUpdateResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(cityUpdateResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (CityService.CITY_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<Void> deleteCity(Integer cityId) {
    try {
      cityService.deleteCity(cityId);
      return ApiResponse.<Void>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .build();
    } catch (ServiceException e) {
      if (CityService.CITY_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }
}
