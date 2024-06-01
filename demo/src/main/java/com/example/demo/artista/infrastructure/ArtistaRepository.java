package com.example.demo.artista.infrastructure;

import com.example.demo.artista.domain.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {
}
