package com.app.datatypes;

import com.app.clases.Usuario;
import com.app.enums.TipoDocumento;

import java.util.ArrayList;
import java.util.List;

public class DtCliente extends DtUsuario{
    private String apellido;
    private String fechaNacimiento;
    private String nacionalidad;
    private TipoDocumento tipoDocumento;
    private int numeroDocumento;
    private List<DtCompraPaquete> comprasPaquetes;
    private List<DtReserva> reservas;

    public DtCliente() {}

    // alta cliente
    public DtCliente(String nickname, String nombre, String email, String password, String urlImage, String apellido, String fechaNacimiento, String nacionalidad, TipoDocumento tipoDocumento, int numeroDocumento) {
        super(nickname, nombre, email, password, urlImage, new ArrayList<>(), new ArrayList<>());
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.comprasPaquetes = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    // modificar cliente
    public DtCliente(String nickname, String nombre, String email, String urlImage, String apellido, String fechaNacimiento, String nacionalidad, TipoDocumento tipoDocumento, int numeroDocumento) {
        super(nickname, nombre, email,null, urlImage, new ArrayList<>(), new ArrayList<>());
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.comprasPaquetes = this.getComprasPaquetes();
        this.reservas = this.getReservas();
    }

    // get datos sin contraseña
    public DtCliente(String nickname, String nombre, String email, String urlImage, List <DtUsuario> seguidores, List <DtUsuario> seguidos, String apellido, String fechaNacimiento, String nacionalidad, TipoDocumento tipoDocumento, int numeroDocumento, List<DtCompraPaquete> comprasPaquetes, List<DtReserva> reservas) {
        super(nickname, nombre, email, null, urlImage, seguidores, seguidos);
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.comprasPaquetes = comprasPaquetes;
        this.reservas = reservas;
    }

    public String getApellido() {return apellido; }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
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

    public List<DtCompraPaquete> getComprasPaquetes() {
        return comprasPaquetes;
    }

    public void setComprasPaquetes(List<DtCompraPaquete> comprasPaquetes) {
        this.comprasPaquetes = comprasPaquetes;
    }

    public List<DtReserva> getReservas() {return reservas;}

    public void setReservas(List<DtReserva> reservas) {this.reservas = reservas;}
}
