package com.basketballticketsproject.basketballticketsproject.service;

import com.basketballticketsproject.basketballticketsproject.entity.Partido;
import com.basketballticketsproject.basketballticketsproject.entity.Sorteo;
import com.basketballticketsproject.basketballticketsproject.entity.Ticket;
import com.basketballticketsproject.basketballticketsproject.entity.Usuario;
import com.basketballticketsproject.basketballticketsproject.repo.PartidoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.SorteoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.TicketRepo;
import com.basketballticketsproject.basketballticketsproject.repo.UsuarioRepo;
import com.basketballticketsproject.basketballticketsproject.utils.EnviarEmailUsuarios;
import jdk.swing.interop.SwingInterOpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.sl.draw.geom.GuideIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
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

    @Autowired
    private TicketRepo ticketRepo;

    public Set<Usuario> getUsuariosSorteo(String fecha) {
        Partido partido = partidoRepo.findByFechaPartido(fecha);
        return partido.getUsuarios();
    }


    public List<Sorteo> getSorteos() {
        return sorteoRepo.findAll();
    }

    public Partido saveUsuarioSorteo(UUID idUser, String fecha) {
        Usuario usuario = usuarioRepo.findById(idUser).orElse(null);
        Partido sorteoFecha = partidoRepo.findByFechaPartido(fecha);
        if (sorteoFecha.getUsuarios().size() <= NUM_ENTRADAS && !ObjectUtils.isEmpty(sorteoFecha)) {
            if (!ObjectUtils.isEmpty(usuario) && !sorteoFecha.getUsuarios().contains(usuario)) {
                sorteoFecha.getUsuarios().add(usuario);
                usuario.getPartidos().add(sorteoFecha);
                partidoRepo.save(sorteoFecha);
            }
        }
        return sorteoFecha;
    }

    public void deleteUsuarioFromSorteo(UUID userID, String fecha) {
        Usuario usuario = usuarioRepo.findById(userID).orElse(null);

        Partido sorteoFecha = partidoRepo.findByFechaPartido(fecha);
        if (!ObjectUtils.isEmpty(usuario)) {
            sorteoFecha.getUsuarios().remove(usuario);
            usuario.getPartidos().remove(sorteoFecha);

            partidoRepo.delete(sorteoFecha);
            usuarioRepo.delete(usuario);
        }
    }

    public byte[] enviarEntrada(String fecha, UUID user) {
        Usuario usuario = usuarioRepo.findById(user).orElse(null);
        Set<Usuario> usuariosSorteo = this.getUsuariosSorteo(fecha);
        byte[] entrada = new byte[0];
        //para comprobar si el usuario está inscrito al sorteo
        if(usuariosSorteo.contains(usuario) && usuario != null) {
            Partido partido = partidoRepo.findByFechaPartido(fecha);

            //filtro para obtener las entradas que no estan entregadas y cojo la primera
            Optional<Ticket> ticketToSend = partido.getTickets().stream().filter(ticket -> !ticket.isEntregada()).findFirst();
            if (ticketToSend.isPresent()) {
                //descodificar base64 en pdf
                ticketToSend.get().setEntregada(true);
                System.out.println("ENTRADA "+ ticketToSend.get().getEntrada());
                entrada = EnviarEmailUsuarios.decodeBase64ToPdf(ticketToSend.get());
                usuario.setEntrada(Collections.singletonList(StringUtils.join(partido.getFechaPartido(),
                        ticketToSend.get().getEntrada())));

                ticketRepo.updateEntregadaByPartidoEquals(true, partido);
                usuarioRepo.save(usuario);
            }
        }
        else {
            throw new ResponseMessage("No estas apuntado a este partido");
        }
        return entrada;
    }

}
