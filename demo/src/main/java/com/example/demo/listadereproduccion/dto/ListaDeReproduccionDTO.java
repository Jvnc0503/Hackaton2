package com.example.demo.listadereproduccion.dto;

import java.util.Date;
import java.util.List;
import com.example.demo.cancion.domain.Cancion;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ListaDeReproduccionDTO {
    @NotNull
    private String nombre;
    @NotNull
    private List<Cancion> canciones;
}