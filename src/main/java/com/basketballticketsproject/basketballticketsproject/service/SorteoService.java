package com.basketballticketsproject.basketballticketsproject.service;

import com.basketballticketsproject.basketballticketsproject.entity.Partido;
import com.basketballticketsproject.basketballticketsproject.entity.Sorteo;
import com.basketballticketsproject.basketballticketsproject.entity.Usuario;
import com.basketballticketsproject.basketballticketsproject.repo.PartidoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.SorteoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

import static com.basketballticketsproject.basketballticketsproject.utils.Constants.NUM_ENTRADAS;

@Service
public class SorteoService {

    @Autowired
    private SorteoRepo sorteoRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private PartidoRepo partidoRepo;

    public List<Usuario> getUsuariosSorteo(String fecha) {
        return null;

    }


    public List<Sorteo> getSorteos() {
        return sorteoRepo.findAll();
    }

    public Sorteo saveUsuarioSorteo(UUID idUser, String fecha){
        Set<Usuario> usuarioSet = new HashSet<>();
        Sorteo sorteo = new Sorteo();
        Usuario usuario = usuarioRepo.findById(idUser).orElse(null);
        if (sorteo.getUsuarios().size() <= NUM_ENTRADAS) {
            if (!ObjectUtils.isEmpty(usuario)) {
                usuario.setEntrada(true);
                usuarioSet.add(usuario);
                sorteo.setUsuarios(usuarioSet);
                sorteo.setPartido(Partido.builder().fechaPartido(fecha).build());
            }
        } else {
            return null;
        }

      return sorteoRepo.save(sorteo);
    }

}
