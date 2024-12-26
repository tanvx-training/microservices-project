package dev.tanvx.catalogservice.entity;

import java.time.OffsetDateTime;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "language")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Integer languageId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_update", nullable = false)
    private OffsetDateTime lastUpdate;
}

