package dev.tanvx.customer_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

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
  public ResponseEntity<ApiResponse<CustomerByIdResponseDTO>> getCustomerById(
      @PathVariable("customerId") Integer customerId) {
    return ResponseEntity.ok(customerUseCase.getCustomerById(customerId));
  }

  @GetMapping("/{addressId}/addresses")
  public ResponseEntity<ApiResponse<Page<AddressByCustomerResponseDTO>>> getAddressesByCustomer(
      @PathVariable("customerId") Integer customerId,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "sort", defaultValue = "addressId,asc") String sort) {

  }
}
