package dev.tanvx.company.entity;

import dev.tanvx.common_library.model.AbstractAuditEntity;
import dev.tanvx.company.entity.id.CompanyIndustryId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "company_industries")
@IdClass(CompanyIndustryId.class)
public class CompanyIndustry extends AbstractAuditEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Id
    @Column(name = "industry_id", nullable = false)
    private Integer industryId;
}
