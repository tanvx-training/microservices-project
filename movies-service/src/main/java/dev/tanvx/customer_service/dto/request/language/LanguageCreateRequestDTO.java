package dev.tanvx.customer_service.dto.request.language;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LanguageCreateRequestDTO {
  @NotBlank(message = "Language name cannot be blank")
  @Size(max = 50, message = "Language name must not exceed 50 characters")
  private String name;
}
