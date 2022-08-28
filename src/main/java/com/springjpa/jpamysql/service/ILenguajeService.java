package com.springjpa.jpamysql.service;

import com.springjpa.jpamysql.entities.Lenguajes;

import java.util.List;
import java.util.Optional;

public interface ILenguajeService {
    public List<Lenguajes> findAll();
    public Lenguajes saveLenguaje(Lenguajes lenguaje);
    public Lenguajes findLenguajeByID(Long id);
    public Lenguajes findLenguajesById(Long id);
    public Optional<Lenguajes> findLenguajesByNombre(String nombre);
}
