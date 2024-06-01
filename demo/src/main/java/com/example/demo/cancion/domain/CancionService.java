package com.example.demo.cancion.domain;

import com.example.demo.artista.domain.Artista;
import com.example.demo.artista.infrastructure.ArtistaRepository;
import com.example.demo.cancion.infrastructure.CancionRepository;
import com.example.demo.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CancionService {
    private final CancionRepository cancionRepository;
    private final ModelMapper modelMapper;
    private final ArtistaRepository artistaRepository;

    public CancionService(CancionRepository cancionRepository, ArtistaRepository artistaRepository) {
        this.cancionRepository = cancionRepository;
        this.modelMapper = new ModelMapper();
        this.artistaRepository = artistaRepository;
    }

    public CancionDto getCancionInfo(Integer id) {
        Cancion cancion = cancionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Cancion not found"));
        return modelMapper.map(cancion, CancionDto.class);
    }

    public String createCancion(CancionDto cancionDto) {
        Cancion cancion = new Cancion();
        Optional<Artista> artista = artistaRepository.findByNombre(cancionDto.getNameArtist());
        if (artista.isEmpty()) {
            Artista artista1 = new Artista();
            artista1.setNombre(cancionDto.getNameArtist());
            artistaRepository.save(artista1);
            cancion.setArtista(artista1);
        }
        else{
            cancion.setArtista(artista.get());
        }
        cancionRepository.save(cancion);
        return "/canciones/" + cancion.getIdSong();
    }

    public void updateCancion(Integer id, CancionDto cancionDto) {
        Cancion cancion = cancionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Cancion not found"));
        cancion.setTitulo(cancionDto.getTitulo());
        cancion.setDuracion(cancionDto.getDuracion());
        cancionRepository.save(cancion);
    }

    public void deleteCancion(Integer id) {
        cancionRepository.deleteById(id);
    }

    public List<CancionDto> getAllCanciones() {
        List<Cancion> canciones = cancionRepository.findAll();
        List<CancionDto> cancionDtos = new ArrayList<>();
        for (Cancion cancion : canciones) {
            CancionDto c = new CancionDto();
            c.setDuracion(cancion.getDuracion());
            c.setTitulo(cancion.getTitulo());
            c.setNameArtist(cancion.getArtista().getNombre());
            c.setAlbumID(cancion.getAlbum().getIdAlbum());
            cancionDtos.add(c);
        }
        return cancionDtos;
    }
}