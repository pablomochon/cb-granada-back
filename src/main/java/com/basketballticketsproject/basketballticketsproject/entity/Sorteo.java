package com.basketballticketsproject.basketballticketsproject.entity;

import jakarta.persistence.*;
import jakarta.servlet.http.Part;
import lombok.*;

import java.util.*;

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
                ", usuarios=" + usuarios +
                '}';
    }
}
