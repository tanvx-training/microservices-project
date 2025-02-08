package dev.tanvx.customer_service.controller.usecase;

import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.customer_service.dto.request.store.StoreCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.store.StoreUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.store.StoresRequestDTO;
import dev.tanvx.customer_service.dto.response.store.StoreByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.store.StoreCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.store.StoreUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.store.StoresResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreUseCase {

  public ApiResponse<StoresResponseDTO> getStores(StoresRequestDTO build) {
    return null;
  }

  public ApiResponse<StoreByIdResponseDTO> getStoreById(Integer storeId) {
    return null;
  }

  public ApiResponse<StoreCreateResponseDTO> createStore(StoreCreateRequestDTO requestDTO) {
    return null;
  }

  public ApiResponse<StoreUpdateResponseDTO> updateStore(Integer storeId, StoreUpdateRequestDTO requestDTO) {
    return null;
  }

  public ApiResponse<Void> deleteStore(Integer storeId) {
    return null;
  }
}
