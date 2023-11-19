package com.basketballticketsproject.basketballticketsproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Partido {

    @Id
    @GeneratedValue
    private UUID partidoId;

    @OneToMany(mappedBy = "partido")
    private List<Usuario> listaUsuarios;

    private String equipoLocal;

    private String equipoVisitante;

    private Date fechaPartido;

    private Boolean confirmacion;
}
