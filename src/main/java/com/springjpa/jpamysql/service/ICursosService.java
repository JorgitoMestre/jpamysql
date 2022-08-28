package com.springjpa.jpamysql.service;

import com.springjpa.jpamysql.entities.Cursos;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ICursosService {
    public List<Cursos> findAll();
    public Cursos saveCursos(Cursos cursos);
    public List<Cursos> getCursosProfesor(Long id);
    public Optional<Cursos> getCursosByName(String nombre);
    public Optional<Cursos> getCursosById(Long id);

    public void deleteCursos(Long id);
}
