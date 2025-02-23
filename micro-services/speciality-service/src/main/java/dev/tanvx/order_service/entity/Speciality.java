package dev.tanvx.order_service.entity;

import dev.tanvx.common_library.model.AbstractAuditEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "specialities")
public class Speciality extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specialityId;

    @Column(nullable = false, length = 255)
    private String specialityName;
}
