package com.app.datatypes;

import java.time.LocalDate;

public class DtCompraPaquete {
    private long id;
    private LocalDate fechaCompra;
    private LocalDate fechaVencimiento;
    private float costo;
    private DtPaquete paquete;
    private DtCliente cliente;

    public DtCompraPaquete() {};
    public DtCompraPaquete(LocalDate fechaCompra, LocalDate fechaVencimiento, float costo, DtPaquete paquete, DtCliente cliente) {
        this.fechaCompra = fechaCompra;
        this.fechaVencimiento = fechaVencimiento;
        this.costo = costo;
        this.paquete = paquete;
        this.cliente = cliente;
    }
    public DtCompraPaquete(LocalDate fechaCompra, LocalDate fechaVencimiento, float costo) {
        this.fechaCompra = fechaCompra;
        this.fechaVencimiento = fechaVencimiento;
        this.costo = costo;
        this.paquete = null;
        this.cliente = null;
    }

    public LocalDate getFechaCompra(){
        return fechaCompra;
    }

    public LocalDate getFechaVencimiento(){
        return fechaVencimiento;
    }

    public float getCosto(){
        return costo;
    }

    public DtPaquete getPaquete() {
        return paquete;
    }

    public DtCliente getCliente() {
        return cliente;
    }

}
