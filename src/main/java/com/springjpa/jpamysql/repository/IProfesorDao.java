package com.springjpa.jpamysql.repository;

//import com.springjpa.jpamysql.entities.MProfesorLenguaje;
import com.springjpa.jpamysql.entities.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

//implementacion del patron DAO
public interface IProfesorDao extends JpaRepository<Profesor,Long> {
     public Profesor findByEmail(String email);
     public Profesor findByEmailAndPassword(String email, String pass);
     public Optional<Profesor> findById(Long id);
     @Query("SELECT p FROM Profesor AS p WHERE p.id=?1")
     public Profesor findByIdSQL(Long id);
}
