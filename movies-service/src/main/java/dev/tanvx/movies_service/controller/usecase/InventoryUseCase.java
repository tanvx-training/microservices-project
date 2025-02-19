package dev.tanvx.movies_service.controller.usecase;

import dev.tanvx.common_library.exception.BusinessException;
import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.common_library.model.MessageProperties;
import dev.tanvx.common_library.model.ResponseConstants;
import dev.tanvx.common_library.util.MessageUtils;
import dev.tanvx.common_library.util.ValidationUtils;
import dev.tanvx.movies_service.dto.request.inventory.InventoryByFilmRequestDTO;
import dev.tanvx.movies_service.dto.request.inventory.InventoryByStoreRequestDTO;
import dev.tanvx.movies_service.dto.request.inventory.InventoryRequestDTO;
import dev.tanvx.movies_service.dto.response.inventory.InventoriesByFilmResponseDTO;
import dev.tanvx.movies_service.dto.response.inventory.InventoriesByStoreResponseDTO;
import dev.tanvx.movies_service.dto.response.inventory.InventoriesResponseDTO;
import dev.tanvx.movies_service.dto.response.inventory.InventoryByIdResponseDTO;
import dev.tanvx.movies_service.entity.Inventory;
import dev.tanvx.movies_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryUseCase {

  private final InventoryService inventoryService;

  private final ValidationUtils validationUtils;

  private final MessageUtils messageUtils;

  @Transactional(readOnly = true)
  public ApiResponse<Page<InventoriesResponseDTO>> getInventories(InventoryRequestDTO requestDTO) {
    try {
      // Validate page and size parameter
      validationUtils.validateRequest(requestDTO);

      // Validate sort parameter
      validationUtils.validateSortParam(requestDTO.getSort(), Inventory.class);

      Page<InventoriesResponseDTO> inventoriesResponseDTOPage = inventoryService.getInventories(
          requestDTO);
      return ApiResponse.<Page<InventoriesResponseDTO>>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(inventoriesResponseDTOPage)
          .build();
    } catch (Exception e) {
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional(readOnly = true)
  public ApiResponse<Page<InventoriesByStoreResponseDTO>> getInventoriesByStore(
      InventoryByStoreRequestDTO requestDTO) {
    return null;
  }

  @Transactional(readOnly = true)
  public ApiResponse<Page<InventoriesByFilmResponseDTO>> getInventoriesByFilm(
      InventoryByFilmRequestDTO requestDTO) {
    return null;
  }


  @Transactional(readOnly = true)
  public ApiResponse<InventoryByIdResponseDTO> getInventoryById(Integer inventoryId) {
    return null;
  }
}
