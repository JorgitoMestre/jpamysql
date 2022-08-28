package com.springjpa.jpamysql.service;

import com.springjpa.jpamysql.entities.Cursos;
import com.springjpa.jpamysql.repository.ICursosDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursosService implements ICursosService{
    @Autowired
    private ICursosDao repository;

    @Override
    @Transactional(readOnly = true)
    public List<Cursos> findAll(){
       return repository.findAll();
    }

    @Override
    public Cursos saveCursos(Cursos cursos){
       return repository.save(cursos);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cursos> getCursosProfesor(Long id){
      return repository.findByProfesorID(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cursos> getCursosByName(String nombre) {
        return repository.findByNameCurso(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cursos> getCursosById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteCursos(Long id) {
         repository.deleteById(id);
    }
}
