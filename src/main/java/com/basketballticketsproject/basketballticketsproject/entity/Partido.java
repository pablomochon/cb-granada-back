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
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OrderBy
    private String fechaPartido;


    private String nombrePartido;

    @OneToMany(mappedBy = "partido", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;


    @Override
    public String toString() {
        return "Partido{" +
                "id=" + id +
                ", fechaPartido='" + fechaPartido + '\'' +
                '}';
    }
}
