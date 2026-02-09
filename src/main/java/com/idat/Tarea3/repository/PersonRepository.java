package com.idat.Tarea3.repository;

import com.idat.Tarea3.model.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByPais(String pais);
    List<Person> findByNombre(String nombre);
    List<Person> findByApellido(String apellido);
    boolean existsByCorreo(String correo);
}






























































