package com.springjpa.jpamysql.controller;

//import com.springjpa.jpamysql.entities.MProfesorLenguaje;
import com.springjpa.jpamysql.entities.Profesor;
import com.springjpa.jpamysql.mapper.Mapper;
import com.springjpa.jpamysql.model.MProfessor;
import com.springjpa.jpamysql.service.IProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProfesorController {
    @Autowired
    private IProfesorService profesorService;

    @GetMapping("/profesores")
    @ResponseStatus(HttpStatus.OK)
    public List<Profesor> getProfesores(){
        return profesorService.findAll();
    }

    @PostMapping("/sing_up")
    public ResponseEntity<?> addProfesor(@RequestBody Profesor profesor){
        if (profesorService.findProfesor(profesor)==null){
            profesorService.save(profesor);
            return new ResponseEntity<>(String.format("Professor created"),HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProfesor(@PathVariable(value="id") Long id, @RequestBody Profesor profesor){
        Optional<Profesor> profe = profesorService.findProfesorById(id);
        if (profe.isPresent()){
            profe.get().setNombre(profesor.getNombre());
            profe.get().setEmail(profesor.getEmail());
            profe.get().setPassword(profesor.getPassword());
            profesorService.updateProfesor(profe.get());
            return new ResponseEntity<Profesor>(profe.get(),HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Profesor with id %d not found",id));
        }
    }

    //otra forma de hacer el update
    @PutMapping("/update_sql")
    public ResponseEntity<?> updateProfesorSQL(@RequestBody Profesor profesor){
        Profesor profe = profesorService.findByIdSQL(profesor.getId());
        if (profe != null){
            profe.setNombre(profesor.getNombre());
            profe.setEmail(profesor.getEmail());
            profe.setPassword(profesor.getPassword());
            profesorService.updateProfesor(profe);
            return new ResponseEntity<Profesor>(profe,HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Profesor with id %d not found",profesor.getId()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProfesor(@PathVariable(value = "id") Long id){
        Optional<Profesor> profe = profesorService.findProfesorById(id);
        if (profe.isPresent()){
            profesorService.deleteProfesor(id);
            return new ResponseEntity<>(String.format("Professor with id %d deleted", id), HttpStatus.OK);
        }else {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Profesor with id %d not found",id));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAllProfesor(){
        profesorService.deleteAllProfesor();
        return new ResponseEntity<>(String.format("Professors deleted"), HttpStatus.OK);
    }

    // delet profesor mediante metodo post (MALA PRACTICA DE PROGRAMACION) pero funciona
    @PostMapping("/delete_post")
    public ResponseEntity<?> deleteProfesorPost(@RequestBody Profesor profesor){
        if (profesorService.findProfesor(profesor) != null){
            profesorService.deleteProfesor(profesor);
            return new ResponseEntity<>(String.format("Professor deleted"), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(String.format("Professor with id %d not found",profesor.getId()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/find_profesor")
    public ResponseEntity<Profesor> findProfesor(@RequestBody Profesor profesor){
        Profesor profe = profesorService.findProfesor(profesor);
        if (profe != null){
            return new ResponseEntity<Profesor>(profe,HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Profesor with email %s not found",profesor.getEmail()));
        }
    }

    @GetMapping("/get_profesor/{id}")
    public ResponseEntity<?> getProfesorById(@PathVariable(value = "id") Long id){
        Optional<Profesor> profe = profesorService.findProfesorById(id);
        if (profe.isPresent()){
            return new ResponseEntity<>(profe.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(String.format("Profesor with id %d not found",id),HttpStatus.NOT_FOUND);
        }
    }

    //loggin con mapeo de datos
    @PostMapping("/loggin")
    public ResponseEntity<?> logginByUserAndPass(@RequestBody Profesor profesor){
        Profesor profe = profesorService.checkProfesorLogin(profesor);
        if (profe != null){
            List<Profesor> profesores = new ArrayList<>();
            profesores.add(profe);
            List<MProfessor> mProfessors = new ArrayList<>();
            mProfessors = Mapper.convertirLista(profesores);
            return new ResponseEntity<>(mProfessors, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(String.format("Profesor with email %s and password %s not found",profesor.getEmail(),profesor.getPassword()),HttpStatus.NOT_FOUND);
        }
    }
}
