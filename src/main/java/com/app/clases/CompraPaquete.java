package com.app.clases;

import java.time.LocalDate;
import java.util.ArrayList;

import com.app.datatypes.DtCliente;
import com.app.datatypes.DtUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import com.app.datatypes.DtCompraPaquete;


@Entity
// @Table(name="compraPaquete")
public class CompraPaquete {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
    public CompraPaquete(DtCompraPaquete dto, Paquete paquete, Cliente cliente) {
        this.setFechaCompra(LocalDate.parse(dto.getFechaCompra()));
        this.setFechaVencimiento(LocalDate.parse(dto.getFechaVencimiento()));
        this.setCosto(dto.getCosto());
        this.setPaquete(paquete);
        this.setCliente(cliente);
    }

    public DtCompraPaquete getDatos(){
        DtCliente cliente = new DtCliente(
                this.cliente.getNickname(),
                this.cliente.getNombre(),
                this.cliente.getEmail(),
                this.cliente.getUrlImage(),
                this.cliente.getSeguidores(),
                this.cliente.getSeguidos(),
                this.cliente.getApellido(),
                this.cliente.getFechaNacimiento().toString(),
                this.cliente.getNacionalidad(),
                this.cliente.getTipoDocumento(),
                this.cliente.getNumeroDocumento(),
                null,
                null
        );
        return new DtCompraPaquete(
                this.getFechaCompra().toString(),
                this.getFechaVencimiento().toString(),
                this.getCosto(),
                this.getPaquete().getDatos(),
                cliente
        );
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

    public Cliente getCliente() {return cliente; }

    public void setCliente(Cliente cliente) {this.cliente = cliente; }
}
