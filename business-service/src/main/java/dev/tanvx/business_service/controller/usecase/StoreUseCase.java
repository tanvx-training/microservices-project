package dev.tanvx.business_service.controller.usecase;

import dev.tanvx.business_service.dto.request.store.StoreByIdRequestDTO;
import dev.tanvx.business_service.dto.request.store.StoreCreateRequestDTO;
import dev.tanvx.business_service.dto.request.store.StoreUpdateRequestDTO;
import dev.tanvx.business_service.dto.request.store.StoresRequestDTO;
import dev.tanvx.business_service.dto.response.store.StoreByIdResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreCreateResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreDeleteResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoreUpdateResponseDTO;
import dev.tanvx.business_service.dto.response.store.StoresResponseDTO;
import dev.tanvx.business_service.entity.Store;
import dev.tanvx.business_service.service.StaffService;
import dev.tanvx.business_service.service.StoreService;
import dev.tanvx.common_library.exception.BusinessException;
import dev.tanvx.common_library.exception.ServiceException;
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

  @Transactional(readOnly = true)
  public ApiResponse<StoreByIdResponseDTO> getStoreById(Integer storeId) {
    try {
      StoreByIdRequestDTO requestDTO = StoreByIdRequestDTO.builder()
          .storeId(storeId)
          .build();
      StoreByIdResponseDTO storeByIdResponseDTO = storeService.getStoreById(requestDTO);
      return ApiResponse.<StoreByIdResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(storeByIdResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (StoreService.STORE_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<StoreCreateResponseDTO> createStore(StoreCreateRequestDTO requestDTO) {
    try {
      // Validate request
      validationUtils.validateRequest(requestDTO);

      StoreCreateResponseDTO storeCreateResponseDTO = storeService.createStore(requestDTO);

      return ApiResponse.<StoreCreateResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(storeCreateResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (StaffService.STAFF_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.BAD_REQUEST,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<StoreUpdateResponseDTO> updateStore(Integer storeId,
      StoreUpdateRequestDTO requestDTO) {
    try {
      // Validate request
      validationUtils.validateRequest(requestDTO);

      StoreUpdateResponseDTO storeUpdateResponseDTO = storeService.updateStore(storeId,
          requestDTO);

      return ApiResponse.<StoreUpdateResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(storeUpdateResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (StoreService.STORE_NOT_FOUND.equals(e.getCauseId())
          || StaffService.STAFF_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<StoreDeleteResponseDTO> deleteStore(Integer storeId) {
    try {
      StoreDeleteResponseDTO storeDeleteResponseDTO = storeService.deleteStore(storeId);
      return ApiResponse.<StoreDeleteResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(storeDeleteResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (StoreService.STORE_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }
}
