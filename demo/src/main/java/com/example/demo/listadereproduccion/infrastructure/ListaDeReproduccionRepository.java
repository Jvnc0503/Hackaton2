package com.example.demo.listadereproduccion.infrastructure;

import com.example.demo.listadereproduccion.domain.ListaDeReproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ListaDeReproduccionRepository extends JpaRepository<ListaDeReproduccion, Integer> {
    @Query("select l from ListaDeReproduccion l " +
            "where l.usuario.idUser = :userID")
    List<ListaDeReproduccion> findAllByIdUser(@Param("userID") Integer userID);
}
