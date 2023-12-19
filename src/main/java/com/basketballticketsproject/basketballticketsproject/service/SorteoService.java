package com.basketballticketsproject.basketballticketsproject.service;

import com.basketballticketsproject.basketballticketsproject.entity.Partido;
import com.basketballticketsproject.basketballticketsproject.entity.Sorteo;
import com.basketballticketsproject.basketballticketsproject.entity.Ticket;
import com.basketballticketsproject.basketballticketsproject.entity.Usuario;
import com.basketballticketsproject.basketballticketsproject.repo.PartidoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.SorteoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.UsuarioRepo;
import com.basketballticketsproject.basketballticketsproject.utils.EnviarEmailUsuarios;
import jdk.swing.interop.SwingInterOpUtils;
import org.apache.poi.sl.draw.geom.GuideIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Sorteo sorteos = sorteoRepo.findByFecha(fecha);
        return sorteos.getUsuarios();
    }


    public List<Sorteo> getSorteos() {
        return sorteoRepo.findAll();
    }

    public Sorteo saveUsuarioSorteo(UUID idUser, String fecha) {
        Usuario usuario = usuarioRepo.findById(idUser).orElse(null);
        Sorteo sorteo = new Sorteo();
        Sorteo sorteoFecha = sorteoRepo.findByFecha(fecha);
        if (sorteoFecha.getUsuarios().size() <= NUM_ENTRADAS && !ObjectUtils.isEmpty(sorteoFecha)) {
            if (!ObjectUtils.isEmpty(usuario)) {
                sorteoFecha.getUsuarios().add(usuario);
                usuario.getSorteos().add(sorteo);
                usuario.setAsistencia_previa(usuario.getAsistencia_previa() + 1);

                sorteoRepo.save(sorteoFecha);
            }
        }
        return sorteoFecha;
    }

    public void deleteUsuarioFromSorteo(UUID userID, String fecha) {
        Usuario usuario = usuarioRepo.findById(userID).orElse(null);

        Sorteo sorteoFecha = sorteoRepo.findByFecha(fecha);
        if (!ObjectUtils.isEmpty(usuario)) {
            sorteoFecha.getUsuarios().remove(usuario);
            sorteoFecha.setPartido(null);
            usuario.getSorteos().remove(sorteoFecha);
            if (usuario.getAsistencia_previa() <= 0) {
                usuario.setAsistencia_previa(usuario.getAsistencia_previa() - 1);
            }
            sorteoRepo.delete(sorteoFecha);
        }
    }

    public void enviarEntrada(UUID userID, String fecha) {
        Usuario usuario = usuarioRepo.findById(userID).orElse(null);
        Set<Usuario> usuariosSorteo = this.getUsuariosSorteo(fecha);

        //para comprobar si el usuario está inscrito al sorteo
        if(usuariosSorteo.contains(usuario)) {
            Partido partido = partidoRepo.findByFechaPartido(fecha);

            //filtro para obtener las entradas que no estan entregadas y cojo la primera
            Optional<Ticket> ticketToSend = partido.getTickets().stream().filter(ticket -> !ticket.isEntregada()).findFirst();
           if (ticketToSend.isPresent()) {

               //descodificar base64 en pdf
               EnviarEmailUsuarios.decodeBase64ToPdf(ticketToSend.get());
               ticketToSend.get().setEntregada(true);
           }
        }
    }

}
