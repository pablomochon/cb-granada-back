package com.basketballticketsproject.basketballticketsproject.service;

import com.basketballticketsproject.basketballticketsproject.entity.Usuario;
import com.basketballticketsproject.basketballticketsproject.repo.SorteoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SorteoService {

    @Autowired
    private SorteoRepo sorteoRepo;

    public List<Usuario> getUsuariosSorteo(String fecha) {
        return sorteoRepo.getUsuarioaParticipantes(fecha);
    }
}
