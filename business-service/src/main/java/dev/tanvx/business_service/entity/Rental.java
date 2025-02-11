package dev.tanvx.business_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  @Column(name = "rental_id")
  private Integer rentalId;

  @Column(name = "rental_date", nullable = false)
  private ZonedDateTime rentalDate;

  @Column(name = "inventory_id", nullable = false)
  private Integer inventoryId;

  @Column(name = "customer_id", nullable = false)
  private Integer customerId;

  @Column(name = "return_date")
  private ZonedDateTime returnDate;

  @ManyToOne
  @JoinColumn(name = "staff_id", nullable = false)
  private Staff staff;

  @Column(name = "last_update", nullable = false)
  private ZonedDateTime lastUpdate;
}
