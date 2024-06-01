package com.example.demo.listadereproduccion.domain;

import com.example.demo.cancion.domain.Cancion;
import com.example.demo.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class ListaDeReproduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlaylist;

    private String nombre;

    private Date fechaDeCreacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private Usuario usuario;

    @OneToMany
    private List<Cancion> canciones;

}
