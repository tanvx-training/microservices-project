package dev.tanvx.customer_service.controller.usecase;

import dev.tanvx.common_library.exception.BusinessException;
import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.exception.ValidationException;
import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.common_library.model.MessageProperties;
import dev.tanvx.common_library.model.ResponseConstants;
import dev.tanvx.common_library.util.MessageUtils;
import dev.tanvx.common_library.util.ValidationUtils;
import dev.tanvx.customer_service.dto.request.AddressByCityRequestDTO;
import dev.tanvx.customer_service.dto.request.AddressByCountryRequestDTO;
import dev.tanvx.customer_service.dto.request.AddressByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.AddressesRequestDTO;
import dev.tanvx.customer_service.dto.response.AddressByCityResponseDTO;
import dev.tanvx.customer_service.dto.response.AddressByCountryResponseDTO;
import dev.tanvx.customer_service.dto.response.AddressByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.AddressesResponseDTO;
import dev.tanvx.customer_service.entity.Address;
import dev.tanvx.customer_service.service.AddressService;
import dev.tanvx.customer_service.service.CityService;
import dev.tanvx.customer_service.service.CountryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressUseCase {

  private static final String SORT_VALIDATION_ERROR = "Sort must be in the format 'field,direction'";

  private final MessageUtils messageUtils;

  private final AddressService addressService;

  private final CountryService countryService;

  private final CityService cityService;

  private final ValidationUtils validationUtils;

  public ApiResponse<Page<AddressByCountryResponseDTO>> getAddressByCountry(
      AddressByCountryRequestDTO requestDTO) {
    try {
      // Validate page and size parameter
      validationUtils.validateRequest(requestDTO);

      // Validate sort parameter
      validationUtils.validateSortParam(requestDTO.getSort(), Address.class);

      // Validate country ID
      countryService.checkCountryById(requestDTO.getCountryId());

      Page<AddressByCountryResponseDTO> addressByCountryRequestDTOPage = addressService
          .getByCountry(requestDTO);

      return ApiResponse.<Page<AddressByCountryResponseDTO>>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(addressByCountryRequestDTOPage)
          .build();
    } catch (IllegalArgumentException e) {
      throw new ValidationException(List.of(SORT_VALIDATION_ERROR));
    } catch (ServiceException e) {
      if (CountryService.COUNTRY_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }


  public ApiResponse<Page<AddressByCityResponseDTO>> getAddressByCity(
      AddressByCityRequestDTO requestDTO) {
    try {
      // Validate page and size parameter
      validationUtils.validateRequest(requestDTO);

      // Validate sort parameter
      validationUtils.validateSortParam(requestDTO.getSort(), Address.class);

      // Validate city ID
      cityService.checkCityById(requestDTO.getCityId());

      Page<AddressByCityResponseDTO> addressByCityResponseDTOPage = addressService
          .getByCity(requestDTO);

      return ApiResponse.<Page<AddressByCityResponseDTO>>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(addressByCityResponseDTOPage)
          .build();
    } catch (IllegalArgumentException e) {
      throw new ValidationException(List.of(SORT_VALIDATION_ERROR));
    } catch (ServiceException e) {
      if (CityService.CITY_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }

      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  public ApiResponse<AddressByIdResponseDTO> getAddressById(Integer id) {
    try {

      AddressByIdRequestDTO requestDTO = AddressByIdRequestDTO.builder()
          .addressId(id)
          .build();
      AddressByIdResponseDTO addressByIdResponseDTO = addressService.getById(requestDTO);
      return ApiResponse.<AddressByIdResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(addressByIdResponseDTO)
          .build();

    } catch (ServiceException e) {
      if (AddressService.ADDRESS_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  public ApiResponse<Page<AddressesResponseDTO>> getAddresses(AddressesRequestDTO requestDTO) {

    try {
      // Validate page and size parameter
      validationUtils.validateRequest(requestDTO);

      // Validate sort parameter
      validationUtils.validateSortParam(requestDTO.getSort(), Address.class);

      Page<AddressesResponseDTO> addressesResponseDTOPage = addressService.getAddresses(requestDTO);

      return ApiResponse.<Page<AddressesResponseDTO>>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(addressesResponseDTOPage)
          .build();
    } catch (IllegalArgumentException e) {
      throw new ValidationException(List.of(SORT_VALIDATION_ERROR));
    } catch (Exception e) {
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }
}