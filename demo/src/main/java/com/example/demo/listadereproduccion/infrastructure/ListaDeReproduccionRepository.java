package com.example.demo.listadereproduccion.infrastructure;

import com.example.demo.listadereproduccion.domain.ListaDeReproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ListaDeReproduccionRepository extends JpaRepository<ListaDeReproduccion, Integer> {
    List<ListaDeReproduccion> findAllByIdUser(int idUser);
}
