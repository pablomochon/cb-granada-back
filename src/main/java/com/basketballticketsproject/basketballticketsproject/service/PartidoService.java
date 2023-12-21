package com.basketballticketsproject.basketballticketsproject.service;

import com.basketballticketsproject.basketballticketsproject.Excel.FechaPartido;
import com.basketballticketsproject.basketballticketsproject.entity.Partido;
import com.basketballticketsproject.basketballticketsproject.entity.Sorteo;
import com.basketballticketsproject.basketballticketsproject.entity.Ticket;
import com.basketballticketsproject.basketballticketsproject.repo.PartidoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.SorteoRepo;
import com.basketballticketsproject.basketballticketsproject.repo.TicketRepo;
import com.poiji.bind.Poiji;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.basketballticketsproject.basketballticketsproject.utils.Constants.PATH_CARPETA_FECHAS_PARTIDOS;

@Service
public class PartidoService {

    @Autowired
    PartidoRepo partidoRepo;

    @Autowired
    SorteoRepo sorteoRepo;

    @Autowired
    TicketRepo ticketRepo;

    public Partido addPartido(final Partido partido) {
        return partidoRepo.save(partido);
    }

    public List<Partido> getPartdios() {
        return partidoRepo.findAll();
    }



    public Optional<Partido> getPartidoById(final UUID id) {
        return partidoRepo.findById(id);
    }

    public List<FechaPartido> getFechasPartidos() {
        File archivoFechas = new File("fechas_partidos.xls");

        return Poiji.fromExcel(archivoFechas, FechaPartido.class);
    }

    public void removePartido (UUID id) {
        partidoRepo.deleteById(id);
    }

    public void crearCarpetasConFechasdelExcel() {
        String path = PATH_CARPETA_FECHAS_PARTIDOS;
        File carpetaConFechas = new File(path);
        if (!carpetaConFechas.exists()) {
            carpetaConFechas.mkdir();
        }

        for (FechaPartido fechaPartido : getFechasPartidos()) {
            String fecha = fechaPartido.getFecha();
            Partido partido = new Partido();
            partido.setFechaPartido(fecha);
            partidoRepo.save(partido);
            Sorteo sorteo = new Sorteo();
            sorteo.setPartido(partido);
            sorteoRepo.save(sorteo);
            File carpeta = new File(path + "\\" + fecha);
            if (!carpeta.exists()) {
                carpeta.mkdir();
            }
        }
    }

    public List<Ticket> getTickets() {
        return ticketRepo.findAll();
    }
}
