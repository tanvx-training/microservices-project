package dev.tanvx.common_library.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomerStatus {
  ACTIVE("Active", "Customer is active"),
  INACTIVE("Inactive", "Customer is inactive"),
  SUSPENDED("Suspended", "Customer is suspended"),
  BANNED("Banned", "Customer is banned");

  private final String code;
  private final String description;


  public static CustomerStatus fromCode(String code) {
    for (CustomerStatus status : CustomerStatus.values()) {
      if (status.code.equalsIgnoreCase(code)) {
        return status;
      }
    }
    throw new IllegalArgumentException("Unknown status code: " + code);
  }
}

