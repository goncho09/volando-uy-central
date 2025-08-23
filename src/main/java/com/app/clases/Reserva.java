package com.app.clases;

import com.app.datatypes.DtFecha;
import com.app.datatypes.DtPasajero;
import com.app.datatypes.DtReserva;
import com.app.enums.TipoAsiento;

import java.util.List;

public class Reserva {
    private DtFecha fecha;
    private TipoAsiento tipoAsiento;
    private int cantPasajes;
    private int equipajeExtra;
    private float costo;
    private List<DtPasajero> pasajeros;
    private Cliente cliente;
    private Vuelo vuelo;
    private Paquete paquete;

    public Reserva(DtReserva reserva) {
        this.fecha = reserva.getFecha();
        this.tipoAsiento = reserva.getTipoAsiento();
        this.cantPasajes = reserva.getCantPasajes();
        this.equipajeExtra = reserva.getEquipajeExtra();
        this.costo = reserva.getCosto();
        this.pasajeros = reserva.getPasajeros();
        this.cliente = reserva.getCliente();
        this.vuelo = reserva.getVuelo();
        this.paquete = reserva.getPaquete();
    }

    public DtFecha getFecha() {
        return fecha;
    }

    public void setFecha(DtFecha fecha) {
        this.fecha = fecha;
    }

    public TipoAsiento getTipoAsiento() {
        return tipoAsiento;
    }

    public void setTipoAsiento(TipoAsiento tipoAsiento) {
        this.tipoAsiento = tipoAsiento;
    }

    public int getCantPasajes() {
        return cantPasajes;
    }

    public void setCantPasajes(int cantPasajes) {
        this.cantPasajes = cantPasajes;
    }

    public int getEquipajeExtra() {
        return equipajeExtra;
    }

    public void setEquipajeExtra(int equipajeExtra) {
        this.equipajeExtra = equipajeExtra;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public List<DtPasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(List<DtPasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
}
