package com.example.demo.artista.application;
import com.example.demo.artista.domain.ArtistaService;
import com.example.demo.artista.dto.ArtistaDTO;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Data
public class ArtistaController {

    private final ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistaDTO> getArtistaById(@PathVariable Integer id) {
        return ResponseEntity.ok(artistaService.getArtistaById(id));
    }

    @PostMapping()
    public ResponseEntity<Void> createArtista(@RequestBody ArtistaDTO artistaDTO) {
        artistaService.createArtista(artistaDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistaDTO> updateArtista(@PathVariable Integer id, @RequestBody ArtistaDTO artistaDTO) {
        return ResponseEntity.ok(artistaService.updateArtista(artistaDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtista(@PathVariable Integer id) {
        artistaService.deleteArtista(id);
        return ResponseEntity.noContent().build();
    }

}
