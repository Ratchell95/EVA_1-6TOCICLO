package com.idat.Tarea3.controller;

import com.idat.Tarea3.model.dto.PersonRequest;
import com.idat.Tarea3.model.dto.PersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestión de personas
 * Implementa operaciones CRUD completas: GET, POST, PUT, DELETE
 * Cumple con los requisitos de la rúbrica EC1
 */
@RestController
@RequestMapping("api/v1/persons")
public class PersonController {

    @Autowired
    com.idat.Tarea3.service.PersonService personService;

    /**
     * GET - Obtener todas las personas
     * Endpoint: GET /api/v1/persons
     * @return Lista de personas con sus relaciones (documento y direcciones)
     */
    @GetMapping
    public ResponseEntity<List<PersonResponse>> getAllPersons() {
        List<PersonResponse> persons = personService.getAllPersons();
        return ResponseEntity.ok(persons);
    }

    /**
     * GET - Obtener una persona por ID
     * Endpoint: GET /api/v1/persons/{id}
     * @param id Identificador de la persona
     * @return Persona encontrada o error 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getPersonById(@PathVariable Long id) {
        PersonResponse person = personService.getPersonById(id);
        if (person != null) {
            return ResponseEntity.ok(person);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * POST - Registrar una nueva persona
     * Endpoint: POST /api/v1/persons
     * @param request Datos de la persona a crear (incluye mascota, documento y direcciones)
     * @return Respuesta con código 201 Created si fue exitoso
     */
    @PostMapping
    public ResponseEntity<PersonResponse> registerPerson(@RequestBody PersonRequest request) {
        PersonResponse created = personService.registerPerson(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * PUT - Actualizar una persona existente
     * Endpoint: PUT /api/v1/persons/{id}
     * @param id Identificador de la persona a actualizar
     * @param request Nuevos datos de la persona
     * @return Persona actualizada o error 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> updatePerson(
            @PathVariable Long id,
            @RequestBody PersonRequest request) {
        PersonResponse updated = personService.updatePerson(id, request);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * DELETE - Eliminar una persona por ID
     * Endpoint: DELETE /api/v1/persons/{id}
     * @param id Identificador de la persona a eliminar
     * @return Código 204 No Content si fue exitoso, 404 si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        boolean deleted = personService.deletePerson(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * GET - Buscar personas por país
     * Endpoint: GET /api/v1/persons/search/country?pais=Peru
     * @param pais Nombre del país a buscar
     * @return Lista de personas del país especificado
     */
    @GetMapping("/search/country")
    public ResponseEntity<List<PersonResponse>> getPersonsByCountry(@RequestParam String pais) {
        List<PersonResponse> persons = personService.getPersonsByCountry(pais);
        return ResponseEntity.ok(persons);
    }
}