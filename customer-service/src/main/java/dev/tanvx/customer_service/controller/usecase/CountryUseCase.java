package dev.tanvx.customer_service.controller.usecase;

import dev.tanvx.common_library.exception.BusinessException;
import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.common_library.model.MessageProperties;
import dev.tanvx.common_library.model.ResponseConstants;
import dev.tanvx.common_library.util.MessageUtils;
import dev.tanvx.common_library.util.ValidationUtils;
import dev.tanvx.customer_service.dto.request.country.CountriesRequestDTO;
import dev.tanvx.customer_service.dto.request.country.CountryByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.country.CountryCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.country.CountryUpdateRequestDTO;
import dev.tanvx.customer_service.dto.response.country.CountriesResponseDTO;
import dev.tanvx.customer_service.dto.response.country.CountryByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.country.CountryCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.country.CountryUpdateResponseDTO;
import dev.tanvx.customer_service.entity.Country;
import dev.tanvx.customer_service.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CountryUseCase {

  private final CountryService countryService;

  private final ValidationUtils validationUtils;

  private final MessageUtils messageUtils;

  @Transactional(readOnly = true)
  public ApiResponse<Page<CountriesResponseDTO>> getCountries(CountriesRequestDTO requestDTO) {
    try {
      // Validate page and size parameter
      validationUtils.validateRequest(requestDTO);

      // Validate sort parameter
      validationUtils.validateSortParam(requestDTO.getSort(), Country.class);

      Page<CountriesResponseDTO> countriesResponseDTOPage = countryService.getCountries(requestDTO);
      return ApiResponse.<Page<CountriesResponseDTO>>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(countriesResponseDTOPage)
          .build();
    } catch (Exception e) {
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional(readOnly = true)
  public ApiResponse<CountryByIdResponseDTO> getCountryById(Integer countryId) {
    try {
      CountryByIdRequestDTO requestDTO = CountryByIdRequestDTO.builder()
          .countryId(countryId)
          .build();
      CountryByIdResponseDTO countryByIdResponseDTO = countryService.getCountryById(requestDTO);
      return ApiResponse.<CountryByIdResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(countryByIdResponseDTO)
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
  public ApiResponse<CountryCreateResponseDTO> createCountry(CountryCreateRequestDTO requestDTO) {
    try {
      // Validate request
      validationUtils.validateRequest(requestDTO);

      CountryCreateResponseDTO countryCreateResponseDTO = countryService.createCountry(requestDTO);

      return ApiResponse.<CountryCreateResponseDTO>builder()
         .status(ResponseConstants.SUCCESS_STATUS)
         .message(ResponseConstants.SUCCESS_MESSAGE)
         .data(countryCreateResponseDTO)
         .build();
    } catch (Exception e) {
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<CountryUpdateResponseDTO> updateCountry(Integer countryId, CountryUpdateRequestDTO requestDTO) {
    try {
      // Validate request
      validationUtils.validateRequest(requestDTO);

      CountryUpdateResponseDTO countryUpdateResponseDTO = countryService.updateCountry(countryId, requestDTO);

      return ApiResponse.<CountryUpdateResponseDTO>builder()
         .status(ResponseConstants.SUCCESS_STATUS)
         .message(ResponseConstants.SUCCESS_MESSAGE)
         .data(countryUpdateResponseDTO)
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
  public ApiResponse<Void> deleteCountry(Integer countryId) {
    try {
      countryService.deleteCountry(countryId);
      return ApiResponse.<Void>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
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
}
