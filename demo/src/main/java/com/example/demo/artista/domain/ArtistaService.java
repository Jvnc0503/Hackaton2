package com.example.demo.artista.domain;
import com.example.demo.artista.dto.ArtistaDTO;
import com.example.demo.artista.infrastructure.ArtistaRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;

    public ArtistaService(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public ArtistaDTO getArtistaById(Integer id) {
        Artista artista = artistaRepository.findById(id).orElseThrow(() -> new RuntimeException("Artist not found"));
        ArtistaDTO artistaDTO = new ArtistaDTO();
        artistaDTO.setNombre(artista.getNombre());
        return artistaDTO;

    }

    public void createArtista(ArtistaDTO artistaDTO) {
        Artista artista = new Artista();
        artista.setNombre(artistaDTO.getNombre());
        artistaRepository.save(artista);
    }

    public ArtistaDTO updateArtista(ArtistaDTO artistaDTO, Integer id) {
        Artista artista = artistaRepository.findById(id).orElseThrow(() -> new RuntimeException("Artist not found"));
        artista.setNombre(artistaDTO.getNombre());
        artistaRepository.save(artista);
        return artistaDTO;
    }

    public void deleteArtista(Integer id) {
        artistaRepository.deleteById(id);
    }

}

