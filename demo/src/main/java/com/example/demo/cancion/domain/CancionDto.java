package com.example.demo.cancion.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CancionDto {
    @NotNull
    String titulo;
    @NotNull
    int idArtist;
    @NotNull
    int idAlbum;
    @NotNull
    int duracion;
}