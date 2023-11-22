package com.basketballticketsproject.basketballticketsproject.entity;

import jakarta.persistence.*;
import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sorteo {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID idSorteo;

    @OneToOne
    @JoinColumn(name = "id_partido")
    private Partido partido;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "sorteo_usuarios",
            joinColumns = { @JoinColumn(name = "sorteo_id") },
            inverseJoinColumns = { @JoinColumn(name = "usuario_id") })
    private Set<Usuario> usuarios = new HashSet<>();

}
