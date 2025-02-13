package dev.tanvx.customer_service.service;

import dev.tanvx.customer_service.dto.request.customer.CustomerByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomerCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomerUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomersRequestDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerDeleteResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomersResponseDTO;
import org.springframework.data.domain.Page;

public interface CustomerService {

  Page<CustomersResponseDTO> getCustomers(CustomersRequestDTO requestDTO);

  CustomerByIdResponseDTO getCustomerById(CustomerByIdRequestDTO requestDTO);

  CustomerCreateResponseDTO createCustomer(CustomerCreateRequestDTO requestDTO);

  CustomerUpdateResponseDTO updateCustomer(Integer customerId, CustomerUpdateRequestDTO requestDTO);

  CustomerDeleteResponseDTO deleteCustomer(Integer customerId);
}
