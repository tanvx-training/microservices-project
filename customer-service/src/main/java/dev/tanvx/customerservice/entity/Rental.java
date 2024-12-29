package dev.tanvx.customerservice.entity;

import jakarta.persistence.*;
import java.time.ZonedDateTime;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rental")
public class Rental {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer rentalId;

  @Column(name = "rental_date", nullable = false)
  private ZonedDateTime rentalDate;

  @Column(name = "inventory_id", nullable = false)
  private Integer inventoryId;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @Column(name = "return_date")
  private ZonedDateTime returnDate;

  @Column(name = "staff_id", nullable = false)
  private Integer staffId;

  @Column(name = "last_update", nullable = false)
  private ZonedDateTime lastUpdate = ZonedDateTime.now();
}
