package com.app.clases;

import com.app.datatypes.DtVuelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Vuelo {
    private String nombre;
    private LocalDate fecha;
    private LocalTime duracion;
    private int maxTuristas;
    private int maxEjecutivos;
    private LocalDate fechaAlta;
    private List<RutaDeVuelo> rutasDeVuelo;

    public DtVuelo getDatos() {return null;}

    public Vuelo(DtVuelo vuelo) {
        this.nombre = vuelo.getNombre();
        this.fecha = vuelo.getFecha();
        this.duracion = vuelo.getDuracion();
        this.maxTuristas = vuelo.getMaxTuristas();
        this.maxEjecutivos = vuelo.getMaxEjecutivos();
        this.fechaAlta = vuelo.getFechaAlta();
        this.rutasDeVuelo = getRutasDeVuelo();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public int getMaxTuristas() {
        return maxTuristas;
    }

    public void setMaxTuristas(int maxTuristas) {
        this.maxTuristas = maxTuristas;
    }

    public int getMaxEjecutivos() {
        return maxEjecutivos;
    }

    public void setMaxEjecutivos(int maxEjecutivos) {
        this.maxEjecutivos = maxEjecutivos;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public List<RutaDeVuelo> getRutasDeVuelo() {
        return rutasDeVuelo;
    }

    public void setRutasDeVuelo(List<RutaDeVuelo> rutasDeVuelo) {
        this.rutasDeVuelo = rutasDeVuelo;
    }
}
