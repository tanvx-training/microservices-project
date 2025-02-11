package dev.tanvx.business_service.service.impl;

import dev.tanvx.business_service.dto.request.store.StoreCreateRequestDTO;
import dev.tanvx.business_service.dto.request.store.StoreUpdateRequestDTO;
import dev.tanvx.business_service.dto.request.store.StoresRequestDTO;
import dev.tanvx.business_service.dto.response.store.StoreByIdResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreCreateResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreUpdateResponseDTO;
import dev.tanvx.business_service.repository.StoreRepository;
import dev.tanvx.business_service.service.StoreService;
import dev.tanvx.common_library.specification.SearchSpecification;
import dev.tanvx.common_library.specification.request.SearchRequest;
import dev.tanvx.common_library.util.SearchSpecificationRequestUtils;
import dev.tanvx.business_service.client.address.AddressByIdResponseDTO;
import dev.tanvx.business_service.client.address.AddressServiceClient;
import dev.tanvx.business_service.dto.response.store.StoreDeleteResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoresResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoresResponseDTO.AddressDTO;
import dev.tanvx.business_service.dto.response.store.StoresResponseDTO.CityDTO;
import dev.tanvx.business_service.dto.response.store.StoresResponseDTO.CountryDTO;
import dev.tanvx.business_service.dto.response.store.StoresResponseDTO.StaffDTO;
import dev.tanvx.business_service.entity.Staff;
import dev.tanvx.business_service.entity.Store;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

  private final StoreRepository storeRepository;

  private final AddressServiceClient addressServiceClient;

  private final SearchSpecificationRequestUtils searchSpecificationRequestUtils;

  @Override
  public Page<StoresResponseDTO> getStores(StoresRequestDTO requestDTO) {
    SearchRequest searchRequest = SearchRequest.builder()
        .sorts(searchSpecificationRequestUtils.buildSortRequestList(requestDTO.getSort()))
        .page(requestDTO.getPage())
        .size(requestDTO.getSize())
        .build();
    SearchSpecification<Store> specification = new SearchSpecification<>(searchRequest);
    Pageable pageable = SearchSpecification.getPageable(requestDTO.getPage(), requestDTO.getSize());
    return storeRepository.findAll(specification, pageable)
        .map(store -> {
          StoresResponseDTO.StoresResponseDTOBuilder builder = StoresResponseDTO.builder();
          builder.storeId(store.getStoreId());
          // Build manager data
          Staff staff = store.getManager();
          if (Objects.nonNull(staff)) {
            AddressByIdResponseDTO staffAddress = addressServiceClient
                .getAddressById(staff.getAddressId())
                .getBody()
                .getData();
            AddressDTO staffAddressDTO = AddressDTO.builder()
                .addressId(staffAddress.getAddressId())
                .address(staffAddress.getAddress())
                .address2(staffAddress.getAddress2())
                .district(staffAddress.getDistrict())
                .postalCode(staffAddress.getPostalCode())
                .phone(staffAddress.getPhone())
                .country(CountryDTO.builder()
                    .countryId(staffAddress.getCountry().getCountryId())
                    .countryName(staffAddress.getCountry().getCountryName())
                    .build())
                .city(CityDTO.builder()
                    .cityId(staffAddress.getCity().getCityId())
                    .cityName(staffAddress.getCity().getCityName())
                    .build())
                .build();
            StaffDTO staffDTO = StaffDTO.builder()
                .staffId(staff.getStaffId())
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .address(staffAddressDTO)
                .email(staff.getEmail())
                .build();
            builder.manager(staffDTO);
          }
          // Build address data
          AddressByIdResponseDTO storeAddress = addressServiceClient
              .getAddressById(store.getAddressId())
              .getBody()
              .getData();
          if (Objects.nonNull(storeAddress)) {
            AddressDTO storeAddressDTO = AddressDTO.builder()
                .addressId(storeAddress.getAddressId())
                .address(storeAddress.getAddress())
                .address2(storeAddress.getAddress2())
                .district(storeAddress.getDistrict())
                .postalCode(storeAddress.getPostalCode())
                .phone(storeAddress.getPhone())
                .country(CountryDTO.builder()
                    .countryId(storeAddress.getCountry().getCountryId())
                    .countryName(storeAddress.getCountry().getCountryName())
                    .build())
                .city(CityDTO.builder()
                    .cityId(storeAddress.getCity().getCityId())
                    .cityName(storeAddress.getCity().getCityName())
                    .build())
                .build();
            builder.address(storeAddressDTO);
          }
          return builder.build();
        });
  }

  @Override
  public StoreByIdResponseDTO getStoreById(Integer storeId) {
    return null;
  }

  @Override
  public StoreCreateResponseDTO createStore(StoreCreateRequestDTO requestDTO) {
    return null;
  }

  @Override
  public StoreUpdateResponseDTO updateStore(Integer storeId, StoreUpdateRequestDTO requestDTO) {
    return null;
  }

  @Override
  public StoreDeleteResponseDTO deleteStore(Integer storeId) {
    return null;
  }
}
