package com.example.demo.listadereproduccion.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class ListaDeReproduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlaylist;

    private String nombre;

    private Integer idUsuario;

    private Date fechaDeCreacion;

    @OneToMany
    @JoinColumn(name = "idCancion")
    private List<Cancion> canciones;

}
