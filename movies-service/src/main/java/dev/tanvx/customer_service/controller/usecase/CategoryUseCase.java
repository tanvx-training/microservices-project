package dev.tanvx.customer_service.controller.usecase;

import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.customer_service.dto.request.category.CategoriesRequestDTO;
import dev.tanvx.customer_service.dto.request.category.CategoryCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.category.CategoryUpdateRequestDTO;
import dev.tanvx.customer_service.dto.response.category.CategoriesResponseDTO;
import dev.tanvx.customer_service.dto.response.category.CategoryByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.category.CategoryCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.category.CategoryUpdateResponseDTO;
import org.springframework.data.domain.Page;

public class CategoryUseCase {

  public ApiResponse<Page<CategoriesResponseDTO>> getCategories(CategoriesRequestDTO build) {
    return null;
  }

  public ApiResponse<CategoryByIdResponseDTO> getCategoryById(Integer categoryId) {
    return null;
  }

  public ApiResponse<CategoryCreateResponseDTO> createCategory(
      CategoryCreateRequestDTO requestDTO) {
    return null;
  }

  public ApiResponse<CategoryUpdateResponseDTO> updateCategory(Integer categoryId,
      CategoryUpdateRequestDTO requestDTO) {
    return null;
  }

  public ApiResponse<Void> deleteCategory(Integer categoryId) {
    return null;
  }
}
