package com.basketballticketsproject.basketballticketsproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue
    private UUID userId;

    private String name;

    @Column(unique = true)
    private String email;

    private int asistencia_previa;

    @OneToOne (mappedBy = "usuario")
    private Partido partido;
}
