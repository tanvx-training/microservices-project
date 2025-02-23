package dev.tanvx.job.entity;

import dev.tanvx.common_library.model.AbstractAuditEntity;
import dev.tanvx.job.entity.id.JobSkillId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "job_skills")
@IdClass(JobSkillId.class)
public class JobSkill extends AbstractAuditEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @Id
    @Column(name = "skill_id", nullable = false)
    private Integer skillId;
}
