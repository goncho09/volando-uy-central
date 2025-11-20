package com.app.datatypes;

import com.app.enums.TipoAsiento;
import com.app.enums.MetodoPago;

import java.util.ArrayList;
import java.util.List;

public class DtReserva {
    private String fecha;
    private TipoAsiento tipoAsiento;
    private int cantPasajes;
    private int equipajeExtra;
    private float costo;
    private List<DtPasajero> pasajeros;
    private DtCliente cliente;
    private DtVuelo vuelo;
    private MetodoPago metodoPago;
    private DtPaquete paquetePago;
    private boolean checkin;

    public DtReserva() {}

    public DtReserva(String fecha, TipoAsiento tipoAsiento, int cantPasajes, int equipajeExtra, float costo, List<DtPasajero> pasajeros, DtCliente cliente, DtVuelo vuelo, MetodoPago metodoPago, boolean checkin) {
        this.fecha = fecha;
        this.tipoAsiento = tipoAsiento;
        this.cantPasajes = cantPasajes;
        this.equipajeExtra = equipajeExtra;
        this.costo = costo;
        this.pasajeros = pasajeros;
        this.cliente = cliente;
        this.vuelo = vuelo;
        this.metodoPago = metodoPago;
        this.checkin = checkin;
    }

    public DtReserva(String fecha, TipoAsiento tipoAsiento, int cantPasajes, int equipajeExtra, float costo, List<DtPasajero> pasajeros, DtCliente cliente, DtVuelo vuelo, MetodoPago metodoPago, DtPaquete paquetePago, boolean checkin) {
        this.fecha = fecha;
        this.tipoAsiento = tipoAsiento;
        this.cantPasajes = cantPasajes;
        this.equipajeExtra = equipajeExtra;
        this.costo = costo;
        this.pasajeros = pasajeros;
        this.cliente = cliente;
        this.vuelo = vuelo;
        this.metodoPago = metodoPago;
        this.paquetePago = paquetePago;
        this.checkin = checkin;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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

    public void setPasajeros(List<DtPasajero> pasajeros) {this.pasajeros = pasajeros;}

    public DtCliente getCliente() {
        return cliente;
    }

    public void setCliente(DtCliente cliente) {
        this.cliente = cliente;
    }

    public DtVuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(DtVuelo vuelo) {
        this.vuelo = vuelo;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public DtPaquete getPaquetePago() {
        return paquetePago;
    }

    public void setPaquetePago(DtPaquete paquetePago) {
        this.paquetePago = paquetePago;
    }

    public void setCheckin(boolean checkin) {
        this.checkin = checkin;
    }

    public boolean getCheckin() {
        return checkin;
    }

    @Override
    public String toString() {
        return this.getCliente().getNickname() + " - " + this.getVuelo().getNombre();
    }

}
