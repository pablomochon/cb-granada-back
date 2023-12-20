package com.basketballticketsproject.basketballticketsproject.repo;

import com.basketballticketsproject.basketballticketsproject.entity.Partido;
import com.basketballticketsproject.basketballticketsproject.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepo extends JpaRepository<Ticket, UUID> {
    @Transactional
    @Modifying
    @Query("update Ticket t set t.entregada = ?1 where t.partido = ?2")
    int updateEntregadaByPartidoEquals(boolean entregada, @NonNull Partido partido);

    Optional<Ticket> findById(UUID id);

    Ticket findByEntrada(String entrada);
}
