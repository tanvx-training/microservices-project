package dev.tanvx.movies_service.dto.response.actor;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ActorByIdResponseDTO {

  private Integer actorId;
  private String firstName;
  private String lastName;
}
