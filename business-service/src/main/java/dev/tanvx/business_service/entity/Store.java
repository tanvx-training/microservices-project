package dev.tanvx.business_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "store")
public class Store {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "store_id")
  private Integer storeId;

  @OneToOne
  @JoinColumn(name = "manager_staff_id", nullable = false)
  private Staff manager;

  @Column(name = "address_id", nullable = false)
  private Integer addressId;

  @Column(name = "delete_flg", nullable = false)
  private boolean deleteFlg;

  @Column(name = "last_update", nullable = false)
  private ZonedDateTime lastUpdate;
}
