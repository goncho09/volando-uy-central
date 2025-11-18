package com.app.datatypes;


public class DtVuelo {
    private String nombre;
    private String fecha;
    private String duracion;
    private int maxTuristas;
    private int maxEjecutivos;
    private String urlImage;
    private String fechaAlta;
    private DtRuta rutaDeVuelo;
    private int cantReservas;

    public DtVuelo(){};
    public DtVuelo(String nombre, String fecha, String duracion, int maxTuristas, int maxEjecutivos, String urlImage, String fechaAlta, DtRuta rutaDeVuelo, int cantReservas) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.duracion = duracion;
        this.maxTuristas = maxTuristas;
        this.maxEjecutivos = maxEjecutivos;
        this.urlImage = urlImage;
        this.fechaAlta = fechaAlta;
        this.rutaDeVuelo = rutaDeVuelo;
        this.cantReservas = cantReservas;
    }

    public DtVuelo getDatos() {
        return new DtVuelo(this.nombre, this.fecha, this.duracion, this.maxTuristas, this.maxEjecutivos, this.urlImage, this.fechaAlta, this.rutaDeVuelo, this.cantReservas);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
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

    public String getUrlImage() {return this.urlImage; };

    public void setUrlImage(String urlImage) {this.urlImage = urlImage; }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getCantReservas(){
        return this.cantReservas;
    }

    public void setCantReservas(int cantReservas) {this.cantReservas = cantReservas; }

    public DtRuta getRutaDeVuelo() {
        return rutaDeVuelo;
    }

    public void setRutaDeVuelo(DtRuta rutaDeVuelo) {
        this.rutaDeVuelo = rutaDeVuelo;
    }

    @Override
    public String toString() {return this.nombre; }
}
