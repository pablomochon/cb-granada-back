package com.basketballticketsproject.basketballticketsproject.service;

import com.basketballticketsproject.basketballticketsproject.entity.Usuario;
import com.basketballticketsproject.basketballticketsproject.repo.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepo usuarioRepo;

    public Usuario getUsuarioByName(String name) {
        return usuarioRepo.findByName(name);
    }

    public Usuario getUsuarioByEmail(String email) {
        return usuarioRepo.findByEmail(email);
    }

    public Usuario saveUsuario(Usuario usuario){
        return usuarioRepo.save(usuario);
    }

    public List<Usuario> getAllUsers(){
        return usuarioRepo.findAll();
    }

    public Usuario modificarUsuario(UUID id, Usuario usuarioNuevo) {
        Usuario updateUser = usuarioRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("Employee not exist with id: " + id));
        updateUser.setEmail(usuarioNuevo.getEmail());
        updateUser.setNombre(usuarioNuevo.getNombre());
        return usuarioRepo.save(updateUser);
    }

    public void borrarUsuario(UUID id) {
        Usuario deleteUser = usuarioRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("Employee not exist with id: " + id));
        if (ObjectUtils.isEmpty(deleteUser)) {
            throw new IllegalStateException("Usuario para borrar no encontrado");
        }
        usuarioRepo.delete(deleteUser);
    }
}
