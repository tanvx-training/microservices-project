package dev.tanvx.movies_service.service.impl;

import dev.tanvx.common_library.enums.DeleteStatus;
import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.specification.SearchSpecification;
import dev.tanvx.common_library.specification.request.SearchRequest;
import dev.tanvx.common_library.util.SearchSpecificationRequestUtils;
import dev.tanvx.movies_service.dto.request.language.LanguageByIdRequestDTO;
import dev.tanvx.movies_service.dto.request.language.LanguageCreateRequestDTO;
import dev.tanvx.movies_service.dto.request.language.LanguageUpdateRequestDTO;
import dev.tanvx.movies_service.dto.request.language.LanguagesRequestDTO;
import dev.tanvx.movies_service.dto.response.language.LanguageByIdResponseDTO;
import dev.tanvx.movies_service.dto.response.language.LanguageCreateResponseDTO;
import dev.tanvx.movies_service.dto.response.language.LanguageDeleteResponseDTO;
import dev.tanvx.movies_service.dto.response.language.LanguageUpdateResponseDTO;
import dev.tanvx.movies_service.dto.response.language.LanguagesResponseDTO;
import dev.tanvx.movies_service.entity.Language;
import dev.tanvx.movies_service.repository.FilmRepository;
import dev.tanvx.movies_service.repository.LanguageRepository;
import dev.tanvx.movies_service.service.LanguageService;
import java.time.ZonedDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

  private final LanguageRepository languageRepository;

  private final FilmRepository filmRepository;

  private final SearchSpecificationRequestUtils searchSpecificationRequestUtils;

  @Override
  public Page<LanguagesResponseDTO> getLanguages(LanguagesRequestDTO requestDTO) {
    SearchRequest searchRequest = SearchRequest.builder()
        .sorts(searchSpecificationRequestUtils.buildSortRequestList(requestDTO.getSort()))
        .page(requestDTO.getPage())
        .size(requestDTO.getSize())
        .build();
    SearchSpecification<Language> specification = new SearchSpecification<>(searchRequest);
    Pageable pageable = SearchSpecification.getPageable(requestDTO.getPage(), requestDTO.getSize());
    return languageRepository.findAll(specification, pageable)
        .map(language -> LanguagesResponseDTO.builder()
            .languageId(language.getLanguageId())
            .name(language.getName())
            .lastUpdate(language.getLastUpdate())
            .build());
  }

  @Override
  public LanguageByIdResponseDTO getLanguageById(LanguageByIdRequestDTO requestDTO)
      throws ServiceException {
    return languageRepository.findById(requestDTO.getId())
        .map(language -> LanguageByIdResponseDTO.builder()
            .languageId(language.getLanguageId())
            .name(language.getName())
            .lastUpdate(language.getLastUpdate())
            .build())
        .orElseThrow(() -> new ServiceException(LANGUAGE_NOT_FOUND));
  }

  @Override
  public LanguageCreateResponseDTO createLanguage(LanguageCreateRequestDTO requestDTO)
      throws ServiceException {
    Optional<Language> optionalLanguage = languageRepository.findLanguageByName(
        requestDTO.getName());
    if (optionalLanguage.isPresent()) {
      throw new ServiceException(LANGUAGE_ALREADY_EXISTS);
    }
    Language language = Language.builder()
        .name(requestDTO.getName())
        .lastUpdate(ZonedDateTime.now())
        .build();
    languageRepository.save(language);
    return LanguageCreateResponseDTO.builder()
        .languageId(language.getLanguageId())
        .name(language.getName())
        .lastUpdate(language.getLastUpdate())
        .build();
  }

  @Override
  public LanguageUpdateResponseDTO updateLanguage(Integer languageId,
      LanguageUpdateRequestDTO requestDTO) throws ServiceException {
    Language language = languageRepository.findById(languageId)
        .orElseThrow(() -> new ServiceException(LANGUAGE_NOT_FOUND));
    language.setName(requestDTO.getName());
    language.setLastUpdate(ZonedDateTime.now());
    languageRepository.save(language);
    return LanguageUpdateResponseDTO.builder()
        .languageId(language.getLanguageId())
        .name(language.getName())
        .lastUpdate(language.getLastUpdate())
        .build();
  }

  @Override
  @Transactional
  public LanguageDeleteResponseDTO deleteLanguage(Integer languageId) throws ServiceException {
    Language language = languageRepository.findById(languageId)
        .orElseThrow(() -> new ServiceException(LANGUAGE_NOT_FOUND));

    filmRepository.findAllByLanguageOrOriginalLanguage(language, language)
        .forEach(film -> {
          film.setDeleteFlg(DeleteStatus.INACTIVE.isValue());
          filmRepository.save(film);
        });

    language.setDeleteFlg(DeleteStatus.INACTIVE.isValue());
    language.setLastUpdate(ZonedDateTime.now());
    languageRepository.save(language);
    return LanguageDeleteResponseDTO.builder()
        .languageId(languageId)
        .deleteFlg(language.isDeleteFlg())
        .build();
  }
}
