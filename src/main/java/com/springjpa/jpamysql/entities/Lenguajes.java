package com.springjpa.jpamysql.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lenguajes")
public class Lenguajes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;

    @Column(name = "fecha", nullable = false)
    @JsonFormat(pattern = "YYYY-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @PrePersist
    public void prePersist(){
        fecha= new Date();
    }

    @ManyToMany
    @JoinTable(name = "profesores_lenguajes",
            joinColumns = @JoinColumn(name = "lenguaje_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "profesor_id", referencedColumnName = "id"))
    private Set<Profesor> profesores = new HashSet();

    private static final long serialVersionUID =1L;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public Set<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(Set<Profesor> profesores) {
        this.profesores = profesores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
