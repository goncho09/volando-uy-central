package com.app.clases;

import com.app.datatypes.DtCategoria;

public class Categoria {
    private String nombre;

    public Categoria(DtCategoria categoria) {
        this.nombre = categoria.getNombre();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
