package dev.tanvx.customer_service.service;

import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.customer_service.dto.request.language.LanguageByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.language.LanguageCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.language.LanguageUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.language.LanguagesRequestDTO;
import dev.tanvx.customer_service.dto.response.language.LanguageByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.language.LanguageCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.language.LanguageUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.language.LanguagesResponseDTO;
import org.springframework.data.domain.Page;

public interface LanguageService {

  String LANGUAGE_NOT_FOUND = "LANGUAGE_NOT_FOUND";

  String LANGUAGE_ALREADY_EXISTS = "LANGUAGE_ALREADY_EXISTS";

  Page<LanguagesResponseDTO> getLanguages(LanguagesRequestDTO requestDTO);

  LanguageByIdResponseDTO getLanguageById(LanguageByIdRequestDTO requestDTO)
      throws ServiceException;

  LanguageCreateResponseDTO createLanguage(LanguageCreateRequestDTO requestDTO)
      throws ServiceException;

  LanguageUpdateResponseDTO updateLanguage(Integer languageId, LanguageUpdateRequestDTO requestDTO)
      throws ServiceException;

  void deleteLanguage(Integer languageId) throws ServiceException;
}
