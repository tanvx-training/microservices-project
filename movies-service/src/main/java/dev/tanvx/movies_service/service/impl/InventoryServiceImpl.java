package dev.tanvx.movies_service.service.impl;

import dev.tanvx.common_library.model.client.StoreByIdResponseDTO;
import dev.tanvx.common_library.specification.SearchSpecification;
import dev.tanvx.common_library.specification.request.SearchRequest;
import dev.tanvx.common_library.util.SearchSpecificationRequestUtils;
import dev.tanvx.movies_service.client.store.StoreServiceClient;
import dev.tanvx.movies_service.dto.request.inventory.InventoryByFilmRequestDTO;
import dev.tanvx.movies_service.dto.request.inventory.InventoryByIdRequestDTO;
import dev.tanvx.movies_service.dto.request.inventory.InventoryByStoreRequestDTO;
import dev.tanvx.movies_service.dto.request.inventory.InventoryRequestDTO;
import dev.tanvx.movies_service.dto.response.inventory.InventoriesByFilmResponseDTO;
import dev.tanvx.movies_service.dto.response.inventory.InventoriesByStoreResponseDTO;
import dev.tanvx.movies_service.dto.response.inventory.InventoriesResponseDTO;
import dev.tanvx.movies_service.dto.response.inventory.InventoriesResponseDTO.FilmDTO;
import dev.tanvx.movies_service.dto.response.inventory.InventoriesResponseDTO.FilmDTO.LanguageDTO;
import dev.tanvx.movies_service.dto.response.inventory.InventoryByIdResponseDTO;
import dev.tanvx.movies_service.entity.Film;
import dev.tanvx.movies_service.entity.Inventory;
import dev.tanvx.movies_service.repository.InventoryRepository;
import dev.tanvx.movies_service.service.InventoryService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

  private final InventoryRepository inventoryRepository;

  private final StoreServiceClient storeServiceClient;

  private final SearchSpecificationRequestUtils searchSpecificationRequestUtils;

  @Override
  public Page<InventoriesResponseDTO> getInventories(InventoryRequestDTO requestDTO) {
    SearchRequest searchRequest = SearchRequest.builder()
        .sorts(searchSpecificationRequestUtils.buildSortRequestList(requestDTO.getSort()))
        .page(requestDTO.getPage())
        .size(requestDTO.getSize())
        .build();
    SearchSpecification<Inventory> specification = new SearchSpecification<>(searchRequest);
    Pageable pageable = SearchSpecification.getPageable(requestDTO.getPage(), requestDTO.getSize());
    return inventoryRepository.findAll(specification, pageable)
        .map(inventory -> {
          InventoriesResponseDTO.InventoriesResponseDTOBuilder builder = InventoriesResponseDTO.builder();
          builder.inventoryId(inventory.getInventoryId());
          Film film = inventory.getFilm();
          builder.film(FilmDTO.builder()
              .filmId(film.getFilmId())
              .title(film.getTitle())
              .description(film.getDescription())
              .releaseYear(film.getReleaseYear())
              .language(LanguageDTO.builder()
                  .languageId(film.getLanguage().getLanguageId())
                  .name(film.getLanguage().getName())
                  .build())
              .originalLanguage(Objects.nonNull(film.getOriginalLanguage()) ? LanguageDTO.builder()
                  .languageId(film.getOriginalLanguage().getLanguageId())
                  .name(film.getOriginalLanguage().getName())
                  .build() : null)
              .rentalDuration(film.getRentalDuration())
              .rentalRate(film.getRentalRate())
              .length(film.getLength())
              .replacementCost(film.getReplacementCost())
              .rating(film.getRating())
              .lastUpdate(film.getLastUpdate())
              .build());
          StoreByIdResponseDTO storeByIdResponseDTO = storeServiceClient.getStoreById(
                  inventory.getStoreId())
              .getBody().getData();
          builder.store(InventoriesResponseDTO.StoreDTO.builder()
              .storeId(storeByIdResponseDTO.getStoreId())
              .manager(InventoriesResponseDTO.StoreDTO.StaffDTO.builder()
                  .staffId(storeByIdResponseDTO.getManager().getStaffId())
                  .firstName(storeByIdResponseDTO.getManager().getFirstName())
                  .lastName(storeByIdResponseDTO.getManager().getLastName())
                  .address(InventoriesResponseDTO.StoreDTO.AddressDTO.builder()
                      .addressId(storeByIdResponseDTO.getManager().getAddress().getAddressId())
                      .address(storeByIdResponseDTO.getManager().getAddress().getAddress())
                      .address2(storeByIdResponseDTO.getManager().getAddress().getAddress2())
                      .district(storeByIdResponseDTO.getManager().getAddress().getDistrict())
                      .postalCode(storeByIdResponseDTO.getManager().getAddress().getPostalCode())
                      .phone(storeByIdResponseDTO.getManager().getAddress().getPhone())
                      .country(InventoriesResponseDTO.StoreDTO.CountryDTO.builder()
                          .countryId(storeByIdResponseDTO.getManager().getAddress().getCountry()
                              .getCountryId())
                          .countryName(storeByIdResponseDTO.getManager().getAddress().getCountry()
                              .getCountryName())
                          .build())
                      .city(InventoriesResponseDTO.StoreDTO.CityDTO.builder()
                          .cityId(
                              storeByIdResponseDTO.getManager().getAddress().getCity().getCityId())
                          .cityName(storeByIdResponseDTO.getManager().getAddress().getCity()
                              .getCityName())
                          .build())
                      .build())
                  .build())
              .address(InventoriesResponseDTO.StoreDTO.AddressDTO.builder()
                  .addressId(storeByIdResponseDTO.getAddress().getAddressId())
                  .address(storeByIdResponseDTO.getAddress().getAddress())
                  .address2(storeByIdResponseDTO.getAddress().getAddress2())
                  .district(storeByIdResponseDTO.getAddress().getDistrict())
                  .postalCode(storeByIdResponseDTO.getAddress().getPostalCode())
                  .phone(storeByIdResponseDTO.getAddress().getPhone())
                  .country(InventoriesResponseDTO.StoreDTO.CountryDTO.builder()
                      .countryId(storeByIdResponseDTO.getAddress().getCountry().getCountryId())
                      .countryName(storeByIdResponseDTO.getAddress().getCountry().getCountryName())
                      .build())
                  .city(InventoriesResponseDTO.StoreDTO.CityDTO.builder()
                      .cityId(storeByIdResponseDTO.getAddress().getCity().getCityId())
                      .cityName(storeByIdResponseDTO.getAddress().getCity().getCityName())
                      .build())
                  .build())
              .build());
          return builder.build();
        });
  }

  @Override
  public Page<InventoriesByStoreResponseDTO> getInventoriesByStore(
      InventoryByStoreRequestDTO requestDTO) {
    return null;
  }

  @Override
  public Page<InventoriesByFilmResponseDTO> getInventoriesByFilm(
      InventoryByFilmRequestDTO requestDTO) {
    return null;
  }

  @Override
  public InventoryByIdResponseDTO getInventoryById(InventoryByIdRequestDTO requestDTO) {
    return null;
  }
}
