package com.springjpa.jpamysql.model;

import com.springjpa.jpamysql.entities.Lenguajes;
import com.springjpa.jpamysql.entities.Profesor;

public class Profesor_Lenguaje {
    private Profesor profesor;
    private Lenguajes lenguajes;

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Lenguajes getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(Lenguajes lenguajes) {
        this.lenguajes = lenguajes;
    }
}
