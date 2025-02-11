package dev.tanvx.business_service.service;

import dev.tanvx.business_service.dto.request.store.StoreCreateRequestDTO;
import dev.tanvx.business_service.dto.request.store.StoreUpdateRequestDTO;
import dev.tanvx.business_service.dto.request.store.StoresRequestDTO;
import dev.tanvx.business_service.dto.response.store.StoreByIdResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreCreateResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreUpdateResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreDeleteResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoresResponseDTO;
import org.springframework.data.domain.Page;

public interface StoreService {

  Page<StoresResponseDTO> getStores(StoresRequestDTO requestDTO);

  StoreByIdResponseDTO getStoreById(Integer storeId);

  StoreCreateResponseDTO createStore(StoreCreateRequestDTO requestDTO);

  StoreUpdateResponseDTO updateStore(Integer storeId, StoreUpdateRequestDTO requestDTO);

  StoreDeleteResponseDTO deleteStore(Integer storeId);
}
