package dev.tanvx.posting.entity;

import dev.tanvx.common_library.model.AbstractAuditEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_postings")
public class JobPosting extends AbstractAuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long postingId;

  @Column(name = "job_id", nullable = false)
  private Long jobId;

  private String companyName;
  private Integer views;
  private Integer applies;
  private LocalDateTime originalListedTime;
  private LocalDateTime listedTime;
  private String postingDomain;
  private String jobPostingUrl;
  private String applicationUrl;
  private String applicationType;
  private Boolean sponsored;
  private Double normalizedSalary;
  private String fips;
}
