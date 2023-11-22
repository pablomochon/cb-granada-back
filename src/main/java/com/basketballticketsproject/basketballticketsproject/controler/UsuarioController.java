package com.basketballticketsproject.basketballticketsproject.controler;

import com.basketballticketsproject.basketballticketsproject.entity.Sorteo;
import com.basketballticketsproject.basketballticketsproject.entity.Usuario;
import com.basketballticketsproject.basketballticketsproject.service.SplitPDFByPages;
import com.basketballticketsproject.basketballticketsproject.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cbgranada-api/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SplitPDFByPages splitPDFByPages;

    @GetMapping
    public String funciona(){
        return "HOLA MUNDOOOO";
    }

    @GetMapping("/userName/{name}")
    public ResponseEntity<Usuario> getUserByName(@PathVariable String name) {
        Usuario user = usuarioService.getUsuarioByName(name);
        if (ObjectUtils.isEmpty(user)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/userEmail/{email}")
    public  ResponseEntity<Usuario> getUserByEmail(@PathVariable String email) {
        Usuario user = usuarioService.getUsuarioByEmail(email);
        if (ObjectUtils.isEmpty(user)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.saveUsuario(usuario), HttpStatus.CREATED);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> allUsers = usuarioService.getAllUsers();
        if (allUsers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }


    @GetMapping("/splitPdf")
    public void splitPdf() throws IOException {
        splitPDFByPages.splitPdf();
    }

    public void addUsuarioSorteo(Usuario user) {
        List<Usuario> usuarios = new ArrayList<>();
        Sorteo sorteo = new Sorteo();
        //sorteo.setUsuarios(usuarios.add(user));
    }

}
