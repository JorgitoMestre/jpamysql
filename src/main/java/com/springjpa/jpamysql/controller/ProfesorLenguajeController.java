package com.springjpa.jpamysql.controller;

import com.springjpa.jpamysql.entities.Lenguajes;
import com.springjpa.jpamysql.entities.Profesor;
import com.springjpa.jpamysql.mapper.Mapper;
import com.springjpa.jpamysql.model.MProfesorLenguaje;
import com.springjpa.jpamysql.model.Profesor_Lenguaje;
import com.springjpa.jpamysql.service.ILenguajeService;
import com.springjpa.jpamysql.service.IProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProfesorLenguajeController {
    @Autowired
    private ILenguajeService lenguajeService;
    @Autowired
    private IProfesorService profesorService;

    //dado el id de un profesor devuelve la lista de lenguajes que conoce
    //este profesor
    @PostMapping("/getlenguajes_profesor")
    public ResponseEntity<?> listaLenguajesProfesor(@RequestBody Profesor profesorLenguaje){
        Profesor profesor1 = profesorService.findById(profesorLenguaje.getId());
        if (profesor1 != null){
            Collection<Lenguajes> listaLenguajes=profesor1.getLenguajes();
            if(listaLenguajes.size()>0){
                //List<MProfesorLenguaje> Mapper.convertirListaLenguajeProfesor(listaLenguajes);
                //return new ResponseEntity<>(listaLenguajes,HttpStatus.OK);
                return new ResponseEntity<>(Mapper.convertirListaLenguajeProfesor(profesor1),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(String.format("There are no Lenguajes for professor %s",profesor1.getNombre()),
                        HttpStatus.NOT_FOUND);
            }
        }else {
            return new ResponseEntity<>(String.format("There professor %s not exist",profesor1.getNombre()),
                    HttpStatus.NOT_FOUND);
        }
    }

    //dado el id de un lenguaje devuelve la lista de profesores que conocen
    //este lenguaje
    @PostMapping("/getprofesor_lenguajes")
    public ResponseEntity<?> listaProfesorLenguajes(@RequestBody Lenguajes lenguajes){
        Lenguajes lenguajes1 = lenguajeService.findLenguajeByID(lenguajes.getId());
        if (lenguajes1 != null){
            Collection<Profesor> listaprofesores=lenguajes1.getProfesores();
            if(listaprofesores.size()>0){
                //List<MProfesorLenguaje> Mapper.convertirListaLenguajeProfesor(listaLenguajes);
                //return new ResponseEntity<>(listaLenguajes,HttpStatus.OK);
                return new ResponseEntity<>(Mapper.convertirListaProfesorLenguaje(lenguajes1),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(String.format("There are no Profesors for lenguage %s",lenguajes1.getNombre()),
                        HttpStatus.NOT_FOUND);
            }
        }else {
            return new ResponseEntity<>(String.format("The lenguage with  id %d not exist",lenguajes.getId()),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save_profesor_lenguaje")
    public ResponseEntity<?> saveProfesorLenguaje(@RequestBody Profesor_Lenguaje profesorLenguaje){
        Profesor profesor = profesorService.findById(profesorLenguaje.getProfesor().getId());
        Lenguajes lenguajes = lenguajeService.findLenguajeByID(profesorLenguaje.getLenguajes().getId());
        if (profesor!=null && lenguajes!=null){
            profesor.addLenguaje(lenguajes);
            profesorService.save(profesor);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
