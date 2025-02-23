package dev.tanvx.job.entity;

import dev.tanvx.common_library.model.AbstractAuditEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "jobs")
public class Job extends AbstractAuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long jobId;

  @Column(name = "company_id", nullable = false)
  private Integer companyId;

  @Column(nullable = false)
  private String companyName;

  @Column(nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String description;

  private Double maxSalary;
  private Double minSalary;
  private Double medSalary;
  private String payPeriod;
  private String workType;
  private String formattedExperienceLevel;
  private Boolean remoteAllowed;
  private String location;
  private String zipCode;
  private String currency;
  private String compensationType;

  @Column(columnDefinition = "TEXT")
  private String skillsDesc;

  private LocalDateTime expiry;
  private LocalDateTime closedTime;

  @OneToOne(mappedBy = "job")
  private JobSalary jobSalary;
}
