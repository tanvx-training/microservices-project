package dev.tanvx.customerservice.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Set;
import lombok.*;

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
  private Integer customerId;

  @Column(name = "store_id", nullable = false)
  private Short storeId;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "email")
  private String email;

  @Column(name = "address_id", nullable = false)
  private Short addressId;

  @Column(name = "active_bool", nullable = false)
  private Boolean activeBool = true;

  @Column(name = "create_date", nullable = false)
  private LocalDate createDate = LocalDate.now();

  @Column(name = "last_update")
  private ZonedDateTime lastUpdate;

  @Column(name = "active_int")
  private Integer activeInt;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Payment> payments;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Rental> rentals;
}
