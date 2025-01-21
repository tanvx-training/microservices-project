package dev.tanvx.customer_service.service;

import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.customer_service.dto.request.address.AddressByCityRequestDTO;
import dev.tanvx.customer_service.dto.request.address.AddressByCountryRequestDTO;
import dev.tanvx.customer_service.dto.request.address.AddressByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.address.AddressCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.address.AddressUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.address.AddressesRequestDTO;
import dev.tanvx.customer_service.dto.response.address.AddressByCityResponseDTO;
import dev.tanvx.customer_service.dto.response.address.AddressByCountryResponseDTO;
import dev.tanvx.customer_service.dto.response.address.AddressByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.address.AddressCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.address.AddressUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.address.AddressesResponseDTO;
import org.springframework.data.domain.Page;

public interface AddressService {

    String ADDRESS_NOT_FOUND = "ADDRESS_NOT_FOUND";

    Page<AddressByCityResponseDTO> getByCity(AddressByCityRequestDTO requestDTO);

    AddressByIdResponseDTO getById(AddressByIdRequestDTO requestDTO) throws ServiceException;

    Page<AddressByCountryResponseDTO> getByCountry(AddressByCountryRequestDTO requestDTO);

    Page<AddressesResponseDTO> getAddresses(AddressesRequestDTO requestDTO);

    AddressCreateResponseDTO createAddress(AddressCreateRequestDTO requestDTO);

    AddressUpdateResponseDTO updateAddress(Integer addressId, AddressUpdateRequestDTO requestDTO)
        throws ServiceException;

    Void deleteAddress(Integer addressId) throws ServiceException;
}
