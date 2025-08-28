package com.app.clases;

import com.app.datatypes.DtVuelo;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name="vuelo")
public class Vuelo {
    @Id
    @Column(nullable = false, length = 50)
    private String nombre;

    private LocalDate fecha;
    private LocalTime duracion;
    private int maxTuristas;
    private int maxEjecutivos;
    private LocalDate fechaAlta;

    @ManyToOne
    @JoinColumn(name="rutaDeVuelo_name", nullable = false)
    private RutaDeVuelo rutaDeVuelo;

    public DtVuelo getDatos() {return null;}

    public Vuelo(DtVuelo vuelo, RutaDeVuelo rutaDeVuelo) {
        this.nombre = vuelo.getNombre();
        this.fecha = vuelo.getFecha();
        this.duracion = vuelo.getDuracion();
        this.maxTuristas = vuelo.getMaxTuristas();
        this.maxEjecutivos = vuelo.getMaxEjecutivos();
        this.fechaAlta = vuelo.getFechaAlta();
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

    public void setRutaDeVuelo(RutaDeVuelo rutaDeVuelo) {
        this.rutaDeVuelo = rutaDeVuelo;
    }
}
