package com.basketballticketsproject.basketballticketsproject.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;



@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Lob
    @Column(name = "pdfBase64", columnDefinition = "longtext")
    @JsonIgnore
    private String pdfBase64;

    private String entrada;

    private String fecha;

    private boolean entregada;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "partido_id")
    private Partido partido;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
