package dev.tanvx.movies_service.controller;

import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.movies_service.controller.usecase.InventoryUseCase;
import dev.tanvx.movies_service.dto.request.inventory.InventoryByFilmRequestDTO;
import dev.tanvx.movies_service.dto.request.inventory.InventoryByStoreRequestDTO;
import dev.tanvx.movies_service.dto.request.inventory.InventoryRequestDTO;
import dev.tanvx.movies_service.dto.response.inventory.InventoriesByFilmResponseDTO;
import dev.tanvx.movies_service.dto.response.inventory.InventoriesByStoreResponseDTO;
import dev.tanvx.movies_service.dto.response.inventory.InventoriesResponseDTO;
import dev.tanvx.movies_service.dto.response.inventory.InventoryByIdResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventories")
public class InventoryController {

  private final InventoryUseCase inventoryUseCase;

  @GetMapping
  public ResponseEntity<ApiResponse<Page<InventoriesResponseDTO>>> getInventories(
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "sort", defaultValue = "inventoryId,asc") String sort) {
    return ResponseEntity.ok(inventoryUseCase.getInventories(
        InventoryRequestDTO.builder()
            .page(page)
            .size(size)
            .sort(sort)
            .build()
    ));
  }

  @GetMapping("/{storeId}/store")
  public ResponseEntity<ApiResponse<Page<InventoriesByStoreResponseDTO>>> getInventoriesByStore(
      @PathVariable("storeId") Integer storeId,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "sort", defaultValue = "inventoryId,asc") String sort) {
    return ResponseEntity.ok(inventoryUseCase.getInventoriesByStore(
        InventoryByStoreRequestDTO.builder()
            .storeId(storeId)
            .page(page)
            .size(size)
            .sort(sort)
            .build()
    ));
  }

  @GetMapping("/{filmId}/film")
  public ResponseEntity<ApiResponse<Page<InventoriesByFilmResponseDTO>>> getInventoriesByFilm(
      @PathVariable("filmId") Integer filmId,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "sort", defaultValue = "inventoryId,asc") String sort) {
    return ResponseEntity.ok(inventoryUseCase.getInventoriesByFilm(
        InventoryByFilmRequestDTO.builder()
            .filmId(filmId)
            .page(page)
            .size(size)
            .sort(sort)
            .build()
    ));
  }

  @GetMapping("/{inventoryId}/")
  public ResponseEntity<ApiResponse<InventoryByIdResponseDTO>> getInventoryById(
      @PathVariable("inventoryId") Integer inventoryId) {
    return ResponseEntity.ok(inventoryUseCase.getInventoryById(inventoryId));
  }
}
