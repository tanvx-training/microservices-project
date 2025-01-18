package dev.tanvx.customer_service.entity;

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
@Table(name = "inventory")
public class Inventory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "inventory_id")
  private Integer inventoryId;

  @ManyToOne
  @JoinColumn(name = "film_id", nullable = false)
  private Film film;

  @Column(name = "store_id", nullable = false)
  private Integer storeId;

  @Column(name = "last_update", nullable = false)
  private ZonedDateTime lastUpdate;
}
