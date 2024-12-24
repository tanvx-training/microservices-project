package dev.tanvx.addressservice.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "address2")
    private String address2;

    @Column(name = "district", nullable = false)
    private String district;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", nullable = false, referencedColumnName = "city_id",
            foreignKey = @ForeignKey(name = "fk_address_city"))
    private City city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "last_update", nullable = false)
    private OffsetDateTime lastUpdate;
}
