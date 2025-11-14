package com.app.soap;

import javax.jws.WebService;
import com.app.clases.Factory;
import com.app.clases.ISistema;
import com.app.datatypes.DtVuelo;

@WebService(endpointInterface = "com.app.soap.VolandoServicePort")
public class VolandoService implements VolandoServicePort {

    private static ISistema sistema = Factory.getSistema();

    // Aquí solamente se deben "publicar" las funciones que se utilizarán por fuera de la app

    @Override
    public String ping() {
        return "Volando SOAP OK";
    }

    @Override
    public String obtenerVuelo(String nombreVuelo) {
        DtVuelo v = sistema.getVuelo(nombreVuelo);
        return (v != null) ? v.toString() : "No existe";
    }


}