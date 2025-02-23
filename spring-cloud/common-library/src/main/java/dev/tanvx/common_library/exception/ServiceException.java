package dev.tanvx.common_library.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ServiceException extends Exception {

  private final String causeId;
}
