package dev.tanvx.company.entity;

import dev.tanvx.common_library.model.AbstractAuditEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer companySize;
    private String state;
    private String country;
    private String city;
    private String zipCode;

    @Column(columnDefinition = "TEXT")
    private String address;

    private String url;

    @OneToMany(mappedBy = "company")
    private List<CompanyMetric> companyMetrics;
}
