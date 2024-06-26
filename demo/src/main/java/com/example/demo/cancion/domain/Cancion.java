package com.example.demo.cancion.domain;

import com.example.demo.album.domain.Album;
import com.example.demo.artista.domain.Artista;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSong;


    private String titulo;

    private Integer duracion;

    @ManyToOne
    @JoinColumn(name = "artistaID")
    private Artista artista;

    @ManyToOne
    private Album album;
}