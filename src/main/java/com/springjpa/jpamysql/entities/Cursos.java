package com.springjpa.jpamysql.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="cursos")
public class Cursos implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre",unique = true)
    private String nameCurso;

    @Column(name="profesor_id")
    private Long profesorID;

    private static final long serialVersionUID =1L;

    public Cursos(){}

    public Cursos(Long id, String nameCurso, Long profesorID) {
        this.id = id;
        this.nameCurso = nameCurso;
        this.profesorID = profesorID;
    }

    public Long getId() {
        return id;
    }

    public String getNameCurso() {
        return nameCurso;
    }

    public void setNameCurso(String nameCurso) {
        this.nameCurso = nameCurso;
    }

    public Long getProfesorID() {
        return profesorID;
    }

    public void setProfesorID(Long profesorID) {
        this.profesorID = profesorID;
    }
}
