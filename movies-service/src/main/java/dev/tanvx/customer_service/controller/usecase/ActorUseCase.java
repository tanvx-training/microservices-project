package dev.tanvx.customer_service.controller.usecase;

import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.customer_service.dto.request.actor.ActorCreateRequestDTO;
import dev.tanvx.customer_service.dto.request.actor.ActorUpdateRequestDTO;
import dev.tanvx.customer_service.dto.request.actor.ActorsRequestDTO;
import dev.tanvx.customer_service.dto.response.actor.ActorByIdResponseDTO;
import dev.tanvx.customer_service.dto.response.actor.ActorCreateResponseDTO;
import dev.tanvx.customer_service.dto.response.actor.ActorUpdateResponseDTO;
import dev.tanvx.customer_service.dto.response.actor.ActorsResponseDTO;
import org.springframework.data.domain.Page;

public class ActorUseCase {

  public ApiResponse<Page<ActorsResponseDTO>> getActors(ActorsRequestDTO requestDTO) {
    return null;
  }

  public ApiResponse<ActorByIdResponseDTO> getActorById(Integer actorId) {
    return null;
  }

  public ApiResponse<ActorCreateResponseDTO> createActor(ActorCreateRequestDTO requestDTO) {
    return null;
  }

  public ApiResponse<ActorUpdateResponseDTO> updateActor(Integer actorId,
      ActorUpdateRequestDTO requestDTO) {
    return null;
  }

  public ApiResponse<Void> deleteActor(Integer actorId) {
    return null;
  }
}
