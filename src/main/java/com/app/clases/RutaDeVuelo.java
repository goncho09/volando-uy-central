package com.app.clases;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;

import com.app.datatypes.DtCategoria;
import com.app.datatypes.DtRuta;

import com.app.enums.EstadoRuta;


@Entity
//@Table(name="rutaDeVuelo")
public class RutaDeVuelo {
    @Id
    @Column(nullable = false, length = 50)
    private String nombre;

    @Column( nullable = false)
    private String descripcion;

    @Column( nullable = true)
    private String descripcionCorta;

    @Column( nullable = false)
    private LocalTime duracion;

    @Column( nullable = false)
    private float costoTurista;

    @Column( nullable = false)
    private float costoEjecutivo;

    @Column( nullable = false)
    private float equipajeExtra;

    @Column( nullable = false)
    private LocalDate fechaAlta;

    @Column( nullable = true)
    private String urlImagen;

    @Column( nullable = true)
    private EstadoRuta estado;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "ruta_categoria",
            joinColumns = @JoinColumn(name = "ruta_nombre"),
            inverseJoinColumns = @JoinColumn(name = "categoria_nombre")
    )
    private List<Categoria> categorias = new ArrayList<>();;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "ciudadDestino_nombre", referencedColumnName = "nombreCiudad"),
            @JoinColumn(name = "ciudadDestino_pais", referencedColumnName = "paisCiudad")
    })
    private Ciudad ciudadDestino;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "ciudadOrigen_nombre", referencedColumnName = "nombreCiudad"),
            @JoinColumn(name = "ciudadOrigen_pais", referencedColumnName = "paisCiudad")
    })
    private Ciudad ciudadOrigen;


    public RutaDeVuelo() {}
    public RutaDeVuelo(DtRuta ruta, List<Categoria> categorias, Ciudad origen, Ciudad destino) {
        this.nombre = ruta.getNombre();
        this.descripcion = ruta.getDescripcion();
        this.descripcionCorta = ruta.getDescripcionCorta();
        this.duracion = ruta.getDuracion();
        this.costoTurista = ruta.getCostoTurista();
        this.costoEjecutivo = ruta.getCostoEjecutivo();
        this.equipajeExtra = ruta.getEquipajeExtra();
        this.fechaAlta = ruta.getFechaAlta();
        this.urlImagen = ruta.getUrlImagen();
        this.estado = ruta.getEstado();
        this.categorias = categorias;
        this.ciudadOrigen = origen;
        this.ciudadDestino = destino;
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

    public String getDescripcionCorta() {return descripcionCorta; }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
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

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getUrlImagen() {return urlImagen; };

    public void setUrlImagen(String urlImagen){this.urlImagen = urlImagen; };

    public  EstadoRuta getEstado() {return estado; }

    public void setEstado(EstadoRuta estado) {this.estado = estado; };

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Ciudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(Ciudad ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public Ciudad getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(Ciudad ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public DtRuta getDatos() {
        List<DtCategoria> categoriaList = new ArrayList<>();
        for (Categoria categoria : this.categorias) {
            categoriaList.add(categoria.getDatos());
        }
        return new DtRuta(
                this.getNombre(),
                this.getDescripcion(),
                this.getDescripcionCorta(),
                this.getDuracion(),
                this.getCostoTurista(),
                this.getCostoEjecutivo(),
                this.getEquipajeExtra(),
                this.getFechaAlta(),
                this.getUrlImagen(),
                this.getEstado(),
                categoriaList,
                this.getCiudadOrigen().getDatos(),
                this.getCiudadDestino().getDatos()
        );
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
