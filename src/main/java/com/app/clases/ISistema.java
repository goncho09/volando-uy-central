package com.app.clases;

import com.app.DAOs.*;
import com.app.datatypes.*;

import java.util.List;

public interface ISistema {

    UserDao getUserDao();
    RutaDeVueloDao getRutaDeVueloDao();
    CategoriaDao getCategoriaDao();
    CiudadDao getCiudadDao();
    PaqueteDao getPaqueteDao();

    List<DtPaquete> listarPaquetes();
    void seleccionarPaquete(String nombre);
    DtPaquete getPaquete();

    void altaCategoria(DtCategoria categoria);

    void altaCiudad(DtCiudad ciudad);

    void consultaVuelo(DtVuelo vuelo);

    List<DtCiudad> listarCiudades();

    List<DtRuta> listarRutasDeVuelo(); //Lista todas las rutas de vuelo
    List<DtRuta> listarRutasDeVuelo(String nickname); //Lista las rutas de vuelos asociadas a una aerolinea específica
    void registrarCliente(DtCliente cliente);
    void modificarCliente(DtCliente cliente);
    void registrarAerolinea(DtAerolinea aerolinea);
    void modificarAerolinea(DtAerolinea aerolinea);
    void cancelarAltaUsuario();
    void confirmarAltaUsuario(DtUsuario user);

    // TEMPORAL
    List<Ciudad> getCiudades();
    void altaPaquete(DtPaquete paquete);
    List<Categoria> getCategorias();

    List<Categoria> getCategoriasPorNombre(List<String> nombres);
    Ciudad buscarCiudad (String nombre);
  
  // Consulta de Ruta de Vuelo
    List<DtAerolinea> listarAerolineas();
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

    // ---------- ALTA DE VUELO ---------- //
    void seleccionarAerolineaParaVuelo(String nickname);
    void seleccionarRuta(String nombre);
    void ingresarDatosVuelo(DtVuelo datosVuelo);
    void confirmarAltaVuelo();
    void cancelarAlta();

    // ---------- COMPRA PAQUETE ---------- //

}