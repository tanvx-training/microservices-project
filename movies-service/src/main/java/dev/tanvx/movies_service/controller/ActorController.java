package dev.tanvx.movies_service.controller;

import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.movies_service.controller.usecase.ActorUseCase;
import dev.tanvx.movies_service.dto.request.actor.ActorCreateRequestDTO;
import dev.tanvx.movies_service.dto.request.actor.ActorUpdateRequestDTO;
import dev.tanvx.movies_service.dto.request.actor.ActorsRequestDTO;
import dev.tanvx.movies_service.dto.response.actor.ActorByIdResponseDTO;
import dev.tanvx.movies_service.dto.response.actor.ActorCreateResponseDTO;
import dev.tanvx.movies_service.dto.response.actor.ActorDeleteResponseDTO;
import dev.tanvx.movies_service.dto.response.actor.ActorUpdateResponseDTO;
import dev.tanvx.movies_service.dto.response.actor.ActorsResponseDTO;
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
@RequestMapping("/api/v1/actors")
public class ActorController {

  private final ActorUseCase actorUseCase;

  @GetMapping
  public ResponseEntity<ApiResponse<Page<ActorsResponseDTO>>> getActors(
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "sort", defaultValue = "actorId,asc") String sort) {

    return ResponseEntity.ok(actorUseCase.getActors(
        ActorsRequestDTO.builder()
            .page(page)
            .size(size)
            .sort(sort)
            .build()
    ));
  }

  @GetMapping("/{actorId}/")
  public ResponseEntity<ApiResponse<ActorByIdResponseDTO>> getActorById(
      @PathVariable("actorId") Integer actorId) {
    return ResponseEntity.ok(actorUseCase.getActorById(actorId));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<ActorCreateResponseDTO>> createActor(
      @RequestBody ActorCreateRequestDTO requestDTO) {
    return ResponseEntity.ok(actorUseCase.createActor(requestDTO));
  }

  @PutMapping("/{actorId}/")
  public ResponseEntity<ApiResponse<ActorUpdateResponseDTO>> updateActor(
      @PathVariable("actorId") Integer actorId,
      @RequestBody ActorUpdateRequestDTO requestDTO) {
    return ResponseEntity.ok(actorUseCase.updateActor(actorId, requestDTO));
  }

  @DeleteMapping("/{actorId}/")
  public ResponseEntity<ApiResponse<ActorDeleteResponseDTO>> deleteActor(
      @PathVariable("actorId") Integer actorId) {
    return ResponseEntity.ok(actorUseCase.deleteActor(actorId));
  }
}
