package com.example.gestion_proyectos.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestion_proyectos.model.Role;
import com.example.gestion_proyectos.model.Usuario;
import com.example.gestion_proyectos.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository userRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String register(@RequestBody Usuario user) {
        if (usuarioRepository.findByUsername(user.getUsername()).isPresent()) {
            return "El usuario ya existe";
        }

        // Cifra la contraseña antes de guardarla
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Si no se especifica el rol, asignamos USER por defecto
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        usuarioRepository.save(user);
        return "Usuario registrado exitosamente con rol: " + user.getRole();
    }
}
