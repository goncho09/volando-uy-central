package com.app.clases;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CiudadId implements Serializable {

    @Column(name="nombreCiudad")
    private String nombre;

    @Column(name="paisCiudad")
    private String pais;

    public CiudadId() {}

    public CiudadId(String nombre, String pais) {
        this.setNombre(nombre);
        this.setPais(pais);
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais() { return pais; }

    public void setPais(String pais) { this.pais = pais; }

}