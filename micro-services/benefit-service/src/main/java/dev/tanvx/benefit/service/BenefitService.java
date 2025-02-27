package dev.tanvx.benefit.service;

import dev.tanvx.benefit.dto.response.BenefitResponse;
import dev.tanvx.common_library.exception.BusinessException;

/**
 * Benefit Service Interface
 * 
 * Defines the contract for benefit-related business operations.
 * This interface specifies the methods that must be implemented
 * to handle benefit management functionality.
 */
public interface BenefitService {
    
    /**
     * Retrieves a benefit by its ID
     * 
     * @param id The ID of the benefit to retrieve
     * @return BenefitResponse containing the benefit information
     * @throws BusinessException if the benefit is not found
     */
    BenefitResponse getBenefitById(Long id);
} 