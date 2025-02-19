package com.example.gestion_proyectos.service;

import com.example.gestion_proyectos.model.Proyecto;
import com.example.gestion_proyectos.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    // Obtener todos los proyectos
    public List<Proyecto> obtenerTodos() {
        return proyectoRepository.findAll();
    }

    // Obtener un proyecto por ID
    public Optional<Proyecto> obtenerPorId(Long id) {
        return proyectoRepository.findById(id);
    }

    // Guardar un nuevo proyecto
    public Proyecto guardar(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    // Eliminar un proyecto por ID
    public void eliminar(Long id) {
        proyectoRepository.deleteById(id);
    }
}
