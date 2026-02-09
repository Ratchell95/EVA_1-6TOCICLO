package com.idat.Tarea3.model.dto;

public class MascotaRequest {
    private String nombre;
    private int edad;

    public MascotaRequest() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}