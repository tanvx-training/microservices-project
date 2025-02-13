package dev.tanvx.customer_service.controller.usecase;

import dev.tanvx.common_library.exception.BusinessException;
import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.common_library.model.MessageProperties;
import dev.tanvx.common_library.model.ResponseConstants;
import dev.tanvx.common_library.util.MessageUtils;
import dev.tanvx.common_library.util.ValidationUtils;
import dev.tanvx.customer_service.dto.request.customer.CustomerCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomerUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomersByAddressRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomersRequestDTO;
import dev.tanvx.customer_service.dto.response.country.CountriesResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerDeleteResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomersByAddressResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomersResponseDTO;
import dev.tanvx.customer_service.entity.Country;
import dev.tanvx.customer_service.entity.Customer;
import dev.tanvx.customer_service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerUseCase {

  private final CustomerService customerService;

  private final ValidationUtils validationUtils;

  private final MessageUtils messageUtils;

  @Transactional(readOnly = true)
  public ApiResponse<Page<CustomersResponseDTO>> getCustomers(CustomersRequestDTO requestDTO) {
    try {
      // Validate page and size parameter
      validationUtils.validateRequest(requestDTO);

      // Validate sort parameter
      validationUtils.validateSortParam(requestDTO.getSort(), Customer.class);

      Page<CustomersResponseDTO> customersResponseDTOPage = customerService.getCustomers(
          requestDTO);
      return ApiResponse.<Page<CustomersResponseDTO>>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(customersResponseDTOPage)
          .build();
    } catch (Exception e) {
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
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

  public ApiResponse<CustomerDeleteResponseDTO> deleteCustomer(Integer customerId) {
    return null;
  }
}
