package dev.tanvx.customer_service.controller;

import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.customer_service.controller.usecase.StoreUseCase;
import dev.tanvx.customer_service.dto.request.store.StoreCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.store.StoreUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.store.StoresRequestDTO;
import dev.tanvx.customer_service.dto.response.store.StoreByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.store.StoreCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.store.StoreUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.store.StoresResponseDTO;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/v1/stores")
public class StoreController {

  private final StoreUseCase storeUseCase;

  @GetMapping
  public ResponseEntity<ApiResponse<StoresResponseDTO>> getStores(
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "sort", defaultValue = "storeId,asc") String sort) {
    return ResponseEntity.ok(storeUseCase.getStores(StoresRequestDTO.builder()
        .page(page)
        .size(size)
        .sort(sort)
        .build()));
  }

  @GetMapping("/{storeId}/")
  public ResponseEntity<ApiResponse<StoreByIdResponseDTO>> getStoreById(
      @PathVariable(value = "storeId") Integer storeId) {
    return ResponseEntity.ok(storeUseCase.getStoreById(storeId));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<StoreCreateResponseDTO>> createStore(
      @RequestBody StoreCreateRequestDTO requestDTO) {
    return ResponseEntity.ok(storeUseCase.createStore(requestDTO));
  }

  @PutMapping("/{storeId}/")
  public ResponseEntity<ApiResponse<StoreUpdateResponseDTO>> updateStore(
      @PathVariable(value = "storeId") Integer storeId,
      @RequestBody StoreUpdateRequestDTO requestDTO) {
    return ResponseEntity.ok(storeUseCase.updateStore(storeId, requestDTO));
  }

  @DeleteMapping("/{storeId}/")
  public ResponseEntity<ApiResponse<Void>> deleteStore(
      @PathVariable(value = "storeId") Integer storeId) {
    return ResponseEntity.ok(storeUseCase.deleteStore(storeId));
  }
}
