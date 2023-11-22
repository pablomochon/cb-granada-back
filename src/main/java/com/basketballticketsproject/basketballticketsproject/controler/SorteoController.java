package com.basketballticketsproject.basketballticketsproject.controler;

import com.basketballticketsproject.basketballticketsproject.entity.Sorteo;
import com.basketballticketsproject.basketballticketsproject.entity.Usuario;
import com.basketballticketsproject.basketballticketsproject.service.SorteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cbgranada-api/v1")
public class  SorteoController {

    @Autowired
    private SorteoService sorteoService;

    @GetMapping("/getUsuariosSorteo/{fecha}")
    public List<Usuario> getUsuariosSorteo(@PathVariable String fecha) {
        return sorteoService.getUsuariosSorteo(fecha);
    }

    @PostMapping("/addSorteo")
    public Sorteo addSorteo(@RequestParam(name = "fecha") String fecha, @RequestBody Sorteo sorteo) {
        return null;
    }

    @GetMapping("/getSorteos")
    public List<Sorteo> getSorteos() {
        return sorteoService.getSorteos();
    }


    @PostMapping("/saveUsuarioSorteo/{userID}/{fecha}")
    public Sorteo saveUsuarioSorteo(@PathVariable UUID userID, @PathVariable String fecha) {
        return sorteoService.saveUsuarioSorteo(userID, fecha);
    }
}
