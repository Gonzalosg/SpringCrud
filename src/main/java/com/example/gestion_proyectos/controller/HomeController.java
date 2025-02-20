package com.example.gestion_proyectos.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gestion_proyectos.model.Proyecto;
import com.example.gestion_proyectos.repository.ProyectoRepository;

@Controller
public class HomeController {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/proyectos")
    public String listarProyectos(Model model) {
        List<Proyecto> proyectos = proyectoRepository.findAll();
        model.addAttribute("proyectos", proyectos);
        return "proyectos";
    }

    @PostMapping("/proyectos/nuevo")
    public String crearProyecto(@RequestParam String nombre,
                                @RequestParam String descripcion,
                                @RequestParam String fechaInicio,
                                @RequestParam(required = false) String estado) {
        try {
            if (estado == null || estado.isEmpty()) {
                estado = "ACTIVO"; // Valor por defecto
            }

            Proyecto proyecto = new Proyecto();
            proyecto.setNombre(nombre);
            proyecto.setDescripcion(descripcion);
            proyecto.setFechaInicio(LocalDate.parse(fechaInicio));
            proyecto.setEstado(Proyecto.EstadoProyecto.valueOf(estado));

            proyectoRepository.save(proyecto);
            System.out.println("Proyecto guardado correctamente: " + proyecto.getNombre());
        } catch (Exception e) {
            System.err.println("Error al crear el proyecto: " + e.getMessage());
        }

        return "redirect:/proyectos";
    }

    @PostMapping("/proyectos/eliminar/{id}")
    public String eliminarProyecto(@PathVariable Long id) {
        Optional<Proyecto> proyecto = proyectoRepository.findById(id);
        if (proyecto.isPresent()) {
            proyectoRepository.deleteById(id);
            System.out.println("Proyecto eliminado correctamente.");
        } else {
            System.err.println("Intento de eliminar un proyecto inexistente.");
        }
        return "redirect:/proyectos";
    }

    @PostMapping("/proyectos/editar")
public String editarProyecto(@RequestParam Long id,
                             @RequestParam String nombre,
                             @RequestParam String descripcion,
                             @RequestParam String fechaInicio,
                             @RequestParam String estado) {
    Proyecto proyecto = proyectoRepository.findById(id).orElse(null);
    if (proyecto != null) {
        proyecto.setNombre(nombre);
        proyecto.setDescripcion(descripcion);
        proyecto.setFechaInicio(LocalDate.parse(fechaInicio));
        proyecto.setEstado(Proyecto.EstadoProyecto.valueOf(estado));
        proyectoRepository.save(proyecto);
    }
    return "redirect:/proyectos";
}
}
