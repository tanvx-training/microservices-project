package dev.tanvx.company.entity;

import dev.tanvx.common_library.model.AbstractAuditEntity;
import dev.tanvx.company.entity.id.CompanySpecialityId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "company_specialities")
@IdClass(CompanySpecialityId.class)
public class CompanySpeciality extends AbstractAuditEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Id
    @Column(name = "speciality_id", nullable = false)
    private Integer specialityId;
}
