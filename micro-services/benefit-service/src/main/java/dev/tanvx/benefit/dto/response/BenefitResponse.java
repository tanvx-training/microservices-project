package dev.tanvx.benefit.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Benefit Response DTO
 * 
 * Data Transfer Object for sending benefit information in API responses.
 * This class represents the structure of benefit data that will be
 * returned to clients through the REST API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BenefitResponse {
    private Long benefitId;
    private String type;
    private ZonedDateTime createdOn;
    private String createdBy;
    private ZonedDateTime lastModifiedOn;
    private String lastModifiedBy;
} 