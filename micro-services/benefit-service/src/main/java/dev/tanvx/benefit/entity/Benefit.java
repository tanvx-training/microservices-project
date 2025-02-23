package dev.tanvx.benefit.entity;

import dev.tanvx.common_library.model.AbstractAuditEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "benefits")
public class Benefit extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long benefitId;

    @Column(nullable = false, length = 255)
    private String type;
}
