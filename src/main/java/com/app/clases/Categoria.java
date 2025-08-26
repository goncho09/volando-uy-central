package com.app.clases;

import com.app.datatypes.DtCategoria;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="categoria")

public class Categoria {
    @Id
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
