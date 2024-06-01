package com.example.demo.auth.domain;

import com.example.demo.auth.dto.JwtAuthResponse;
import com.example.demo.auth.dto.LoginReq;
import com.example.demo.auth.dto.RegisterReq;
import com.example.demo.config.JwtService;
import com.example.demo.events.EmailService;
import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.user.domain.Role;
import com.example.demo.user.domain.User;
import com.example.demo.user.infrastructure.UserRepository;
import com.example.demo.usuario.domain.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository<User> userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final EmailService emailService;

    @Autowired
    public AuthService(UserRepository<User> userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, EmailService emailService, UserDetailsServiceAutoConfiguration userDetailsServiceAutoConfiguration) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = new ModelMapper();
        this.emailService = emailService;
    }

    public JwtAuthResponse login(LoginReq req) {
        Optional<User> user;
        user = userRepository.findByEmail(req.getEmail());

        if (user.isEmpty()) throw new UsernameNotFoundException("Email is not registered");

        if (!passwordEncoder.matches(req.getPassword(), user.get().getPassword()))
            throw new IllegalArgumentException("Password is incorrect");

        JwtAuthResponse response = new JwtAuthResponse();

        response.setToken(jwtService.generateToken(user.get()));
        return response;
    }

    public JwtAuthResponse register(RegisterReq req){
        Optional<User> user = userRepository.findByEmail(req.getEmail());
        if (user.isPresent()) throw new UserAlreadyExistException("Email is already registered");

        Usuario usuario = new Usuario();
        usuario.setEmail(req.getEmail());
        usuario.setName(req.getName());
        usuario.setPassword(passwordEncoder.encode(req.getPassword()));
        usuario.setRole(Role.USER);
        usuario.setFechaDeRegistro(Date.from(Instant.now()));
        userRepository.save(usuario);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(jwtService.generateToken(usuario));
        emailService.sendRegisterMessage(req.getEmail(),"Gracias por registrarte!",
                    req.getName());
            return response;
        }
    }
