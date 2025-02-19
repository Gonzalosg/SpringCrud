package com.example.gestion_proyectos.controller;

import com.example.gestion_proyectos.model.Proyecto;
import com.example.gestion_proyectos.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    public List<Proyecto> listarProyectos() {
        return proyectoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Proyecto> obtenerProyecto(@PathVariable Long id) {
        return proyectoService.obtenerPorId(id);
    }

    @PostMapping
    public Proyecto crearProyecto(@RequestBody Proyecto proyecto) {
        return proyectoService.guardar(proyecto);
    }

    @PutMapping("/{id}")
    public Proyecto actualizarProyecto(@PathVariable Long id, @RequestBody Proyecto proyecto) {
        proyecto.setId(id);
        return proyectoService.guardar(proyecto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminar(id);
    }
}
