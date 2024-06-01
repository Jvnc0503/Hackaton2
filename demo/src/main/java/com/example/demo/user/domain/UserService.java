package com.example.demo.user.domain;


import com.example.demo.user.infrastructure.UserRepository;
import com.example.demo.usuario.infrastructure.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository<User> userRepository;
    private final UsuarioRepository usuarioRepository;

    public UserService(UserRepository<User> userRepository, UsuarioRepository usuarioRepository) {
        this.userRepository = userRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public User findByEmail(String username, String tipo) {
        User user;
        user = usuarioRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }

    @Bean(name = "UserDetailsService")
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository
                    .findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();
        };
    }

}
