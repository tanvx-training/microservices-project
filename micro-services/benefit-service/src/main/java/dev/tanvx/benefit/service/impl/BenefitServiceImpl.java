package dev.tanvx.benefit.service.impl;

import dev.tanvx.benefit.dto.response.BenefitResponse;
import dev.tanvx.benefit.entity.Benefit;
import dev.tanvx.benefit.repository.BenefitRepository;
import dev.tanvx.benefit.service.BenefitService;
import dev.tanvx.common_library.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Benefit Service Implementation
 * <p>
 * Implements the BenefitService interface to provide concrete implementations of benefit-related
 * business operations. This class handles the business logic for managing benefits, including
 * validation and data transformation.
 */
@Service
@RequiredArgsConstructor
public class BenefitServiceImpl implements BenefitService {

  private final BenefitRepository benefitRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  @Transactional(readOnly = true)
  public BenefitResponse getBenefitById(Long id) {
    Benefit benefit = benefitRepository.findById(id)
        .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,
            String.format("Benefit with id=%s is not existed.", id)));

    return mapToResponse(benefit);
  }

  /**
   * Maps a Benefit entity to a BenefitResponse DTO
   *
   * @param benefit The benefit entity to map
   * @return BenefitResponse containing the mapped data
   */
  private BenefitResponse mapToResponse(Benefit benefit) {
    return BenefitResponse.builder()
        .benefitId(benefit.getBenefitId())
        .type(benefit.getType())
        .createdBy(benefit.getCreatedBy())
        .createdOn(benefit.getCreatedOn())
        .lastModifiedBy(benefit.getLastModifiedBy())
        .lastModifiedOn(benefit.getLastModifiedOn())
        .build();
  }
} 