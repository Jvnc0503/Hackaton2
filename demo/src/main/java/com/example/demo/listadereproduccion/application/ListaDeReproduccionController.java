package com.example.demo.listadereproduccion.application;

import com.example.demo.listadereproduccion.domain.ListaDeReproduccionService;
import com.example.demo.listadereproduccion.dto.ListaDeReproduccionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class ListaDeReproduccionController {
    private final ListaDeReproduccionService listaDeReproduccionService;

    public ListaDeReproduccionController(ListaDeReproduccionService listaDeReproduccionService) {
        this.listaDeReproduccionService = listaDeReproduccionService;
    }

    @GetMapping("/users/{idUser}/playlists")
    public ResponseEntity<List<ListaDeReproduccionDTO>> getUserPlaylists(@PathVariable Integer idUser) {
        return ResponseEntity.ok(listaDeReproduccionService.getUserPlaylists(idUser));
    }

    @GetMapping("/playlists/{playlist_id}")
    public ResponseEntity<ListaDeReproduccionDTO> getPlaylistInfo(@PathVariable Integer playlist_id) {
        return ResponseEntity.ok(listaDeReproduccionService.getListaDeReproduccionInfo(playlist_id));
    }

    @PostMapping("/users/{user_id}/playlists")
    public ResponseEntity<Void> createPlaylist(@PathVariable Integer user_id, @RequestBody ListaDeReproduccionDTO listaDeReproduccionDTO) {
        return ResponseEntity.created(URI.create(listaDeReproduccionService.createListaDeReproduccion(user_id, listaDeReproduccionDTO))).build();
    }

    @PutMapping("/playlists/{playlist_id}")
    public ResponseEntity<Void> updatePlaylist(@PathVariable Integer playlist_id, @RequestBody ListaDeReproduccionDTO listaDeReproduccionDTO) {
        listaDeReproduccionService.updateListaDeReproduccion(playlist_id, listaDeReproduccionDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/playlists/{playlist_id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Integer playlist_id) {
        listaDeReproduccionService.deleteListaDeReproduccion(playlist_id);
        return ResponseEntity.noContent().build();
    }
}