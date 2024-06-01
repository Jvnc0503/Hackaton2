package com.example.demo.cancion.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idSong;

    String titulo;

    int idArtist;

    int idAlbum;

    int duracion;
}