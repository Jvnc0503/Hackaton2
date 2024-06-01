package com.example.demo.album.domain;

import com.example.demo.album.dto.AlbumDTO;
import com.example.demo.album.infrastructure.AlbumRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public AlbumDTO getAlbumById(Integer id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Album not found"));
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setNombre(album.getNombre());
        return albumDTO;
    }

    public void createAlbum(AlbumDTO albumDTO) {
        Album album = new Album();
        album.setNombre(albumDTO.getNombre());
        albumRepository.save(album);
    }

    public AlbumDTO updateAlbum(AlbumDTO albumDTO, Integer id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Etiqueta not found"));
        album.setNombre(albumDTO.getNombre());
        album.setFechaDeLanzamiento(albumDTO.getFechaDeLanzamiento());
        albumRepository.save(album);
        return albumDTO;
    }

    public void deleteAlbumById(Integer id) {
        albumRepository.deleteById(id);
    }

}
