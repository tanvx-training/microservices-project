package dev.tanvx.common_library.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomerStatus {
  INACTIVE(0),
  ACTIVE(1),
  PENDING(2),
  SUSPENDED(3);

  private final int value;

  public static CustomerStatus fromInt(Integer value) {
    if (value == null) {
      return INACTIVE;
    }
    for (CustomerStatus status : CustomerStatus.values()) {
      if (status.getValue() == value) {
        return status;
      }
    }
    throw new IllegalArgumentException("Unknown activeInt value: " + value);
  }
}
