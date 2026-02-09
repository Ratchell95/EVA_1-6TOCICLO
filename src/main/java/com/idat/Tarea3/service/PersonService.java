package com.idat.Tarea3.service;

import com.idat.Tarea3.model.dto.PersonRequest;
import com.idat.Tarea3.model.dto.PersonResponse;

import java.util.List;

public interface PersonService {

    List<PersonResponse> getAllPersons();
    PersonResponse getPersonById(Long id);
    PersonResponse registerPerson(PersonRequest request);
    PersonResponse updatePerson(Long id, PersonRequest request);
    boolean deletePerson(Long id);
    List<PersonResponse> getPersonsByCountry(String pais);
}