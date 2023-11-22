package com.basketballticketsproject.basketballticketsproject.service;

import com.basketballticketsproject.basketballticketsproject.entity.Partido;
import com.basketballticketsproject.basketballticketsproject.entity.Sorteo;
import com.basketballticketsproject.basketballticketsproject.entity.Usuario;
import com.basketballticketsproject.basketballticketsproject.repo.SorteoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
public class SorteoService {

    @Autowired
    private SorteoRepo sorteoRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    public List<Usuario> getUsuariosSorteo(String fecha) {
        return sorteoRepo.getUsuarioaParticipantes(fecha);
    }


    public List<Sorteo> getSorteos() {
        return sorteoRepo.findAll();
    }

    public Sorteo saveUsuarioSorteo(UUID idUser, String fecha){
        Set<Usuario> usuarioSet = new HashSet<>();
        Sorteo sorteo = new Sorteo();
        Usuario usuario = usuarioRepo.findById(idUser).orElse(null);
        if (!ObjectUtils.isEmpty(usuario)) {
            usuarioSet.add(usuario);
            System.out.println("HOLAAAAAAA " + usuario.toString());
            sorteo.setUsuarios(usuarioSet);
            sorteo.setPartido(Partido.builder().fechaPartido(fecha).build());
        }
      return sorteoRepo.save(sorteo);
    }

}
