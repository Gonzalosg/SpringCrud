package com.example.gestion_proyectos.controller;

import com.example.gestion_proyectos.model.Tarea;
import com.example.gestion_proyectos.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public List<Tarea> listarTareas() {
        return tareaService.obtenerTodas();
    }

    @GetMapping("/proyecto/{proyectoId}")
    public List<Tarea> listarTareasPorProyecto(@PathVariable Long proyectoId) {
        return tareaService.obtenerPorProyecto(proyectoId);
    }

    @GetMapping("/{id}")
    public Optional<Tarea> obtenerTarea(@PathVariable Long id) {
        return tareaService.obtenerPorId(id);
    }

    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        return tareaService.guardar(tarea);
    }

    @PutMapping("/{id}")
    public Tarea actualizarTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        tarea.setId(id);
        return tareaService.guardar(tarea);
    }

    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Long id) {
        tareaService.eliminar(id);
    }
}
