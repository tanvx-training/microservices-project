package dev.tanvx.customer_service.service.impl;

import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.specification.SearchSpecification;
import dev.tanvx.common_library.specification.request.SearchRequest;
import dev.tanvx.common_library.util.SearchSpecificationRequestUtils;
import dev.tanvx.customer_service.dto.request.film.FilmByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.film.FilmCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.film.FilmUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.film.FilmsRequestDTO;
import dev.tanvx.customer_service.dto.response.film.FilmByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmsResponseDTO;
import dev.tanvx.customer_service.entity.Film;
import dev.tanvx.customer_service.repository.FilmRepository;
import dev.tanvx.customer_service.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

  private final FilmRepository filmRepository;

  private final SearchSpecificationRequestUtils searchSpecificationRequestUtils;

  @Override
  public void checkFilmById(Integer id) throws ServiceException {
    filmRepository.findById(id)
        .orElseThrow(() -> new ServiceException(FILM_NOT_FOUND));
  }

  @Override
  public Page<FilmsResponseDTO> getFilms(FilmsRequestDTO requestDTO) {
    SearchRequest searchRequest = SearchRequest.builder()
        .sorts(searchSpecificationRequestUtils.buildSortRequestList(requestDTO.getSort()))
        .page(requestDTO.getPage())
        .size(requestDTO.getSize())
        .build();
    SearchSpecification<Film> specification = new SearchSpecification<>(searchRequest);
    Pageable pageable = SearchSpecification.getPageable(requestDTO.getPage(), requestDTO.getSize());
    return filmRepository.findAll(specification, pageable)
        .map(film -> FilmsResponseDTO.builder()
            .filmId(film.getFilmId())
            .title(film.getTitle())
            .description(film.getDescription())
            .releaseYear(film.getReleaseYear())
            .language(film.getLanguage())
            .rentalDuration(film.getRentalDuration())
            .rentalRate(film.getRentalRate())
            .length(film.getLength())
            .replacementCost(film.getReplacementCost())
            .rating(film.getRating())
            .specialFeatures(film.getSpecialFeatures())
            .lastUpdate(film.getLastUpdate())
            .build());
  }

  @Override
  public FilmByIdResponseDTO getFilmById(FilmByIdRequestDTO requestDTO) throws ServiceException {
    return null;
  }

  @Override
  public FilmCreateResponseDTO createFilm(FilmCreateRequestDTO requestDTO) throws ServiceException {
    return null;
  }

  @Override
  public FilmUpdateResponseDTO updateFilm(Integer filmId, FilmUpdateRequestDTO requestDTO)
      throws ServiceException {
    return null;
  }

  @Override
  public void deleteFilm(Integer filmId) throws ServiceException {

  }
}
