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
    public void addPartido(@RequestBody Partido partido) {
        partidoService.addPartido(partido);
    }

    @GetMapping("/usuariosConfirmados")
    public int getUsuariosConfirmados(){
        return partidoService.getUsuariosConfirmados();
    }

    @GetMapping("/getPartidos")
    public List<Partido> getPartidos(){
        return partidoService.getPartdios();
    }

    @GetMapping("/getPartido/{partidoId}")
    public Optional<Partido> getPartidoById(@PathVariable UUID partidoId) {
        return partidoService.getPartidoById(partidoId);
    }

    @GetMapping("crearCarpetas")
    public void crearCarpetasConFechas(){
        partidoService.crearCarpetasConFechas();
    }
}
