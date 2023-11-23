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
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String fechaPartido;

    @Override
    public String toString() {
        return "Partido{" +
                "id=" + id +
                ", fechaPartido='" + fechaPartido + '\'' +
                '}';
    }
}
