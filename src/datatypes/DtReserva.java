package datatypes;

import clases.Cliente;
import clases.Paquete;
import clases.Vuelo;
import enums.TipoAsiento;

import java.util.List;

public class DtReserva {
    private DtFecha fecha;
    private TipoAsiento tipoAsiento;
    private int cantPasajes;
    private int equipajeExtra;
    private float costo;
    private List<DtPasajero> pasajeros;
    private Cliente cliente;
    private Vuelo vuelo;
    private Paquete paquete;

    public DtReserva(DtFecha fecha, TipoAsiento tipoAsiento, int cantPasajes, int equipajeExtra, float costo, List<DtPasajero> pasajeros, Cliente cliente, Vuelo vuelo, Paquete paquete) {
        this.fecha = fecha;
        this.tipoAsiento = tipoAsiento;
        this.cantPasajes = cantPasajes;
        this.equipajeExtra = equipajeExtra;
        this.costo = costo;
        this.pasajeros = pasajeros;
        this.cliente = cliente;
        this.vuelo = vuelo;
        this.paquete = paquete;
    }

    public DtFecha getFecha() {
        return fecha;
    }

    public void setFecha(DtFecha fecha) {
        this.fecha = fecha;
    }

    public TipoAsiento getTipoAsiento() {
        return tipoAsiento;
    }

    public void setTipoAsiento(TipoAsiento tipoAsiento) {
        this.tipoAsiento = tipoAsiento;
    }

    public int getCantPasajes() {
        return cantPasajes;
    }

    public void setCantPasajes(int cantPasajes) {
        this.cantPasajes = cantPasajes;
    }

    public int getEquipajeExtra() {
        return equipajeExtra;
    }

    public void setEquipajeExtra(int equipajeExtra) {
        this.equipajeExtra = equipajeExtra;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public List<DtPasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(List<DtPasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
}
