package dev.tanvx.business_service.service.impl;

import dev.tanvx.common_library.model.client.AddressByIdResponseDTO;
import dev.tanvx.business_service.client.address.AddressServiceClient;
import dev.tanvx.business_service.dto.request.staff.StaffByIdRequestDTO;
import dev.tanvx.business_service.dto.request.staff.StaffCreateRequestDTO;
import dev.tanvx.business_service.dto.request.staff.StaffRequestDTO;
import dev.tanvx.business_service.dto.request.staff.StaffUpdateRequestDTO;
import dev.tanvx.business_service.dto.response.staff.StaffByIdResponseDTO;
import dev.tanvx.business_service.dto.response.staff.StaffCreateResponseDTO;
import dev.tanvx.business_service.dto.response.staff.StaffDeleteResponseDTO;
import dev.tanvx.business_service.dto.response.staff.StaffUpdateResponseDTO;
import dev.tanvx.business_service.dto.response.staff.StaffsResponseDTO;
import dev.tanvx.business_service.entity.Staff;
import dev.tanvx.business_service.entity.Store;
import dev.tanvx.business_service.repository.StaffRepository;
import dev.tanvx.business_service.repository.StoreRepository;
import dev.tanvx.business_service.service.StaffService;
import dev.tanvx.business_service.service.StoreService;
import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.specification.SearchSpecification;
import dev.tanvx.common_library.specification.request.SearchRequest;
import dev.tanvx.common_library.util.SearchSpecificationRequestUtils;
import java.time.ZonedDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

  private final StaffRepository staffRepository;

  private final StoreRepository storeRepository;

  private final PasswordEncoder passwordEncoder;

  private final AddressServiceClient addressServiceClient;

  private final SearchSpecificationRequestUtils searchSpecificationRequestUtils;

  @Override
  public Page<StaffsResponseDTO> getStaffs(StaffRequestDTO requestDTO) {
    SearchRequest searchRequest = SearchRequest.builder()
        .sorts(searchSpecificationRequestUtils.buildSortRequestList(requestDTO.getSort()))
        .page(requestDTO.getPage())
        .size(requestDTO.getSize())
        .build();
    SearchSpecification<Staff> specification = new SearchSpecification<>(searchRequest);
    Pageable pageable = SearchSpecification.getPageable(requestDTO.getPage(), requestDTO.getSize());
    return staffRepository.findAll(specification, pageable)
        .map(staff -> {
          StaffsResponseDTO.StaffsResponseDTOBuilder builder = StaffsResponseDTO.builder()
              .staffId(staff.getStaffId())
              .firstName(staff.getFirstName())
              .lastName(staff.getLastName())
              .email(staff.getEmail())
              .isActive(staff.getActive());
          Store store = staff.getStore();
          AddressByIdResponseDTO storeAddress = addressServiceClient
              .getAddressById(store.getAddressId())
              .getBody()
              .getData();
          builder.store(StaffsResponseDTO.StoreDTO.builder()
              .storeId(store.getStoreId())
              .address(StaffsResponseDTO.AddressDTO.builder()
                  .addressId(storeAddress.getAddressId())
                  .address(storeAddress.getAddress())
                  .address2(storeAddress.getAddress2())
                  .district(storeAddress.getDistrict())
                  .postalCode(storeAddress.getPostalCode())
                  .phone(storeAddress.getPhone())
                  .country(StaffsResponseDTO.CountryDTO.builder()
                      .countryId(storeAddress.getCountry().getCountryId())
                      .countryName(storeAddress.getCountry().getCountryName())
                      .build())
                  .city(StaffsResponseDTO.CityDTO.builder()
                      .cityId(storeAddress.getCity().getCityId())
                      .cityName(storeAddress.getCity().getCityName())
                      .build())
                  .build())
              .build());
          AddressByIdResponseDTO staffAddress = addressServiceClient
              .getAddressById(staff.getAddressId())
              .getBody()
              .getData();
          builder.address(StaffsResponseDTO.AddressDTO.builder()
              .addressId(staffAddress.getAddressId())
              .address(staffAddress.getAddress())
              .address2(staffAddress.getAddress2())
              .district(staffAddress.getDistrict())
              .postalCode(staffAddress.getPostalCode())
              .phone(staffAddress.getPhone())
              .country(StaffsResponseDTO.CountryDTO.builder()
                  .countryId(staffAddress.getCountry().getCountryId())
                  .countryName(staffAddress.getCountry().getCountryName())
                  .build())
              .city(StaffsResponseDTO.CityDTO.builder()
                  .cityId(staffAddress.getCity().getCityId())
                  .cityName(staffAddress.getCity().getCityName())
                  .build())
              .build());
          return builder.build();
        });
  }

  @Override
  public StaffByIdResponseDTO getStaffById(StaffByIdRequestDTO requestDTO) throws ServiceException {
    return staffRepository.findById(requestDTO.getStaffId())
        .map(staff -> {
          StaffByIdResponseDTO.StaffByIdResponseDTOBuilder builder = StaffByIdResponseDTO.builder()
              .staffId(staff.getStaffId())
              .firstName(staff.getFirstName())
              .lastName(staff.getLastName())
              .email(staff.getEmail())
              .isActive(staff.getActive());
          Store store = staff.getStore();
          AddressByIdResponseDTO storeAddress = addressServiceClient
              .getAddressById(store.getAddressId())
              .getBody()
              .getData();
          builder.store(StaffByIdResponseDTO.StoreDTO.builder()
              .storeId(store.getStoreId())
              .address(StaffByIdResponseDTO.AddressDTO.builder()
                  .addressId(storeAddress.getAddressId())
                  .address(storeAddress.getAddress())
                  .address2(storeAddress.getAddress2())
                  .district(storeAddress.getDistrict())
                  .postalCode(storeAddress.getPostalCode())
                  .phone(storeAddress.getPhone())
                  .country(StaffByIdResponseDTO.CountryDTO.builder()
                      .countryId(storeAddress.getCountry().getCountryId())
                      .countryName(storeAddress.getCountry().getCountryName())
                      .build())
                  .city(StaffByIdResponseDTO.CityDTO.builder()
                      .cityId(storeAddress.getCity().getCityId())
                      .cityName(storeAddress.getCity().getCityName())
                      .build())
                  .build())
              .build());
          AddressByIdResponseDTO staffAddress = addressServiceClient
              .getAddressById(staff.getAddressId())
              .getBody()
              .getData();
          builder.address(StaffByIdResponseDTO.AddressDTO.builder()
              .addressId(staffAddress.getAddressId())
              .address(staffAddress.getAddress())
              .address2(staffAddress.getAddress2())
              .district(staffAddress.getDistrict())
              .postalCode(staffAddress.getPostalCode())
              .phone(staffAddress.getPhone())
              .country(StaffByIdResponseDTO.CountryDTO.builder()
                  .countryId(staffAddress.getCountry().getCountryId())
                  .countryName(staffAddress.getCountry().getCountryName())
                  .build())
              .city(StaffByIdResponseDTO.CityDTO.builder()
                  .cityId(staffAddress.getCity().getCityId())
                  .cityName(staffAddress.getCity().getCityName())
                  .build())
              .build());
          return builder.build();
        })
        .orElseThrow(() -> new ServiceException(STAFF_NOT_FOUND));
  }

  @Override
  public StaffCreateResponseDTO createStaff(StaffCreateRequestDTO requestDTO)
      throws ServiceException {
    Store store = storeRepository.findById(requestDTO.getStoreId())
        .orElseThrow(() -> new ServiceException(StoreService.STORE_NOT_FOUND));
    AddressByIdResponseDTO staffAddress = addressServiceClient
        .getAddressById(requestDTO.getAddressId())
        .getBody()
        .getData();
    Staff staff = Staff.builder()
        .firstName(requestDTO.getFirstName())
        .lastName(requestDTO.getLastName())
        .addressId(staffAddress.getAddressId())
        .email(requestDTO.getEmail())
        .store(store)
        .active(Boolean.TRUE)
        .username(requestDTO.getUsername())
        .password(passwordEncoder.encode(requestDTO.getPassword()))
        .lastUpdate(ZonedDateTime.now())
        .build();
    staffRepository.save(staff);
    return StaffCreateResponseDTO.builder()
        .storeId(store.getStoreId())
        .active(staff.getActive())
        .lastUpdate(staff.getLastUpdate())
        .build();
  }

  @Override
  public StaffUpdateResponseDTO updateStaff(Integer staffId, StaffUpdateRequestDTO requestDTO)
      throws ServiceException {
    Staff staff = staffRepository.findById(staffId)
        .orElseThrow(() -> new ServiceException(STAFF_NOT_FOUND));
    if (!Objects.equals(staff.getStore().getStoreId(), requestDTO.getStoreId())) {
      Store store = storeRepository.findById(requestDTO.getStoreId())
          .orElseThrow(() -> new ServiceException(StoreService.STORE_NOT_FOUND));
      staff.setStore(store);
    }
    if (!Objects.equals(staff.getAddressId(), requestDTO.getAddressId())) {
      AddressByIdResponseDTO address = addressServiceClient
          .getAddressById(requestDTO.getAddressId())
          .getBody()
          .getData();
      staff.setAddressId(address.getAddressId());
    }
    if (!Objects.equals(staff.getFirstName(), requestDTO.getFirstName())) {
      staff.setFirstName(requestDTO.getFirstName());
    }
    if (!Objects.equals(staff.getLastName(), requestDTO.getLastName())) {
      staff.setLastName(requestDTO.getLastName());
    }
    if (!Objects.equals(staff.getEmail(), requestDTO.getEmail())) {
      staff.setEmail(requestDTO.getEmail());
    }
    if (!Objects.equals(staff.getPassword(), requestDTO.getPassword())) {
      staff.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
    }
    staff.setLastUpdate(ZonedDateTime.now());
    staffRepository.save(staff);
    return StaffUpdateResponseDTO.builder()
        .staffId(staff.getStaffId())
        .active(staff.getActive())
        .lastUpdate(staff.getLastUpdate())
        .build();
  }

  @Override
  public StaffDeleteResponseDTO deleteStaff(Integer staffId) throws ServiceException {
    Staff staff = staffRepository.findById(staffId)
        .orElseThrow(() -> new ServiceException(STAFF_NOT_FOUND));
    staff.setActive(Boolean.FALSE);
    staff.setLastUpdate(ZonedDateTime.now());
    staffRepository.save(staff);
    return StaffDeleteResponseDTO.builder()
        .staffId(staff.getStaffId())
        .active(staff.getActive())
        .lastUpdate(staff.getLastUpdate())
        .build();
  }
}
