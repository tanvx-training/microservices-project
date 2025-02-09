package dev.tanvx.customer_service.service.impl;

import dev.tanvx.common_library.enums.DeleteStatus;
import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.specification.SearchSpecification;
import dev.tanvx.common_library.specification.request.SearchRequest;
import dev.tanvx.common_library.util.SearchSpecificationRequestUtils;
import dev.tanvx.customer_service.dto.request.actor.ActorByIdRequestDTO;
import dev.tanvx.customer_service.dto.request.actor.ActorCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.actor.ActorUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.actor.ActorsRequestDTO;
import dev.tanvx.customer_service.dto.response.actor.ActorByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.actor.ActorCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.actor.ActorDeleteResponseDTO;
import dev.tanvx.customer_service.dto.response.actor.ActorUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.actor.ActorsResponseDTO;
import dev.tanvx.customer_service.entity.Actor;
import dev.tanvx.customer_service.repository.ActorRepository;
import dev.tanvx.customer_service.repository.FilmActorRepository;
import dev.tanvx.customer_service.service.ActorService;
import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

  private final ActorRepository actorRepository;

  private final FilmActorRepository filmActorRepository;

  private final SearchSpecificationRequestUtils searchSpecificationRequestUtils;

  @Override
  public void checkActorById(Integer id) throws ServiceException {
    actorRepository.findById(id)
        .orElseThrow(() -> new ServiceException(ACTOR_NOT_FOUND));
  }

  @Override
  public Page<ActorsResponseDTO> getActors(ActorsRequestDTO requestDTO) {
    SearchRequest searchRequest = SearchRequest.builder()
        .sorts(searchSpecificationRequestUtils.buildSortRequestList(requestDTO.getSort()))
        .page(requestDTO.getPage())
        .size(requestDTO.getSize())
        .build();
    SearchSpecification<Actor> specification = new SearchSpecification<>(searchRequest);
    Pageable pageable = SearchSpecification.getPageable(requestDTO.getPage(), requestDTO.getSize());
    return actorRepository.findAll(specification, pageable)
        .map(actor -> ActorsResponseDTO.builder()
            .actorId(actor.getActorId())
            .firstName(actor.getFirstName())
            .lastName(actor.getLastName())
            .build());
  }

  @Override
  public ActorByIdResponseDTO getActorById(ActorByIdRequestDTO requestDTO) throws ServiceException {
    return actorRepository.findById(requestDTO.getActorId())
        .map(actor -> ActorByIdResponseDTO.builder()
            .actorId(actor.getActorId())
            .firstName(actor.getFirstName())
            .lastName(actor.getLastName())
            .build())
        .orElseThrow(() -> new ServiceException(ACTOR_NOT_FOUND));
  }

  @Override
  public ActorCreateResponseDTO createActor(ActorCreateRequestDTO requestDTO) {
    Actor actor = Actor.builder()
        .firstName(requestDTO.getFirstName())
        .lastName(requestDTO.getLastName())
        .lastUpdate(ZonedDateTime.now()) // Set current time as last update timestamp
        .build();
    actorRepository.save(actor);
    return ActorCreateResponseDTO.builder()
        .actorId(actor.getActorId())
        .firstName(actor.getFirstName())
        .lastName(actor.getLastName())
        .lastUpdate(actor.getLastUpdate())
        .build();
  }

  @Override
  public ActorUpdateResponseDTO updateActor(Integer actorId, ActorUpdateRequestDTO requestDTO)
      throws ServiceException {
    Actor actor = actorRepository.findActorByActorId(actorId)
        .orElseThrow(() -> new ServiceException(ACTOR_NOT_FOUND));
    actor.setFirstName(requestDTO.getFirstName());
    actor.setLastName(requestDTO.getLastName());
    actor.setLastUpdate(ZonedDateTime.now()); // Set current time as last update timestamp
    actorRepository.save(actor);
    return ActorUpdateResponseDTO.builder()
        .actorId(actor.getActorId())
        .firstName(actor.getFirstName())
        .lastName(actor.getLastName())
        .lastUpdate(actor.getLastUpdate())
        .build();
  }

  @Override
  public ActorDeleteResponseDTO deleteActor(Integer actorId) throws ServiceException {
    Actor actor = actorRepository.findActorByActorId(actorId)
        .orElseThrow(() -> new ServiceException(ACTOR_NOT_FOUND));
    filmActorRepository.deleteAllByIdActor(actor);
    actor.setDeleteFlg(DeleteStatus.INACTIVE.isValue());
    actor.setLastUpdate(ZonedDateTime.now()); // Set current time as last update timestamp
    actorRepository.save(actor);
    return ActorDeleteResponseDTO.builder()
        .actorId(actor.getActorId())
        .deleteFlg(actor.isDeleteFlg())
        .build();
  }
}
