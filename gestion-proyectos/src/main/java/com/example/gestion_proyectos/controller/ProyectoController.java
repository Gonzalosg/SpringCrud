package com.example.gestion_proyectos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestion_proyectos.model.Proyecto;
import com.example.gestion_proyectos.repository.ProyectoRepository;

@RestController
@RequestMapping("/api/proyectos") // Ruta base para proyectos
public class ProyectoController {

    @Autowired
    private ProyectoRepository proyectoRepository;

    // Obtener todos los proyectos
    @GetMapping
    public List<Proyecto> obtenerProyectos() {
        return proyectoRepository.findAll();
    }

    // Obtener un proyecto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> obtenerProyectoPorId(@PathVariable Long id) {
        Optional<Proyecto> proyecto = proyectoRepository.findById(id);
        return proyecto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo proyecto
    @PostMapping
    public Proyecto crearProyecto(@RequestBody Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    // Actualizar un proyecto existente
    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> actualizarProyecto(@PathVariable Long id, @RequestBody Proyecto proyectoActualizado) {
        return proyectoRepository.findById(id)
            .map(proyecto -> {
                proyecto.setNombre(proyectoActualizado.getNombre());
                proyecto.setDescripcion(proyectoActualizado.getDescripcion());
                proyecto.setFechaInicio(proyectoActualizado.getFechaInicio());
                proyecto.setEstado(proyectoActualizado.getEstado());
                return ResponseEntity.ok(proyectoRepository.save(proyecto));
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un proyecto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProyecto(@PathVariable Long id) {
        if (proyectoRepository.existsById(id)) {
            proyectoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
