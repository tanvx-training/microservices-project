package dev.tanvx.common_library.specification.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import dev.tanvx.common_library.specification.enums.FieldType;
import dev.tanvx.common_library.specification.enums.Operator;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FilterRequest implements Serializable {

  @Serial
  private static final long serialVersionUID = 6293344849078612450L;

  private String key;

  private Operator operator;

  private FieldType fieldType;

  private transient Object value;

  private transient Object valueTo;

  private transient List<Object> values;

}
