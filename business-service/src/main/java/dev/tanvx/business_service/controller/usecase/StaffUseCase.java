package dev.tanvx.business_service.controller.usecase;

import dev.tanvx.business_service.dto.request.staff.StaffByIdRequestDTO;
import dev.tanvx.business_service.dto.request.staff.StaffCreateRequestDTO;
import dev.tanvx.business_service.dto.request.staff.StaffRequestDTO;
import dev.tanvx.business_service.dto.request.staff.StaffUpdateRequestDTO;
import dev.tanvx.business_service.dto.response.staff.StaffByIdResponseDTO;
import dev.tanvx.business_service.dto.response.staff.StaffCreateResponseDTO;
import dev.tanvx.business_service.dto.response.staff.StaffDeleteResponseDTO;
import dev.tanvx.business_service.dto.response.staff.StaffUpdateResponseDTO;
import dev.tanvx.business_service.dto.response.staff.StaffsResponseDTO;
import dev.tanvx.business_service.entity.Staff;
import dev.tanvx.business_service.service.StaffService;
import dev.tanvx.business_service.service.StoreService;
import dev.tanvx.common_library.exception.BusinessException;
import dev.tanvx.common_library.exception.ServiceException;
import dev.tanvx.common_library.model.ApiResponse;
import dev.tanvx.common_library.model.MessageProperties;
import dev.tanvx.common_library.model.ResponseConstants;
import dev.tanvx.common_library.util.MessageUtils;
import dev.tanvx.common_library.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StaffUseCase {

  private final StaffService staffService;

  private final ValidationUtils validationUtils;

  private final MessageUtils messageUtils;

  @Transactional(readOnly = true)
  public ApiResponse<Page<StaffsResponseDTO>> getStaffs(StaffRequestDTO requestDTO) {
    try {
      // Validate page and size parameter
      validationUtils.validateRequest(requestDTO);

      // Validate sort parameter
      validationUtils.validateSortParam(requestDTO.getSort(), Staff.class);

      Page<StaffsResponseDTO> staffsResponseDTOPage = staffService.getStaffs(requestDTO);
      return ApiResponse.<Page<StaffsResponseDTO>>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(staffsResponseDTOPage)
          .build();
    } catch (Exception e) {
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional(readOnly = true)
  public ApiResponse<StaffByIdResponseDTO> getStaffById(Integer staffId) {
    try {
      StaffByIdRequestDTO requestDTO = StaffByIdRequestDTO.builder()
          .staffId(staffId)
          .build();
      StaffByIdResponseDTO staffByIdResponseDTO = staffService.getStaffById(requestDTO);
      return ApiResponse.<StaffByIdResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(staffByIdResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (StaffService.STAFF_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<StaffCreateResponseDTO> createStaff(StaffCreateRequestDTO requestDTO) {
    try {
      // Validate request
      validationUtils.validateRequest(requestDTO);

      StaffCreateResponseDTO staffCreateResponseDTO = staffService.createStaff(requestDTO);

      return ApiResponse.<StaffCreateResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(staffCreateResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (StoreService.STORE_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.BAD_REQUEST,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<StaffUpdateResponseDTO> updateStaff(Integer staffId,
      StaffUpdateRequestDTO requestDTO) {
    try {
      // Validate request
      validationUtils.validateRequest(requestDTO);

      StaffUpdateResponseDTO staffUpdateResponseDTO = staffService.updateStaff(staffId,
          requestDTO);

      return ApiResponse.<StaffUpdateResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(staffUpdateResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (StaffService.STAFF_NOT_FOUND.equals(e.getCauseId())
          || StoreService.STORE_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }

  @Transactional
  public ApiResponse<StaffDeleteResponseDTO> deleteStaff(Integer staffId) {
    try {
      StaffDeleteResponseDTO staffDeleteResponseDTO = staffService.deleteStaff(staffId);
      return ApiResponse.<StaffDeleteResponseDTO>builder()
          .status(ResponseConstants.SUCCESS_STATUS)
          .message(ResponseConstants.SUCCESS_MESSAGE)
          .data(staffDeleteResponseDTO)
          .build();
    } catch (ServiceException e) {
      if (StaffService.STAFF_NOT_FOUND.equals(e.getCauseId())) {
        throw new BusinessException(HttpStatus.NOT_FOUND,
            messageUtils.getMessage(e.getCauseId(), null));
      }
      throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
          messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
    }
  }
}
