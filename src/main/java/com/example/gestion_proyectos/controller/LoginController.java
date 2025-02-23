package com.example.gestion_proyectos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Esto buscará el archivo login.html en /resources/templates/
    }
}
