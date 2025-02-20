package dev.tanvx.common_library.util;

import dev.tanvx.common_library.specification.enums.SortDirection;
import dev.tanvx.common_library.specification.request.SortRequest;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecificationUtils {

  public List<SortRequest> buildSortRequestList(String sortParam) {

    List<SortRequest> sortRequestList = new ArrayList<>();

    String[] sortParts = sortParam.split(",");
    for (int i = 0; i < sortParts.length; i += 2) {
      String field = sortParts[i].trim();
      String direction = sortParts[i + 1].trim();

      SortRequest sortRequest = new SortRequest(field, SortDirection.valueOf(direction.toUpperCase()));
      sortRequestList.add(sortRequest);
    }

    return sortRequestList;
  }
}
