package com.basketballticketsproject.basketballticketsproject.service;

import com.basketballticketsproject.basketballticketsproject.model.Partido;
import com.basketballticketsproject.basketballticketsproject.repo.PartidoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidoService {

    @Autowired
    PartidoRepo partidoRepo;

    public Partido addPartido(Partido partido) {
        return partidoRepo.save(partido);
    }

    public int getUsuariosConfirmados() {
        return partidoRepo.getUsuariosConfirmados();
    }
}
