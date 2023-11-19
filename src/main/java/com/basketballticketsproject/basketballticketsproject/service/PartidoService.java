package com.basketballticketsproject.basketballticketsproject.service;

import com.basketballticketsproject.basketballticketsproject.model.Partido;
import com.basketballticketsproject.basketballticketsproject.repo.PartidoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PartidoService {

    @Autowired
    PartidoRepo partidoRepo;

    public Partido addPartido(Partido partido) {
        return partidoRepo.save(partido);
    }

    public List<Partido> getPartdios() {
        return partidoRepo.findAll();
    }

    public int getUsuariosConfirmados() {
        return partidoRepo.getUsuariosConfirmados();
    }

    public Optional<Partido> getPartidoById(UUID id) {
        return partidoRepo.findById(id);
    }
}
