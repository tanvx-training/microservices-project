package dev.tanvx.customer_service.dto.response.language;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LanguagesResponseDTO {
  private Integer languageId;
  private String name;
  private ZonedDateTime lastUpdate;
}
