package dev.tanvx.business_service.controller.usecase;

import dev.tanvx.business_service.dto.request.store.StoreCreateRequestDTO;
import dev.tanvx.business_service.dto.request.store.StoreUpdateRequestDTO;
import dev.tanvx.business_service.dto.request.store.StoresRequestDTO;
import dev.tanvx.business_service.dto.response.store.StoreByIdResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreCreateResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreDeleteResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreUpdateResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoresResponseDTO;
import dev.tanvx.business_service.entity.Store;
import dev.tanvx.business_service.service.StoreService;
import dev.tanvx.common_library.exception.BusinessException;
import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.common_library.model.MessageProperties;
import dev.tanvx.common_library.model.ResponseConstants;
import dev.tanvx.common_library.util.MessageUtils;
import dev.tanvx.common_library.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreUseCase {

  private final StoreService storeService;

  private final ValidationUtils validationUtils;

  private final MessageUtils messageUtils;

  @Transactional(readOnly = true)
  public ApiResponse<Page<StoresResponseDTO>> getStores(StoresRequestDTO requestDTO) {
    try {
      // Validate page and size parameter
      validationUtils.validateRequest(requestDTO);

      // Validate sort parameter
      validationUtils.validateSortParam(requestDTO.getSort(), Store.class);

      Page<StoresResponseDTO> storesResponseDTOPage = storeService
          .getStores(requestDTO);

      return ApiResponse.<Page<StoresResponseDTO>>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(storesResponseDTOPage)
          .build();
    } catch (Exception e) {
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
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
