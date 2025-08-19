package datatypes;

import clases.RutaDeVuelo;
import clases.RutaEnPaquete;


import java.util.List;

public class DtPaquete {
    private String nombre;
    private String descripcion;
    private int validezDias;
    private float descuento;
    private float costo;
    private List<RutaDeVuelo> rutasDeVuelo;
    private List<RutaEnPaquete> rutaEnPaquete;

    public DtPaquete(String nombre, String descripcion, int validezDias, float descuento, float costo, List<RutaDeVuelo> rutasDeVuelo, List<RutaEnPaquete> rutaEnPaquete) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.validezDias = validezDias;
        this.descuento = descuento;
        this.costo = costo;
        this.rutasDeVuelo = rutasDeVuelo;
        this.rutaEnPaquete = rutaEnPaquete;
    }

    public List<RutaEnPaquete> getRutaEnPaquete() {
        return rutaEnPaquete;
    }

    public void setRutaEnPaquete(List<RutaEnPaquete> rutaEnPaquete) {
        this.rutaEnPaquete = rutaEnPaquete;
    }

    public List<RutaDeVuelo> getRutasDeVuelo() {
        return rutasDeVuelo;
    }

    public void setRutasDeVuelo(List<RutaDeVuelo> rutasDeVuelo) {
        this.rutasDeVuelo = rutasDeVuelo;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public int getValidezDias() {
        return validezDias;
    }

    public void setValidezDias(int validezDias) {
        this.validezDias = validezDias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
