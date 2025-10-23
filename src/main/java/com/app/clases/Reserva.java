package com.app.clases;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


import com.app.datatypes.DtPasajero;
import com.app.datatypes.DtReserva;
import com.app.datatypes.DtPaquete;

import com.app.enums.TipoAsiento;
import com.app.enums.MetodoPago;

@Entity
//@Table(name="reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate fecha;
    private TipoAsiento tipoAsiento;
    private int cantPasajes;
    private int equipajeExtra;
    private float costo;
    private MetodoPago metodoPago;

    @OneToMany // Una reserva "puede" tener 1 o más pasajeros (1-N)
    @JoinColumn(name="reserva_id", nullable = false) // nullable = false indica que debe existir obligatoriamente 1 pasaje.
    private List<DtPasajero> pasajeros = new ArrayList<>();

    @ManyToOne(optional = false) // Muchas reserva es contenida por un cliente, el optional indica que debe existir un cliente sí o sí
    @JoinColumn(name="cliente_nickName")
    private Cliente cliente;

    @ManyToOne(optional = true) // Muchas reservas pueden tener 1 único vuelo asociado (N-1)
    @JoinColumn(name = "paquete_id")
    private Paquete paquetePago;

    // Relación opcional con Vuelo
    @ManyToOne(optional = false) // (N-1)
    @JoinColumn(name = "vuelo_id")
    private Vuelo vuelo;

    public Reserva() {}
    public Reserva(DtReserva reserva, Cliente cliente, Vuelo vuelo) {
        this.setFecha(reserva.getFecha());
        this.setTipoAsiento(reserva.getTipoAsiento());
        this.setCantPasajes(reserva.getCantPasajes());
        this.setEquipajeExtra(reserva.getEquipajeExtra());
        this.setCosto(reserva.getCosto());
        this.setPasajeros(reserva.getPasajeros());
        this.setCliente(cliente);
        this.setVuelo(vuelo);
        this.setMetodoPago(reserva.getMetodoPago());
        this.setPaquetePago(null);
    }

    public Reserva(DtReserva reserva, Cliente cliente, Paquete paquete) {
        this.setFecha(reserva.getFecha());
        this.setTipoAsiento(reserva.getTipoAsiento());
        this.setCantPasajes(reserva.getCantPasajes());
        this.setEquipajeExtra(reserva.getEquipajeExtra());
        this.setCosto(reserva.getCosto());
        this.setPasajeros(reserva.getPasajeros());
        this.setCliente(cliente);
        this.setVuelo(null);
        this.setMetodoPago(reserva.getMetodoPago());
        this.setPaquetePago(paquete);
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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

    public Paquete getPaquetePago() {
        return paquetePago;
    }

    public void setPaquetePago(Paquete paquete) {
        this.paquetePago = paquete;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public DtReserva getDatos() {
        Paquete paquete = this.getPaquetePago();
        DtPaquete paqueteData;

        if (paquete == null){
            paqueteData = null;
        } else {
            paqueteData = paquete.getDatos();
        }

        return new DtReserva(
                this.getFecha(),
                this.getTipoAsiento(),
                this.getCantPasajes(),
                this.getEquipajeExtra(),
                this.getCosto(),
                this.getPasajeros(),
                this.getCliente().getDatos(),
                this.getVuelo().getDatos(),
                this.getMetodoPago(),
                paqueteData
        );
    }
}
