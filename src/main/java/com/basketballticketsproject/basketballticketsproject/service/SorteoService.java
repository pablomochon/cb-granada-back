package com.basketballticketsproject.basketballticketsproject.service;

import com.basketballticketsproject.basketballticketsproject.entity.Partido;
import com.basketballticketsproject.basketballticketsproject.entity.Sorteo;
import com.basketballticketsproject.basketballticketsproject.entity.Usuario;
import com.basketballticketsproject.basketballticketsproject.repo.PartidoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.SorteoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.UsuarioRepo;
import jdk.swing.interop.SwingInterOpUtils;
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

    public Set<Usuario> getUsuariosSorteo(String fecha) {
        List<Sorteo> sorteos = sorteoRepo.findByFecha(fecha);
        Set<Usuario> usuarios = new HashSet<>();
        sorteos.forEach(s -> usuarios.addAll(s.getUsuarios()));
        return usuarios;
    }


    public List<Sorteo> getSorteos() {
        return sorteoRepo.findAll();
    }

    public Sorteo saveUsuarioSorteo(UUID idUser, String fecha) {
        Usuario usuario = usuarioRepo.findById(idUser).orElse(null);
        Sorteo sorteo = new Sorteo();
        if (sorteoRepo.findByFecha(fecha).size() <= NUM_ENTRADAS) {
            if (!ObjectUtils.isEmpty(usuario)) {
                sorteo.getUsuarios().add(usuario);
                sorteo.setPartido(Partido.builder().fechaPartido(fecha).build());
                usuario.getSorteos().add(sorteo);
                usuario.setAsistencia_previa(usuario.getAsistencia_previa() + 1);

                //System.out.println("SORTEO----" + sorteo);
                sorteoRepo.save(sorteo);
            }
        }
        return null;
    }


     /*public Sorteo getUsuariosSorteoByFecha(String fecha) {
        return sorteoRepo.getUsuariosByFecha(fecha);
    }*/
}
