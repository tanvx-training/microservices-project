package dev.tanvx.catalogservice.entity;

import lombok.*;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "store")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer storeId;

    @Column(name = "manager_staff_id", nullable = false)
    private Short managerStaffId;

    @Column(name = "address_id", nullable = false)
    private Short addressId;

    @Column(name = "last_update", nullable = false)
    private OffsetDateTime lastUpdate;
}
