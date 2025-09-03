package com.app.clases;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CiudadId implements Serializable {
    private String nombre;
    private String pais;

    public CiudadId(){};
    public CiudadId(String nombre, String pais){
        this.nombre = nombre;
        this.pais = pais;
    }

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getPais() {return this.pais;}

    public void setPais(String pais) {this.pais = pais;}

    @Override
    public String toString() {
        return pais + ", " + nombre;
    }
}