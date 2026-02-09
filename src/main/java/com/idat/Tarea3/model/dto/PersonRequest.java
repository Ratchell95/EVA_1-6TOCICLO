package com.idat.Tarea3.model.dto;

import java.util.List;

public class PersonRequest {
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String correo;
    private String pais;
    private MascotaRequest mascota;
    private DocumentoRequest documento;
    private List<DireccionRequest> direcciones;

    public PersonRequest() {
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public MascotaRequest getMascota() {
        return mascota;
    }

    public void setMascota(MascotaRequest mascota) {
        this.mascota = mascota;
    }

    public DocumentoRequest getDocumento() {
        return documento;
    }

    public void setDocumento(DocumentoRequest documento) {
        this.documento = documento;
    }

    public List<DireccionRequest> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionRequest> direcciones) {
        this.direcciones = direcciones;
    }
}