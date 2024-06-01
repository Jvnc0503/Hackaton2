package com.example.demo.album.application;

import com.example.demo.album.domain.AlbumService;
import com.example.demo.album.dto.AlbumDTO;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")

@Data
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable Integer id) {
        return ResponseEntity.ok(albumService.getAlbumById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<Void> createEtiqueta(@RequestBody AlbumDTO albumDTO) {
        albumService.createAlbum(albumDTO);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<AlbumDTO> fixEtiqueta(@PathVariable Integer id, @RequestBody AlbumDTO albumDTO) {
        return ResponseEntity.ok(albumService.updateAlbum(albumDTO, id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtiqueta(@PathVariable Integer id) {
        albumService.deleteAlbumById(id);
        return ResponseEntity.noContent().build();
    }

}
