package dev.tanvx.movies_service.controller;

import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.movies_service.controller.usecase.LanguageUseCase;
import dev.tanvx.movies_service.dto.request.language.LanguageCreateRequestDTO;
import dev.tanvx.movies_service.dto.request.language.LanguageUpdateRequestDTO;
import dev.tanvx.movies_service.dto.request.language.LanguagesRequestDTO;
import dev.tanvx.movies_service.dto.response.language.LanguageByIdResponseDTO;
import dev.tanvx.movies_service.dto.response.language.LanguageCreateResponseDTO;
import dev.tanvx.movies_service.dto.response.language.LanguageDeleteResponseDTO;
import dev.tanvx.movies_service.dto.response.language.LanguageUpdateResponseDTO;
import dev.tanvx.movies_service.dto.response.language.LanguagesResponseDTO;
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
@RequestMapping("/api/v1/languages")
public class LanguageController {

  private final LanguageUseCase languageUseCase;

  @GetMapping
  public ResponseEntity<ApiResponse<Page<LanguagesResponseDTO>>> getLanguages(
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "sort", defaultValue = "languageId,asc") String sort) {

    return ResponseEntity.ok(languageUseCase.getLanguages(
        LanguagesRequestDTO.builder()
            .page(page)
            .size(size)
            .sort(sort)
            .build()
    ));
  }

  @GetMapping("/{languageId}/")
  public ResponseEntity<ApiResponse<LanguageByIdResponseDTO>> getLanguageById(
      @PathVariable("languageId") Integer languageId) {
    return ResponseEntity.ok(languageUseCase.getLanguageById(languageId));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<LanguageCreateResponseDTO>> createLanguage(
      @RequestBody LanguageCreateRequestDTO requestDTO) {
    return ResponseEntity.ok(languageUseCase.createLanguage(requestDTO));
  }

  @PutMapping("/{languageId}/")
  public ResponseEntity<ApiResponse<LanguageUpdateResponseDTO>> updateLanguage(
      @PathVariable("languageId") Integer languageId,
      @RequestBody LanguageUpdateRequestDTO requestDTO) {
    return ResponseEntity.ok(languageUseCase.updateLanguage(languageId, requestDTO));
  }

  @DeleteMapping("/{languageId}/")
  public ResponseEntity<ApiResponse<LanguageDeleteResponseDTO>> deleteLanguage(
      @PathVariable("languageId") Integer languageId) {
    return ResponseEntity.ok(languageUseCase.deleteLanguage(languageId));
  }
}
