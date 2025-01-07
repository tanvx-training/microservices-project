package dev.tanvx.common_library.util;

import dev.tanvx.common_library.exception.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
}
