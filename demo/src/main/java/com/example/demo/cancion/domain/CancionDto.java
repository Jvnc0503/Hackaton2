package com.example.demo.cancion.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CancionDto {
    private String titulo;
    private String nameArtist;
    private Integer albumID;
    private Integer duracion;
}