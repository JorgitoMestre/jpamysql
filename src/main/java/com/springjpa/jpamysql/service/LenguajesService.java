package com.springjpa.jpamysql.service;

import com.springjpa.jpamysql.entities.Lenguajes;
import com.springjpa.jpamysql.repository.ILenguajesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LenguajesService implements ILenguajeService{
    @Autowired
    private ILenguajesDao lenguajesRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Lenguajes> findAll() {
        return lenguajesRepository.findAll();
    }

    @Override
    @Transactional
    public Lenguajes saveLenguaje(Lenguajes lenguaje) {
         return lenguajesRepository.save(lenguaje);
    }

    @Override
    @Transactional(readOnly = true)
    public Lenguajes findLenguajeByID(Long id) {
        return lenguajesRepository.findByIdSQL(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Lenguajes findLenguajesById(Long id) {
        return lenguajesRepository.findById(id).orElseThrow(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Lenguajes> findLenguajesByNombre(String nombre) {
        return lenguajesRepository.findByNombre(nombre);
    }
}
