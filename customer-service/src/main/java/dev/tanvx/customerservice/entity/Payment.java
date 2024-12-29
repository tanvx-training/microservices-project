package dev.tanvx.customerservice.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.*;

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
  private Integer paymentId;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @Column(name = "staff_id", nullable = false)
  private Integer staffId;

  @Column(name = "rental_id", nullable = false)
  private Integer rentalId;

  @Column(name = "amount", nullable = false, precision = 5, scale = 2)
  private BigDecimal amount;

  @Column(name = "payment_date", nullable = false)
  private ZonedDateTime paymentDate;
}
