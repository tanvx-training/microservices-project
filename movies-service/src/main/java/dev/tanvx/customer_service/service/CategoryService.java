package dev.tanvx.customer_service.service;

import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.customer_service.dto.request.category.CategoriesRequestDTO;
import dev.tanvx.customer_service.dto.request.category.CategoryByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.category.CategoryCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.category.CategoryUpdateRequestDTO;
import dev.tanvx.customer_service.dto.response.category.CategoriesResponseDTO;
import dev.tanvx.customer_service.dto.response.category.CategoryByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.category.CategoryCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.category.CategoryUpdateResponseDTO;
import org.springframework.data.domain.Page;

public interface CategoryService {

  String CATEGORY_NOT_FOUND = "CATEGORY_NOT_FOUND";

  String CATEGORY_ALREADY_EXISTS = "CATEGORY_ALREADY_EXISTS";

  void checkCategoryById(Integer categoryId) throws ServiceException;

  Page<CategoriesResponseDTO> getCategories(CategoriesRequestDTO requestDTO);

  CategoryByIdResponseDTO getCategoryById(CategoryByIdRequestDTO requestDTO)
      throws ServiceException;

  CategoryCreateResponseDTO createCategory(CategoryCreateRequestDTO requestDTO)
      throws ServiceException;

  CategoryUpdateResponseDTO updateCategory(Integer categoryId, CategoryUpdateRequestDTO requestDTO)
      throws ServiceException;

  void deleteCategory(Integer categoryId) throws ServiceException;
}
