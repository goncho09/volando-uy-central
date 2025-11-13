package com.app.clases;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import com.app.datatypes.DtAerolinea;
import com.app.datatypes.DtUsuario;
import com.app.datatypes.DtRuta;

@Entity
public class Aerolinea extends Usuario {
    private String descripcion;
    private String linkWeb;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "aerolinea_rutas",
            joinColumns = @JoinColumn(name = "aerolinea_nombre"),
            inverseJoinColumns = @JoinColumn(name = "ruta_nombre")
    )
    private List<RutaDeVuelo> rutasDeVuelo;

    public Aerolinea() {}

    public Aerolinea(DtAerolinea aerolinea) {
        super(new DtUsuario(aerolinea.getNickname(), aerolinea.getNombre(), aerolinea.getEmail(), aerolinea.getPassword(), aerolinea.getUrlImage(),new ArrayList<>(), new ArrayList<>()));
        this.setDescripcion(aerolinea.getDescripcion());
        this.setLinkWeb(aerolinea.getLinkWeb());
        this.setRutasDeVuelo(new ArrayList<>());
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLinkWeb() {
        return linkWeb;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setLinkWeb(String linkWeb) {
        this.linkWeb = linkWeb;
    }

    public List<RutaDeVuelo> getRutasDeVuelo() {
        return this.rutasDeVuelo;
    }

    public List<DtRuta> getRutasDeVueloDatos() {
        List<DtRuta> rutasList = new ArrayList<>();
        for (RutaDeVuelo ruta : this.rutasDeVuelo) {
            rutasList.add(ruta.getDatos());
        }
        return rutasList;
    }

    public void setRutasDeVuelo(List<RutaDeVuelo> rutas) {
        this.rutasDeVuelo = rutas;
    }

    public DtAerolinea getDatos(){
        return new DtAerolinea(
                this.getNickname(),
                this.getNombre(),
                this.getEmail(),
                this.getUrlImage(),
                this.getSeguidores(),
                this.getSeguidos(),
                this.getDescripcion(),
                this.getLinkWeb(),
                this.getRutasDeVuelo()
        );
    }

    public void addRuta(RutaDeVuelo ruta) {
        this.getRutasDeVuelo().add(ruta);
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
}
