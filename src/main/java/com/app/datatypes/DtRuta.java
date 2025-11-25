package com.app.datatypes;

import java.util.List;

import com.app.enums.EstadoRuta;

public class DtRuta {
    private String nombre;
    private String descripcion;
    private String descripcionCorta;
    private String duracion;
    private float costoTurista;
    private float costoEjecutivo;
    private float equipajeExtra;
    private String fechaAlta;
    private String urlImagen;
    private String urlVideo;
    private EstadoRuta estado;
    private List<DtCategoria> categorias;
    private DtCiudad ciudadOrigen;
    private DtCiudad ciudadDestino;

    public DtRuta() {}
    public DtRuta(String nombre, String descripcion, String descripcionCorta, String duracion, float costoTurista, float costoEjecutivo, float equipajeExtra, String fechaAlta, String urlImagen, String urlVideo, List<DtCategoria> categorias, DtCiudad origen, DtCiudad destino) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descripcionCorta = descripcionCorta;
        this.duracion = duracion;
        this.costoTurista = costoTurista;
        this.costoEjecutivo = costoEjecutivo;
        this.equipajeExtra = equipajeExtra;
        this.fechaAlta = fechaAlta;
        this.urlImagen = urlImagen;
        this.urlVideo = urlVideo;
        this.estado = EstadoRuta.INGRESADA; // Por defecto se creará la ruta con "Ingresada"
        this.categorias = categorias;
        this.ciudadOrigen = origen;
        this.ciudadDestino = destino;
    }

    public DtRuta(String nombre, String descripcion, String descripcionCorta, String duracion, float costoTurista, float costoEjecutivo, float equipajeExtra, String fechaAlta, String urlImagen, EstadoRuta estado, List<DtCategoria> categorias, DtCiudad origen, DtCiudad destino) {
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

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
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

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getUrlImagen() {return urlImagen; }

    public void setUrlImagen(String urlImagen) {this.urlImagen = urlImagen; }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

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

    public int getVecesVisitada() { return vecesVisitada; }

    public void setCiudadOrigen(DtCiudad ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    @Override
    public String toString() { return this.nombre; }
}
