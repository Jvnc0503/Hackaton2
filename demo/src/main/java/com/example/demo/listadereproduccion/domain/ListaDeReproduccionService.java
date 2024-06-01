package com.example.demo.listadereproduccion.domain;

import com.example.demo.album.infrastructure.AlbumRepository;
import com.example.demo.auth.utils.AuthorizationUtils;
import com.example.demo.cancion.domain.Cancion;
import com.example.demo.cancion.domain.CancionDto;
import com.example.demo.cancion.infrastructure.CancionRepository;
import com.example.demo.events.EmailService;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.listadereproduccion.dto.ListaDeReproduccionDTO;
import com.example.demo.listadereproduccion.infrastructure.ListaDeReproduccionRepository;
import com.example.demo.usuario.domain.Usuario;
import com.example.demo.usuario.infrastructure.UsuarioRepository;
import jakarta.persistence.GeneratedValue;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ListaDeReproduccionService {
    private final ListaDeReproduccionRepository listaDeReproduccionRepository;
    private final ModelMapper modelMapper;
    private final UsuarioRepository usuarioRepository;
    private final AuthorizationUtils authorizationUtils;
    private final EmailService emailService;
    private final CancionRepository cancionRepository;

    public ListaDeReproduccionService(ListaDeReproduccionRepository listaDeReproduccionRepository, UsuarioRepository usuarioRepository, AuthorizationUtils authorizationUtils, EmailService emailService, CancionRepository cancionRepository) {
        this.listaDeReproduccionRepository = listaDeReproduccionRepository;
        this.modelMapper = new ModelMapper();
        this.usuarioRepository = usuarioRepository;
        this.authorizationUtils = authorizationUtils;
        this.emailService = emailService;
        this.cancionRepository = cancionRepository;
    }

    public List<ListaDeReproduccionDTO> getUserPlaylists(int idUser) {
        List<ListaDeReproduccion> listaDeReproduccionList = listaDeReproduccionRepository.findAllByIdUser(idUser);
        return listaDeReproduccionList.stream().map(listaDeReproduccion -> modelMapper.map(listaDeReproduccion, ListaDeReproduccionDTO.class)).toList();
    }

    public ListaDeReproduccionDTO getListaDeReproduccionInfo(Integer idPlaylist) {
        ListaDeReproduccion listaDeReproduccion = listaDeReproduccionRepository.findById(idPlaylist).orElseThrow(()->new ResourceNotFoundException("Lista de reproduccion no encontrada"));
        return modelMapper.map(listaDeReproduccion, ListaDeReproduccionDTO.class);
    }

    public String createListaDeReproduccion(Integer idUser, ListaDeReproduccionDTO listaDeReproduccionDTO) {
        String usermail = usuarioRepository.findById(idUser).get().getEmail();
        ListaDeReproduccion listaDeReproduccion = modelMapper.map(listaDeReproduccionDTO, ListaDeReproduccion.class);
        Usuario usuario = usuarioRepository.findById(idUser).orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado"));
        listaDeReproduccion.setUsuario(usuario);
        List<Cancion> canciones = new ArrayList<>();
        for (Integer i:listaDeReproduccionDTO.getCancionesId()){
            canciones.add(cancionRepository.findById(i).get());
        }
        listaDeReproduccion.setCanciones(canciones);
        listaDeReproduccionRepository.save(listaDeReproduccion);
        emailService.sendSimpleMessage(usermail,"Nueva PlayList"," Tu nueva playlist facha.ad");
        return "/users/"+idUser+"/playlists/"+listaDeReproduccion.getIdPlaylist();
    }

    public void updateListaDeReproduccion(int id, ListaDeReproduccionDTO listaDeReproduccionDTO) {
        ListaDeReproduccion listaDeReproduccion = listaDeReproduccionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Lista de reproduccion no encontrada"));
        listaDeReproduccion.setNombre(listaDeReproduccionDTO.getNombre());
        listaDeReproduccion.setFechaDeCreacion(Date.from(Instant.now()));
        for(Integer cancionID: listaDeReproduccionDTO.getCancionesId()){
            Cancion cancion = cancionRepository.findById(cancionID).get();
            listaDeReproduccion.getCanciones().add(cancion);
        }
        listaDeReproduccionRepository.save(listaDeReproduccion);
    }

    public void deleteListaDeReproduccion(int id) {
        listaDeReproduccionRepository.deleteById(id);
    }

    public List<CancionDto> getCancionesFromPlaylist(int idPlaylist) {
        ListaDeReproduccion listaDeReproduccion = listaDeReproduccionRepository.findById(idPlaylist).orElseThrow(()->new ResourceNotFoundException("Lista de reproduccion no encontrada"));
        List<Cancion> canciones = listaDeReproduccion.getCanciones();
        return canciones.stream().map(cancion -> modelMapper.map(cancion, CancionDto.class)).toList();
    }

    public String addCancionToPlaylist(int idPlaylist, int idCancion) {
        ListaDeReproduccion listaDeReproduccion = listaDeReproduccionRepository.findById(idPlaylist).orElseThrow(()->new ResourceNotFoundException("Lista de reproduccion no encontrada"));
        Cancion cancion = cancionRepository.findById(idCancion).orElseThrow(()->new ResourceNotFoundException("Cancion no encontrada"));
        listaDeReproduccion.getCanciones().add(cancion);
        listaDeReproduccionRepository.save(listaDeReproduccion);
        return "/playlists/"+idPlaylist+"/songs";
    }

    public void deleteCancionFromPlaylist(int idPlaylist, int idCancion) {
        ListaDeReproduccion listaDeReproduccion = listaDeReproduccionRepository.findById(idPlaylist).orElseThrow(()->new ResourceNotFoundException("Lista de reproduccion no encontrada"));
        Cancion cancion = cancionRepository.findById(idCancion).orElseThrow(()->new ResourceNotFoundException("Cancion no encontrada"));
        listaDeReproduccion.getCanciones().remove(cancion);
        listaDeReproduccionRepository.save(listaDeReproduccion);
    }
}
