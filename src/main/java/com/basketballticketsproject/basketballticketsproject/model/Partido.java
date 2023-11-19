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

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String equipoLocal;

    private String equipoVisitante;

    private String numEntrada;

    private Date fechaPartido;

    private Boolean confirmacion;
}
