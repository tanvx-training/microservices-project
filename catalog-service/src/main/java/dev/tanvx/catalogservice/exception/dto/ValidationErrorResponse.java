package dev.tanvx.catalogservice.exception.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ValidationErrorResponse {

  @JsonProperty("result")
  private final String result;

  @JsonProperty("errorMessages")
  private final List<String> errorMessages;

  @JsonProperty("validationErrors")
  private final ValidationErrors validationErrors;

  @Getter
  @Builder
  public static class ValidationErrors{

    @JsonProperty("title")
    private final String title;

    @JsonProperty("errors")
    private final List<Errors> errors;
  }

  @Getter
  @Builder
  @EqualsAndHashCode
  public static class Errors{

    @JsonProperty("name")
    private final String name;

    @JsonProperty("messages")
    private final List<String> messages;
  }
}
