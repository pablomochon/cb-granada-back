package com.basketballticketsproject.basketballticketsproject.service;

import com.basketballticketsproject.basketballticketsproject.model.Usuario;
import com.basketballticketsproject.basketballticketsproject.repo.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepo usuarioRepo;

    public Usuario getUsuarioByName(String name) {
        Usuario user = usuarioRepo.findByName(name);
        if (ObjectUtils.isEmpty(user)) {
            throw new IllegalStateException("Usuario no encontrado");
        }
        return user;
    }

    public Usuario getUsuarioByEmail(String email) {
        Usuario user = usuarioRepo.findByEmail(email);
        if (ObjectUtils.isEmpty(user)) {
            throw new IllegalStateException("Email no encontrado");
        }
        return user;
    }

    public Usuario saveUsuario(Usuario usuario){
        return usuarioRepo.save(usuario);
    }

    public List<Usuario> getAllUsers(){
        return usuarioRepo.findAll();
    }

}
