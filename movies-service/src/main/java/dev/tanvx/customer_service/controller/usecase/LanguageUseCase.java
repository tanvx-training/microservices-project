package dev.tanvx.customer_service.controller.usecase;

import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.customer_service.dto.request.language.LanguageCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.language.LanguageUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.language.LanguagesRequestDTO;
import dev.tanvx.customer_service.dto.response.language.LanguageByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.language.LanguageCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.language.LanguageUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.language.LanguagesResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class LanguageUseCase {

  public ApiResponse<Page<LanguagesResponseDTO>> getLanguages(LanguagesRequestDTO requestDTO) {
    return null;
  }

  public ApiResponse<LanguageByIdResponseDTO> getLanguageById(Integer languageId) {
    return null;
  }

  public ApiResponse<LanguageCreateResponseDTO> createLanguage(
      LanguageCreateRequestDTO requestDTO) {
    return null;
  }

  public ApiResponse<LanguageUpdateResponseDTO> updateLanguage(Integer languageId,
      LanguageUpdateRequestDTO requestDTO) {
    return null;
  }

  public ApiResponse<Void> deleteLanguage(Integer languageId) {
    return null;
  }
}
