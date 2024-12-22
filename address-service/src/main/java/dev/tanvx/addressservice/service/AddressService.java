package dev.tanvx.addressservice.service;

import dev.tanvx.addressservice.dto.request.AddressByCountryRequestDTO;
import dev.tanvx.addressservice.dto.response.AddressByCountryResponseDTO;
import dev.tanvx.addressservice.exception.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {

    Page<AddressByCountryResponseDTO> getByCountry(Integer countryId, Pageable pageable) throws ServiceException;
}
