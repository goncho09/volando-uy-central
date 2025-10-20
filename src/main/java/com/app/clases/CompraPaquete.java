package com.app.clases;

import com.app.datatypes.DtCompraPaquete;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
// @Table(name="compraPaquete")
public class CompraPaquete {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private LocalDate fechaCompra;
    private  LocalDate fechaVencimiento;
    private float costo;

    @ManyToOne
    @JoinColumn(name = "paquete_name", nullable = false)
    private Paquete paquete;

    @ManyToOne(optional = false) // Indica que sí o sí debe existir un cliente asociado
    @JoinColumn(name="cliente_nickName")
    private Cliente cliente;

    public CompraPaquete() {}

    public CompraPaquete(DtCompraPaquete dto) {
        this.fechaCompra = dto.getFechaCompra();
        this.fechaVencimiento = dto.getFechaVencimiento();
        this.costo = 0;
        this.paquete = dto.getPaquete();
        this.cliente = dto.getCliente();
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public Cliente getCliente() {return cliente;}

    public void setCliente(Cliente cliente) {this.cliente = cliente;}
}
