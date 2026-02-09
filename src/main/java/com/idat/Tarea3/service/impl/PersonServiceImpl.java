package com.idat.Tarea3.service.impl;

import com.idat.Tarea3.model.entity.Person;
import com.idat.Tarea3.model.dto.PersonRequest;
import com.idat.Tarea3.model.dto.PersonResponse;
import com.idat.Tarea3.model.mapper.PersonMapper;
import com.idat.Tarea3.repository.PersonRepository;
import com.idat.Tarea3.service.PersonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de persona
 * Contiene la lógica de negocio para operaciones CRUD
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonMapper personMapper;

    /**
     * Obtener todas las personas
     */
    @Override
    public List<PersonResponse> getAllPersons() {
        Iterable<Person> persons = personRepository.findAll();
        List<PersonResponse> response = new ArrayList<>();

        persons.forEach(p -> {
            PersonResponse dto = personMapper.toPersonResponse(p);
            response.add(dto);
        });

        return response;
    }

    /**
     * Obtener persona por ID
     */
    @Override
    public PersonResponse getPersonById(Long id) {
        Optional<Person> personOpt = personRepository.findById(id);

        if (personOpt.isPresent()) {
            return personMapper.toPersonResponse(personOpt.get());
        }
        return null;
    }

    /**
     * Registrar una nueva persona
     * Incluye cascade para guardar mascota, documento y direcciones
     */
    @Override
    @Transactional
    public PersonResponse registerPerson(PersonRequest request) {
        // Convertir DTO a entidad
        Person entity = personMapper.toPersonEntity(request);

        // Guardar en base de datos
        Person saved = personRepository.save(entity);

        // Retornar como DTO
        return personMapper.toPersonResponse(saved);
    }

    /**
     * Actualizar persona existente
     * Actualiza datos personales, documento y direcciones
     */
    @Override
    @Transactional
    public PersonResponse updatePerson(Long id, PersonRequest request) {
        Optional<Person> personOpt = personRepository.findById(id);

        if (personOpt.isPresent()) {
            Person existingPerson = personOpt.get();

            // Actualizar datos básicos
            existingPerson.setNombre(request.getNombre());
            existingPerson.setApellido(request.getApellido());
            existingPerson.setFechaNacimiento(request.getFechaNacimiento());
            existingPerson.setCorreo(request.getCorreo());
            existingPerson.setPais(request.getPais());

            // Actualizar mascota si viene en el request
            if (request.getMascota() != null) {
                if (existingPerson.getMascota() != null) {
                    // Actualizar mascota existente
                    existingPerson.getMascota().setNombre(request.getMascota().getNombre());
                    existingPerson.getMascota().setEdad(request.getMascota().getEdad());
                } else {
                    // Crear nueva mascota
                    existingPerson.setMascota(personMapper.toMascotaEntity(request.getMascota()));
                }
            }

            // Actualizar documento si viene en el request
            if (request.getDocumento() != null) {
                if (existingPerson.getDocumento() != null) {
                    // Actualizar documento existente
                    existingPerson.getDocumento().setTipoDocumento(request.getDocumento().getTipoDocumento());
                    existingPerson.getDocumento().setNumeroDocumento(request.getDocumento().getNumeroDocumento());
                    existingPerson.getDocumento().setFechaExpedicion(request.getDocumento().getFechaExpedicion());
                    existingPerson.getDocumento().setFechaVencimiento(request.getDocumento().getFechaVencimiento());
                } else {
                    // Crear nuevo documento
                    existingPerson.setDocumento(personMapper.toDocumentoEntity(request.getDocumento()));
                }
            }

            // Actualizar direcciones (eliminar las viejas y agregar las nuevas)
            if (request.getDirecciones() != null) {
                // Limpiar direcciones existentes
                existingPerson.getDirecciones().clear();

                // Agregar nuevas direcciones
                request.getDirecciones().forEach(direccionRequest -> {
                    existingPerson.addDireccion(personMapper.toDireccionEntity(direccionRequest));
                });
            }

            // Guardar cambios
            Person updated = personRepository.save(existingPerson);

            return personMapper.toPersonResponse(updated);
        }

        return null;
    }

    /**
     * Eliminar persona por ID
     * Elimina en cascada documento y direcciones (por orphanRemoval)
     */
    @Override
    @Transactional
    public boolean deletePerson(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Buscar personas por país
     */
    @Override
    public List<PersonResponse> getPersonsByCountry(String pais) {
        List<Person> persons = personRepository.findByPais(pais);

        return persons.stream()
                .map(personMapper::toPersonResponse)
                .collect(Collectors.toList());
    }
}






























































