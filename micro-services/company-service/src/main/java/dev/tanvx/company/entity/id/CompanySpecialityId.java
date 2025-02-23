package dev.tanvx.company.entity.id;

import java.io.Serializable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class CompanySpecialityId implements Serializable {
    private Long companyId;
    private Integer specialityId;
}
