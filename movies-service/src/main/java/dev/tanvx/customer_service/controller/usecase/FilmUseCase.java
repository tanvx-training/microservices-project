package dev.tanvx.customer_service.controller.usecase;

import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.common_library.util.MessageUtils;
import dev.tanvx.common_library.util.ValidationUtils;
import dev.tanvx.customer_service.dto.request.film.FilmCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.film.FilmUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.film.FilmsRequestDTO;
import dev.tanvx.customer_service.dto.response.film.FilmByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmsResponseDTO;
import dev.tanvx.customer_service.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmUseCase {

  private final FilmService filmService;

  private final ValidationUtils validationUtils;

  private final MessageUtils messageUtils;

  public ApiResponse<Page<FilmsResponseDTO>> getFilms(FilmsRequestDTO requestDTO) {
    return null;
  }

  public ApiResponse<FilmByIdResponseDTO> getFilmById(Integer filmId) {
    return null;
  }

  public ApiResponse<FilmCreateResponseDTO> createFilm(FilmCreateRequestDTO requestDTO) {
    return null;
  }

  public ApiResponse<FilmUpdateResponseDTO> updateFilm(Integer filmId,
      FilmUpdateRequestDTO requestDTO) {
    return null;
  }

  public ApiResponse<Void> deleteFilm(Integer filmId) {
    return null;
  }
}
