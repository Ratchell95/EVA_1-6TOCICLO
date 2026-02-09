package com.idat.Tarea3.model.dto;

import java.util.List;

public class PersonResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String pais;
    private DocumentoResponse documento;
    private List<DireccionResponse> direcciones;

    public PersonResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public DocumentoResponse getDocumento() {
        return documento;
    }

    public void setDocumento(DocumentoResponse documento) {
        this.documento = documento;
    }

    public List<DireccionResponse> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionResponse> direcciones) {
        this.direcciones = direcciones;
    }
}