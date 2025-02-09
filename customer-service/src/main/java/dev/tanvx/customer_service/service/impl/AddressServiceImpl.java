package dev.tanvx.customer_service.service.impl;

import dev.tanvx.common_library.enums.DeleteStatus;
import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.specification.SearchSpecification;
import dev.tanvx.common_library.specification.enums.FieldType;
import dev.tanvx.common_library.specification.enums.Operator;
import dev.tanvx.common_library.specification.request.FilterRequest;
import dev.tanvx.common_library.specification.request.SearchRequest;
import dev.tanvx.common_library.util.SearchSpecificationRequestUtils;
import dev.tanvx.customer_service.dto.request.address.AddressesByCityRequestDTO;
import dev.tanvx.customer_service.dto.request.address.AddressesByCountryRequestDTO;
import dev.tanvx.customer_service.dto.request.address.AddressByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.address.AddressCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.address.AddressUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.address.AddressesRequestDTO;
import dev.tanvx.customer_service.dto.response.address.AddressByCityResponseDTO;
import dev.tanvx.customer_service.dto.response.address.AddressByCountryResponseDTO;
import dev.tanvx.customer_service.dto.response.address.AddressByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.address.AddressCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.address.AddressDeleteResponseDTO;
import dev.tanvx.customer_service.dto.response.address.AddressUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.address.AddressesResponseDTO;
import dev.tanvx.customer_service.entity.Address;
import dev.tanvx.customer_service.entity.City;
import dev.tanvx.customer_service.repository.AddressRepository;
import dev.tanvx.customer_service.repository.CityRepository;
import dev.tanvx.customer_service.service.AddressService;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

  private final AddressRepository addressRepository;

  private final CityRepository cityRepository;

  private final SearchSpecificationRequestUtils searchSpecificationRequestUtils;

  @Override
  public Page<AddressByCityResponseDTO> getAddressesByCity(AddressesByCityRequestDTO requestDTO) {

    // Building request
    FilterRequest filterRequest = FilterRequest.builder()
        .key("city.cityId")
        .operator(Operator.EQUAL)
        .fieldType(FieldType.INTEGER)
        .value(requestDTO.getCityId())
        .build();

    SearchRequest searchRequest = SearchRequest.builder()
        .filters(List.of(filterRequest))
        .sorts(searchSpecificationRequestUtils.buildSortRequestList(requestDTO.getSort()))
        .page(requestDTO.getPage())
        .size(requestDTO.getSize())
        .build();

    SearchSpecification<Address> specification = new SearchSpecification<>(searchRequest);
    Pageable pageable = SearchSpecification.getPageable(requestDTO.getPage(), requestDTO.getSize());
    return addressRepository.findAll(specification, pageable)
        .map(address -> AddressByCityResponseDTO.builder()
            .addressId(address.getAddressId())
            .address(address.getAddress())
            .address2(address.getAddress2())
            .district(address.getDistrict())
            .postalCode(address.getPostalCode())
            .phone(address.getPhone())
            .city(AddressByCityResponseDTO.CityDTO.builder()
                .cityId(address.getCity().getCityId())
                .cityName(address.getCity().getName())
                .build())
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
                .cityName(address.getCity().getName())
                .build())
            .country(AddressByIdResponseDTO.CountryDTO.builder()
                .countryId(address.getCity().getCountry().getCountryId())
                .countryName(address.getCity().getCountry().getName())
                .build())
            .build())
        .orElseThrow(() -> new ServiceException(ADDRESS_NOT_FOUND));
  }

  @Override
  public Page<AddressByCountryResponseDTO> getAddressesByCountry(
      AddressesByCountryRequestDTO requestDTO) {

    // Building request
    FilterRequest filterRequest = FilterRequest.builder()
        .key("city.country.countryId")
        .operator(Operator.EQUAL)
        .fieldType(FieldType.INTEGER)
        .value(requestDTO.getCountryId())
        .build();

    SearchRequest searchRequest = SearchRequest.builder()
        .filters(List.of(filterRequest))
        .sorts(searchSpecificationRequestUtils.buildSortRequestList(requestDTO.getSort()))
        .page(requestDTO.getPage())
        .size(requestDTO.getSize())
        .build();

    SearchSpecification<Address> specification = new SearchSpecification<>(searchRequest);
    Pageable pageable = SearchSpecification.getPageable(requestDTO.getPage(), requestDTO.getSize());
    return addressRepository.findAll(specification, pageable)
        .map(address -> AddressByCountryResponseDTO.builder()
            .addressId(address.getAddressId())
            .address(address.getAddress())
            .address2(address.getAddress2())
            .district(address.getDistrict())
            .postalCode(address.getPostalCode())
            .phone(address.getPhone())
            .city(AddressByCountryResponseDTO.CityDTO.builder()
                .cityId(address.getCity().getCityId())
                .cityName(address.getCity().getName())
                .build())
            .country(AddressByCountryResponseDTO.CountryDTO.builder()
                .countryId(address.getCity().getCountry().getCountryId())
                .countryName(address.getCity().getCountry().getName())
                .build())
            .build());
  }

  @Override
  public Page<AddressesResponseDTO> getAddresses(AddressesRequestDTO requestDTO) {

    SearchRequest searchRequest = SearchRequest.builder()
        .sorts(searchSpecificationRequestUtils.buildSortRequestList(requestDTO.getSort()))
        .page(requestDTO.getPage())
        .size(requestDTO.getSize())
        .build();

    SearchSpecification<Address> specification = new SearchSpecification<>(searchRequest);
    Pageable pageable = SearchSpecification.getPageable(requestDTO.getPage(), requestDTO.getSize());
    return addressRepository.findAll(specification, pageable)
        .map(address -> AddressesResponseDTO.builder()
            .addressId(address.getAddressId())
            .address(address.getAddress())
            .address2(address.getAddress2())
            .district(address.getDistrict())
            .postalCode(address.getPostalCode())
            .phone(address.getPhone())
            .city(AddressesResponseDTO.CityDTO.builder()
                .cityId(address.getCity().getCityId())
                .cityName(address.getCity().getName())
                .build())
            .country(AddressesResponseDTO.CountryDTO.builder()
                .countryId(address.getCity().getCountry().getCountryId())
                .countryName(address.getCity().getCountry().getName())
                .build())
            .build());
  }

  @Override
  public AddressCreateResponseDTO createAddress(AddressCreateRequestDTO requestDTO) {
    Address address = Address.builder()
        .address(requestDTO.getAddress())
        .address2(requestDTO.getAddress2())
        .district(requestDTO.getDistrict())
        .postalCode(requestDTO.getPostalCode())
        .phone(requestDTO.getPhone())
        .lastUpdate(OffsetDateTime.now())
        .city(cityRepository.findById(requestDTO.getCityId())
            .orElse(City.builder().build()))
        .build();
    addressRepository.save(address);
    return AddressCreateResponseDTO.builder()
        .addressId(address.getAddressId())
        .lastUpdate(address.getLastUpdate())
        .build();
  }

  @Override
  public AddressUpdateResponseDTO updateAddress(Integer addressId, AddressUpdateRequestDTO requestDTO)
      throws ServiceException {
    Address address = addressRepository.findAddressByAddressId(addressId)
        .orElseThrow(() -> new ServiceException(ADDRESS_NOT_FOUND));

    address.setAddress(requestDTO.getAddress());
    address.setAddress2(requestDTO.getAddress2());
    address.setDistrict(requestDTO.getDistrict());
    address.setPostalCode(requestDTO.getPostalCode());
    address.setPhone(requestDTO.getPhone());
    address.setCity(cityRepository.findById(requestDTO.getCityId())
        .orElse(City.builder().build()));
    address.setLastUpdate(OffsetDateTime.now());
    addressRepository.save(address);

    return AddressUpdateResponseDTO.builder()
        .addressId(address.getAddressId())
        .lastUpdate(address.getLastUpdate())
        .build();
  }

  @Override
  public AddressDeleteResponseDTO deleteAddress(Integer addressId) throws ServiceException {
    Address address = addressRepository.findAddressByAddressId(addressId)
        .orElseThrow(() -> new ServiceException(ADDRESS_NOT_FOUND));
    address.setDeleteFlg(DeleteStatus.INACTIVE.isValue());
    addressRepository.save(address);
    return AddressDeleteResponseDTO.builder()
        .addressId(address.getAddressId())
        .deleteFlg(address.isDeleteFlg())
        .build();
  }
}
