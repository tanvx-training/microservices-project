package dev.tanvx.common_library.specification.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SearchRequest implements Serializable {

  @Serial
  private static final long serialVersionUID = 8514625832019794838L;

  private List<FilterRequest> filters;

  private List<SortRequest> sorts;

  private Integer page;

  private Integer size;

  public List<FilterRequest> getFilters() {
    if (Objects.isNull(this.filters)) return new ArrayList<>();
    return this.filters;
  }

  public List<SortRequest> getSorts() {
    if (Objects.isNull(this.sorts)) return new ArrayList<>();
    return this.sorts;
  }

}
