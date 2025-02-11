package dev.tanvx.movies_service.service.impl;

import dev.tanvx.common_library.enums.DeleteStatus;
import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.specification.SearchSpecification;
import dev.tanvx.common_library.specification.request.SearchRequest;
import dev.tanvx.common_library.util.SearchSpecificationRequestUtils;
import dev.tanvx.movies_service.dto.request.category.CategoriesRequestDTO;
import dev.tanvx.movies_service.dto.request.category.CategoryByIdRequestDTO;
import dev.tanvx.movies_service.dto.request.category.CategoryCreateRequestDTO;
import dev.tanvx.movies_service.dto.request.category.CategoryUpdateRequestDTO;
import dev.tanvx.movies_service.dto.response.category.CategoriesResponseDTO;
import dev.tanvx.movies_service.dto.response.category.CategoryByIdResponseDTO;
import dev.tanvx.movies_service.dto.response.category.CategoryCreateResponseDTO;
import dev.tanvx.movies_service.dto.response.category.CategoryDeleteResponseDTO;
import dev.tanvx.movies_service.dto.response.category.CategoryUpdateResponseDTO;
import dev.tanvx.movies_service.entity.Category;
import dev.tanvx.movies_service.repository.CategoryRepository;
import dev.tanvx.movies_service.repository.FilmCategoryRepository;
import dev.tanvx.movies_service.service.CategoryService;
import java.time.ZonedDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  private final FilmCategoryRepository filmCategoryRepository;

  private final SearchSpecificationRequestUtils searchSpecificationRequestUtils;

  @Override
  public void checkCategoryById(Integer categoryId) throws ServiceException {
    categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ServiceException(CATEGORY_NOT_FOUND));
  }

  @Override
  public Page<CategoriesResponseDTO> getCategories(CategoriesRequestDTO requestDTO) {
    SearchRequest searchRequest = SearchRequest.builder()
        .sorts(searchSpecificationRequestUtils.buildSortRequestList(requestDTO.getSort()))
        .page(requestDTO.getPage())
        .size(requestDTO.getSize())
        .build();
    SearchSpecification<Category> specification = new SearchSpecification<>(searchRequest);
    Pageable pageable = SearchSpecification.getPageable(requestDTO.getPage(), requestDTO.getSize());
    return categoryRepository.findAll(specification, pageable)
        .map(category -> CategoriesResponseDTO.builder()
            .categoryId(category.getCategoryId())
            .name(category.getName())
            .build());
  }

  @Override
  public CategoryByIdResponseDTO getCategoryById(CategoryByIdRequestDTO requestDTO)
      throws ServiceException {
    return categoryRepository.findById(requestDTO.getCategoryId())
        .map(category -> CategoryByIdResponseDTO.builder()
            .categoryId(category.getCategoryId())
            .name(category.getName())
            .build())
        .orElseThrow(() -> new ServiceException(CATEGORY_NOT_FOUND));
  }

  @Override
  public CategoryCreateResponseDTO createCategory(CategoryCreateRequestDTO requestDTO)
      throws ServiceException {
    Optional<Category> categoryOptional = categoryRepository.findCategoryByName(
        requestDTO.getName());
    if (categoryOptional.isPresent()) {
      throw new ServiceException(CATEGORY_ALREADY_EXISTS);
    }
    Category category = Category.builder()
        .name(requestDTO.getName())
        .lastUpdate(ZonedDateTime.now())
        .build();
    categoryRepository.save(category);
    return CategoryCreateResponseDTO.builder()
        .categoryId(category.getCategoryId())
        .lastUpdate(category.getLastUpdate())
        .build();
  }

  @Override
  public CategoryUpdateResponseDTO updateCategory(Integer categoryId,
      CategoryUpdateRequestDTO requestDTO) throws ServiceException {
    Category category = categoryRepository.findCategoryByCategoryId(categoryId)
        .orElseThrow(() -> new ServiceException(CATEGORY_NOT_FOUND));

    // Update category properties
    category.setName(requestDTO.getName());
    category.setLastUpdate(ZonedDateTime.now());
    categoryRepository.save(category);
    return CategoryUpdateResponseDTO.builder()
        .categoryId(category.getCategoryId())
        .lastUpdate(category.getLastUpdate())
        .build();
  }

  @Override
  public CategoryDeleteResponseDTO deleteCategory(Integer categoryId) throws ServiceException {
    Category category = categoryRepository.findCategoryByCategoryId(categoryId)
        .orElseThrow(() -> new ServiceException(CATEGORY_NOT_FOUND));
    filmCategoryRepository.deleteAllByIdCategory(category);

    category.setDeleteFlg(DeleteStatus.INACTIVE.isValue());
    category.setLastUpdate(ZonedDateTime.now());
    categoryRepository.save(category);
    return CategoryDeleteResponseDTO.builder()
        .categoryId(category.getCategoryId())
        .deleteFlg(category.isDeleteFlg())
        .build();
  }
}
