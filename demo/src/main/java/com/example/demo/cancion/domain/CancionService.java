package com.example.demo.cancion.domain;

import com.example.demo.cancion.infrastructure.CancionRepository;
import com.example.demo.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CancionService {
    private final CancionRepository cancionRepository;
    private final ModelMapper modelMapper;

    public CancionService(CancionRepository cancionRepository) {
        this.cancionRepository = cancionRepository;
        this.modelMapper = new ModelMapper();
    }

    public CancionDto getCancionInfo(Integer id) {
        Cancion cancion = cancionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Cancion not found"));
        return modelMapper.map(cancion, CancionDto.class);
    }

    public String createCancion(CancionDto cancionDto) {
        Cancion cancion = modelMapper.map(cancionDto, Cancion.class);
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
            c.setAlbum(cancion.getAlbum().getNombre());
            cancionDtos.add(c);
        }
        return cancionDtos;
    }
}