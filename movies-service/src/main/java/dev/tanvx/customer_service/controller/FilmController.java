package dev.tanvx.customer_service.controller;

import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.customer_service.controller.usecase.FilmUseCase;
import dev.tanvx.customer_service.dto.request.film.FilmCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.film.FilmUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.film.FilmsRequestDTO;
import dev.tanvx.customer_service.dto.response.film.FilmByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmsResponseDTO;
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
@RequestMapping("/api/v1/films")
public class FilmController {

  private final FilmUseCase filmUseCase;

  @GetMapping
  public ResponseEntity<ApiResponse<Page<FilmsResponseDTO>>> getFilms(
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "sort", defaultValue = "filmId,asc") String sort) {

    return ResponseEntity.ok(filmUseCase.getFilms(
        FilmsRequestDTO.builder()
            .page(page)
            .size(size)
            .sort(sort)
            .build()
    ));
  }

  @GetMapping("/{filmId}/")
  public ResponseEntity<ApiResponse<FilmByIdResponseDTO>> getFilmById(
      @PathVariable("filmId") Integer filmId) {
    return ResponseEntity.ok(filmUseCase.getFilmById(filmId));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<FilmCreateResponseDTO>> createFilm(
      @RequestBody FilmCreateRequestDTO requestDTO) {
    return ResponseEntity.ok(filmUseCase.createFilm(requestDTO));
  }

  @PutMapping("/{filmId}/")
  public ResponseEntity<ApiResponse<FilmUpdateResponseDTO>> updateFilm(
      @PathVariable("filmId") Integer filmId,
      @RequestBody FilmUpdateRequestDTO requestDTO) {
    return ResponseEntity.ok(filmUseCase.updateFilm(filmId, requestDTO));
  }

  @DeleteMapping("/{filmId}/")
  public ResponseEntity<ApiResponse<Void>> deleteFilm(
      @PathVariable("filmId") Integer filmId) {
    return ResponseEntity.ok(filmUseCase.deleteFilm(filmId));
  }
}
