package dev.tanvx.addressservice.service;

import dev.tanvx.addressservice.dto.request.AddressByIdRequestDTO;
import dev.tanvx.addressservice.dto.response.AddressByCityResponseDTO;
import dev.tanvx.addressservice.dto.response.AddressByCountryResponseDTO;
import dev.tanvx.addressservice.dto.response.AddressByIdResponseDTO;
import dev.tanvx.addressservice.exception.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {

    String ADDRESS_NOT_FOUND = "ADDRESS_NOT_FOUND";

    Page<AddressByCountryResponseDTO> getByCountry(Integer countryId, Pageable pageable);

    Page<AddressByCityResponseDTO> getByCity(Integer cityId, Pageable pageable);

    AddressByIdResponseDTO getById(AddressByIdRequestDTO requestDTO) throws ServiceException;
}
