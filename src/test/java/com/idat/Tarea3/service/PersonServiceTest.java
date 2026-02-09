package com.idat.Tarea3.service;

import com.idat.Tarea3.model.dto.*;
import com.idat.Tarea3.model.entity.Documento;
import com.idat.Tarea3.model.entity.Mascota;
import com.idat.Tarea3.model.entity.Person;
import com.idat.Tarea3.model.mapper.PersonMapper;
import com.idat.Tarea3.repository.PersonRepository;
import com.idat.Tarea3.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonServiceImpl personService;

    private Person person;
    private PersonRequest personRequest;
    private PersonResponse personResponse;

    @BeforeEach
    void setUp() {

        person = new Person();
        person.setId(1L);
        person.setNombre("Juan");
        person.setApellido("Pérez");
        person.setCorreo("juan.perez@email.com");
        person.setPais("Peru");


        Documento documento = new Documento();
        documento.setTipoDocumento("DNI");
        documento.setNumeroDocumento("12345678");
        person.setDocumento(documento);

        Mascota mascota = new Mascota();
        mascota.setNombre("Firulais");
        mascota.setEdad(3);
        person.setMascota(mascota);

        personRequest = new PersonRequest();
        personRequest.setNombre("Juan");
        personRequest.setApellido("Pérez");
        personRequest.setCorreo("juan.perez@email.com");
        personRequest.setPais("Peru");

        personResponse = new PersonResponse();
        personResponse.setId(1L);
        personResponse.setNombre("Juan");
        personResponse.setApellido("Pérez");
        personResponse.setCorreo("juan.perez@email.com");
        personResponse.setPais("Peru");
    }

    @Test
    void testGetAllPersons() {

        List<Person> persons = Arrays.asList(person);
        when(personRepository.findAll()).thenReturn(persons);
        when(personMapper.toPersonResponse(any(Person.class))).thenReturn(personResponse);
        List<PersonResponse> result = personService.getAllPersons();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Juan", result.get(0).getNombre());
        verify(personRepository, times(1)).findAll();
    }

    @Test
    void testGetPersonById_Found() {

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personMapper.toPersonResponse(person)).thenReturn(personResponse);
        PersonResponse result = personService.getPersonById(1L);
        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        assertEquals("Pérez", result.getApellido());
        verify(personRepository, times(1)).findById(1L);
    }

    @Test
    void testGetPersonById_NotFound() {

        when(personRepository.findById(999L)).thenReturn(Optional.empty());
        PersonResponse result = personService.getPersonById(999L);
        assertNull(result);
        verify(personRepository, times(1)).findById(999L);
    }

    @Test
    void testRegisterPerson() {

        when(personMapper.toPersonEntity(personRequest)).thenReturn(person);
        when(personRepository.save(person)).thenReturn(person);
        when(personMapper.toPersonResponse(person)).thenReturn(personResponse);
        PersonResponse result = personService.registerPerson(personRequest);
        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(personRepository, times(1)).save(person);
    }

    @Test
    void testDeletePerson_Success() {

        when(personRepository.existsById(1L)).thenReturn(true);
        doNothing().when(personRepository).deleteById(1L);
        boolean result = personService.deletePerson(1L);
        assertTrue(result);
        verify(personRepository, times(1)).existsById(1L);
        verify(personRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeletePerson_NotFound() {

        when(personRepository.existsById(999L)).thenReturn(false);
        boolean result = personService.deletePerson(999L);
        assertFalse(result);
        verify(personRepository, times(1)).existsById(999L);
        verify(personRepository, never()).deleteById(999L);
    }

    @Test
    void testGetPersonsByCountry() {

        List<Person> persons = Arrays.asList(person);
        when(personRepository.findByPais("Peru")).thenReturn(persons);
        when(personMapper.toPersonResponse(person)).thenReturn(personResponse);
        List<PersonResponse> result = personService.getPersonsByCountry("Peru");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Peru", result.get(0).getPais());
        verify(personRepository, times(1)).findByPais("Peru");
    }
}