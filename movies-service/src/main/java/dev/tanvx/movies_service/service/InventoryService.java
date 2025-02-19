package dev.tanvx.movies_service.service;

import dev.tanvx.movies_service.dto.request.inventory.InventoryByFilmRequestDTO;
import dev.tanvx.movies_service.dto.request.inventory.InventoryByIdRequestDTO;
import dev.tanvx.movies_service.dto.request.inventory.InventoryByStoreRequestDTO;
import dev.tanvx.movies_service.dto.request.inventory.InventoryRequestDTO;
import dev.tanvx.movies_service.dto.response.inventory.InventoriesByFilmResponseDTO;
import dev.tanvx.movies_service.dto.response.inventory.InventoriesByStoreResponseDTO;
import dev.tanvx.movies_service.dto.response.inventory.InventoriesResponseDTO;
import dev.tanvx.movies_service.dto.response.inventory.InventoryByIdResponseDTO;
import org.springframework.data.domain.Page;

public interface InventoryService {

  String INVENTORY_NOT_FOUND = "INVENTORY_NOT_FOUND";

  Page<InventoriesResponseDTO> getInventories(InventoryRequestDTO requestDTO);

  Page<InventoriesByStoreResponseDTO> getInventoriesByStore(InventoryByStoreRequestDTO requestDTO);

  Page<InventoriesByFilmResponseDTO> getInventoriesByFilm(InventoryByFilmRequestDTO requestDTO);

  InventoryByIdResponseDTO getInventoryById(InventoryByIdRequestDTO requestDTO);
}
