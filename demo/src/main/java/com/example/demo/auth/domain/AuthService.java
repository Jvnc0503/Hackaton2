package com.example.demo.auth.domain;


import com.example.demo.auth.dto.JwtAuthResponse;
import com.example.demo.auth.dto.LoginReq;
import com.example.demo.auth.dto.RegisterReq;
import com.example.demo.config.JwtService;
import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.user.domain.User;
import com.example.demo.user.infrastructure.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository<User> userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final EmailService emailService;

    @Autowired
    public AuthService(UserRepository<User> userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, EmailService emailService) {
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

            JwtAuthResponse response = new JwtAuthResponse();
            response.setToken(jwtService.generateToken(anfitrion));
            emailService.sendRegisterMessage(anfitrion.getEmail(),"Gracias por registrarte!",
                    anfitrion.getNombre(),anfitrion.getApellido(),anfitrion.getRole().name());
            return response;

            JwtAuthResponse response = new JwtAuthResponse();
            response.setToken(jwtService.generateToken(roomie));
            emailService.sendRegisterMessage(roomie.getEmail(),"Gracias por registrarte!",
                    roomie.getNombre(),roomie.getApellido(),roomie.getRole().name());
            return response;
        }

    }
}
