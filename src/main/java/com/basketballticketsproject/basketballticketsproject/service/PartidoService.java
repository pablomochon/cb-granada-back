package com.basketballticketsproject.basketballticketsproject.service;

import com.basketballticketsproject.basketballticketsproject.Excel.FechaPartido;
import com.basketballticketsproject.basketballticketsproject.entity.Partido;
import com.basketballticketsproject.basketballticketsproject.repo.PartidoRepo;
import com.poiji.bind.Poiji;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
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

    public List<FechaPartido> getFechasPartidos() {
        File archivoFechas = new File("fechas_partidos.xls");
        return Poiji.fromExcel(archivoFechas, FechaPartido.class);
    }

    public void crearCarpetasConFechas() {
        String path = "C:\\Fechas";
        File carpetaConFechas = new File(path);
        if (carpetaConFechas.mkdir()){
            System.out.println("CREADAAAAA");
        } else {
            System.out.println("falloooooo");
        }

        for (FechaPartido fechaPartido : getFechasPartidos()) {
            File carpeta = new File(path + "\\" + fechaPartido.getFecha());
            if (!carpeta.exists()) {
                carpeta.mkdir();
            }
        }
    }
}
