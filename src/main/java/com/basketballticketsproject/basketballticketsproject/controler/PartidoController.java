package com.basketballticketsproject.basketballticketsproject.controler;


import com.basketballticketsproject.basketballticketsproject.model.Partido;
import com.basketballticketsproject.basketballticketsproject.service.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
