package dev.tanvx.movies_service.service;

import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.movies_service.dto.request.film.FilmByIdRequestDTO;
import dev.tanvx.movies_service.dto.request.film.FilmCreateRequestDTO;
import dev.tanvx.movies_service.dto.request.film.FilmUpdateRequestDTO;
import dev.tanvx.movies_service.dto.request.film.FilmsRequestDTO;
import dev.tanvx.movies_service.dto.response.film.FilmByIdResponseDTO;
import dev.tanvx.movies_service.dto.response.film.FilmCreateResponseDTO;
import dev.tanvx.movies_service.dto.response.film.FilmDeleteResponseDTO;
import dev.tanvx.movies_service.dto.response.film.FilmUpdateResponseDTO;
import dev.tanvx.movies_service.dto.response.film.FilmsResponseDTO;
import org.springframework.data.domain.Page;

public interface FilmService {

  String FILM_NOT_FOUND = "FILM_NOT_FOUND";

  String FILM_ALREADY_EXISTS = "FILM_ALREADY_EXISTS";

  String INVALID_ACTOR_ID = "INVALID_ACTOR_ID";

  String INVALID_CATEGORY_ID = "INVALID_CATEGORY_ID";

  void checkFilmById(Integer id) throws ServiceException;

  Page<FilmsResponseDTO> getFilms(FilmsRequestDTO requestDTO);

  FilmByIdResponseDTO getFilmById(FilmByIdRequestDTO requestDTO) throws ServiceException;

  FilmCreateResponseDTO createFilm(FilmCreateRequestDTO requestDTO) throws ServiceException;

  FilmUpdateResponseDTO updateFilm(Integer filmId, FilmUpdateRequestDTO requestDTO)
      throws ServiceException;

  FilmDeleteResponseDTO deleteFilm(Integer filmId) throws ServiceException;
}
