package dev.tanvx.job.entity;

import dev.tanvx.common_library.model.AbstractAuditEntity;
import dev.tanvx.job.entity.id.JobIndustryId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "job_industries")
@IdClass(JobIndustryId.class)
public class JobIndustry extends AbstractAuditEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @Id
    @Column(name = "industry_id", nullable = false)
    private Integer industryId;
}
