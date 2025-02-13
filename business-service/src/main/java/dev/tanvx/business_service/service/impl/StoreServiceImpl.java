package dev.tanvx.business_service.service.impl;

import dev.tanvx.business_service.dto.request.store.StoreByIdRequestDTO;
import dev.tanvx.business_service.dto.request.store.StoreCreateRequestDTO;
import dev.tanvx.business_service.dto.request.store.StoreUpdateRequestDTO;
import dev.tanvx.business_service.dto.request.store.StoresRequestDTO;
import dev.tanvx.business_service.dto.response.store.StoreByIdResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreCreateResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreUpdateResponseDTO;
import dev.tanvx.business_service.repository.StaffRepository;
import dev.tanvx.business_service.repository.StoreRepository;
import dev.tanvx.business_service.service.StaffService;
import dev.tanvx.business_service.service.StoreService;
import dev.tanvx.common_library.enums.DeleteStatus;
import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.specification.SearchSpecification;
import dev.tanvx.common_library.specification.request.SearchRequest;
import dev.tanvx.common_library.util.SearchSpecificationRequestUtils;
import dev.tanvx.business_service.client.address.AddressByIdResponseDTO;
import dev.tanvx.business_service.client.address.AddressServiceClient;
import dev.tanvx.business_service.dto.response.store.StoreDeleteResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoresResponseDTO;
import dev.tanvx.business_service.entity.Staff;
import dev.tanvx.business_service.entity.Store;
import java.time.ZonedDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

  private final StoreRepository storeRepository;

  private final StaffRepository staffRepository;

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
            StoresResponseDTO.AddressDTO staffAddressDTO = StoresResponseDTO.AddressDTO.builder()
                .addressId(staffAddress.getAddressId())
                .address(staffAddress.getAddress())
                .address2(staffAddress.getAddress2())
                .district(staffAddress.getDistrict())
                .postalCode(staffAddress.getPostalCode())
                .phone(staffAddress.getPhone())
                .country(StoresResponseDTO.CountryDTO.builder()
                    .countryId(staffAddress.getCountry().getCountryId())
                    .countryName(staffAddress.getCountry().getCountryName())
                    .build())
                .city(StoresResponseDTO.CityDTO.builder()
                    .cityId(staffAddress.getCity().getCityId())
                    .cityName(staffAddress.getCity().getCityName())
                    .build())
                .build();
            StoresResponseDTO.StaffDTO staffDTO = StoresResponseDTO.StaffDTO.builder()
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
            StoresResponseDTO.AddressDTO storeAddressDTO = StoresResponseDTO.AddressDTO.builder()
                .addressId(storeAddress.getAddressId())
                .address(storeAddress.getAddress())
                .address2(storeAddress.getAddress2())
                .district(storeAddress.getDistrict())
                .postalCode(storeAddress.getPostalCode())
                .phone(storeAddress.getPhone())
                .country(StoresResponseDTO.CountryDTO.builder()
                    .countryId(storeAddress.getCountry().getCountryId())
                    .countryName(storeAddress.getCountry().getCountryName())
                    .build())
                .city(StoresResponseDTO.CityDTO.builder()
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
  public StoreByIdResponseDTO getStoreById(StoreByIdRequestDTO requestDTO) throws ServiceException {
    Store store = storeRepository.findById(requestDTO.getStoreId())
        .orElseThrow(() -> new ServiceException(STORE_NOT_FOUND));
    StoreByIdResponseDTO.StoreByIdResponseDTOBuilder builder = StoreByIdResponseDTO.builder();
    builder.storeId(store.getStoreId());
    Staff staff = store.getManager();
    if (Objects.nonNull(staff)) {
      AddressByIdResponseDTO staffAddress = addressServiceClient
          .getAddressById(staff.getAddressId())
          .getBody()
          .getData();
      StoreByIdResponseDTO.AddressDTO staffAddressDTO = StoreByIdResponseDTO.AddressDTO.builder()
          .addressId(staffAddress.getAddressId())
          .address(staffAddress.getAddress())
          .address2(staffAddress.getAddress2())
          .district(staffAddress.getDistrict())
          .postalCode(staffAddress.getPostalCode())
          .phone(staffAddress.getPhone())
          .country(StoreByIdResponseDTO.CountryDTO.builder()
              .countryId(staffAddress.getCountry().getCountryId())
              .countryName(staffAddress.getCountry().getCountryName())
              .build())
          .city(StoreByIdResponseDTO.CityDTO.builder()
              .cityId(staffAddress.getCity().getCityId())
              .cityName(staffAddress.getCity().getCityName())
              .build())
          .build();
      StoreByIdResponseDTO.StaffDTO staffDTO = StoreByIdResponseDTO.StaffDTO.builder()
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
      StoreByIdResponseDTO.AddressDTO storeAddressDTO = StoreByIdResponseDTO.AddressDTO.builder()
          .addressId(storeAddress.getAddressId())
          .address(storeAddress.getAddress())
          .address2(storeAddress.getAddress2())
          .district(storeAddress.getDistrict())
          .postalCode(storeAddress.getPostalCode())
          .phone(storeAddress.getPhone())
          .country(StoreByIdResponseDTO.CountryDTO.builder()
              .countryId(storeAddress.getCountry().getCountryId())
              .countryName(storeAddress.getCountry().getCountryName())
              .build())
          .city(StoreByIdResponseDTO.CityDTO.builder()
              .cityId(storeAddress.getCity().getCityId())
              .cityName(storeAddress.getCity().getCityName())
              .build())
          .build();
      builder.address(storeAddressDTO);
    }
    return builder.build();
  }

  @Override
  public StoreCreateResponseDTO createStore(StoreCreateRequestDTO requestDTO)
      throws ServiceException {
    Store.StoreBuilder builder = Store.builder();
    if (Objects.nonNull(requestDTO.getManagerStaffId())) {
      Staff staff = staffRepository.findById(requestDTO.getManagerStaffId())
          .orElseThrow(() -> new ServiceException(StaffService.STAFF_NOT_FOUND));
      builder.manager(staff);
    }
    AddressByIdResponseDTO address = addressServiceClient
        .getAddressById(requestDTO.getAddressId())
        .getBody()
        .getData();
    builder.addressId(address.getAddressId());
    builder.deleteFlg(DeleteStatus.ACTIVE.isValue());
    builder.lastUpdate(ZonedDateTime.now());
    Store store = builder.build();
    storeRepository.save(store);
    return StoreCreateResponseDTO.builder()
        .storeId(store.getStoreId())
        .deleteFlg(store.isDeleteFlg())
        .lastUpdate(store.getLastUpdate())
        .build();
  }

  @Override
  public StoreUpdateResponseDTO updateStore(Integer storeId, StoreUpdateRequestDTO requestDTO)
      throws ServiceException {
    Store store = storeRepository.findById(storeId)
        .orElseThrow(() -> new ServiceException(STORE_NOT_FOUND));
    if (Objects.nonNull(requestDTO.getManagerStaffId())
        && !Objects.equals(store.getManager().getStaffId(), requestDTO.getManagerStaffId())){
      Staff staff = staffRepository.findById(requestDTO.getManagerStaffId())
          .orElseThrow(() -> new ServiceException(StaffService.STAFF_NOT_FOUND));
      store.setManager(staff);
    }
    if (!Objects.equals(store.getAddressId(), requestDTO.getAddressId())){
      AddressByIdResponseDTO address = addressServiceClient
          .getAddressById(requestDTO.getAddressId())
          .getBody()
          .getData();
      store.setAddressId(address.getAddressId());
    }
    store.setLastUpdate(ZonedDateTime.now());
    storeRepository.save(store);
    return StoreUpdateResponseDTO.builder()
        .storeId(store.getStoreId())
        .deleteFlg(store.isDeleteFlg())
        .lastUpdate(store.getLastUpdate())
        .build();
  }

  @Override
  public StoreDeleteResponseDTO deleteStore(Integer storeId) throws ServiceException {
    Store store = storeRepository.findById(storeId)
        .orElseThrow(() -> new ServiceException(STORE_NOT_FOUND));
    store.setDeleteFlg(DeleteStatus.INACTIVE.isValue());
    store.setLastUpdate(ZonedDateTime.now());
    storeRepository.save(store);
    return StoreDeleteResponseDTO.builder()
        .storeId(store.getStoreId())
        .deleteFlg(store.isDeleteFlg())
        .lastUpdate(store.getLastUpdate())
        .build();
  }
}
