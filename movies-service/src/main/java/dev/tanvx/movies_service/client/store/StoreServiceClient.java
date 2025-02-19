package dev.tanvx.movies_service.client.store;

import dev.tanvx.common_library.config.FeignClientConfiguration;
import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.common_library.model.client.StoreByIdResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "business-service", configuration = {FeignClientConfiguration.class})
public interface StoreServiceClient {

  @GetMapping("/api/v1/stores/{storeId}/")
  ResponseEntity<ApiResponse<StoreByIdResponseDTO>> getStoreById(
      @PathVariable("storeId") Integer storeId);
}
