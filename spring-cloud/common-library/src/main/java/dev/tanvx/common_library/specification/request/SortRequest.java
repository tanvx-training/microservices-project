package dev.tanvx.common_library.specification.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import dev.tanvx.common_library.specification.enums.SortDirection;
import java.io.Serial;
import java.io.Serializable;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SortRequest implements Serializable {

  @Serial
  private static final long serialVersionUID = 3194362295851723069L;

  private String key;

  private SortDirection direction;
}