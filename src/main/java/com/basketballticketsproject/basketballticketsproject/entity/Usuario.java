package com.basketballticketsproject.basketballticketsproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    private String nombre;

    private String apellido;

    @Column(unique = true)
    private String email;

    private int asistencia_previa;

    private String entrada;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "usuarios")
    @JsonIgnore
    private Set<Sorteo> sorteos = new HashSet<>();


    @Override
    public String toString() {
        return "Usuario{" +
                "userId=" + userId +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", asistencia_previa=" + asistencia_previa +
                ", entrada=" + entrada +
                ", sorteos=" + sorteos +
                '}';
    }
}
