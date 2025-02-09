package dev.tanvx.customer_service.controller;

import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.customer_service.controller.usecase.CategoryUseCase;
import dev.tanvx.customer_service.dto.request.category.CategoriesRequestDTO;
import dev.tanvx.customer_service.dto.request.category.CategoryCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.category.CategoryUpdateRequestDTO;
import dev.tanvx.customer_service.dto.response.category.CategoriesResponseDTO;
import dev.tanvx.customer_service.dto.response.category.CategoryByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.category.CategoryCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.category.CategoryDeleteResponseDTO;
import dev.tanvx.customer_service.dto.response.category.CategoryUpdateResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
@RequestMapping("/api/v1/categories")
public class CategoryController {

  private final CategoryUseCase categoryUseCase;

  @GetMapping
  public ResponseEntity<ApiResponse<Page<CategoriesResponseDTO>>> getCategories(
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "sort", defaultValue = "categoryId,asc") String sort) {

    return ResponseEntity.ok(categoryUseCase.getCategories(
        CategoriesRequestDTO.builder()
            .page(page)
            .size(size)
            .sort(sort)
            .build()
    ));
  }

  @GetMapping("/{categoryId}/")
  public ResponseEntity<ApiResponse<CategoryByIdResponseDTO>> getCategoryById(
      @PathVariable("categoryId") Integer categoryId) {

    return ResponseEntity.ok(categoryUseCase.getCategoryById(categoryId));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<CategoryCreateResponseDTO>> createCategory(
      @RequestBody CategoryCreateRequestDTO requestDTO) {

    return ResponseEntity.ok(categoryUseCase.createCategory(requestDTO));
  }

  @PutMapping("/{categoryId}/")
  public ResponseEntity<ApiResponse<CategoryUpdateResponseDTO>> updateCategory(
      @PathVariable("categoryId") Integer categoryId,
      @RequestBody CategoryUpdateRequestDTO requestDTO) {

    return ResponseEntity.ok(categoryUseCase.updateCategory(categoryId, requestDTO));
  }

  @DeleteMapping("/{categoryId}/")
  public ResponseEntity<ApiResponse<CategoryDeleteResponseDTO>> deleteCategory(
      @PathVariable("categoryId") Integer categoryId) {

    return ResponseEntity.ok(categoryUseCase.deleteCategory(categoryId));
  }
}
