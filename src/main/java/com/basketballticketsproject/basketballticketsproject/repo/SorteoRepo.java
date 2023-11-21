package com.basketballticketsproject.basketballticketsproject.repo;

import com.basketballticketsproject.basketballticketsproject.entity.Sorteo;
import com.basketballticketsproject.basketballticketsproject.entity.Usuario;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SorteoRepo extends JpaRepository<Sorteo, UUID> {

    @Query(value = "SELECT usuario FROM Sorteo WHERE id_fecha_partido = ?1", nativeQuery = true)
    public List<Usuario> getUsuarioaParticipantes(String fecha);


    @Query(value = "INSERT INTO Sorteo VALUES () Sorteo WHERE id_fecha_partido = ?1", nativeQuery = true)
    void saveSorteo(String fecha, Sorteo sorteo);
}
