package dev.tanvx.business_service.client.address;

import dev.tanvx.common_library.config.FeignClientConfiguration;
import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.common_library.model.client.AddressByIdResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", configuration = {FeignClientConfiguration.class})
public interface AddressServiceClient {

  @GetMapping("/api/v1/addresses/{addressId}/")
  ResponseEntity<ApiResponse<AddressByIdResponseDTO>> getAddressById(
      @PathVariable("addressId") Integer addressId);
}
