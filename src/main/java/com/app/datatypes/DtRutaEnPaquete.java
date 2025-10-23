package com.app.datatypes;

import com.app.enums.TipoAsiento;

public class DtRutaEnPaquete {

    private int cantidad;
    private TipoAsiento tipoAsiento;
    private DtRuta rutaDeVuelo;

    public DtRutaEnPaquete(int cantidad, TipoAsiento tipoAsiento, DtRuta rutaDeVuelo) {
        this.cantidad = cantidad;
        this.tipoAsiento = tipoAsiento;
        this.rutaDeVuelo = rutaDeVuelo;
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

    public DtRuta getRutaDeVuelo() {
        return rutaDeVuelo;
    }

    public void setRutaDeVuelo(DtRuta rutaDeVuelo) {
        this.rutaDeVuelo = rutaDeVuelo;
    }

    @Override
    public String toString() {return this.rutaDeVuelo.getNombre(); }
}
