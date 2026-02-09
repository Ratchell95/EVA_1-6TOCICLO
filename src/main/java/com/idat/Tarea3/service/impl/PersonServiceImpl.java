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


@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonMapper personMapper;


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

    @Override
    public PersonResponse getPersonById(Long id) {
        Optional<Person> personOpt = personRepository.findById(id);

        if (personOpt.isPresent()) {
            return personMapper.toPersonResponse(personOpt.get());
        }
        return null;
    }

    @Override
    @Transactional
    public PersonResponse registerPerson(PersonRequest request) {

        Person entity = personMapper.toPersonEntity(request);

        Person saved = personRepository.save(entity);

        return personMapper.toPersonResponse(saved);
    }

    @Override
    @Transactional
    public PersonResponse updatePerson(Long id, PersonRequest request) {
        Optional<Person> personOpt = personRepository.findById(id);

        if (personOpt.isPresent()) {
            Person existingPerson = personOpt.get();

            existingPerson.setNombre(request.getNombre());
            existingPerson.setApellido(request.getApellido());
            existingPerson.setFechaNacimiento(request.getFechaNacimiento());
            existingPerson.setCorreo(request.getCorreo());
            existingPerson.setPais(request.getPais());

            if (request.getMascota() != null) {
                if (existingPerson.getMascota() != null) {

                    existingPerson.getMascota().setNombre(request.getMascota().getNombre());
                    existingPerson.getMascota().setEdad(request.getMascota().getEdad());
                } else {
                    existingPerson.setMascota(personMapper.toMascotaEntity(request.getMascota()));
                }
            }


            if (request.getDocumento() != null) {
                if (existingPerson.getDocumento() != null) {
                    existingPerson.getDocumento().setTipoDocumento(request.getDocumento().getTipoDocumento());
                    existingPerson.getDocumento().setNumeroDocumento(request.getDocumento().getNumeroDocumento());
                    existingPerson.getDocumento().setFechaExpedicion(request.getDocumento().getFechaExpedicion());
                    existingPerson.getDocumento().setFechaVencimiento(request.getDocumento().getFechaVencimiento());
                } else {
                    existingPerson.setDocumento(personMapper.toDocumentoEntity(request.getDocumento()));
                }
            }

            if (request.getDirecciones() != null) {

                existingPerson.getDirecciones().clear();


                request.getDirecciones().forEach(direccionRequest -> {
                    existingPerson.addDireccion(personMapper.toDireccionEntity(direccionRequest));
                });
            }

            Person updated = personRepository.save(existingPerson);

            return personMapper.toPersonResponse(updated);
        }

        return null;
    }


    @Override
    @Transactional
    public boolean deletePerson(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<PersonResponse> getPersonsByCountry(String pais) {
        List<Person> persons = personRepository.findByPais(pais);

        return persons.stream()
                .map(personMapper::toPersonResponse)
                .collect(Collectors.toList());
    }
}






























































