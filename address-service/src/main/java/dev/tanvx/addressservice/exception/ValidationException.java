package dev.tanvx.addressservice.exception;

import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
public class ValidationException extends RuntimeException {

  private final Map<String, List<String>> errors;
  private final List<String> errorMessages;

  public ValidationException(List<String> errorMessages, String name, String message) {
    this.errorMessages = errorMessages;
    this.errors = Collections.singletonMap(name, Collections.singletonList(message));
  }

  public ValidationException(List<String> errorMessages, Map<String, List<String>> errors) {
    this.errorMessages = errorMessages;
    this.errors = errors;
  }

  public ValidationException(String errorMessage, String name, String message) {
    this.errorMessages = Collections.singletonList(errorMessage);
    this.errors = Collections.singletonMap(name, Collections.singletonList(message));
  }

  public ValidationException(String name, String message) {
    this.errorMessages = Collections.emptyList();
    this.errors = Collections.singletonMap(name, Collections.singletonList(message));
  }

  public ValidationException(Map<String, List<String>> errors) {
    this.errorMessages = Collections.emptyList();
    this.errors = errors;
  }

  public ValidationException(List<String> errorMessages) {
    this.errorMessages = errorMessages;
    this.errors = Collections.emptyMap();
  }
}

