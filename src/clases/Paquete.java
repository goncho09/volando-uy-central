package clases;

import datatypes.DtPaquete;

import java.util.List;

public class Paquete {
    private String nombre;
    private String descripcion;
    private int validezDias;
    private float descuento;
    private float costo;
    private List<RutaDeVuelo> rutasDeVuelo;
    private List<RutaEnPaquete> rutaEnPaquete;

    public Paquete(DtPaquete paquete) {
        this.nombre = paquete.getNombre();
        this.descripcion = paquete.getDescripcion();
        this.validezDias = paquete.getValidezDias();
        this.descuento = paquete.getDescuento();
        this.costo = paquete.getCosto();
        this.rutasDeVuelo = paquete.getRutasDeVuelo();
        this.rutaEnPaquete = paquete.getRutaEnPaquete();
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
