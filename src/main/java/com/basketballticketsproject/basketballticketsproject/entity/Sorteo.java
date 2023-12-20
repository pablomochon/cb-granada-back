package com.basketballticketsproject.basketballticketsproject.entity;

import jakarta.persistence.*;
import lombok.*;

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


    @Override
    public String toString() {
        return "Sorteo{" +
                "idSorteo=" + idSorteo +
                ", partido=" + partido +
                '}';
    }
}
