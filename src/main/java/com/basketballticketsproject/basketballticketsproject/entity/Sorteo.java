package com.basketballticketsproject.basketballticketsproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sorteo {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID idSorteo;

    @OneToMany(mappedBy = "sorteo")
    private List<Usuario> usuario;

    @OneToOne
    @JoinColumn(name = "id_fechaPartido")
    private Partido partido;
}
