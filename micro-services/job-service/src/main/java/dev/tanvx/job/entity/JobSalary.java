package dev.tanvx.job.entity;

import dev.tanvx.common_library.model.AbstractAuditEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "job_salaries")
public class JobSalary extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaryId;

    @OneToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    private Double maxSalary;
    private Double medSalary;
    private Double minSalary;
    private String payPeriod;
    private String currency;
    private String compensationType;
}
