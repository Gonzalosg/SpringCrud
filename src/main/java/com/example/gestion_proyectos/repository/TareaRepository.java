package com.example.gestion_proyectos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_proyectos.model.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByProyectoId(Long proyectoId);
}
