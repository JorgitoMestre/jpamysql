package com.springjpa.jpamysql.controller;

import com.springjpa.jpamysql.entities.Cursos;
import com.springjpa.jpamysql.entities.Profesor;
import com.springjpa.jpamysql.service.ICursosService;
import com.springjpa.jpamysql.service.IProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CursosController {
    @Autowired
    private ICursosService cursosService;
    @Autowired
    private IProfesorService profesorService;

    @GetMapping("/cursos")
    public ResponseEntity<List<Cursos>> getCursos(){
        return new ResponseEntity<List<Cursos>>(cursosService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/createCurso")
    public ResponseEntity<?> setCursos(@RequestBody Cursos cursos){
        Optional<Cursos> curso= cursosService.getCursosByName(cursos.getNameCurso());
        if (curso.isPresent()){
            return new ResponseEntity<>(String.format("The course %s exist",cursos.getNameCurso()),HttpStatus.CONFLICT);
        }else{
            return new ResponseEntity<Cursos>(cursosService.saveCursos(cursos),HttpStatus.CREATED);
        }
    }

    @PutMapping("/updateCurso")
    public ResponseEntity<?> updateCursos(@RequestBody Cursos cursos){
        Optional<Cursos> curso= cursosService.getCursosById(cursos.getId());
        if (curso.isPresent()){
            curso.get().setNameCurso(cursos.getNameCurso());
            curso.get().setProfesorID(cursos.getProfesorID());
            return new ResponseEntity<Cursos>(cursosService.saveCursos(curso.get()),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(String.format("The course %s not exist",cursos.getNameCurso()),HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("/deleteCurso")
    public ResponseEntity<?> deleteCursos(@RequestBody Cursos cursos){
        Optional<Cursos> curso= cursosService.getCursosById(cursos.getId());
        if (curso.isPresent()){
            cursosService.deleteCursos(cursos.getId());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(String.format("The course %s not exist",cursos.getNameCurso()),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cursos_profesor")
    public ResponseEntity<?> getCursosProfesor(@RequestBody Profesor profesor){
        Profesor profe = profesorService.findProfesor(profesor);
        if (profe != null){
            if (profe.getCursos().size() > 0){
                return new ResponseEntity<List<Cursos>>(cursosService.getCursosProfesor(profe.getId()),HttpStatus.OK);
            }else {
                return new ResponseEntity<>(String.format("Professor %s not have any courses",profe.getNombre()),HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(String.format("Professor %s not exist",profesor.getNombre()),HttpStatus.NOT_FOUND);
        }
    }
}
