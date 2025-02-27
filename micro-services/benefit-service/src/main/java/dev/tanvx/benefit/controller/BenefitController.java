package dev.tanvx.benefit.controller;

import dev.tanvx.benefit.dto.response.BenefitResponse;
import dev.tanvx.benefit.service.BenefitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Benefit Controller
 *
 * REST controller for managing benefit-related operations.
 * Provides endpoints for retrieving benefit information.
 */
@RestController
@RequestMapping("/api/v1/benefits")
@RequiredArgsConstructor
@Tag(name = "Benefit", description = "Benefit management APIs")
public class BenefitController {

  private final BenefitService benefitService;

  /**
   * Retrieves a benefit by its ID
   *
   * @param id The ID of the benefit to retrieve
   * @return ResponseEntity containing the benefit information
   */
  @Operation(
      summary = "Get benefit by ID",
      description = "Retrieves a benefit by its unique identifier"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "Benefit found",
          content = @Content(schema = @Schema(implementation = BenefitResponse.class))
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Benefit not found"
      )
  })
  @GetMapping("/{id}")
  public ResponseEntity<BenefitResponse> getBenefitById(
      @Parameter(description = "ID of the benefit to retrieve", required = true)
      @PathVariable Long id) {
    return ResponseEntity.ok(benefitService.getBenefitById(id));
  }
} 