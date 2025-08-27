package com.app.clases;

import com.app.datatypes.DtFecha;
import com.app.datatypes.DtPasajero;
import java.util.Set;

public class Pasaje {
    private String nicknameCliente;
    private Vuelo vuelo;
    private boolean esEjecutivo;  // true = ejecutivo, false = turista
    private int cantidadPasajes;
    private int equipajeExtra;
    private Set<DtPasajero> pasajeros;
    private DtFecha fechaReserva;
    private float costoTotal;

    public Pasaje(String nicknameCliente, Vuelo vuelo, boolean esEjecutivo,
                  int cantidadPasajes, int equipajeExtra, Set<DtPasajero> pasajeros,
                  DtFecha fechaReserva) {
        this.nicknameCliente = nicknameCliente;
        this.vuelo = vuelo;
        this.esEjecutivo = esEjecutivo;
        this.cantidadPasajes = cantidadPasajes;
        this.equipajeExtra = equipajeExtra;
        this.pasajeros = pasajeros;
        this.fechaReserva = fechaReserva;
    }

    public boolean isEjecutivo() {
        return esEjecutivo;
    }


    public String getNicknameCliente() {
        return nicknameCliente;
    }

    public void setNicknameCliente(String nicknameCliente) {
        this.nicknameCliente = nicknameCliente;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public int getCantidadPasajes() {
        return cantidadPasajes;
    }

    public int getEquipajeExtra() {
        return equipajeExtra;
    }

    public Set<DtPasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(Set<DtPasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public DtFecha getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(DtFecha fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

}