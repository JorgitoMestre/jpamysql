package com.springjpa.jpamysql.service;

//import com.springjpa.jpamysql.entities.MProfesorLenguaje;
import com.springjpa.jpamysql.entities.Profesor;

import java.util.List;
import java.util.Optional;

public interface IProfesorService {
    public List<Profesor> findAll();
    public Profesor findProfesor(Profesor profesor);
    public Profesor checkProfesorLogin(Profesor profesor);
    public void deleteProfesor(Profesor profesor);

    public void deleteAllProfesor();
    public Profesor updateProfesor(Profesor profesor);
    public Optional<Profesor> findProfesorById(Long id);
    public void deleteProfesor(Long id);
    public Profesor findById(Long id);
    public Profesor findByIdSQL(Long id);

   public void save(Profesor profesor);
}
