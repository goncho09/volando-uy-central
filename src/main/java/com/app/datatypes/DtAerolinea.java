package com.app.datatypes;

import com.app.clases.RutaDeVuelo;

import java.util.ArrayList;
import java.util.List;

public class DtAerolinea extends DtUsuario {
    private String descripcion;
    private String linkWeb;
    private List<RutaDeVuelo> rutasDeVuelo;

    public DtAerolinea(String nickname, String nombre, String email, String descripcion) {
        super(nickname, nombre, email);
        this.descripcion = descripcion;
        this.linkWeb = "";
        this.rutasDeVuelo = new ArrayList<>();
    }

    public DtAerolinea(String nickname, String nombre, String email, String descripcion, String linkWeb) {
        super(nickname, nombre, email);
        this.descripcion = descripcion;
        this.linkWeb = linkWeb;
        this.rutasDeVuelo = new ArrayList<>();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLinkWeb() {
        return linkWeb;
    }

    public List<RutaDeVuelo> getRutasDeVuelo() {
        return rutasDeVuelo;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setLinkWeb(String linkWeb) {
        this.linkWeb = linkWeb;
    }


    public void setRutasDeVuelo(List<RutaDeVuelo> rutasDeVuelo) {
        this.rutasDeVuelo = rutasDeVuelo;
    }
}
