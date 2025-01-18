package dev.tanvx.customer_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
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
@Table(name = "payment")
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "payment_id")
  private Integer paymentId;

  @Column(name = "customer_id", nullable = false)
  private Integer customerId;

  @ManyToOne
  @JoinColumn(name = "staff_id", nullable = false)
  private Staff staff;

  @ManyToOne
  @JoinColumn(name = "rental_id", nullable = false)
  private Rental rental;

  @Column(name = "amount", nullable = false)
  private BigDecimal amount;

  @Column(name = "payment_date", nullable = false)
  private ZonedDateTime paymentDate;
}
