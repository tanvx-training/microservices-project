package dev.tanvx.business_service.dto.response.staff;

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
public class StaffCreateResponseDTO {

  private Integer storeId;
  private boolean deleteFlg;
  private ZonedDateTime lastUpdate;
}
