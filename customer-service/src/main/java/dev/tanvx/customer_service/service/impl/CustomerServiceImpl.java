package dev.tanvx.customer_service.service.impl;

import dev.tanvx.common_library.specification.SearchSpecification;
import dev.tanvx.common_library.specification.request.SearchRequest;
import dev.tanvx.common_library.util.SearchSpecificationRequestUtils;
import dev.tanvx.customer_service.client.store.StoreByIdResponseDTO;
import dev.tanvx.customer_service.client.store.StoreServiceClient;
import dev.tanvx.customer_service.dto.request.customer.CustomerByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomerCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomerUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomersRequestDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerDeleteResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomersResponseDTO;
import dev.tanvx.customer_service.entity.Customer;
import dev.tanvx.customer_service.repository.CustomerRepository;
import dev.tanvx.customer_service.service.CustomerService;
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
        .map(this::fromEntity);
  }

  @Override
  public CustomerByIdResponseDTO getCustomerById(CustomerByIdRequestDTO requestDTO) {
    return null;
  }

  @Override
  public CustomerCreateResponseDTO createCustomer(CustomerCreateRequestDTO requestDTO) {
    return null;
  }

  @Override
  public CustomerUpdateResponseDTO updateCustomer(Integer customerId,
      CustomerUpdateRequestDTO requestDTO) {
    return null;
  }

  @Override
  public CustomerDeleteResponseDTO deleteCustomer(Integer customerId) {
    return null;
  }

  private CustomersResponseDTO fromEntity(Customer customer) {
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
