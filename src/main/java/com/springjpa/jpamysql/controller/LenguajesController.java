package com.springjpa.jpamysql.controller;

import com.springjpa.jpamysql.entities.Lenguajes;
import com.springjpa.jpamysql.service.ILenguajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LenguajesController {
    @Autowired
    private ILenguajeService lenguajeService;

    @GetMapping("/getLenguajes")
    public ResponseEntity<List<Lenguajes>> getLenguajes(){
        return new ResponseEntity<>(lenguajeService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/setLenguaje")
    public ResponseEntity<Lenguajes> setLenguaje(@RequestBody Lenguajes lenguaje){
        Optional<Lenguajes> find = lenguajeService.findLenguajesByNombre(lenguaje.getNombre());
        if (!find.isPresent()){
            return new ResponseEntity<>(lenguajeService.saveLenguaje(lenguaje),HttpStatus.CREATED);
        }else{
            throw new ResponseStatusException(HttpStatus.CONFLICT,String.format("The lenguage %s is already",lenguaje.getNombre()));
        }
    }

   @GetMapping("/getLenguajeId")
    public ResponseEntity<?> getLenguajeID(@RequestBody Long id){
        Lenguajes leng = lenguajeService.findLenguajeByID(id);
        if (leng != null){
           return new ResponseEntity<>(leng,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(String.format("The language with id %d not found",id),HttpStatus.NOT_FOUND);
        }
    }

}
