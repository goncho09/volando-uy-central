package com.app.clases;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

import com.app.datatypes.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import com.app.enums.TipoDocumento;

@Entity
@DiscriminatorValue("Cliente") // Cuando se haga la tabla de usuarios, éste en particular será "userType" Cliente
public class Cliente extends Usuario{
    private String apellido;
    private LocalDate fechaNacimiento;
    private String nacionalidad;
    private TipoDocumento tipoDocumento;
    private int numeroDocumento;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)// 1 - N (un cliente tiene muchas "compras"
    private List<CompraPaquete> comprasPaquetes = new ArrayList<>();

    @OneToMany(mappedBy = "cliente") // 1 - N (un cliente tiene muchas "reservas"
    private List<Reserva> reservas = new ArrayList<>();

    public Cliente(){}
    public Cliente(DtCliente cliente) {
        super(new DtUsuario(cliente.getNickname(), cliente.getNombre(), cliente.getEmail(), cliente.getPassword(), cliente.getUrlImage()));
        this.setApellido(cliente.getApellido());
        this.setFechaNacimiento(cliente.getFechaNacimiento());
        this.setNacionalidad(cliente.getNacionalidad());
        this.setTipoDocumento(cliente.getTipoDocumento());
        this.setNumeroDocumento(cliente.getNumeroDocumento());
        this.setComprasPaquetes(new ArrayList<>());
        this.setReservas(new ArrayList<>());
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
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

    public List<CompraPaquete> getComprasPaquetes() {return comprasPaquetes;}

    public void setComprasPaquetes(List<CompraPaquete> comprasPaquetes) {
        this.comprasPaquetes = comprasPaquetes;
    }

    public List<Reserva> getReservas(){
        return this.reservas;
    }

    public void setReservas(List<Reserva> reservas){
        this.reservas = reservas;
    }

    public DtCliente getDatos(){
        List<DtCompraPaquete> paquetesList = new ArrayList<>();
        for (CompraPaquete cp : this.getComprasPaquetes()){
            paquetesList.add(cp.getDatos());
        }

        List<DtReserva> reservasList = new ArrayList<>();
        for (Reserva r : this.getReservas()){
            reservasList.add(r.getDatos());
        }

        return new DtCliente(
                this.getNickname(),
                this.getNombre(),
                this.getEmail(),
                this.getPassword(),
                this.getUrlImage(),
                this.getApellido(),
                this.getFechaNacimiento(),
                this.getNacionalidad(),
                this.getTipoDocumento(),
                this.getNumeroDocumento(),
                paquetesList,
                reservasList
        );
    }

    public boolean existeVueloReserva(Vuelo vuelo){
        for (Reserva r : this.getReservas()) {
            if (r.getVuelo().getNombre().equals(vuelo.getNombre())){
                return true;
            }
        }
        return false;
    }

}

