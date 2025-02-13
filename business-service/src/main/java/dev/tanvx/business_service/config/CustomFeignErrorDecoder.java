package dev.tanvx.business_service.config;

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
    if (response.status() == 400) {
      try {
        String error = IOUtils.toString(response.body().asReader());
        return new ValidationException(List.of(error));
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
    } else if (response.status() == 404) {
      try {
        return new BusinessException(HttpStatus.NOT_FOUND,
            IOUtils.toString(response.body().asReader()));
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
    } else if (response.status() == 500) {
      try {
        return new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
            IOUtils.toString(response.body().asReader()));
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
    }
    return errorStatus(methodKey, response);
  }
}
