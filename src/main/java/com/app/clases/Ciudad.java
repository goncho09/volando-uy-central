package com.app.clases;

import com.app.datatypes.DtCiudad;

import java.time.LocalDate;

public class Ciudad {
    private String nombre;
    private String pais;
    private String aeropuerto;
    private String descripcion;
    private String sitioWeb;
    private LocalDate fechaAlta;

    public Ciudad(DtCiudad ciudad) {
        this.nombre = ciudad.getNombre();
        this.pais = ciudad.getPais();
        this.aeropuerto = ciudad.getAeropuerto();
        this.descripcion = ciudad.getDescripcion();
        this.sitioWeb = ciudad.getSitioWeb();
        this.fechaAlta = ciudad.getFechaAlta();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String ciudad) {
        this.pais = pais;
    }

    public String getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(String aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
