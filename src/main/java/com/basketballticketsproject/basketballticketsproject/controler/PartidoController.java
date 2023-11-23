package com.basketballticketsproject.basketballticketsproject.controler;


import com.basketballticketsproject.basketballticketsproject.entity.Partido;
import com.basketballticketsproject.basketballticketsproject.service.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cbgranada-api/v1")
public class PartidoController {

    @Autowired
    PartidoService partidoService;

    @PostMapping("/addPartido")
    public Partido addPartido(@RequestBody Partido partido) {
        return partidoService.addPartido(partido);
    }

    @DeleteMapping("/borrarPartido/{id}")
    public void borrarPartidoById(@PathVariable UUID id) {
        partidoService.removePartido(id);
    }

    @GetMapping("/getPartidos")
    public List<Partido> getPartidos(){
        return partidoService.getPartdios();
    }

    @GetMapping("/getPartido/{partidoId}")
    public Optional<Partido> getPartidoById(@PathVariable UUID partidoId) {
        return partidoService.getPartidoById(partidoId);
    }

    //crear las carpetas con las fechas de los partidos, leidas desde un excel
    //a su vez, mete todas las fechas en la tabla de partidos
    @GetMapping("crearCarpetas")
    public void crearCarpetasConFechas(){
        partidoService.crearCarpetasConFechasdelExcel();
    }
}
