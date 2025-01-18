package dev.tanvx.customer_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
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
@Table(name = "customer")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customer_id")
  private Integer customerId;

  @Column(name = "store_id", nullable = false)
  private Integer storeId;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "email")
  private String email;

  @ManyToOne
  @JoinColumn(name = "address_id", nullable = false)
  private Address address;

  @Column(name = "active_bool", nullable = false)
  private Boolean activeBool;

  @Column(name = "create_date", nullable = false)
  private LocalDate createDate;

  @Column(name = "last_update", nullable = false)
  private ZonedDateTime lastUpdate;

  @Column(name = "active_int")
  private Integer activeInt;
}
