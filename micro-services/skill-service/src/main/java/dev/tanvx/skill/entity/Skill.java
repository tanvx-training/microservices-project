package dev.tanvx.skill.entity;

import dev.tanvx.common_library.model.AbstractAuditEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "skills")
public class Skill extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;

    @Column(nullable = false, length = 50)
    private String skillAbr;

    @Column(nullable = false, length = 255)
    private String skillName;
}
