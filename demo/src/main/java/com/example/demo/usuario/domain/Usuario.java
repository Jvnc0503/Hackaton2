package com.example.demo.usuario.domain;

import com.example.demo.cancion.domain.Cancion;
import com.example.demo.listadereproduccion.domain.ListaDeReproduccion;
import com.example.demo.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends User {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date fechaDeRegistro;

    @OneToMany
    private List<Cancion> canciones;
}
