package com.example.demo.usuario.infrastructure;

import com.example.demo.user.infrastructure.UserRepository;
import com.example.demo.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends UserRepository<Usuario> {

}

