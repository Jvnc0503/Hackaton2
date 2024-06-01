package com.example.demo.cancion.infrastructure;

import com.example.demo.album.domain.Album;
import com.example.demo.cancion.domain.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CancionRepository extends JpaRepository<Cancion, Integer> {
    Optional<Cancion> findByTitulo(String titulo);
    Optional<Cancion> findByTituloAndAlbumAndDuracion(String titulo, Album album, Integer duracion);
}