package com.basketballticketsproject.basketballticketsproject.controler;

import com.basketballticketsproject.basketballticketsproject.model.Partido;
import com.basketballticketsproject.basketballticketsproject.model.Usuario;
import com.basketballticketsproject.basketballticketsproject.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cbgranada-api/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String funciona(){
        return "HOLA MUNDOOOO";
    }

    @GetMapping("/userName/{name}")
    public Usuario getUserByName(@PathVariable String name) {
        return usuarioService.getUsuarioByName(name);
    }

    @GetMapping("/userEmail/{email}")
    public Usuario getUserByEmail(@PathVariable String email) {
        return usuarioService.getUsuarioByEmail(email);
    }

    @PostMapping("/addUser")
    public Usuario addUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    @GetMapping("/getAllUsers")
    public List<Usuario> getAllUsers() {
        return usuarioService.getAllUsers();
    }


}
