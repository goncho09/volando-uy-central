package com.app.clases;

import com.app.DAOs.*;
import com.app.datatypes.*;
import java.util.List;

public interface ISistema {

    // ---------- USUARIOS ---------- //
    List<DtUsuario> listarUsuarios();
    List<DtAerolinea> listarAerolineas();
    List<DtCliente> listarClientes();
    void elegirUsuario(String nickname);
    DtUsuario getUsuarioSeleccionado();

    void registrarCliente(DtCliente cliente);
    void modificarCliente(DtCliente cliente);

    void registrarAerolinea(DtAerolinea aerolinea);
    void modificarAerolinea(DtAerolinea aerolinea);

    void cancelarAltaUsuario();
    void confirmarAltaUsuario(DtUsuario user);


    // ----------- Reservas ------------ //
    List<DtReserva> mostrarReservas();
    List<DtPaquete> mostrarPaquetes();
    List<DtRuta> mostrarRutasDeVuelo();


    // ---------- RUTAS DE VUELO ---------- //
    List<DtRuta> listarRutasDeVuelo();
    List<DtRuta> listarRutasDeVuelo(String nickname);
    DtRuta consultarRuta(String nombre);

    boolean existeRuta(String nombre);
    void altaRutaDeVuelo(String nickname, DtRuta datosRuta);

    // ---------- VUELOS ---------- //
    DtVuelo consultarVuelo(String nombre);

    void seleccionarAerolineaParaVuelo(String nickname);
    void seleccionarRuta(String nombre);
    void ingresarDatosVuelo(DtVuelo datosVuelo);
    void confirmarAltaVuelo();
    void cancelarAlta();

    // ---------- PAQUETES ---------- //
    List<DtPaquete> listarPaquetes();
    void seleccionarPaquete(String nombre);
    DtPaquete getPaquete();

    void altaPaquete(DtPaquete paquete);

    // ---------- CATEGORÍAS ---------- //
    void altaCategoria(DtCategoria categoria);
    List<Categoria> getCategorias();
    List<Categoria> getCategoriasPorNombre(List<String> nombres);

    // ---------- CIUDADES ---------- //
    void altaCiudad(DtCiudad ciudad);
    List<DtCiudad> listarCiudades();
    Ciudad buscarCiudad(String nombre);
    List<Ciudad> getCiudades();

    // ---------- INFRAESTRUCTURA (Daos y Acceso Directo) ---------- //
    UserDao getUserDao();
    RutaDeVueloDao getRutaDeVueloDao();
    CategoriaDao getCategoriaDao();
    CiudadDao getCiudadDao();
    PaqueteDao getPaqueteDao();

    List<Aerolinea> getAerolineas();
    List<RutaDeVuelo> getRutasDeVuelo();
}
