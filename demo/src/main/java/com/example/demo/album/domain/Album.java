package com.example.demo.album.domain;

import com.example.demo.cancion.domain.Cancion;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlbum;

    private String nombre;

    private Date fechaDeLanzamiento;

    @OneToMany
    private List<Cancion> canciones;
}
