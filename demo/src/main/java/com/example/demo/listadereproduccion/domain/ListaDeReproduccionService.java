package com.example.demo.listadereproduccion.domain;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.listadereproduccion.dto.ListaDeReproduccionDTO;
import com.example.demo.listadereproduccion.infrastructure.ListaDeReproduccionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListaDeReproduccionService {
    private final ListaDeReproduccionRepository listaDeReproduccionRepository;
    private final ModelMapper modelMapper;

    public ListaDeReproduccionService(ListaDeReproduccionRepository listaDeReproduccionRepository) {
        this.listaDeReproduccionRepository = listaDeReproduccionRepository;
        this.modelMapper = new ModelMapper();
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
        listaDeReproduccion.setIdUser(idUser);
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
