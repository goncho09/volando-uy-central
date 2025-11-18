package com.app.datatypes;

public class DtCategoria {
    private String nombre;

    public DtCategoria() {}

    public DtCategoria(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
