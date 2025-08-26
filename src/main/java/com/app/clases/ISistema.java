package com.app.clases;

import com.app.datatypes.*;

import java.util.List;

public interface ISistema {

    List<DtPaquete> listarPaquetes();
    void seleccionarPaquete(String nombre);
    DtPaquete getPaquete();

    void altaCategoria(DtCategoria categoria);

    void altaCiudad(DtCiudad ciudad);

    void consultaVuelo(DtVuelo vuelo);

    void registrarCliente(DtCliente cliente);
    void modificarCliente(DtCliente cliente);
    void registrarAerolinea(DtAerolinea aerolinea);
    void modificarAerolinea(DtAerolinea aerolinea);
    void cancelarAltaUsuario();
    void confirmarAltaUsuario();

    // TEMPORAL
    List<Ciudad> getCiudades();
    void altaPaquete(DtPaquete paquete);
    List<Categoria> getCategorias();
  
  // Consulta de Ruta de Vuelo
    List<String> listarAerolineas();
    List<String> listarRutasDeAerolinea(String nickname);
    DtRuta consultarRuta(String nombre);
    DtVuelo consultarVuelo(String nombre);

    // Alta de Ruta de Vuelo
    boolean existeRuta(String nombre);
    void altaRutaDeVuelo(String nickname, DtRuta datosRuta);


    // Consulta de Usuario
    List<DtUsuario> listarUsuarios();
    void elegirUsuario(String nickname);
    DtUsuario getUsuarioSeleccionado();
    List<DtReserva> mostrarReservas();
    List<DtPaquete> mostrarPaquetes();
    List<DtRuta> mostrarRutasDeVuelo();
}