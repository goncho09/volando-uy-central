package com.app.datatypes;

import com.app.clases.Categoria;
import com.app.clases.Ciudad;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DtRuta {
    private String nombre;
    private String descripcion;
    private LocalTime hora;
    private float costoTurista;
    private float costoEjecutivo;
    private float equipajeExtra;
    private LocalDate fechaAlta;
    private List<Categoria> categorias;
    private Ciudad ciudadOrigen;
    private Ciudad ciudadDestino;

    public DtRuta(String nombre, String descripcion, LocalTime hora, float costoTurista, float costoEjecutivo, float equipajeExtra, LocalDate fechaAlta, List<Categoria> categorias, Ciudad origen,Ciudad destino) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.hora = hora;
        this.costoTurista = costoTurista;
        this.costoEjecutivo = costoEjecutivo;
        this.equipajeExtra = equipajeExtra;
        this.fechaAlta = fechaAlta;
        this.categorias = categorias;
        this.ciudadOrigen = origen;
        this.ciudadDestino = destino;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public float getCostoTurista() {
        return costoTurista;
    }

    public void setCostoTurista(float costoTurista) {
        this.costoTurista = costoTurista;
    }

    public float getCostoEjecutivo() {
        return costoEjecutivo;
    }

    public void setCostoEjecutivo(float costoEjecutivo) {
        this.costoEjecutivo = costoEjecutivo;
    }

    public float getEquipajeExtra() {
        return equipajeExtra;
    }

    public void setEquipajeExtra(float equipajeExtra) {
        this.equipajeExtra = equipajeExtra;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Ciudad getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(Ciudad ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public Ciudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(Ciudad ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }
}
