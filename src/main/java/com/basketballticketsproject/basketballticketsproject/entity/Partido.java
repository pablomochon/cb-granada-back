package com.basketballticketsproject.basketballticketsproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OrderBy
    private String fechaPartido;


    private String nombrePartido;

    @OneToMany(mappedBy = "partido", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "partido_usuarios",
            joinColumns = { @JoinColumn(name = "partido_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "usuario_id", referencedColumnName = "userId") })
    private Set<Usuario> usuarios = new LinkedHashSet<>();


    @Override
    public String toString() {
        return "Partido{" +
                "id=" + id +
                ", fechaPartido='" + fechaPartido + '\'' +
                '}';
    }
}
