package com.app.clases;

import com.app.datatypes.*;
import java.util.ArrayList;
import java.util.List;

public class Aerolinea extends Usuario {
    private String descripcion;
    private String linkWeb;
    private List<RutaDeVuelo> rutasDeVuelo;

    public Aerolinea(DtAerolinea aerolinea) {
        super(new DtUsuario(aerolinea.getNickname(), aerolinea.getNombre(), aerolinea.getEmail()));
        this.descripcion = aerolinea.getDescripcion();
        this.linkWeb = aerolinea.getLinkWeb();
        this.rutasDeVuelo = new ArrayList<>();
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
        return rutasDeVuelo;
    }

    public void agregarRuta(RutaDeVuelo ruta) {
        this.rutasDeVuelo.add(ruta);
    }

}
