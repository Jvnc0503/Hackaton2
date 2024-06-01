package com.example.demo.listadereproduccion.domain;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.listadereproduccion.dto.ListaDeReproduccionDTO;
import com.example.demo.listadereproduccion.infrastructure.ListaDeReproduccionRepository;
import com.example.demo.usuario.infrastructure.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListaDeReproduccionService {
    private final ListaDeReproduccionRepository listaDeReproduccionRepository;
    private final ModelMapper modelMapper;
    private final UsuarioRepository usuarioRepository;

    public ListaDeReproduccionService(ListaDeReproduccionRepository listaDeReproduccionRepository, UsuarioRepository usuarioRepository) {
        this.listaDeReproduccionRepository = listaDeReproduccionRepository;
        this.modelMapper = new ModelMapper();
        this.usuarioRepository = usuarioRepository;
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
        ListaDeReproduccion listaDeReproduccion = modelMapper.map(listaDeReproduccionDTO, ListaDeReproduccion.class);
        listaDeReproduccion.setUsuario(usuarioRepository.findById(idUser).get());
        listaDeReproduccionRepository.save(listaDeReproduccion);
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
