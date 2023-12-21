package com.basketballticketsproject.basketballticketsproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sorteo {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID idSorteo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_partido", referencedColumnName = "id")
    private Partido partido;

    @ManyToMany
    @JoinTable(name = "sorteo_usuarios",
            joinColumns = { @JoinColumn(name = "sorteo_id") },
            inverseJoinColumns = { @JoinColumn(name = "usuario_id") })
    private Set<Usuario> usuarios = new HashSet<>();
    @Override
    public String toString() {
        return "Sorteo{" +
                "idSorteo=" + idSorteo +
                ", partido=" + partido +
                '}';
    }
}
