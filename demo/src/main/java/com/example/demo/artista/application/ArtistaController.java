package com.example.demo.artista.application;
import com.example.demo.artista.domain.ArtistaService;
import com.example.demo.artista.dto.ArtistaDTO;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artistas")
@Data
public class ArtistaController {

    private final ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ArtistaDTO> getArtistaById(@PathVariable Integer id) {
        return ResponseEntity.ok(artistaService.getArtistaById(id));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping()
    public ResponseEntity<Void> createArtista(@RequestBody ArtistaDTO artistaDTO) {
        artistaService.createArtista(artistaDTO);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ArtistaDTO> updateArtista(@PathVariable Integer id, @RequestBody ArtistaDTO artistaDTO) {
        return ResponseEntity.ok(artistaService.updateArtista(artistaDTO, id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtista(@PathVariable Integer id) {
        artistaService.deleteArtista(id);
        return ResponseEntity.noContent().build();
    }

}
