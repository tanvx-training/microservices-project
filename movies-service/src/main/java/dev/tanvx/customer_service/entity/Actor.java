package dev.tanvx.customer_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "actor")
public class Actor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "actor_id")
  private Integer actorId;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "delete_flg", nullable = false)
  private boolean deleteFlg;

  @Column(name = "last_update", nullable = false)
  private ZonedDateTime lastUpdate;
}
