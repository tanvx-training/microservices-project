package dev.tanvx.business_service.service;

import dev.tanvx.business_service.dto.request.staff.StaffByIdRequestDTO;
import dev.tanvx.business_service.dto.request.staff.StaffRequestDTO;
import dev.tanvx.business_service.dto.request.staff.StaffUpdateRequestDTO;
import dev.tanvx.business_service.dto.response.staff.StaffByIdResponseDTO;
import dev.tanvx.business_service.dto.response.staff.StaffCreateResponseDTO;
import dev.tanvx.business_service.dto.response.staff.StaffDeleteResponseDTO;
import dev.tanvx.business_service.dto.response.staff.StaffUpdateResponseDTO;
import dev.tanvx.business_service.dto.response.staff.StaffsResponseDTO;
import dev.tanvx.common_library.exception.ServiceException;
import org.springframework.data.domain.Page;

public interface StaffService {

  String STAFF_NOT_FOUND = "STAFF_NOT_FOUND";

  Page<StaffsResponseDTO> getStaffs(StaffRequestDTO requestDTO);

  StaffByIdResponseDTO getStaffById(StaffByIdRequestDTO requestDTO) throws ServiceException;

  StaffCreateResponseDTO createStaff(StaffCreateResponseDTO requestDTO);

  StaffUpdateResponseDTO updateStaff(Integer staffId, StaffUpdateRequestDTO requestDTO);

  StaffDeleteResponseDTO deleteStaff(Integer staffId);
}
