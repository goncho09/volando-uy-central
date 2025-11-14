package com.app.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface VolandoServicePort {

    @WebMethod
    String ping();

    @WebMethod
    String obtenerVuelo(String nombreVuelo);
}
