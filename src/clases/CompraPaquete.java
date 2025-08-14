package clases;

import datatypes.DtFecha;

public class CompraPaquete {
    private DtFecha fechaCompra;
    private  DtFecha fechaVencimiento;
    private float costo;
    private Paquete paquete;
    private Cliente cliente;

    public CompraPaquete(DtFecha fechaCompra, DtFecha fechaVencimiento, float costo, Paquete paquete, Cliente cliente) {
        this.fechaCompra = fechaCompra;
        this.fechaVencimiento = fechaVencimiento;
        this.costo = costo;
        this.paquete = paquete;
        this.cliente = cliente;
    }

    public DtFecha getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(DtFecha fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public DtFecha getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(DtFecha fechaVencimiento) {
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
