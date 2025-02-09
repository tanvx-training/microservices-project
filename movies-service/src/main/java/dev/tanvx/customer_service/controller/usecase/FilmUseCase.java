package dev.tanvx.customer_service.controller.usecase;

import dev.tanvx.common_library.exception.BusinessException;
import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.common_library.model.MessageProperties;
import dev.tanvx.common_library.model.ResponseConstants;
import dev.tanvx.common_library.util.MessageUtils;
import dev.tanvx.common_library.util.ValidationUtils;
import dev.tanvx.customer_service.dto.request.film.FilmByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.film.FilmCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.film.FilmUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.film.FilmsRequestDTO;
import dev.tanvx.customer_service.dto.response.film.FilmByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmDeleteResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmsResponseDTO;
import dev.tanvx.customer_service.entity.Film;
import dev.tanvx.customer_service.service.FilmService;
import dev.tanvx.customer_service.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FilmUseCase {

  private final FilmService filmService;

  private final ValidationUtils validationUtils;

  private final MessageUtils messageUtils;

  @Transactional(readOnly = true)
  public ApiResponse<Page<FilmsResponseDTO>> getFilms(FilmsRequestDTO requestDTO) {
    try {
      // Validate page and size parameter
      validationUtils.validateRequest(requestDTO);

      // Validate sort parameter
      validationUtils.validateSortParam(requestDTO.getSort(), Film.class);

      Page<FilmsResponseDTO> filmsResponseDTOPage = filmService.getFilms(requestDTO);
      return ApiResponse.<Page<FilmsResponseDTO>>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(filmsResponseDTOPage)
          .build();
    } catch (Exception e) {
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional(readOnly = true)
  public ApiResponse<FilmByIdResponseDTO> getFilmById(Integer filmId) {
    try {
      FilmByIdRequestDTO requestDTO = FilmByIdRequestDTO.builder()
          .id(filmId)
          .build();
      FilmByIdResponseDTO filmByIdResponseDTO = filmService.getFilmById(requestDTO);
      return ApiResponse.<FilmByIdResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(filmByIdResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (FilmService.FILM_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<FilmCreateResponseDTO> createFilm(FilmCreateRequestDTO requestDTO) {
    try {
      // Validate request
      validationUtils.validateRequest(requestDTO);

      FilmCreateResponseDTO filmCreateResponseDTO = filmService.createFilm(requestDTO);

      return ApiResponse.<FilmCreateResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(filmCreateResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (FilmService.FILM_ALREADY_EXISTS.equals(e.getCauseId())
          || FilmService.INVALID_ACTOR_ID.equals(e.getCauseId())
          || FilmService.INVALID_CATEGORY_ID.equals(e.getCauseId())
          || LanguageService.LANGUAGE_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.BAD_REQUEST,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<FilmUpdateResponseDTO> updateFilm(Integer filmId,
      FilmUpdateRequestDTO requestDTO) {
    try {
      // Validate request
      validationUtils.validateRequest(requestDTO);

      FilmUpdateResponseDTO filmUpdateResponseDTO = filmService.updateFilm(filmId,
          requestDTO);

      return ApiResponse.<FilmUpdateResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(filmUpdateResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (FilmService.FILM_NOT_FOUND.equals(e.getCauseId())
          || FilmService.INVALID_ACTOR_ID.equals(e.getCauseId())
          || FilmService.INVALID_CATEGORY_ID.equals(e.getCauseId())
          || LanguageService.LANGUAGE_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.BAD_REQUEST,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<FilmDeleteResponseDTO> deleteFilm(Integer filmId) {
    try {
      FilmDeleteResponseDTO filmDeleteResponseDTO = filmService.deleteFilm(filmId);
      return ApiResponse.<FilmDeleteResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(filmDeleteResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (FilmService.FILM_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }
}
