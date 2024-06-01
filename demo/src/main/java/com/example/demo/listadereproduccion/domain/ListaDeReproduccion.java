package com.example.demo.listadereproduccion.domain;

import com.example.demo.cancion.domain.Cancion;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class ListaDeReproduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlaylist;

    private String nombre;

    private Integer idUser;

    private Date fechaDeCreacion;

    @OneToMany
    @JoinColumn(name = "idCancion")
    private List<Cancion> canciones;

}
