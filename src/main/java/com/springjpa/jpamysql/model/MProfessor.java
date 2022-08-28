package com.springjpa.jpamysql.model;

import com.springjpa.jpamysql.entities.Profesor;

public class MProfessor {
    private Long id;
    private String nombre;
    private String email;
    private String foto;

    public MProfessor(){}

    public MProfessor(Profesor profesor){
        this.id= profesor.getId();
        this.nombre = profesor.getNombre();
        this.email = profesor.getEmail();
        this.foto = profesor.getFoto();
    }

    public MProfessor(Long id, String nombre, String email, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.foto = foto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
