package com.springjpa.jpamysql.service;

//import com.springjpa.jpamysql.entities.MProfesorLenguaje;
import com.springjpa.jpamysql.entities.Profesor;
import com.springjpa.jpamysql.repository.IProfesorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorServiceImp implements IProfesorService{
   @Autowired
   private IProfesorDao repository;

    @Override
    @Transactional(readOnly = true)
    public List<Profesor> findAll() {
        return  repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Profesor findProfesor(Profesor profesor) {
        return repository.findByEmail(profesor.getEmail());
    }

    @Override
    @Transactional(readOnly = true)
    public Profesor checkProfesorLogin(Profesor profesor) {
        return repository.findByEmailAndPassword(profesor.getEmail(), profesor.getPassword());
    }

    @Override
    @Transactional
    public void deleteProfesor(Profesor profesor) {
        repository.deleteById(profesor.getId());
    }

    @Override
    @Transactional
    public Profesor updateProfesor(Profesor profesor) {
        return repository.save(profesor);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Profesor> findProfesorById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void deleteProfesor(Long id) {
       repository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllProfesor(){
        repository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Profesor findById(Long id) {
        return repository.findById(id).orElseThrow(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Profesor findByIdSQL(Long id) {
        return repository.findByIdSQL(id);
    }

    @Override
    @Transactional
    public void save(Profesor profesor) {
         repository.save(profesor);
    }
}
