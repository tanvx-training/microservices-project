package dev.tanvx.customer_service.service;

import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.customer_service.dto.request.film.FilmByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.film.FilmCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.film.FilmUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.film.FilmsRequestDTO;
import dev.tanvx.customer_service.dto.response.film.FilmByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmsResponseDTO;
import org.springframework.data.domain.Page;

public interface FilmService {

  String FILM_NOT_FOUND = "FILM_NOT_FOUND";

  void checkFilmById(Integer id) throws ServiceException;

  Page<FilmsResponseDTO> getFilms(FilmsRequestDTO requestDTO);

  FilmByIdResponseDTO getFilmById(FilmByIdRequestDTO requestDTO) throws ServiceException;

  FilmCreateResponseDTO createFilm(FilmCreateRequestDTO requestDTO) throws ServiceException;

  FilmUpdateResponseDTO updateFilm(Integer filmId, FilmUpdateRequestDTO requestDTO)
      throws ServiceException;

  void deleteFilm(Integer filmId) throws ServiceException;
}
