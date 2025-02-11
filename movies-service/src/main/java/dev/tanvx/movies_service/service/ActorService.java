package dev.tanvx.movies_service.service;

import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.movies_service.dto.request.actor.ActorByIdRequestDTO;
import dev.tanvx.movies_service.dto.request.actor.ActorCreateRequestDTO;
import dev.tanvx.movies_service.dto.request.actor.ActorUpdateRequestDTO;
import dev.tanvx.movies_service.dto.request.actor.ActorsRequestDTO;
import dev.tanvx.movies_service.dto.response.actor.ActorByIdResponseDTO;
import dev.tanvx.movies_service.dto.response.actor.ActorCreateResponseDTO;
import dev.tanvx.movies_service.dto.response.actor.ActorDeleteResponseDTO;
import dev.tanvx.movies_service.dto.response.actor.ActorUpdateResponseDTO;
import dev.tanvx.movies_service.dto.response.actor.ActorsResponseDTO;
import org.springframework.data.domain.Page;

public interface ActorService {

  String ACTOR_NOT_FOUND = "ACTOR_NOT_FOUND";

  Page<ActorsResponseDTO> getActors(ActorsRequestDTO requestDTO);

  ActorByIdResponseDTO getActorById(ActorByIdRequestDTO requestDTO) throws ServiceException;

  ActorCreateResponseDTO createActor(ActorCreateRequestDTO requestDTO);

  ActorUpdateResponseDTO updateActor(Integer actorId, ActorUpdateRequestDTO requestDTO) throws ServiceException;

  ActorDeleteResponseDTO deleteActor(Integer actorId) throws ServiceException;
}
