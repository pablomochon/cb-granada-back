package com.basketballticketsproject.basketballticketsproject.repo;

import com.basketballticketsproject.basketballticketsproject.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UsuarioRepo extends JpaRepository<Usuario, UUID> {

    @Query(value = "SELECT * FROM Usuario WHERE nombre = ?1", nativeQuery = true)
    Usuario findByName(String name);

    //usuarios que eligen ir
    //@Query("SELECT u FROM Usuario")
    //List<Usuario> getUsersWhoAccepted();

    @Query(value = "SELECT * FROM Usuario WHERE email = ?1", nativeQuery = true)
    Usuario findByEmail(String email);


}
