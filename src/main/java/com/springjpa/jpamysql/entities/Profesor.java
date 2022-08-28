package com.springjpa.jpamysql.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="profesors")
public class Profesor implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String nombre;

    @Column(length = 60, unique = true)
    private String email;

    private String password;

    @Column(length = 2040)
    private String foto;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)  //indica q es un recurso temporal
    private Date createAt;
    @PrePersist
    public void prePersist(){
        createAt= new Date();
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "profesor_id" /*este es el id de profesor en la clase cursos*/
                , referencedColumnName = "id" /*este es el id de la clase profesor aqui*/)
    private List<Cursos> cursos= new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(name="profesores_lenguajes", joinColumns = @JoinColumn(name = "profesor_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "lenguaje_id",referencedColumnName = "id"))
    private Set<Lenguajes> lenguajes = new HashSet();

    public Set<Lenguajes> getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(Set<Lenguajes> lenguajes) {
        this.lenguajes = lenguajes;
    }

    public List<Cursos> getCursos() {
        return cursos;
    }

    public void setCursos(List<Cursos> cursos) {
        this.cursos = cursos;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void addLenguaje(Lenguajes lenguajes){
        this.lenguajes.add(lenguajes);
    }

    private static final long serialVersionUID =1L;
}
