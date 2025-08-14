package datatypes;

import clases.Categoria;
import clases.Ciudad;

import java.util.List;

public class DtRuta {
    private String nombre;
    private String descripcion;
    private DtHora hora;
    private float costoTurista;
    private float costoEjecutivo;
    private float equipajeExtra;
    private DtFecha fechaAlta;
    private List<Categoria> categorias;
    private List<Ciudad> ciudades;

    public DtRuta(String nombre, String descripcion, DtHora hora, float costoTurista, float costoEjecutivo, float equipajeExtra, DtFecha fechaAlta, List<Categoria> categorias, List<Ciudad> ciudades) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.hora = hora;
        this.costoTurista = costoTurista;
        this.costoEjecutivo = costoEjecutivo;
        this.equipajeExtra = equipajeExtra;
        this.fechaAlta = fechaAlta;
        this.categorias = categorias;
        this.ciudades = ciudades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DtHora getHora() {
        return hora;
    }

    public void setHora(DtHora hora) {
        this.hora = hora;
    }

    public float getCostoTurista() {
        return costoTurista;
    }

    public void setCostoTurista(float costoTurista) {
        this.costoTurista = costoTurista;
    }

    public float getCostoEjecutivo() {
        return costoEjecutivo;
    }

    public void setCostoEjecutivo(float costoEjecutivo) {
        this.costoEjecutivo = costoEjecutivo;
    }

    public float getEquipajeExtra() {
        return equipajeExtra;
    }

    public void setEquipajeExtra(float equipajeExtra) {
        this.equipajeExtra = equipajeExtra;
    }

    public DtFecha getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(DtFecha fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }
}
