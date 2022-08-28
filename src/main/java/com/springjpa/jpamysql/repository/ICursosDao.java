package com.springjpa.jpamysql.repository;

import com.springjpa.jpamysql.entities.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICursosDao extends JpaRepository<Cursos,Long> {

    public List<Cursos> findByProfesorID(Long id);
    public Optional<Cursos> findByNameCurso(String nombre);
    public Optional<Cursos> findById(Long id);
}
