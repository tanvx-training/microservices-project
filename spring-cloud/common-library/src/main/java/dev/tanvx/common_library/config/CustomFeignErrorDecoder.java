package dev.tanvx.common_library.config;

import static feign.FeignException.errorStatus;

import dev.tanvx.common_library.exception.BusinessException;
import dev.tanvx.common_library.exception.ValidationException;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomFeignErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    int status = response.status();
    String errorMessage = extractResponseBody(response);

    if (errorMessage == null) {
      return errorStatus(methodKey, response);
    }

    return switch (status) {
      case 400 -> new ValidationException(List.of(errorMessage));
      case 404 -> new BusinessException(HttpStatus.NOT_FOUND, errorMessage);
      case 500 -> new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
      default -> errorStatus(methodKey, response);
    };
  }

  private String extractResponseBody(Response response) {
    if (response.body() == null) {
      return null;
    }
    try {
      return IOUtils.toString(response.body().asReader());
    } catch (IOException e) {
      log.error("Error reading response body: {}", e.getMessage(), e);
      return null;
    }
  }
}

