package com.basketballticketsproject.basketballticketsproject.repo;

import com.basketballticketsproject.basketballticketsproject.entity.Sorteo;
import com.basketballticketsproject.basketballticketsproject.entity.Usuario;
import jakarta.transaction.Transactional;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface SorteoRepo extends JpaRepository<Sorteo, UUID> {


    @Query(value = "SELECT * FROM sorteo_usuarios WHERE sorteo_id = " +
            "(SELECT id_sorteo FROM sorteo WHERE id_partido = ?1)", nativeQuery = true)
    List<Sorteo> getUsuarios(String fecha);

    @Query(value = "SELECT * FROM sorteo WHERE fecha_artido = ?1", nativeQuery = true)
    Sorteo getUsuariosByFecha(String fecha);

    @Transactional
    @Query(value = "SELECT s FROM Sorteo s WHERE s.partido.fechaPartido = ?1")
    Sorteo findByFecha(String fecha);

    @Query(value = "SELECT idSorteo FROM Sorteo s WHERE s.partido.fechaPartido = ?1")
    Sorteo getIdByFecha(String fecha);
}
