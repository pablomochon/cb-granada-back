package com.basketballticketsproject.basketballticketsproject.repo;

import com.basketballticketsproject.basketballticketsproject.model.Usuario;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UsuarioRepo extends JpaRepository<Usuario, UUID> {

    @Query(value = "SELECT * FROM Usuario WHERE name = ?1", nativeQuery = true)
    Usuario findByName(String name);

    //usuarios que eligen ir
    //@Query("SELECT u FROM Usuario")
    //List<Usuario> getUsersWhoAccepted();

    @Query(value = "SELECT * FROM Usuario WHERE email = ?1", nativeQuery = true)
    Usuario findByEmail(String email);


}
