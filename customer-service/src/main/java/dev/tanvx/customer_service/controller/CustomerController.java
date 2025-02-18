package dev.tanvx.customer_service.controller;

import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.customer_service.controller.usecase.CustomerUseCase;
import dev.tanvx.customer_service.dto.request.customer.CustomerCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomerUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomersByAddressRequestDTO;
import dev.tanvx.customer_service.dto.request.customer.CustomersRequestDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerDeleteResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomerUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomersByAddressResponseDTO;
import dev.tanvx.customer_service.dto.response.customer.CustomersResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

  private final CustomerUseCase customerUseCase;

  @GetMapping
  public ResponseEntity<ApiResponse<Page<CustomersResponseDTO>>> getCustomers(
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "sort", defaultValue = "customerId,asc") String sort) {

    return ResponseEntity.ok(customerUseCase.getCustomers(
        CustomersRequestDTO.builder()
            .page(page)
            .size(size)
            .sort(sort)
            .build()
    ));
  }

  @GetMapping("/{customerId}/")
  public ResponseEntity<ApiResponse<CustomersResponseDTO>> getCustomerById(
      @PathVariable("customerId") Integer customerId) {
    return ResponseEntity.ok(customerUseCase.getCustomerById(customerId));
  }

  @GetMapping("/{addressId}/addresses")
  public ResponseEntity<ApiResponse<Page<CustomersByAddressResponseDTO>>> getCustomersByAddress(
      @PathVariable("addressId") Integer addressId,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "sort", defaultValue = "addressId,asc") String sort) {

    return ResponseEntity.ok(customerUseCase.getCustomersByAddress(
        CustomersByAddressRequestDTO.builder()
            .addressId(addressId)
            .page(page)
            .size(size)
            .sort(sort)
            .build()
    ));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<CustomerCreateResponseDTO>> createCustomer(
      @RequestBody CustomerCreateRequestDTO responseDTO) {
    return ResponseEntity.ok(customerUseCase.createCustomer(responseDTO));
  }

  @PutMapping("/{customerId}/")
  public ResponseEntity<ApiResponse<CustomerUpdateResponseDTO>> updateCustomer(
      @PathVariable("customerId") Integer customerId,
      @RequestBody CustomerUpdateRequestDTO responseDTO) {
    return ResponseEntity.ok(customerUseCase.updateCustomer(customerId, responseDTO));
  }

  @DeleteMapping("/{customerId}/")
  public ResponseEntity<ApiResponse<CustomerDeleteResponseDTO>> deleteCustomer(
      @PathVariable("customerId") Integer customerId) {
    return ResponseEntity.ok(customerUseCase.deleteCustomer(customerId));
  }
}
