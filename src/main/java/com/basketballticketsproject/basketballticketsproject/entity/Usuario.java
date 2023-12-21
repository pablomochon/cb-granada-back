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

    private String password;

    @Column(unique = true)
    private String email;

    private boolean isAdmin = false;


    private List<String> entrada;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;


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
                ", entrada=" + entrada +
                ", partidos=" + sorteos +
                '}';
    }

}
