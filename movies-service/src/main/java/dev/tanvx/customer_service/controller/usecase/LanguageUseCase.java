package dev.tanvx.customer_service.controller.usecase;

import dev.tanvx.common_library.exception.BusinessException;
import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.common_library.model.MessageProperties;
import dev.tanvx.common_library.model.ResponseConstants;
import dev.tanvx.common_library.util.MessageUtils;
import dev.tanvx.common_library.util.ValidationUtils;
import dev.tanvx.customer_service.dto.request.language.LanguageByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.language.LanguageCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.language.LanguageUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.language.LanguagesRequestDTO;
import dev.tanvx.customer_service.dto.response.language.LanguageByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.language.LanguageCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.language.LanguageDeleteResponseDTO;
import dev.tanvx.customer_service.dto.response.language.LanguageUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.language.LanguagesResponseDTO;
import dev.tanvx.customer_service.entity.Language;
import dev.tanvx.customer_service.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LanguageUseCase {

  private final LanguageService languageService;

  private final ValidationUtils validationUtils;

  private final MessageUtils messageUtils;

  @Transactional(readOnly = true)
  public ApiResponse<Page<LanguagesResponseDTO>> getLanguages(LanguagesRequestDTO requestDTO) {
    try {
      // Validate page and size parameter
      validationUtils.validateRequest(requestDTO);

      // Validate sort parameter
      validationUtils.validateSortParam(requestDTO.getSort(), Language.class);

      Page<LanguagesResponseDTO> languagesResponseDTOPage = languageService.getLanguages(
          requestDTO);
      return ApiResponse.<Page<LanguagesResponseDTO>>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(languagesResponseDTOPage)
          .build();
    } catch (Exception e) {
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional(readOnly = true)
  public ApiResponse<LanguageByIdResponseDTO> getLanguageById(Integer languageId) {
    try {
      LanguageByIdRequestDTO requestDTO = LanguageByIdRequestDTO.builder()
          .id(languageId)
          .build();
      LanguageByIdResponseDTO languageByIdResponseDTO = languageService.getLanguageById(requestDTO);
      return ApiResponse.<LanguageByIdResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(languageByIdResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (LanguageService.LANGUAGE_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<LanguageCreateResponseDTO> createLanguage(
      LanguageCreateRequestDTO requestDTO) {
    try {
      // Validate request
      validationUtils.validateRequest(requestDTO);

      LanguageCreateResponseDTO languageCreateResponseDTO = languageService.createLanguage(
          requestDTO);

      return ApiResponse.<LanguageCreateResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(languageCreateResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (LanguageService.LANGUAGE_ALREADY_EXISTS.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.BAD_REQUEST,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<LanguageUpdateResponseDTO> updateLanguage(Integer languageId,
      LanguageUpdateRequestDTO requestDTO) {
    try {
      // Validate request
      validationUtils.validateRequest(requestDTO);

      LanguageUpdateResponseDTO languageUpdateResponseDTO = languageService.updateLanguage(
          languageId,
          requestDTO);

      return ApiResponse.<LanguageUpdateResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(languageUpdateResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (LanguageService.LANGUAGE_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<LanguageDeleteResponseDTO> deleteLanguage(Integer languageId) {
    try {
      LanguageDeleteResponseDTO languageDeleteResponseDTO = languageService.deleteLanguage(languageId);
      return ApiResponse.<LanguageDeleteResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(languageDeleteResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (LanguageService.LANGUAGE_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }
}
