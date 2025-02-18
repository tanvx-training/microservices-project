package dev.tanvx.business_service.controller;

import dev.tanvx.business_service.controller.usecase.StaffUseCase;
import dev.tanvx.business_service.dto.request.staff.StaffCreateRequestDTO;
import dev.tanvx.business_service.dto.request.staff.StaffRequestDTO;
import dev.tanvx.business_service.dto.request.staff.StaffUpdateRequestDTO;
import dev.tanvx.business_service.dto.response.staff.StaffByIdResponseDTO;
import dev.tanvx.business_service.dto.response.staff.StaffCreateResponseDTO;
import dev.tanvx.business_service.dto.response.staff.StaffDeleteResponseDTO;
import dev.tanvx.business_service.dto.response.staff.StaffUpdateResponseDTO;
import dev.tanvx.business_service.dto.response.staff.StaffsResponseDTO;
import dev.tanvx.common_library.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/staffs")
public class StaffController {

  private final StaffUseCase staffUseCase;

  @GetMapping
  public ResponseEntity<ApiResponse<Page<StaffsResponseDTO>>> getStaffs(
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "sort", defaultValue = "staffId,asc") String sort) {
    return ResponseEntity.ok(staffUseCase.getStaffs(
        StaffRequestDTO.builder()
            .page(page)
            .size(size)
            .sort(sort)
            .build()
    ));
  }

  @GetMapping("/{staffId}/")
  public ResponseEntity<ApiResponse<StaffByIdResponseDTO>> getStaffById(
      @PathVariable(value = "staffId") Integer staffId) {
    return ResponseEntity.ok(staffUseCase.getStaffById(staffId));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<StaffCreateResponseDTO>> createStaff(
      @RequestBody StaffCreateRequestDTO requestDTO) {
    return ResponseEntity.ok(staffUseCase.createStaff(requestDTO));
  }

  @PutMapping("/{staffId}/")
  public ResponseEntity<ApiResponse<StaffUpdateResponseDTO>> updateStaff(
      @PathVariable(value = "staffId") Integer staffId,
      @RequestBody StaffUpdateRequestDTO requestDTO) {
    return ResponseEntity.ok(staffUseCase.updateStaff(staffId, requestDTO));
  }

  @DeleteMapping("/{staffId}/")
  public ResponseEntity<ApiResponse<StaffDeleteResponseDTO>> deleteStaff(
      @PathVariable(value = "staffId") Integer staffId) {
    return ResponseEntity.ok(staffUseCase.deleteStaff(staffId));
  }
}
