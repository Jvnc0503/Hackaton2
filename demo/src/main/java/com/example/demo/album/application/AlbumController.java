package com.example.demo.album.application;

import com.example.demo.album.domain.AlbumService;
import com.example.demo.album.dto.AlbumDTO;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album")
@Data
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable Integer id) {
        return ResponseEntity.ok(albumService.getAlbumById(id));
    }

    @PostMapping()
    public ResponseEntity<Void> createEtiqueta(@RequestBody AlbumDTO albumDTO) {
        albumService.createAlbum(albumDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumDTO> fixEtiqueta(@PathVariable Integer id, @RequestBody AlbumDTO albumDTO) {
        return ResponseEntity.ok(albumService.updateAlbum(albumDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtiqueta(@PathVariable Integer id) {
        albumService.deleteAlbumById(id);
        return ResponseEntity.noContent().build();
    }

}
