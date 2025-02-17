package dev.tanvx.customer_service.service.impl;

import dev.tanvx.common_library.enums.CustomerStatus;
import dev.tanvx.common_library.enums.DeleteStatus;
import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.specification.SearchSpecification;
import dev.tanvx.common_library.specification.request.SearchRequest;
import dev.tanvx.common_library.util.SearchSpecificationRequestUtils;
import dev.tanvx.customer_service.client.store.StoreByIdResponseDTO;
import dev.tanvx.customer_service.client.store.StoreServiceClient;
import dev.tanvx.customer_service.dto.request.customer.CustomerByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomerCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomerUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomersRequestDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerDeleteResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomersResponseDTO;
import dev.tanvx.customer_service.entity.Address;
import dev.tanvx.customer_service.entity.Customer;
import dev.tanvx.customer_service.repository.AddressRepository;
import dev.tanvx.customer_service.repository.CustomerRepository;
import dev.tanvx.customer_service.service.AddressService;
import dev.tanvx.customer_service.service.CustomerService;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  private final StoreServiceClient storeServiceClient;

  private final AddressRepository addressRepository;

  private final SearchSpecificationRequestUtils searchSpecificationRequestUtils;

  @Override
  public Page<CustomersResponseDTO> getCustomers(CustomersRequestDTO requestDTO) {
    SearchRequest searchRequest = SearchRequest.builder()
        .sorts(searchSpecificationRequestUtils.buildSortRequestList(requestDTO.getSort()))
        .page(requestDTO.getPage())
        .size(requestDTO.getSize())
        .build();
    SearchSpecification<Customer> specification = new SearchSpecification<>(searchRequest);
    Pageable pageable = SearchSpecification.getPageable(requestDTO.getPage(), requestDTO.getSize());
    return customerRepository.findAll(specification, pageable)
        .map(this::customersResponseDTOFromEntity);
  }

  @Override
  public CustomersResponseDTO getCustomerById(CustomerByIdRequestDTO requestDTO)
      throws ServiceException {
    return customerRepository.findById(requestDTO.getCustomerId())
        .map(this::customersResponseDTOFromEntity)
        .orElseThrow(() -> new ServiceException(CUSTOMER_NOT_FOUND));
  }

  @Override
  public CustomerCreateResponseDTO createCustomer(CustomerCreateRequestDTO requestDTO)
      throws ServiceException {
    Address address = addressRepository.findById(requestDTO.getAddressId())
        .orElseThrow(() -> new ServiceException(AddressService.ADDRESS_NOT_FOUND));
    StoreByIdResponseDTO storeByIdResponseDTO = storeServiceClient.getStoreById(
            requestDTO.getStoreId())
        .getBody()
        .getData();
    Customer customer = Customer.builder()
        .storeId(storeByIdResponseDTO.getStoreId())
        .firstName(requestDTO.getFirstName())
        .lastName(requestDTO.getLastName())
        .email(requestDTO.getEmail())
        .address(address)
        .activeBool(DeleteStatus.ACTIVE.isValue())
        .createDate(LocalDate.now())
        .lastUpdate(ZonedDateTime.now())
        .activeInt(CustomerStatus.ACTIVE.getValue())
        .build();
    customerRepository.save(customer);
    return CustomerCreateResponseDTO.builder()
        .customerId(customer.getCustomerId())
        .isActive(customer.getActiveBool())
        .customerStatus(customer.getActiveInt())
        .createDate(customer.getCreateDate())
        .build();
  }

  @Override
  public CustomerUpdateResponseDTO updateCustomer(Integer customerId,
      CustomerUpdateRequestDTO requestDTO) throws ServiceException {
    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new ServiceException(CUSTOMER_NOT_FOUND));
    if (!Objects.equals(requestDTO.getStoreId(), customer.getStoreId())) {
      StoreByIdResponseDTO storeByIdResponseDTO = storeServiceClient.getStoreById(
              requestDTO.getStoreId())
          .getBody()
          .getData();
      customer.setStoreId(storeByIdResponseDTO.getStoreId());
    }
    if (!Objects.equals(requestDTO.getFirstName(), customer.getFirstName())) {
      customer.setFirstName(requestDTO.getFirstName());
    }
    if (!Objects.equals(requestDTO.getLastName(), customer.getLastName())) {
      customer.setLastName(requestDTO.getLastName());
    }
    if (!Objects.equals(requestDTO.getEmail(), customer.getEmail())) {
      customer.setEmail(requestDTO.getEmail());
    }
    if (!Objects.equals(requestDTO.getAddressId(), customer.getAddress().getAddressId())) {
      Address address = addressRepository.findById(requestDTO.getAddressId())
          .orElseThrow(() -> new ServiceException(AddressService.ADDRESS_NOT_FOUND));
      customer.setAddress(address);
    }
    if (!Objects.equals(requestDTO.getCustomerStatus(), customer.getActiveInt())) {
      CustomerStatus customerStatus = CustomerStatus.fromInt(requestDTO.getCustomerStatus());
      customer.setActiveInt(customerStatus.getValue());
    }
    customer.setLastUpdate(ZonedDateTime.now());
    customerRepository.save(customer);
    return CustomerUpdateResponseDTO.builder()
        .customerId(customer.getCustomerId())
        .isActive(customer.getActiveBool())
        .customerStatus(customer.getActiveInt())
        .lastUpdate(customer.getLastUpdate())
        .build();
  }

  @Override
  public CustomerDeleteResponseDTO deleteCustomer(Integer customerId) throws ServiceException {
    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new ServiceException(CUSTOMER_NOT_FOUND));
    customer.setActiveBool(DeleteStatus.INACTIVE.isValue());
    customer.setActiveInt(CustomerStatus.INACTIVE.getValue());
    customer.setLastUpdate(ZonedDateTime.now());
    customerRepository.save(customer);
    return CustomerDeleteResponseDTO.builder()
        .customerId(customer.getCustomerId())
        .isActive(customer.getActiveBool())
        .customerStatus(customer.getActiveInt())
        .build();
  }

  private CustomersResponseDTO customersResponseDTOFromEntity(Customer customer) {
    CustomersResponseDTO.CustomersResponseDTOBuilder builder = CustomersResponseDTO.builder();
    builder.customerId(customer.getCustomerId());
    builder.firstName(customer.getFirstName());
    builder.lastName(customer.getLastName());
    builder.email(customer.getEmail());
    builder.isActive(customer.getActiveBool());
    builder.statusCode(customer.getActiveInt());

    StoreByIdResponseDTO storeByIdResponseDTO = storeServiceClient.getStoreById(
            customer.getStoreId())
        .getBody()
        .getData();
    builder.store(mapStore(storeByIdResponseDTO));
    builder.address(mapAddress(storeByIdResponseDTO.getAddress()));
    return builder.build();
  }

  private CustomersResponseDTO.StoreDTO mapStore(StoreByIdResponseDTO storeData) {
    if (storeData == null) {
      return null;
    }

    return CustomersResponseDTO.StoreDTO.builder()
        .storeId(storeData.getStoreId())
        .manager(mapStaff(storeData.getManager()))
        .address(mapAddress(storeData.getManager().getAddress()))
        .build();
  }

  private CustomersResponseDTO.StaffDTO mapStaff(StoreByIdResponseDTO.StaffDTO manager) {
    if (manager == null) {
      return null;
    }

    return CustomersResponseDTO.StaffDTO.builder()
        .staffId(manager.getStaffId())
        .firstName(manager.getFirstName())
        .lastName(manager.getLastName())
        .email(manager.getEmail())
        .address(mapAddress(manager.getAddress()))
        .build();
  }

  private CustomersResponseDTO.AddressDTO mapAddress(StoreByIdResponseDTO.AddressDTO address) {
    if (Objects.isNull(address)) {
      return null;
    }
    return CustomersResponseDTO.AddressDTO.builder()
        .addressId(address.getAddressId())
        .address(address.getAddress())
        .address2(address.getAddress2())
        .district(address.getDistrict())
        .postalCode(address.getPostalCode())
        .phone(address.getPhone())
        .country(mapCountry(address.getCountry()))
        .city(mapCity(address.getCity()))
        .build();
  }

  private CustomersResponseDTO.CountryDTO mapCountry(StoreByIdResponseDTO.CountryDTO country) {
    if (Objects.isNull(country)) {
      return null;
    }
    return CustomersResponseDTO.CountryDTO.builder()
        .countryId(country.getCountryId())
        .countryName(country.getCountryName())
        .build();
  }

  private CustomersResponseDTO.CityDTO mapCity(StoreByIdResponseDTO.CityDTO city) {
    if (Objects.isNull(city)) {
      return null;
    }
    return CustomersResponseDTO.CityDTO.builder()
        .cityId(city.getCityId())
        .cityName(city.getCityName())
        .build();
  }
}
