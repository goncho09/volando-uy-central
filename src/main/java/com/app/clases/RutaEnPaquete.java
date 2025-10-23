package com.app.clases;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.app.datatypes.DtRutaEnPaquete;

import com.app.enums.TipoAsiento;


@Entity
//@Table(name = "rutaEnPaquete")
public class RutaEnPaquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int cantidad;
    private TipoAsiento tipoAsiento;

    @ManyToOne
    @JoinColumn(name="rutaDeVuelo_name", nullable = false)
    private RutaDeVuelo rutaDeVuelo;

    public RutaEnPaquete(){}
    public RutaEnPaquete(int cantidad, TipoAsiento tipoAsiento, RutaDeVuelo rutaDeVuelo) {
        this.setCantidad(cantidad);
        this.setTipoAsiento(tipoAsiento);
        this.setRutaDeVuelo(rutaDeVuelo);
    }

    public DtRutaEnPaquete getDatos(){
        return new DtRutaEnPaquete(
                this.getCantidad(),
                this.getTipoAsiento(),
                this.getRutaDeVuelo().getDatos()
        );
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public TipoAsiento getTipoAsiento() {
        return tipoAsiento;
    }

    public void setTipoAsiento(TipoAsiento tipoAsiento) {
        this.tipoAsiento = tipoAsiento;
    }

    public RutaDeVuelo getRutaDeVuelo() {return rutaDeVuelo; }

    public void setRutaDeVuelo(RutaDeVuelo rutaDeVuelo) {
        this.rutaDeVuelo = rutaDeVuelo;
    }

    @Override
    public String toString() {return this.rutaDeVuelo.getNombre(); }
}
