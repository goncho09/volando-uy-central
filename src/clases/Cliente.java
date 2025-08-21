package clases;

import datatypes.*;
import enums.TipoDocumento;

import java.util.ArrayList;

import java.util.List;

public class Cliente extends Usuario{
    private String apellido;
    private DtFecha fechaNacimiento;
    private String nacionalidad;
    private TipoDocumento tipoDocumento;
    private int numeroDocumento;
    private List<CompraPaquete> comprasPaquetes;
    private List<Reserva> reservas = new ArrayList<>();


    public Cliente(DtCliente cliente) {
        super(new DtUsuario(cliente.getNickname(), cliente.getNombre(), cliente.getEmail()));
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.comprasPaquetes = comprasPaquetes;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public DtFecha getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(DtFecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public List<CompraPaquete> getComprasPaquetes() {
        return comprasPaquetes;
    }

    public void setComprasPaquetes(List<CompraPaquete> comprasPaquetes) {
        this.comprasPaquetes = comprasPaquetes;
    }

    public List<DtReserva> getReservas() {
        List<DtReserva> dtReservas = new ArrayList<>();
        for (Reserva reserva : this.reservas) {
            dtReservas.add(new DtReserva(
                    reserva.getFecha(),
                    reserva.getTipoAsiento(),
                    reserva.getCantPasajes(),
                    reserva.getEquipajeExtra(),
                    reserva.getCosto(),
                    reserva.getPasajeros(),
                    this,  // El cliente actual
                    reserva.getVuelo(),
                    reserva.getPaquete()
            ));
        }
        return dtReservas;
    }

    public List<DtPaquete> getPaquetesAdquiridos() {
        List<DtPaquete> dtPaquetes = new ArrayList<>();
        if (comprasPaquetes != null) {
            for (CompraPaquete compra : comprasPaquetes) {
                Paquete paquete = compra.getPaquete();
                dtPaquetes.add(new DtPaquete(
                        paquete.getNombre(),
                        paquete.getDescripcion(),
                        paquete.getValidezDias(),
                        paquete.getDescuento(),
                        paquete.getCosto()
                ));
            }
        }
        return dtPaquetes;
    }
}

