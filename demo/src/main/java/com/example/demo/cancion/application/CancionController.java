package com.example.demo.cancion.application;

import com.example.demo.cancion.domain.CancionDto;
import com.example.demo.cancion.domain.CancionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/canciones")
public class CancionController {
    private final CancionService cancionService;

    public CancionController(CancionService cancionService) {
        this.cancionService = cancionService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<CancionDto> getCancionInfo(@PathVariable int id) {
        return ResponseEntity.ok(cancionService.getCancionInfo(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<Void> createCancion(@RequestBody CancionDto cancionDto) {
        return ResponseEntity.created(URI.create(cancionService.createCancion(cancionDto))).build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCancion(@PathVariable int id, @RequestBody CancionDto cancionDto) {
        cancionService.updateCancion(id, cancionDto);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCancion(@PathVariable int id) {
        cancionService.deleteCancion(id);
        return ResponseEntity.noContent().build();
    }
}