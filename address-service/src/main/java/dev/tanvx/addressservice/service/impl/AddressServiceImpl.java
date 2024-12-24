package dev.tanvx.addressservice.service.impl;

import dev.tanvx.addressservice.dto.request.AddressByIdRequestDTO;
import dev.tanvx.addressservice.dto.response.AddressByCityResponseDTO;
import dev.tanvx.addressservice.dto.response.AddressByCountryResponseDTO;
import dev.tanvx.addressservice.dto.response.AddressByIdResponseDTO;
import dev.tanvx.addressservice.entity.Address;
import dev.tanvx.addressservice.exception.ServiceException;
import dev.tanvx.addressservice.repository.AddressRepository;
import dev.tanvx.addressservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Page<AddressByCountryResponseDTO> getByCountry(Integer countryId, Pageable pageable) {
        Page<Address> addressPage = addressRepository.findByCountryId(countryId, pageable);
        return addressPage.map(address -> AddressByCountryResponseDTO.builder()
                .addressId(address.getAddressId())
                .address(address.getAddress())
                .address2(address.getAddress2())
                .district(address.getDistrict())
                .postalCode(address.getPostalCode())
                .phone(address.getPhone())
                .city(AddressByCountryResponseDTO.CityDTO.builder()
                        .cityId(address.getCity().getCityId())
                        .cityName(address.getCity().getCity())
                        .build())
                .build());
    }

    @Override
    public Page<AddressByCityResponseDTO> getByCity(Integer cityId, Pageable pageable) {
        Page<Address> addressPage = addressRepository.findByCityId(cityId, pageable);
        return addressPage.map(address -> AddressByCityResponseDTO.builder()
                .addressId(address.getAddressId())
                .address(address.getAddress())
                .address2(address.getAddress2())
                .district(address.getDistrict())
                .postalCode(address.getPostalCode())
                .phone(address.getPhone())
                .build());
    }

    @Override
    public AddressByIdResponseDTO getById(AddressByIdRequestDTO requestDTO) throws ServiceException {
        return addressRepository.findById(requestDTO.getAddressId())
                .map(address -> AddressByIdResponseDTO.builder()
                        .addressId(address.getAddressId())
                        .address(address.getAddress())
                        .address2(address.getAddress2())
                        .district(address.getDistrict())
                        .postalCode(address.getPostalCode())
                        .phone(address.getPhone())
                        .city(AddressByIdResponseDTO.CityDTO.builder()
                                .cityId(address.getCity().getCityId())
                                .cityName(address.getCity().getCity())
                                .build())
                        .country(AddressByIdResponseDTO.CountryDTO.builder()
                                .countryId(address.getCity().getCountry().getCountryId())
                                .countryName(address.getCity().getCountry().getCountry())
                                .build())
                        .build())
                .orElseThrow(() -> new ServiceException(ADDRESS_NOT_FOUND));
    }
}
