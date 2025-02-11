package dev.tanvx.movies_service.controller.usecase;

import dev.tanvx.common_library.exception.BusinessException;
import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.common_library.model.MessageProperties;
import dev.tanvx.common_library.model.ResponseConstants;
import dev.tanvx.common_library.util.MessageUtils;
import dev.tanvx.common_library.util.ValidationUtils;
import dev.tanvx.movies_service.dto.request.actor.ActorByIdRequestDTO;
import dev.tanvx.movies_service.dto.request.actor.ActorCreateRequestDTO;
import dev.tanvx.movies_service.dto.request.actor.ActorUpdateRequestDTO;
import dev.tanvx.movies_service.dto.request.actor.ActorsRequestDTO;
import dev.tanvx.movies_service.dto.response.actor.ActorByIdResponseDTO;
import dev.tanvx.movies_service.dto.response.actor.ActorCreateResponseDTO;
import dev.tanvx.movies_service.dto.response.actor.ActorDeleteResponseDTO;
import dev.tanvx.movies_service.dto.response.actor.ActorUpdateResponseDTO;
import dev.tanvx.movies_service.dto.response.actor.ActorsResponseDTO;
import dev.tanvx.movies_service.entity.Actor;
import dev.tanvx.movies_service.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ActorUseCase {

  private final ActorService actorService;

  private final ValidationUtils validationUtils;

  private final MessageUtils messageUtils;

  @Transactional(readOnly = true)
  public ApiResponse<Page<ActorsResponseDTO>> getActors(ActorsRequestDTO requestDTO) {
    try {
      // Validate page and size parameter
      validationUtils.validateRequest(requestDTO);

      // Validate sort parameter
      validationUtils.validateSortParam(requestDTO.getSort(), Actor.class);

      Page<ActorsResponseDTO> actorsResponseDTOPage = actorService.getActors(requestDTO);
      return ApiResponse.<Page<ActorsResponseDTO>>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(actorsResponseDTOPage)
          .build();
    } catch (Exception e) {
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional(readOnly = true)
  public ApiResponse<ActorByIdResponseDTO> getActorById(Integer actorId) {
    try {
      ActorByIdRequestDTO requestDTO = ActorByIdRequestDTO.builder()
          .actorId(actorId)
          .build();
      ActorByIdResponseDTO actorByIdResponseDTO = actorService.getActorById(requestDTO);
      return ApiResponse.<ActorByIdResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(actorByIdResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (ActorService.ACTOR_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<ActorCreateResponseDTO> createActor(ActorCreateRequestDTO requestDTO) {
    try {
      // Validate request
      validationUtils.validateRequest(requestDTO);

      ActorCreateResponseDTO actorCreateResponseDTO = actorService.createActor(requestDTO);

      return ApiResponse.<ActorCreateResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(actorCreateResponseDTO)
          .build();
    } catch (Exception e) {
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<ActorUpdateResponseDTO> updateActor(Integer actorId,
      ActorUpdateRequestDTO requestDTO) {
    try {
      // Validate request
      validationUtils.validateRequest(requestDTO);

      ActorUpdateResponseDTO actorUpdateResponseDTO = actorService.updateActor(actorId,
          requestDTO);

      return ApiResponse.<ActorUpdateResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(actorUpdateResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (ActorService.ACTOR_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<ActorDeleteResponseDTO> deleteActor(Integer actorId) {
    try {
      ActorDeleteResponseDTO actorDeleteResponseDTO = actorService.deleteActor(actorId);
      return ApiResponse.<ActorDeleteResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(actorDeleteResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (ActorService.ACTOR_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }
}
