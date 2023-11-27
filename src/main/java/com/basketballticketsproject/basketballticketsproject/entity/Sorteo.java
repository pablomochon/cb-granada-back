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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sorteo_usuarios",
            joinColumns = { @JoinColumn(name = "sorteo_id", referencedColumnName = "idSorteo") },
            inverseJoinColumns = { @JoinColumn(name = "usuario_id", referencedColumnName = "userId") })
    private Set<Usuario> usuarios = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "Sorteo{" +
                "idSorteo=" + idSorteo +
                ", partido=" + partido +
                ", usuarios=" + usuarios +
                '}';
    }
}
