package com.basketballticketsproject.basketballticketsproject.repo;

import com.basketballticketsproject.basketballticketsproject.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PartidoRepo extends JpaRepository<Partido, UUID> {

    @Query(value = "SELECT COUNT(*) FROM Partido WHERE confirmacion = true", nativeQuery = true)
    int getUsuariosConfirmados();
}
