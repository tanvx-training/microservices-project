package dev.tanvx.customer_service.service.impl;

import dev.tanvx.common_library.enums.DeleteStatus;
import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.specification.SearchSpecification;
import dev.tanvx.common_library.specification.request.SearchRequest;
import dev.tanvx.common_library.util.SearchSpecificationRequestUtils;
import dev.tanvx.customer_service.dto.request.film.FilmByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.film.FilmCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.film.FilmUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.film.FilmsRequestDTO;
import dev.tanvx.customer_service.dto.response.film.FilmByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmDeleteResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.film.FilmsResponseDTO;
import dev.tanvx.customer_service.entity.Actor;
import dev.tanvx.customer_service.entity.Category;
import dev.tanvx.customer_service.entity.Film;
import dev.tanvx.customer_service.entity.FilmActor;
import dev.tanvx.customer_service.entity.FilmCategory;
import dev.tanvx.customer_service.entity.Language;
import dev.tanvx.customer_service.entity.id.FilmActorId;
import dev.tanvx.customer_service.entity.id.FilmCategoryId;
import dev.tanvx.customer_service.repository.ActorRepository;
import dev.tanvx.customer_service.repository.CategoryRepository;
import dev.tanvx.customer_service.repository.FilmActorRepository;
import dev.tanvx.customer_service.repository.FilmCategoryRepository;
import dev.tanvx.customer_service.repository.FilmRepository;
import dev.tanvx.customer_service.repository.LanguageRepository;
import dev.tanvx.customer_service.service.FilmService;
import dev.tanvx.customer_service.service.LanguageService;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

  private final FilmRepository filmRepository;

  private final LanguageRepository languageRepository;

  private final ActorRepository actorRepository;

  private final FilmActorRepository filmActorRepository;

  private final CategoryRepository categoryRepository;

  private final FilmCategoryRepository filmCategoryRepository;

  private final SearchSpecificationRequestUtils searchSpecificationRequestUtils;

  @Override
  public void checkFilmById(Integer id) throws ServiceException {
    filmRepository.findById(id)
        .orElseThrow(() -> new ServiceException(FILM_NOT_FOUND));
  }

  @Override
  public Page<FilmsResponseDTO> getFilms(FilmsRequestDTO requestDTO) {
    SearchRequest searchRequest = SearchRequest.builder()
        .sorts(searchSpecificationRequestUtils.buildSortRequestList(requestDTO.getSort()))
        .page(requestDTO.getPage())
        .size(requestDTO.getSize())
        .build();
    SearchSpecification<Film> specification = new SearchSpecification<>(searchRequest);
    Pageable pageable = SearchSpecification.getPageable(requestDTO.getPage(), requestDTO.getSize());
    return filmRepository.findAll(specification, pageable)
        .map(film -> FilmsResponseDTO.builder()
            .filmId(film.getFilmId())
            .title(film.getTitle())
            .description(film.getDescription())
            .releaseYear(film.getReleaseYear())
            .language(FilmsResponseDTO.LanguageDTO.builder()
                .languageId(film.getLanguage().getLanguageId())
                .name(film.getLanguage().getName())
                .build())
            .originalLanguage(FilmsResponseDTO.LanguageDTO.builder()
                .languageId(film.getOriginalLanguage().getLanguageId())
                .name(film.getOriginalLanguage().getName())
                .build())
            .rentalDuration(film.getRentalDuration())
            .rentalRate(film.getRentalRate())
            .length(film.getLength())
            .replacementCost(film.getReplacementCost())
            .rating(film.getRating())
            .lastUpdate(film.getLastUpdate())
            .build());
  }

  @Override
  public FilmByIdResponseDTO getFilmById(FilmByIdRequestDTO requestDTO) throws ServiceException {
    return filmRepository.findById(requestDTO.getId())
        .map(film -> FilmByIdResponseDTO.builder()
            .filmId(film.getFilmId())
            .title(film.getTitle())
            .description(film.getDescription())
            .releaseYear(film.getReleaseYear())
            .language(FilmByIdResponseDTO.LanguageDTO.builder()
                .languageId(film.getLanguage().getLanguageId())
                .name(film.getLanguage().getName())
                .build())
            .originalLanguage(FilmByIdResponseDTO.LanguageDTO.builder()
                .languageId(film.getOriginalLanguage().getLanguageId())
                .name(film.getOriginalLanguage().getName())
                .build())
            .rentalDuration(film.getRentalDuration())
            .rentalRate(film.getRentalRate())
            .length(film.getLength())
            .replacementCost(film.getReplacementCost())
            .rating(film.getRating())
            .lastUpdate(film.getLastUpdate())
            .build())
        .orElseThrow(() -> new ServiceException(FILM_NOT_FOUND));
  }

  @Override
  @Transactional
  public FilmCreateResponseDTO createFilm(FilmCreateRequestDTO requestDTO) throws ServiceException {
    // Check if film already exists
    filmRepository.findByTitle(requestDTO.getTitle())
        .orElseThrow(() -> new ServiceException(FILM_ALREADY_EXISTS));
    // Get language
    Language language = languageRepository.findById(requestDTO.getLanguageId())
        .orElseThrow(() -> new ServiceException(LanguageService.LANGUAGE_NOT_FOUND));
    // Get original language
    Language originalLanguage = null;
    if (Objects.nonNull(requestDTO.getOriginalLanguageId())) {
      originalLanguage = languageRepository.findById(requestDTO.getOriginalLanguageId())
          .orElseThrow(() -> new ServiceException(LanguageService.LANGUAGE_NOT_FOUND));
    }
    // Save film
    Film film = Film.builder()
        .title(requestDTO.getTitle())
        .description(requestDTO.getDescription())
        .releaseYear(requestDTO.getReleaseYear())
        .language(language)
        .originalLanguage(originalLanguage)
        .rentalDuration(requestDTO.getRentalDuration())
        .rentalRate(requestDTO.getRentalRate())
        .length(requestDTO.getLength())
        .replacementCost(requestDTO.getReplacementCost())
        .rating(requestDTO.getRating())
        .lastUpdate(ZonedDateTime.now())
        .build();
    filmRepository.save(film);

    // Save film actors
    List<Actor> actors = actorRepository.findAllById(requestDTO.getActorIds());
    if (!Objects.equals(requestDTO.getActorIds().size(), actors.size())) {
      throw new ServiceException(INVALID_ACTOR_ID);
    }
    List<FilmActor> filmActors = actors.stream()
        .map(actor -> FilmActor.builder()
            .id(new FilmActorId(actor, film))
            .lastUpdate(ZonedDateTime.now())
            .build())
        .toList();
    filmActorRepository.saveAll(filmActors);

    // Save film categories
    List<Category> categories = categoryRepository.findAllById(requestDTO.getCategoryIds());
    if (!Objects.equals(requestDTO.getCategoryIds().size(), categories.size())) {
      throw new ServiceException(INVALID_CATEGORY_ID);
    }
    List<FilmCategory> filmCategories = categories.stream()
        .map(category -> FilmCategory.builder()
            .id(new FilmCategoryId(film, category))
            .lastUpdate(ZonedDateTime.now())
            .build())
        .toList();
    filmCategoryRepository.saveAll(filmCategories);

    return FilmCreateResponseDTO.builder()
        .filmId(film.getFilmId())
        .title(film.getTitle())
        .description(film.getDescription())
        .releaseYear(film.getReleaseYear())
        .language(FilmCreateResponseDTO.LanguageDTO.builder()
            .languageId(film.getLanguage().getLanguageId())
            .name(film.getLanguage().getName())
            .build())
        .originalLanguage(Objects.nonNull(film.getOriginalLanguage())
            ? FilmCreateResponseDTO.LanguageDTO.builder()
            .languageId(film.getOriginalLanguage().getLanguageId())
            .name(film.getOriginalLanguage().getName())
            .build()
            : null)
        .rentalDuration(film.getRentalDuration())
        .rentalRate(film.getRentalRate())
        .length(film.getLength())
        .replacementCost(film.getReplacementCost())
        .rating(film.getRating())
        .lastUpdate(film.getLastUpdate())
        .actors(actors.stream()
            .map(actor -> FilmCreateResponseDTO.ActorDTO.builder()
                .actorId(actor.getActorId())
                .firstName(actor.getFirstName())
                .lastName(actor.getLastName())
                .build())
            .toList())
        .categories(categories.stream()
            .map(category -> FilmCreateResponseDTO.CategoryDTO.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .build())
            .toList())
        .build();
  }

  @Override
  @Transactional
  public FilmUpdateResponseDTO updateFilm(Integer filmId, FilmUpdateRequestDTO requestDTO)
      throws ServiceException {
    Film film = filmRepository.findById(filmId)
        .orElseThrow(() -> new ServiceException(FILM_NOT_FOUND));
    film.setTitle(Objects.equals(requestDTO.getTitle(), film.getTitle())
        ? film.getTitle()
        : requestDTO.getTitle());
    film.setDescription(Objects.equals(requestDTO.getDescription(), film.getDescription())
        ? film.getDescription()
        : requestDTO.getDescription());
    film.setReleaseYear(Objects.equals(requestDTO.getReleaseYear(), film.getReleaseYear())
        ? film.getReleaseYear()
        : requestDTO.getReleaseYear());
    film.setLanguage(Objects.equals(requestDTO.getLanguageId(), film.getLanguage().getLanguageId())
        ? film.getLanguage()
        : languageRepository.findById(requestDTO.getLanguageId())
            .orElseThrow(() -> new ServiceException(LanguageService.LANGUAGE_NOT_FOUND)));
    film.setOriginalLanguage(Objects.equals(requestDTO.getOriginalLanguageId(),
        film.getOriginalLanguage().getLanguageId())
        ? film.getOriginalLanguage()
        : languageRepository.findById(requestDTO.getOriginalLanguageId())
            .orElseThrow(() -> new ServiceException(LanguageService.LANGUAGE_NOT_FOUND)));
    film.setRentalDuration(Objects.equals(requestDTO.getRentalDuration(), film.getRentalDuration())
        ? film.getRentalDuration()
        : requestDTO.getRentalDuration());
    film.setRentalRate(Objects.equals(requestDTO.getRentalRate(), film.getRentalRate())
        ? film.getRentalRate()
        : requestDTO.getRentalRate());
    film.setLength(Objects.equals(requestDTO.getLength(), film.getLength())
        ? film.getLength()
        : requestDTO.getLength());
    film.setReplacementCost(
        Objects.equals(requestDTO.getReplacementCost(), film.getReplacementCost())
            ? film.getReplacementCost()
            : requestDTO.getReplacementCost());
    film.setRating(Objects.equals(requestDTO.getRating(), film.getRating())
        ? film.getRating()
        : requestDTO.getRating());
    film.setLastUpdate(ZonedDateTime.now());
    filmRepository.save(film);

    List<Actor> actors = actorRepository.findAllById(requestDTO.getActorIds());
    if (!Objects.equals(requestDTO.getActorIds().size(), actors.size())) {
      throw new ServiceException(INVALID_ACTOR_ID);
    }
    List<Integer> oldActorIds = filmActorRepository.findAllByIdFilm(film)
        .stream()
        .map(filmActor -> filmActor.getId().getActor().getActorId())
        .toList();
    if (isListChanged(oldActorIds, requestDTO.getActorIds())) {
      filmActorRepository.deleteAllByIdFilm(film);
      List<FilmActor> filmActors = actors.stream()
          .map(actor -> FilmActor.builder()
              .id(new FilmActorId(actor, film))
              .lastUpdate(ZonedDateTime.now())
              .build())
          .toList();
      filmActorRepository.saveAll(filmActors);
    }

    List<Category> categories = categoryRepository.findAllById(requestDTO.getCategoryIds());
    if (!Objects.equals(requestDTO.getCategoryIds().size(), categories.size())) {
      throw new ServiceException(INVALID_CATEGORY_ID);
    }
    List<Integer> oldCategoryIds = filmCategoryRepository.findAllByIdFilm(film)
        .stream()
        .map(filmCategory -> filmCategory.getId().getCategory().getCategoryId())
        .toList();
    if (isListChanged(oldCategoryIds, requestDTO.getCategoryIds())) {
      filmCategoryRepository.deleteAllByIdFilm(film);
      List<FilmCategory> filmCategories = categories.stream()
          .map(category -> FilmCategory.builder()
              .id(new FilmCategoryId(film, category))
              .lastUpdate(ZonedDateTime.now())
              .build())
          .toList();
      filmCategoryRepository.saveAll(filmCategories);
    }

    return FilmUpdateResponseDTO.builder()
        .filmId(film.getFilmId())
        .title(film.getTitle())
        .description(film.getDescription())
        .releaseYear(film.getReleaseYear())
        .language(FilmCreateResponseDTO.LanguageDTO.builder()
            .languageId(film.getLanguage().getLanguageId())
            .name(film.getLanguage().getName())
            .build())
        .originalLanguage(Objects.nonNull(film.getOriginalLanguage())
            ? FilmCreateResponseDTO.LanguageDTO.builder()
            .languageId(film.getOriginalLanguage().getLanguageId())
            .name(film.getOriginalLanguage().getName())
            .build()
            : null)
        .rentalDuration(film.getRentalDuration())
        .rentalRate(film.getRentalRate())
        .length(film.getLength())
        .replacementCost(film.getReplacementCost())
        .rating(film.getRating())
        .lastUpdate(film.getLastUpdate())
        .actors(actors.stream()
            .map(actor -> FilmCreateResponseDTO.ActorDTO.builder()
                .actorId(actor.getActorId())
                .firstName(actor.getFirstName())
                .lastName(actor.getLastName())
                .build())
            .toList())
        .categories(categories.stream()
            .map(category -> FilmCreateResponseDTO.CategoryDTO.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .build())
            .toList())
        .build();
  }

  @Override
  @Transactional
  public FilmDeleteResponseDTO deleteFilm(Integer filmId) throws ServiceException {
    // Check if film exists
    Film film = filmRepository.findById(filmId)
        .orElseThrow(() -> new ServiceException(FILM_NOT_FOUND));
    // Delete film actors
    filmActorRepository.deleteAllByIdFilm(film);
    // Delete film categories
    filmCategoryRepository.deleteAllByIdFilm(film);
    // Delete film
    film.setDeleteFlg(DeleteStatus.INACTIVE.isValue());
    film.setLastUpdate(ZonedDateTime.now());
    filmRepository.save(film);
    return FilmDeleteResponseDTO.builder()
        .filmId(film.getFilmId())
        .deleteFlg(film.isDeleteFlg())
        .build();
  }

  private boolean isListChanged(List<Integer> oldList, List<Integer> newList) {
    return Objects.equals(new HashSet<>(oldList), new HashSet<>(newList));
  }
}
