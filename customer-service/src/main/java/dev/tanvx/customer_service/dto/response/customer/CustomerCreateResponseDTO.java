package dev.tanvx.customer_service.dto.response.customer;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomerCreateResponseDTO {
  private Integer customerId;
  private Boolean activeBool;
  private Integer activeInt;
  private LocalDate createDate;
}
