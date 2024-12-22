package dev.tanvx.addressservice.controller.usecase;

import dev.tanvx.addressservice.common.ApiResponse;
import dev.tanvx.addressservice.dto.request.AddressByCountryRequestDTO;
import dev.tanvx.addressservice.dto.response.AddressByCountryResponseDTO;
import dev.tanvx.addressservice.exception.ServiceException;
import dev.tanvx.addressservice.service.AddressService;
import dev.tanvx.addressservice.service.CountryService;
import dev.tanvx.addressservice.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressUseCase {

    private static final String PAGE_VALIDATION_ERROR = "Page number must be greater than zero";

    private static final String SIZE_VALIDATION_ERROR = "Size must be between 1 and 100";

    private static final String SORT_VALIDATION_ERROR = "Sort must be in the format 'field,direction'";

    private final MessageUtils messageUtils;

    private final AddressService addressService;

    private final CountryService countryService;

    public ApiResponse<Page<AddressByCountryResponseDTO>> getAddressByCountry(AddressByCountryRequestDTO requestDTO) {
        try {
            // Validate page parameter
            if (requestDTO.getPage() < 0) {
                throw new ServiceException(PAGE_VALIDATION_ERROR);
            }
            // Validate size parameter
            if (requestDTO.getSize() <= 0 || requestDTO.getPage() > 100) {
                throw new ServiceException(SIZE_VALIDATION_ERROR);
            }
            // Validate sort parameter
            String[] parts = requestDTO.getSort().split(",");
            if (parts.length < 1 || parts.length > 2) {
                throw new ServiceException(SORT_VALIDATION_ERROR);
            }

            countryService.getCountryById(requestDTO.getCountryId());
        } catch (ServiceException e) {

        }
    }


}
