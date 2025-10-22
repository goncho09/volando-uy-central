package com.app.datatypes;

import com.app.clases.RutaDeVuelo;
import com.app.clases.RutaEnPaquete;


import java.util.ArrayList;
import java.util.List;

public class DtPaquete {
    private String nombre;
    private String descripcion;
    private int validezDias;
    private float descuento;
    private float costo;
    private List<DtRutaEnPaquete> rutaEnPaquete;

    public  DtPaquete(){};

    public DtPaquete(String nombre, String descripcion, int validezDias, float descuento, float costo, List<DtRutaEnPaquete> rutaEnPaquete) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.validezDias = validezDias;
        this.descuento = descuento;
        this.costo = costo;
        this.rutaEnPaquete = rutaEnPaquete;
    }

    public DtPaquete(String nombre, String descripcion, int validezDias, float descuento) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.validezDias = validezDias;
        this.descuento = descuento;
        this.costo = 0;
        this.rutaEnPaquete = new ArrayList<>();
    }

    public List<DtRutaEnPaquete> getRutaEnPaquete() {
        return rutaEnPaquete;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public int getValidezDias() {
        return validezDias;
    }

    public void setValidezDias(int validezDias) {
        this.validezDias = validezDias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() { return this.nombre; }
}
