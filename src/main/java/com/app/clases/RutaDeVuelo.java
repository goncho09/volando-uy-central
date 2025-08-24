package com.app.clases;

import com.app.datatypes.DtRuta;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name="rutaDeVuelo")
public class RutaDeVuelo {
    @Id
    @Column(nullable = false, length = 50)
    private String nombre;

    private String descripcion;
    private LocalTime hora;
    private float costoTurista;
    private float costoEjecutivo;
    private float equipajeExtra;
    private LocalDate fechaAlta;

    @OneToMany
    @JoinColumn(name="rutaDeVuelo_name", nullable = false)
    private List<Categoria> categorias;

    @OneToMany
    @JoinColumn(name="rutaDeVuelo_name", nullable = false)
    private List<Ciudad> ciudades;

    public RutaDeVuelo() {}
    public RutaDeVuelo(DtRuta ruta) {
        this.nombre = ruta.getNombre();
        this.descripcion = ruta.getDescripcion();
        this.hora = ruta.getHora();
        this.costoTurista = ruta.getCostoTurista();
        this.costoEjecutivo = ruta.getCostoEjecutivo();
        this.equipajeExtra = ruta.getEquipajeExtra();
        this.fechaAlta = ruta.getFechaAlta();
        this.categorias = ruta.getCategorias();
        this.ciudades = ruta.getCiudades();
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

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}
