package com.app.datatypes;

import com.app.clases.RutaDeVuelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class DtVuelo {
    private String nombre;
    private LocalDate fecha;
    private LocalTime duracion;
    private int maxTuristas;
    private int maxEjecutivos;
    private LocalDate fechaAlta;
    private RutaDeVuelo rutaDeVuelo;

    public DtVuelo(String nombre, LocalDate fecha, LocalTime duracion, int maxTuristas, int maxEjecutivos, LocalDate fechaAlta,RutaDeVuelo rutaDeVuelo) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.duracion = duracion;
        this.maxTuristas = maxTuristas;
        this.maxEjecutivos = maxEjecutivos;
        this.fechaAlta = fechaAlta;
        this.rutaDeVuelo = rutaDeVuelo;
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

    public RutaDeVuelo getRutaDeVuelo() {
        return rutaDeVuelo;
    }

    public void setRutaDeVuelo(RutaDeVuelo rutaDeVuelo) {
        this.rutaDeVuelo = rutaDeVuelo;
    }
}
