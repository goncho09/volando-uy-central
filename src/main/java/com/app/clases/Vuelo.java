package com.app.clases;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import com.app.datatypes.DtVuelo;


@Entity
//@Table(name="vuelo")
public class Vuelo {
    @Id
    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime duracion;

    @Column(nullable = false)
    private int maxTuristas;

    @Column(nullable = false)
    private int maxEjecutivos;

    @Column(nullable = true)
    private String urlImage;

    @Column(nullable = false)
    private LocalDate fechaAlta;

    @Column(nullable = false)
    private int cantReservas;

    @ManyToOne(optional = false)
    private RutaDeVuelo rutaDeVuelo;

    public Vuelo() {}

    public Vuelo(DtVuelo vuelo, RutaDeVuelo rutaDeVuelo) {
        this.setNombre(vuelo.getNombre());
        this.setFecha(vuelo.getFecha());
        this.setDuracion(vuelo.getDuracion());
        this.setMaxTuristas(vuelo.getMaxTuristas());
        this.setMaxEjecutivos(vuelo.getMaxEjecutivos());
        this.setUrlImage(vuelo.getUrlImage());
        this.setFechaAlta(vuelo.getFechaAlta());
        this.setRutaDeVuelo(rutaDeVuelo);
        this.setCantReservas(vuelo.getCantReservas());
    }

    public DtVuelo getDatos() {
        return new DtVuelo(
                this.getNombre(),
                this.getFecha(),
                this.getDuracion(),
                this.getMaxTuristas(),
                this.getMaxEjecutivos(),
                this.getUrlImage(),
                this.getFechaAlta(),
                this.getRutaDeVuelo().getDatos(),
                this.getCantReservas()
        );
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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setCantReservas(int cantReservas){
        this.cantReservas = cantReservas;
    }

    public int getCantReservas(){
        return this.cantReservas;
    }

    public RutaDeVuelo getRutaDeVuelo() {
        return rutaDeVuelo;
    }

    public void setRutaDeVuelo(RutaDeVuelo rutaDeVuelo) {
        this.rutaDeVuelo = rutaDeVuelo;
    }
}
