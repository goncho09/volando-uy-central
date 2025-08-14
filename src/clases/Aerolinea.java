package clases;


import datatypes.DtAerolinea;
import datatypes.DtUsuario;

import java.util.List;

public class Aerolinea extends Usuario {
    private String descripcion;
    private String linkWeb;
    private List<RutaDeVuelo> rutasDeVuelo;

    public Aerolinea(DtAerolinea aerolinea) {
        super(new DtUsuario(aerolinea.getNickname(), aerolinea.getNombre(), aerolinea.getEmail()));
        this.descripcion = aerolinea.getDescripcion();
        this.linkWeb = aerolinea.getLinkWeb();
        this.rutasDeVuelo = aerolinea.getRutasDeVuelo();
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
}
