package com.example.gestion_proyectos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_proyectos.model.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
}
