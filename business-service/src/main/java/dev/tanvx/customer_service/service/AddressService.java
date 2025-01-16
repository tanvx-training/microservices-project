package dev.tanvx.customer_service.service;

import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.customer_service.dto.request.AddressByCityRequestDTO;
import dev.tanvx.customer_service.dto.request.AddressByCountryRequestDTO;
import dev.tanvx.customer_service.dto.request.AddressByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.AddressesRequestDTO;
import dev.tanvx.customer_service.dto.response.AddressByCityResponseDTO;
import dev.tanvx.customer_service.dto.response.AddressByCountryResponseDTO;
import dev.tanvx.customer_service.dto.response.AddressByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.AddressesResponseDTO;
import org.springframework.data.domain.Page;

public interface AddressService {

    String ADDRESS_NOT_FOUND = "ADDRESS_NOT_FOUND";

    Page<AddressByCityResponseDTO> getByCity(AddressByCityRequestDTO requestDTO);

    AddressByIdResponseDTO getById(AddressByIdRequestDTO requestDTO) throws ServiceException;

    Page<AddressByCountryResponseDTO> getByCountry(AddressByCountryRequestDTO requestDTO);

    Page<AddressesResponseDTO> getAddresses(AddressesRequestDTO requestDTO);
}
