package com.app.datatypes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.app.enums.EstadoRuta;

public class DtRuta {
    private String nombre;
    private String descripcion;
    private String descripcionCorta;
    private LocalTime duracion;
    private float costoTurista;
    private float costoEjecutivo;
    private float equipajeExtra;
    private LocalDate fechaAlta;
    private String urlImagen;
    private EstadoRuta estado;
    private List<DtCategoria> categorias;
    private DtCiudad ciudadOrigen;
    private DtCiudad ciudadDestino;

    public DtRuta() {}
    public DtRuta(String nombre, String descripcion, String descripcionCorta, LocalTime duracion, float costoTurista, float costoEjecutivo, float equipajeExtra, LocalDate fechaAlta, String urlImagen, List<DtCategoria> categorias, DtCiudad origen, DtCiudad destino) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descripcionCorta = descripcionCorta;
        this.duracion = duracion;
        this.costoTurista = costoTurista;
        this.costoEjecutivo = costoEjecutivo;
        this.equipajeExtra = equipajeExtra;
        this.fechaAlta = fechaAlta;
        this.urlImagen = urlImagen;
        this.estado = EstadoRuta.INGRESADA; // Por defecto se creará la ruta con "Ingresada"
        this.categorias = categorias;
        this.ciudadOrigen = origen;
        this.ciudadDestino = destino;
    }

    public DtRuta(String nombre, String descripcion, String descripcionCorta, LocalTime duracion, float costoTurista, float costoEjecutivo, float equipajeExtra, LocalDate fechaAlta, String urlImagen, EstadoRuta estado, List<DtCategoria> categorias, DtCiudad origen, DtCiudad destino) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.descripcionCorta = descripcionCorta;
        this.costoTurista = costoTurista;
        this.costoEjecutivo = costoEjecutivo;
        this.equipajeExtra = equipajeExtra;
        this.fechaAlta = fechaAlta;
        this.urlImagen = urlImagen;
        this.estado = estado;
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

    public String getDescripcionCorta(){ return descripcionCorta; }

    public void setDescripcionCorta(String descripcionCorta) { this.descripcionCorta = descripcionCorta; }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
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

    public String getUrlImagen() {return urlImagen; }

    public void setUrlImagen(String urlImagen) {this.urlImagen = urlImagen; }

    public EstadoRuta getEstado() {return estado; }

    public void setEstado(EstadoRuta estado) {this.estado = estado; }

    public List<DtCategoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<DtCategoria> categorias) {
        this.categorias = categorias;
    }

    public DtCiudad getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(DtCiudad ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public DtCiudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(DtCiudad ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    @Override
    public String toString() { return this.nombre; }
}
