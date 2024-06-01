package com.example.demo.usuario.domain;

import com.example.demo.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Usuario extends User {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Date fechaDeRegistro;

    @OneToMany
    @JoinColumn
    private List<ListaDeReproduccion> listaDeReproduccion;


}
