package dev.tanvx.business_service.dto.request.staff;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StaffUpdateRequestDTO {

  @NotBlank(message = "First name is required")
  @Size(max = 50, message = "First name must be less than or equal to 50 characters")
  private String firstName;

  @NotBlank(message = "Last name is required")
  @Size(max = 50, message = "Last name must be less than or equal to 50 characters")
  private String lastName;

  @NotNull(message = "Address id is required")
  private Integer addressId;

  @Email(message = "Email should be valid")
  @Size(max = 256, message = "Email must be less than or equal to 256 characters")
  private String email;

  @NotNull(message = "Store id is required")
  private Integer storeId;

  @NotBlank(message = "Password is required")
  @Size(max = 256, message = "Password must be less than or equal to 256 characters")
  private String password;
}
