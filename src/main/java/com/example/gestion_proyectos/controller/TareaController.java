package com.example.gestion_proyectos.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gestion_proyectos.model.Proyecto;
import com.example.gestion_proyectos.model.Tarea;
import com.example.gestion_proyectos.repository.ProyectoRepository;
import com.example.gestion_proyectos.repository.TareaRepository;

@Controller
public class TareaController {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    // Guardar una nueva tarea
    @PostMapping("/tareas/guardar")
    public String guardarTarea(@RequestParam Long proyectoId,
                               @RequestParam String titulo,
                               @RequestParam String descripcion,
                               @RequestParam String fechaLimite,
                               @RequestParam String estado) {

        Optional<Proyecto> proyectoOptional = proyectoRepository.findById(proyectoId);
        if (proyectoOptional.isPresent()) {
            Proyecto proyecto = proyectoOptional.get();
            Tarea tarea = new Tarea();
            tarea.setTitulo(titulo);
            tarea.setDescripcion(descripcion);
            tarea.setFechaLimite(fechaLimite.isEmpty() ? null : LocalDate.parse(fechaLimite));
            tarea.setEstado(Tarea.EstadoTarea.valueOf(estado));
            tarea.setProyecto(proyecto);
            tareaRepository.save(tarea);
        }
        return "redirect:/proyectos";
    }

    // Eliminar tarea
    @PostMapping("/tareas/eliminar/{id}")
    public String eliminarTarea(@PathVariable Long id) {
        tareaRepository.deleteById(id);
        return "redirect:/proyectos";
    }
}
