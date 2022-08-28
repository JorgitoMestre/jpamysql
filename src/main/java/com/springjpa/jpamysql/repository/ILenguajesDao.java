package com.springjpa.jpamysql.repository;

import com.springjpa.jpamysql.entities.Lenguajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ILenguajesDao extends JpaRepository<Lenguajes,Long> {
    @Query("SELECT l FROM Lenguajes AS l WHERE l.id=?1")
    public Lenguajes findByIdSQL(Long id);

    public Optional<Lenguajes> findById(Long id);

    public Optional<Lenguajes> findByNombre(String nombre);
}
