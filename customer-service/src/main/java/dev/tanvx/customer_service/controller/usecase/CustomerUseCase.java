package dev.tanvx.customer_service.controller.usecase;

import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.customer_service.dto.request.customer.CustomerCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomerUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomersByAddressRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomersRequestDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomersByAddressResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomersResponseDTO;
import org.springframework.data.domain.Page;

public class CustomerUseCase {

  public ApiResponse<Page<CustomersResponseDTO>> getCustomers(CustomersRequestDTO build) {
    return null;
  }

  public ApiResponse<CustomerByIdResponseDTO> getCustomerById(Integer customerId) {
    return null;
  }

  public ApiResponse<Page<CustomersByAddressResponseDTO>> getCustomersByAddress(
      CustomersByAddressRequestDTO build) {
    return null;
  }

  public ApiResponse<CustomerCreateResponseDTO> createCustomer(
      CustomerCreateRequestDTO responseDTO) {
    return null;
  }

  public ApiResponse<CustomerUpdateResponseDTO> updateCustomer(Integer customerId,
      CustomerUpdateRequestDTO responseDTO) {
    return null;
  }

  public ApiResponse<Void> deleteCustomer(Integer customerId) {
    return null;
  }
}
