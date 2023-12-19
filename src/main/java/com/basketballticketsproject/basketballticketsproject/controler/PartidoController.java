package com.basketballticketsproject.basketballticketsproject.controler;


import com.basketballticketsproject.basketballticketsproject.entity.Partido;
import com.basketballticketsproject.basketballticketsproject.entity.Ticket;
import com.basketballticketsproject.basketballticketsproject.service.FileStorageService;
import com.basketballticketsproject.basketballticketsproject.service.PartidoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cbgranada-api/v1")
@Slf4j
public class PartidoController {

    @Autowired
    PartidoService partidoService;


    //añadir un partido sin pdf
    @PostMapping("/addPartido")
    public Partido addPartido(@RequestBody Partido partido) {
        return partidoService.addPartido(partido);
    }

    //borrar un partido
    @DeleteMapping("/borrarPartido/{id}")
    public void borrarPartidoById(@PathVariable UUID id) {
        partidoService.removePartido(id);
    }


    //obtener todos los partidos
    @GetMapping("/getPartidos")
    public List<Partido> getPartidos(){
        return partidoService.getPartdios();
    }

    //obtener entradas
    @GetMapping("/getTickets")
    public List<Ticket> getTickets(){
        return partidoService.getTickets();
    }

    //obtener un partido en especifico
    @GetMapping("/getPartido/{partidoId}")
    public Optional<Partido> getPartidoById(@PathVariable UUID partidoId) {
        return partidoService.getPartidoById(partidoId);
    }


}
