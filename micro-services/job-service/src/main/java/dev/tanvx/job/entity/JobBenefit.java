package dev.tanvx.job.entity;

import dev.tanvx.common_library.model.AbstractAuditEntity;
import dev.tanvx.job.entity.id.JobBenefitId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "job_benefits")
@IdClass(JobBenefitId.class)
public class JobBenefit extends AbstractAuditEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @Id
    @Column(name = "benefit_id", nullable = false)
    private Integer benefitId;

    private Boolean inferred;
}
