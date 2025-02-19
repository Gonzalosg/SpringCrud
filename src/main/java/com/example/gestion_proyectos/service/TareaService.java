package com.example.gestion_proyectos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestion_proyectos.model.Tarea;
import com.example.gestion_proyectos.repository.TareaRepository;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    // Obtener todas las tareas
    public List<Tarea> obtenerTodas() {
        return tareaRepository.findAll();
    }

    // Obtener tareas de un proyecto
    public List<Tarea> obtenerPorProyecto(Long proyectoId) {
        return tareaRepository.findByProyectoId(proyectoId);
    }

    // Obtener una tarea por ID
    public Optional<Tarea> obtenerPorId(Long id) {
        return tareaRepository.findById(id);
    }

    // Guardar una nueva tarea
    public Tarea guardar(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    // Eliminar una tarea por ID
    public void eliminar(Long id) {
        tareaRepository.deleteById(id);
    }
}
