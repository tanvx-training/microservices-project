package dev.tanvx.addressservice.service.impl;

import dev.tanvx.addressservice.dto.response.AddressByCountryResponseDTO;
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
    public Page<AddressByCountryResponseDTO> getByCountry(Integer countryId, Pageable pageable) throws ServiceException {
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
}
