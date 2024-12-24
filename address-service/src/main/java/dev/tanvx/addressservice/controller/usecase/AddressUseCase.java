package dev.tanvx.addressservice.controller.usecase;

import dev.tanvx.addressservice.common.ApiResponse;
import dev.tanvx.addressservice.common.MessageProperties;
import dev.tanvx.addressservice.common.ResponseConstants;
import dev.tanvx.addressservice.dto.request.AddressByCityRequestDTO;
import dev.tanvx.addressservice.dto.request.AddressByCountryRequestDTO;
import dev.tanvx.addressservice.dto.request.AddressByIdRequestDTO;
import dev.tanvx.addressservice.dto.response.AddressByCityResponseDTO;
import dev.tanvx.addressservice.dto.response.AddressByCountryResponseDTO;
import dev.tanvx.addressservice.dto.response.AddressByIdResponseDTO;
import dev.tanvx.addressservice.exception.BusinessException;
import dev.tanvx.addressservice.exception.ServiceException;
import dev.tanvx.addressservice.exception.ValidationException;
import dev.tanvx.addressservice.service.AddressService;
import dev.tanvx.addressservice.service.CityService;
import dev.tanvx.addressservice.service.CountryService;
import dev.tanvx.addressservice.util.MessageUtils;
import dev.tanvx.addressservice.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressUseCase {

    private static final String SORT_VALIDATION_ERROR = "Sort must be in the format 'field,direction'";

    private final MessageUtils messageUtils;

    private final AddressService addressService;

    private final CountryService countryService;

    private final CityService cityService;

    private final ValidationUtils validationUtils;

    public ApiResponse<Page<AddressByCountryResponseDTO>> getAddressByCountry(AddressByCountryRequestDTO requestDTO) {
        try {
            // Validate page and size parameter
            validationUtils.validateRequest(requestDTO);

            // Validate sort parameter
            String[] parts = requestDTO.getSort().split(",");
            if (parts.length < 1 || parts.length > 2) {
                throw new ServiceException(SORT_VALIDATION_ERROR);
            }

            countryService.checkCountryById(requestDTO.getCountryId());

            Sort sort = Sort.by(Sort.Direction.fromString(parts[1]), parts[0]);
            Pageable pageable = PageRequest.of(requestDTO.getPage(), requestDTO.getSize(), sort);
            Page<AddressByCountryResponseDTO> addressByCountryRequestDTOPage = addressService
                    .getByCountry(requestDTO.getCountryId(), pageable);

            return ApiResponse.<Page<AddressByCountryResponseDTO>>builder()
                    .status(ResponseConstants.SUCCESS)
                    .message(ResponseConstants.GET_SUCCESS_MESSAGE)
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


    public ApiResponse<Page<AddressByCityResponseDTO>> getAddressByCity(AddressByCityRequestDTO requestDTO) {
        try {
            // Validate page and size parameter
            validationUtils.validateRequest(requestDTO);

            // Validate sort parameter
            String[] parts = requestDTO.getSort().split(",");
            if (parts.length < 1 || parts.length > 2) {
                throw new ServiceException(SORT_VALIDATION_ERROR);
            }

            cityService.checkCityById(requestDTO.getCityId());

            Sort sort = Sort.by(Sort.Direction.fromString(parts[1]), parts[0]);
            Pageable pageable = PageRequest.of(requestDTO.getPage(), requestDTO.getSize(), sort);
            Page<AddressByCityResponseDTO> addressByCityResponseDTOPage = addressService
                    .getByCity(requestDTO.getCityId(), pageable);

            return ApiResponse.<Page<AddressByCityResponseDTO>>builder()
                    .status(ResponseConstants.SUCCESS)
                    .message(ResponseConstants.GET_SUCCESS_MESSAGE)
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
                    .status(ResponseConstants.SUCCESS)
                    .message(ResponseConstants.GET_SUCCESS_MESSAGE)
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
}
