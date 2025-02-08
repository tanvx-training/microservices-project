package dev.tanvx.common_library.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeleteStatus {
  ACTIVE(false),
  INACTIVE(true);

  private final boolean value;
}
