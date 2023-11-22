package com.basketballticketsproject.basketballticketsproject.controler;

import com.basketballticketsproject.basketballticketsproject.entity.Sorteo;
import com.basketballticketsproject.basketballticketsproject.entity.Usuario;
import com.basketballticketsproject.basketballticketsproject.service.SorteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cbgranada-api/v1")
public class  SorteoController {

    @Autowired
    private SorteoService sorteoService;

    //dada la fecha del sorteo, obtener los usuarios de ese partido
    @GetMapping("/getUsuariosSorteo/{fecha}")
    public ResponseEntity<List<Usuario>> getUsuariosSorteo(@PathVariable String fecha) {
        List<Usuario> usuarios =  sorteoService.getUsuariosSorteo(fecha);
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/getSorteos")
    public List<Sorteo> getSorteos() {
        return sorteoService.getSorteos();
    }


    //guardar usurio que se apunte al partido, pasandole su id y la fecha del partido
    @PostMapping("/saveUsuarioSorteo/{userID}/{fecha}")
    public ResponseEntity<Sorteo> saveUsuarioSorteo(@PathVariable UUID userID, @PathVariable String fecha) {
        Sorteo sorteo =  sorteoService.saveUsuarioSorteo(userID, fecha);
        if (ObjectUtils.isEmpty(sorteo)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sorteo, HttpStatus.OK);
    }

}
