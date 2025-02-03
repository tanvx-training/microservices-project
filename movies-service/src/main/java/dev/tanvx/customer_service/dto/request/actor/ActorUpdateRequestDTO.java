package dev.tanvx.customer_service.dto.request.actor;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ActorUpdateRequestDTO {

  @NotNull(message = "First name must not be null.")
  private String firstName;
  @NotNull(message = "Last name must not be null.")
  private String lastName;
}
