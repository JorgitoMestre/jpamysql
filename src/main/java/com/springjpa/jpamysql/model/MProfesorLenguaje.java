package com.springjpa.jpamysql.model;
import com.springjpa.jpamysql.entities.Lenguajes;
import com.springjpa.jpamysql.entities.Profesor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MProfesorLenguaje {
    //para mapear los datos de lenguajes q sabe un profesor
    //usado en el endpoint "getlenguajes_profesor"
    //(dado el id del profesor obtengo los lenguajes)
    private Long id_profe;
    private String nombre;
    private String email;

    private Long id_lenguaje;

    private String nombreLenguaje;

    public MProfesorLenguaje(){

    }

    public Long getId_profe() {
        return id_profe;
    }

    public void setId_profe(Long id_profe) {
        this.id_profe = id_profe;
    }

    public Long getId_lenguaje() {
        return id_lenguaje;
    }

    public void setId_lenguaje(Long id_lenguaje) {
        this.id_lenguaje = id_lenguaje;
    }

    public String getNombreLenguaje() {
        return nombreLenguaje;
    }

    public void setNombreLenguaje(String nombreLenguaje) {
        this.nombreLenguaje = nombreLenguaje;
    }

    public MProfesorLenguaje(Profesor profesor, Lenguajes lenguajes){
        this.id_lenguaje= lenguajes.getId();
        this.nombreLenguaje= lenguajes.getNombre();
        this.id_profe= profesor.getId();
        this.nombre= profesor.getNombre();
        this.email= profesor.getEmail();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
