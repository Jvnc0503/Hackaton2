package com.example.demo.artista.infrastructure;

import com.example.demo.artista.domain.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {
    Optional<Artista> findByNombre(String nombre);
}
