package com.app.clases;

import com.app.DAOs.*;
import com.app.datatypes.*;
import com.app.enums.TipoAsiento;

import java.util.List;

public interface ISistema {

    // ---------- USUARIOS ---------- //
    List<DtUsuario> listarUsuarios();
    void elegirUsuario(String nickname);
    DtUsuario getUsuarioSeleccionado();

    List<DtCliente> listarClientes();
    List<Cliente> getClientes();
    void registrarCliente(DtCliente cliente);
    void modificarCliente(DtCliente cliente);
    Cliente buscarCliente(String nickname);

    List<DtAerolinea> listarAerolineas();
    List<Aerolinea> getAerolineas();
    void registrarAerolinea(DtAerolinea aerolinea);
    void modificarAerolinea(DtAerolinea aerolinea);

    void cancelarAltaUsuario();
    void confirmarAltaUsuario(DtUsuario user);

    // ------------ RESERVAS ----------- //
    void altaReserva(DtReserva reserva, String nickCliente, String nameVuelo); //Reserva de un Vuelo

    List<DtReserva> mostrarReservas();
    List<DtPaquete> mostrarPaquetes();
    List<DtReserva> listarReservaDeVuelo(String nombreVuelo);


    // ---------- RUTAS DE VUELO ---------- //
    List<DtRuta> listarRutasDeVuelo(); // Devuelve TODAS las rutas de vuelo
    List<DtRuta> listarRutasDeVuelo(String nickname); // Devuelve TODAS las rutas de vuelo de una aerolínea en específico
    DtRuta consultarRuta(String nombre);

    boolean existeRuta(String nombre);
    void altaRutaDeVuelo(String nickname, DtRuta datosRuta);

    // ---------- VUELOS ---------- //
    List<DtVuelo> listarVuelos();
    List<DtVuelo> listarVuelos(String nombre);
    Vuelo buscarVuelo(String nombre);
    DtVuelo consultarVuelo(String nombre);

    void seleccionarAerolineaParaVuelo(String nickname);
    void seleccionarRuta(String nombre);
    void ingresarDatosVuelo(DtVuelo datosVuelo);
    void confirmarAltaVuelo();
    void cancelarAlta();
    List <DtVuelo> getVuelosRutaDeVuelo(String nombre);

    // ---------- PAQUETES ---------- //
    List<DtPaquete> listarPaquetes();
    List<DtPaquete> listarPaquetesNoComprados();
    List<DtPaquete> listarPaquetesConRutas();
    void agregarRutaAPaquete(String nombrePaquete, String nombreRuta,int cantidad, TipoAsiento tipoAsiento);
    DtPaquete getPaquete();
    Paquete buscarPaquete(String nombre);

    void altaPaquete(DtPaquete paquete);

    // ---------- CATEGORÍAS ---------- //
    void altaCategoria(DtCategoria categoria);
    List<Categoria> getCategorias();
    List<Categoria> getCategoriasPorNombre(List<String> nombres);

    // ---------- CIUDADES ---------- //
    void altaCiudad(DtCiudad ciudad);
    List<DtCiudad> listarCiudades();
    Ciudad buscarCiudad(String nombre, String pais);
    List<Ciudad> getCiudades();

    // ---------- COMPRAS ---------- //
    void compraPaquete(String paquete,String nickname);

    // ---------- INFRAESTRUCTURA ---------- //
    UserDao getUserDao();
    RutaDeVueloDao getRutaDeVueloDao();
    CategoriaDao getCategoriaDao();
    CiudadDao getCiudadDao();
    PaqueteDao getPaqueteDao();
    VueloDao getVueloDao();

    List<RutaDeVuelo> getRutasDeVuelo();
}
