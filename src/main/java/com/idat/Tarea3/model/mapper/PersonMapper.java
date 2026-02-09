package com.idat.Tarea3.model.mapper;

import com.idat.Tarea3.model.dto.*;
import com.idat.Tarea3.model.entity.Direccion;
import com.idat.Tarea3.model.entity.Documento;
import com.idat.Tarea3.model.entity.Mascota;
import com.idat.Tarea3.model.entity.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    public PersonResponse toPersonResponse(Person p) {
        var response = new PersonResponse();
        response.setId(p.getId());
        response.setNombre(p.getNombre());
        response.setApellido(p.getApellido());
        response.setPais(p.getPais());
        response.setCorreo(p.getCorreo());

        if (p.getDocumento() != null) {
            response.setDocumento(toDocumentoResponse(p.getDocumento()));
        }

        if (p.getDirecciones() != null && !p.getDirecciones().isEmpty()) {
            List<DireccionResponse> direccionesResponse = p.getDirecciones().stream()
                    .map(this::toDireccionResponse)
                    .collect(Collectors.toList());
            response.setDirecciones(direccionesResponse);
        }

        return response;
    }

    public Person toPersonEntity(PersonRequest p) {
        var entity = new Person();
        entity.setNombre(p.getNombre());
        entity.setApellido(p.getApellido());
        entity.setPais(p.getPais());
        entity.setCorreo(p.getCorreo());
        entity.setFechaNacimiento(p.getFechaNacimiento());


        if (p.getMascota() != null) {
            entity.setMascota(toMascotaEntity(p.getMascota()));
        }

        if (p.getDocumento() != null) {
            entity.setDocumento(toDocumentoEntity(p.getDocumento()));
        }

        if (p.getDirecciones() != null && !p.getDirecciones().isEmpty()) {
            for (DireccionRequest direccionRequest : p.getDirecciones()) {
                Direccion direccion = toDireccionEntity(direccionRequest);
                entity.addDireccion(direccion);
            }
        }

        return entity;
    }

    public Mascota toMascotaEntity(MascotaRequest p) {
        var entity = new Mascota();
        entity.setEdad(p.getEdad());
        entity.setNombre(p.getNombre());
        return entity;
    }

    public Documento toDocumentoEntity(DocumentoRequest d) {
        var entity = new Documento();
        entity.setTipoDocumento(d.getTipoDocumento());
        entity.setNumeroDocumento(d.getNumeroDocumento());
        entity.setFechaExpedicion(d.getFechaExpedicion());
        entity.setFechaVencimiento(d.getFechaVencimiento());
        return entity;
    }

    public DocumentoResponse toDocumentoResponse(Documento d) {
        var response = new DocumentoResponse();
        response.setTipoDocumento(d.getTipoDocumento());
        response.setNumeroDocumento(d.getNumeroDocumento());
        return response;
    }

    public Direccion toDireccionEntity(DireccionRequest d) {
        var entity = new Direccion();
        entity.setCalle(d.getCalle());
        entity.setNumero(d.getNumero());
        entity.setCiudad(d.getCiudad());
        entity.setProvincia(d.getProvincia());
        entity.setCodigoPostal(d.getCodigoPostal());
        entity.setTipoDireccion(d.getTipoDireccion());
        return entity;
    }

    public DireccionResponse toDireccionResponse(Direccion d) {
        var response = new DireccionResponse();
        response.setCalle(d.getCalle());
        response.setNumero(d.getNumero());
        response.setCiudad(d.getCiudad());
        response.setTipoDireccion(d.getTipoDireccion());
        return response;
    }
}