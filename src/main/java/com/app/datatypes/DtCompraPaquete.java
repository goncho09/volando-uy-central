package com.app.datatypes;

import java.time.LocalDate;

public class DtCompraPaquete {
    private long id;
    private String fechaCompra;
    private String fechaVencimiento;
    private float costo;
    private DtPaquete paquete;
    private DtCliente cliente;

    public DtCompraPaquete() {};
    public DtCompraPaquete(String fechaCompra, String fechaVencimiento, float costo, DtPaquete paquete, DtCliente cliente) {
        this.fechaCompra = fechaCompra;
        this.fechaVencimiento = fechaVencimiento;
        this.costo = costo;
        this.paquete = paquete;
        this.cliente = cliente;
    }
    public DtCompraPaquete(String fechaCompra, String fechaVencimiento, float costo) {
        this.fechaCompra = fechaCompra;
        this.fechaVencimiento = fechaVencimiento;
        this.costo = costo;
        this.paquete = null;
        this.cliente = null;
    }

    public String getFechaCompra(){
        return fechaCompra;
    }

    public String getFechaVencimiento(){
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
