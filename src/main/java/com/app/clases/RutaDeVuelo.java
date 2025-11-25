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

    @Column(nullable = true, length = 500)
    private String urlVideo;

    @Column( nullable = true)
    private EstadoRuta estado;

    @Column( nullable = true)
    private int vecesVisitada;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "ruta_categoria",
            joinColumns = @JoinColumn(name = "ruta_nombre"),
            inverseJoinColumns = @JoinColumn(name = "categoria_nombre")
    )
    private List<Categoria> categorias = new ArrayList<>();

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
        this.setNombre(ruta.getNombre());
        this.setDescripcion(ruta.getDescripcion());
        this.setDescripcionCorta(ruta.getDescripcionCorta());
        this.setDuracion(LocalTime.parse(ruta.getDuracion()));
        this.setCostoTurista(ruta.getCostoTurista());
        this.setCostoEjecutivo(ruta.getCostoEjecutivo());
        this.setEquipajeExtra(ruta.getEquipajeExtra());
        this.setFechaAlta(LocalDate.parse(ruta.getFechaAlta()));
        this.setUrlImagen(ruta.getUrlImagen());
        this.setUrlVideo(ruta.getUrlVideo());
        this.setEstado(EstadoRuta.INGRESADA); // Siempre inicializa en Ingresada.
        this.setCategorias(categorias);
        this.setCiudadOrigen(origen);
        this.setCiudadDestino(destino);
        this.setVecesVisitada(0); // Siempre inicializa en 0
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

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

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

    public int getVecesVisitada(){return vecesVisitada;}

    public void setVecesVisitada(int vecesVisitada){this.vecesVisitada = vecesVisitada;}

    public DtRuta getDatos() {
        List<DtCategoria> categoriaList = new ArrayList<>();
        for (Categoria categoria : this.getCategorias()) {
            categoriaList.add(categoria.getDatos());
        }
        return new DtRuta(
                this.getNombre(),
                this.getDescripcion(),
                this.getDescripcionCorta(),
                this.getDuracion().toString(),
                this.getCostoTurista(),
                this.getCostoEjecutivo(),
                this.getEquipajeExtra(),
                this.getFechaAlta().toString(),
                this.getUrlImagen(),
                this.getUrlVideo(),
                this.getEstado(),
                categoriaList,
                this.getCiudadOrigen().getDatos(),
                this.getCiudadDestino().getDatos(),
                this.getVecesVisitada()
        );
    }

    @Override
    public String toString() {
        return this.nombre;
    }


}
