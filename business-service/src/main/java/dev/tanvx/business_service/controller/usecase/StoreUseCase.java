package dev.tanvx.business_service.controller.usecase;

import dev.tanvx.business_service.dto.request.store.StoreCreateRequestDTO;
import dev.tanvx.business_service.dto.request.store.StoreUpdateRequestDTO;
import dev.tanvx.business_service.dto.request.store.StoresRequestDTO;
import dev.tanvx.business_service.dto.response.store.StoreByIdResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreCreateResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreDeleteResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreUpdateResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoresResponseDTO;
import dev.tanvx.business_service.service.StoreService;
import dev.tanvx.common_library.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreUseCase {

  private final StoreService storeService;

  public ApiResponse<Page<StoresResponseDTO>> getStores(StoresRequestDTO requestDTO) {
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

  public ApiResponse<StoreDeleteResponseDTO> deleteStore(Integer storeId) {
    return null;
  }
}
