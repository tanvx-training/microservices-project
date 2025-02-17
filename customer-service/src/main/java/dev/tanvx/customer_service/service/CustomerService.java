package dev.tanvx.customer_service.service;

import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.customer_service.dto.request.customer.CustomerByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomerCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomerUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomersRequestDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerDeleteResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomersResponseDTO;
import org.springframework.data.domain.Page;

public interface CustomerService {

  String CUSTOMER_NOT_FOUND = "CUSTOMER_NOT_FOUND";

  Page<CustomersResponseDTO> getCustomers(CustomersRequestDTO requestDTO);

  CustomersResponseDTO getCustomerById(CustomerByIdRequestDTO requestDTO) throws ServiceException;

  CustomerCreateResponseDTO createCustomer(CustomerCreateRequestDTO requestDTO)
      throws ServiceException;

  CustomerUpdateResponseDTO updateCustomer(Integer customerId, CustomerUpdateRequestDTO requestDTO)
      throws ServiceException;

  CustomerDeleteResponseDTO deleteCustomer(Integer customerId) throws ServiceException;
}
