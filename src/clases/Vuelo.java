package clases;

import datatypes.DtFecha;
import datatypes.DtHora;
import datatypes.DtVuelo;

import java.util.List;

public class Vuelo {
    private String nombre;
    private DtFecha fecha;
    private DtHora duracion;
    private int maxTuristas;
    private int maxEjecutivos;
    private DtFecha fechaAlta;
    private List<RutaDeVuelo> rutasDeVuelo;

    public DtVuelo getDatos() {return null;}

    public Vuelo(DtVuelo vuelo) {
        this.nombre = vuelo.getNombre();
        this.fecha = vuelo.getFecha();
        this.duracion = vuelo.getDuracion();
        this.maxTuristas = vuelo.getMaxTuristas();
        this.maxEjecutivos = vuelo.getMaxEjecutivos();
        this.fechaAlta = vuelo.getFechaAlta();
        this.rutasDeVuelo = getRutasDeVuelo();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DtFecha getFecha() {
        return fecha;
    }

    public void setFecha(DtFecha fecha) {
        this.fecha = fecha;
    }

    public DtHora getDuracion() {
        return duracion;
    }

    public void setDuracion(DtHora duracion) {
        this.duracion = duracion;
    }

    public int getMaxTuristas() {
        return maxTuristas;
    }

    public void setMaxTuristas(int maxTuristas) {
        this.maxTuristas = maxTuristas;
    }

    public int getMaxEjecutivos() {
        return maxEjecutivos;
    }

    public void setMaxEjecutivos(int maxEjecutivos) {
        this.maxEjecutivos = maxEjecutivos;
    }

    public DtFecha getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(DtFecha fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public List<RutaDeVuelo> getRutasDeVuelo() {
        return rutasDeVuelo;
    }

    public void setRutasDeVuelo(List<RutaDeVuelo> rutasDeVuelo) {
        this.rutasDeVuelo = rutasDeVuelo;
    }
}
