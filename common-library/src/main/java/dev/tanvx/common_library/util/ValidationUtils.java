package dev.tanvx.common_library.util;

import dev.tanvx.common_library.exception.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class ValidationUtils {

  private final Validator validator;

  public void validateRequest(Object request) {

    Map<String, List<String>> result = validator
        .validate(request)
        .stream()
        .collect(Collectors.groupingBy(o -> o.getPropertyPath().toString(),
            Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())));

    if (!CollectionUtils.isEmpty(result)) {
      throw new ValidationException(result);
    }
  }

  public <T> void validateSortParam(String sortParam, Class<T> entityClass) {
    List<String> errors = new ArrayList<>();
    if (StringUtils.hasText(sortParam)) {
      String[] sortParts = sortParam.split(",");
      if (sortParts.length % 2 != 0) {
        errors.add("Sort parameter should be comma separated and in the format 'field1:order1,field2:order2'.");
      } else {
        for (int i = 0; i < sortParts.length; i+=2) {
          String field = sortParts[i].trim();
          String direction = sortParts[i+1].trim();
          if (!StringUtils.hasText(field)) {
            errors.add("Sort field is required.");
          }
          List<String> validFields = getValidFields(entityClass);
          if (!validFields.contains(field)) {
            errors.add("Invalid field: " + field + ". Allowed fields are: " + validFields);
          }
          if (!StringUtils.hasText(direction) || !Arrays.asList("asc", "desc").contains(direction)) {
            errors.add("Invalid sort order. Use 'asc' or 'desc'.");
          }
        }
      }
    } else {
      errors.add("Sort parameter is required.");
    }
    if (!CollectionUtils.isEmpty(errors)) {
      throw new ValidationException(Map.of("sort", errors));
    }
  }
  private static <T> List<String> getValidFields(Class<T> entityClass) {
    Field[] fields = entityClass.getDeclaredFields();
    List<String> validFields = new ArrayList<>();
    for (Field field : fields) {
      validFields.add(field.getName());
    }
    return validFields;
  }
}
