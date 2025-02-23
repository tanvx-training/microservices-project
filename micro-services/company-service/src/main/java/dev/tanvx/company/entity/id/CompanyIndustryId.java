package dev.tanvx.company.entity.id;

import java.io.Serializable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class CompanyIndustryId implements Serializable {
    private Long companyId;
    private Integer industryId;
}
