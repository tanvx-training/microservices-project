package dev.tanvx.common_library.exception.handler;

import dev.tanvx.common_library.exception.BusinessException;
import dev.tanvx.common_library.exception.ValidationException;
import dev.tanvx.common_library.exception.dto.ErrorResponse;
import dev.tanvx.common_library.exception.dto.ValidationErrorResponse;
import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.common_library.model.MessageProperties;
import dev.tanvx.common_library.model.ResponseConstants;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String RESULT_NG = "NG";

  private final MessageSource messageSource;

  // Handle validation exception
  @ExceptionHandler(ValidationException.class)
  public ApiResponse<ValidationErrorResponse> handleValidationException(
      ValidationException error) {
    // Logging
    log.error(error.getMessage(), Objects.isNull(error.getCause()) ? error : error.getCause());
    // Convert to validation error response
    List<ValidationErrorResponse.Errors> errors = error.getErrors().entrySet().stream()
        .map(c -> ValidationErrorResponse.Errors
            .builder()
            .name(c.getKey())
            .messages(c.getValue())
            .build())
        .toList();
    // Build validation error response
    ValidationErrorResponse.ValidationErrors validationErrors = ValidationErrorResponse.ValidationErrors
        .builder()
        .title(messageSource.getMessage(MessageProperties.RESPONSE_400, null,
            Locale.getDefault()))
        .errors(errors)
        .build();
    // Build response
    ValidationErrorResponse response = ValidationErrorResponse
        .builder()
        .result(RESULT_NG)
        .validationErrors(validationErrors)
        .errorMessages(error.getErrorMessages())
        .build();
    // Return response
    return ApiResponse.<ValidationErrorResponse>builder()
        .status(ResponseConstants.ERROR_STATUS)
        .message(ResponseConstants.ERROR_MESSAGE)
        .data(response)
        .build();
  }

  // Handle service exception
  @ExceptionHandler(BusinessException.class)
  public ApiResponse<ErrorResponse> handleBusinessExceptionException(BusinessException error) {
    // Logging
    log.error(CollectionUtils.isEmpty(error.getMessages())
            ? error.getMessage()
            : error.getMessages().toString(),
        Objects.isNull(error.getCause()) ? error : error.getCause());
    // Return response
    return ApiResponse.<ErrorResponse>builder()
        .status(ResponseConstants.ERROR_STATUS)
        .message(ResponseConstants.ERROR_MESSAGE)
        .data(ErrorResponse
            .builder()
            .result(RESULT_NG)
            .errorMessages(CollectionUtils.isEmpty(error.getMessages())
                ? Collections.singletonList(error.getMessage())
                : error.getMessages())
            .build())
        .build();
  }

  @ExceptionHandler(Throwable.class)
  public ApiResponse<ErrorResponse> handleThrowable(Throwable throwable) {

    String errorMessage = "An unexpected error occurred: " + throwable.getMessage();

    return ApiResponse.<ErrorResponse>builder()
        .status(ResponseConstants.ERROR_STATUS)
        .message(ResponseConstants.ERROR_MESSAGE)
        .data(ErrorResponse
            .builder()
            .result(RESULT_NG)
            .errorMessages(Collections.singletonList(errorMessage))
            .build())
        .build();
  }
}
