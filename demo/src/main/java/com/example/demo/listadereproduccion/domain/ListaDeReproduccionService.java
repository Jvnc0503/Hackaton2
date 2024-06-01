package com.example.demo.listadereproduccion.domain;

import com.example.demo.auth.utils.AuthorizationUtils;
import com.example.demo.events.EmailService;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.listadereproduccion.dto.ListaDeReproduccionDTO;
import com.example.demo.listadereproduccion.infrastructure.ListaDeReproduccionRepository;
import com.example.demo.usuario.domain.Usuario;
import com.example.demo.usuario.infrastructure.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListaDeReproduccionService {
    private final ListaDeReproduccionRepository listaDeReproduccionRepository;
    private final ModelMapper modelMapper;
    private final UsuarioRepository usuarioRepository;
    private final AuthorizationUtils authorizationUtils;
    private final EmailService emailService;

    public ListaDeReproduccionService(ListaDeReproduccionRepository listaDeReproduccionRepository, UsuarioRepository usuarioRepository, AuthorizationUtils authorizationUtils, EmailService emailService) {
        this.listaDeReproduccionRepository = listaDeReproduccionRepository;
        this.modelMapper = new ModelMapper();
        this.usuarioRepository = usuarioRepository;
        this.authorizationUtils = authorizationUtils;
        this.emailService = emailService;
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
        String usermail = authorizationUtils.getCurrentUserEmail();
        ListaDeReproduccion listaDeReproduccion = modelMapper.map(listaDeReproduccionDTO, ListaDeReproduccion.class);
        Usuario usuario = usuarioRepository.findById(idUser).orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado"));
        listaDeReproduccion.setUsuario(usuario);
        listaDeReproduccionRepository.save(listaDeReproduccion);
        emailService.sendSimpleMessage(usermail,"Nueva PlayList"," Tu nueva playlist facha.ad");
        return "/users/"+idUser+"/playlists/"+listaDeReproduccion.getIdPlaylist();
    }

    public void updateListaDeReproduccion(int id, ListaDeReproduccionDTO listaDeReproduccionDTO) {
        ListaDeReproduccion listaDeReproduccion = listaDeReproduccionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Lista de reproduccion no encontrada"));
        listaDeReproduccion.setNombre(listaDeReproduccionDTO.getNombre());
        listaDeReproduccion.setFechaDeCreacion(listaDeReproduccionDTO.getFechaDeCreacion());
        listaDeReproduccion.setCanciones(listaDeReproduccionDTO.getCanciones());
        listaDeReproduccionRepository.save(listaDeReproduccion);
    }

    public void deleteListaDeReproduccion(int id) {
        listaDeReproduccionRepository.deleteById(id);
    }
}
