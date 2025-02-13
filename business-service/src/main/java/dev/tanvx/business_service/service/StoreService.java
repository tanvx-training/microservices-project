package dev.tanvx.business_service.service;

import dev.tanvx.business_service.dto.request.store.StoreByIdRequestDTO;
import dev.tanvx.business_service.dto.request.store.StoreCreateRequestDTO;
import dev.tanvx.business_service.dto.request.store.StoreUpdateRequestDTO;
import dev.tanvx.business_service.dto.request.store.StoresRequestDTO;
import dev.tanvx.business_service.dto.response.store.StoreByIdResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreCreateResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreUpdateResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreDeleteResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoresResponseDTO;
import dev.tanvx.common_library.exception.ServiceException;
import org.springframework.data.domain.Page;

public interface StoreService {

  String STORE_NOT_FOUND = "STORE_NOT_FOUND";

  Page<StoresResponseDTO> getStores(StoresRequestDTO requestDTO);

  StoreByIdResponseDTO getStoreById(StoreByIdRequestDTO requestDTO) throws ServiceException;

  StoreCreateResponseDTO createStore(StoreCreateRequestDTO requestDTO) throws ServiceException;

  StoreUpdateResponseDTO updateStore(Integer storeId, StoreUpdateRequestDTO requestDTO)
      throws ServiceException;

  StoreDeleteResponseDTO deleteStore(Integer storeId) throws ServiceException;
}
