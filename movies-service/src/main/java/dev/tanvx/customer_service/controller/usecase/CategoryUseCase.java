package dev.tanvx.customer_service.controller.usecase;

import dev.tanvx.common_library.exception.BusinessException;
import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.common_library.model.MessageProperties;
import dev.tanvx.common_library.model.ResponseConstants;
import dev.tanvx.common_library.util.MessageUtils;
import dev.tanvx.common_library.util.ValidationUtils;
import dev.tanvx.customer_service.dto.request.category.CategoriesRequestDTO;
import dev.tanvx.customer_service.dto.request.category.CategoryByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.category.CategoryCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.category.CategoryUpdateRequestDTO;
import dev.tanvx.customer_service.dto.response.category.CategoriesResponseDTO;
import dev.tanvx.customer_service.dto.response.category.CategoryByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.category.CategoryCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.category.CategoryUpdateResponseDTO;
import dev.tanvx.customer_service.entity.Category;
import dev.tanvx.customer_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryUseCase {

  private final CategoryService categoryService;

  private final ValidationUtils validationUtils;

  private final MessageUtils messageUtils;

  @Transactional(readOnly = true)
  public ApiResponse<Page<CategoriesResponseDTO>> getCategories(CategoriesRequestDTO requestDTO) {
    try {
      // Validate page and size parameter
      validationUtils.validateRequest(requestDTO);

      // Validate sort parameter
      validationUtils.validateSortParam(requestDTO.getSort(), Category.class);

      Page<CategoriesResponseDTO> categoriesResponseDTOPage = categoryService.getCategories(
          requestDTO);
      return ApiResponse.<Page<CategoriesResponseDTO>>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(categoriesResponseDTOPage)
          .build();
    } catch (Exception e) {
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional(readOnly = true)
  public ApiResponse<CategoryByIdResponseDTO> getCategoryById(Integer categoryId) {
    try {
      CategoryByIdRequestDTO requestDTO = CategoryByIdRequestDTO.builder()
          .categoryId(categoryId)
          .build();
      CategoryByIdResponseDTO categoryByIdResponseDTO = categoryService.getCategoryById(requestDTO);
      return ApiResponse.<CategoryByIdResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(categoryByIdResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (CategoryService.CATEGORY_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<CategoryCreateResponseDTO> createCategory(
      CategoryCreateRequestDTO requestDTO) {
    try {
      // Validate request
      validationUtils.validateRequest(requestDTO);

      CategoryCreateResponseDTO categoryCreateResponseDTO = categoryService.createCategory(
          requestDTO);

      return ApiResponse.<CategoryCreateResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(categoryCreateResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (CategoryService.CATEGORY_ALREADY_EXISTS.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.BAD_REQUEST,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<CategoryUpdateResponseDTO> updateCategory(Integer categoryId,
      CategoryUpdateRequestDTO requestDTO) {
    try {
      // Validate request
      validationUtils.validateRequest(requestDTO);

      CategoryUpdateResponseDTO categoryUpdateResponseDTO = categoryService.updateCategory(
          categoryId,
          requestDTO);

      return ApiResponse.<CategoryUpdateResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(categoryUpdateResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (CategoryService.CATEGORY_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<Void> deleteCategory(Integer categoryId) {
    try {
      categoryService.deleteCategory(categoryId);
      return ApiResponse.<Void>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .build();
    } catch (ServiceException e) {
      if (CategoryService.CATEGORY_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }
}
