package dev.tanvx.addressservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "city", nullable = false)
    private String city;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false, referencedColumnName = "country_id",
            foreignKey = @ForeignKey(name = "fk_city_country"))
    private Country country;

    @Column(name = "last_update", nullable = false)
    private OffsetDateTime lastUpdate;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;
}