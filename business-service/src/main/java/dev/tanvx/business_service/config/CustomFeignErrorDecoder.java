package dev.tanvx.business_service.config;

import dev.tanvx.common_library.exception.BusinessException;
import dev.tanvx.common_library.model.MessageProperties;
import dev.tanvx.common_library.util.MessageUtils;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomFeignErrorDecoder implements ErrorDecoder {

  private final MessageUtils messageUtils;

  private final ErrorDecoder defaultErrorDecoder = new Default();

  @Override
  public Exception decode(String methodKey, Response response) {
    HttpStatus status = HttpStatus.valueOf(response.status());
    String message = messageUtils.getMessage(MessageProperties.RESPONSE_500, null);
    try {
      if (Objects.nonNull(response.body())) {
        message = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
      }
    } catch (IOException e) {
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
    return switch (status) {
      case NOT_FOUND -> // 404
          new BusinessException(HttpStatus.NOT_FOUND, message);
      case INTERNAL_SERVER_ERROR -> // 500
          new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, message);
      default -> defaultErrorDecoder.decode(methodKey, response);
    };
  }
}
