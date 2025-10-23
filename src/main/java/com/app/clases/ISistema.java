package com.app.clases;

import java.time.LocalDate;
import java.util.List;

import com.app.DAOs.CategoriaDao;
import com.app.DAOs.RutaDeVueloDao;
import com.app.DAOs.UserDao;
import com.app.DAOs.VueloDao;
import com.app.DAOs.CiudadDao;
import com.app.DAOs.PaqueteDao;

import com.app.datatypes.DtAerolinea;
import com.app.datatypes.DtCliente;
import com.app.datatypes.DtReserva;
import com.app.datatypes.DtUsuario;
import com.app.datatypes.DtVuelo;
import com.app.datatypes.DtRuta;
import com.app.datatypes.DtPaquete;
import com.app.datatypes.DtCiudad;
import com.app.datatypes.DtCategoria;
import com.app.datatypes.DtPasajero;
import com.app.enums.EstadoRuta;
import com.app.enums.TipoAsiento;



public interface ISistema {
    // ---------- USUARIOS ---------- //
    List<DtUsuario> listarUsuarios();
    void elegirUsuario(String nickname);
    DtUsuario getUsuarioSeleccionado();
    void borrarUsuarioSeleccionado();
    boolean validarUsuario(String nickname, String password);
    boolean existeUsuarioNickname(String nickname);
    boolean existeUsuarioEmail(String email);
    boolean clienteTienePaquete(String nickname, String nombrePaquete);

    List<DtCliente> listarClientes();
    List<Cliente> getClientes();
    void registrarCliente(DtCliente cliente);
    void modificarCliente(DtCliente cliente);
    void modificarClienteImagen(DtCliente cliente, String urlImagen);
    Cliente buscarCliente(DtCliente cliente);
    DtCliente getCliente(String nickname);

    List<DtAerolinea> listarAerolineas();
    List<Aerolinea> getAerolineas();
    void registrarAerolinea(DtAerolinea aerolinea);
    void modificarAerolinea(DtAerolinea aerolinea);
    void modificarAerolineaImagen(DtAerolinea aerolinea, String urlImagen);
    Aerolinea buscarAerolinea(DtAerolinea aerolinea);
    DtAerolinea getAerolinea(String nickname);
    boolean aerolineaTieneRuta(DtAerolinea aerolinea, String nombre);

    void cancelarAltaUsuario();
    void confirmarAltaUsuario(DtUsuario user);

    // ------------ RESERVAS ----------- //
    List<DtReserva> listarReservas();
    List<DtReserva> listarReservas(DtVuelo vuelo); // Devuelve las reservas de un vuelo en específico
    List<DtReserva> listarReservas(DtCliente cliente); // Devuelve las reservas de un cliente en específico
    List<DtReserva> listarReservas(DtCliente cliente, DtVuelo vuelo); // Devuelve las reservas de un cliente en un vuelo en específico
    List<DtReserva> listarReservas(DtAerolinea aerolinea); // Devuelve las reservas de una aerolínea en específico

    void altaReserva(DtReserva reserva); //Reserva de un Vuelo
    DtReserva getReservaCliente(DtVuelo vuelo, DtCliente cliente, LocalDate fechaReserva);
    DtReserva getReservaAerolinea(DtVuelo vuelo, DtAerolinea aerolinea, LocalDate fechaReserva);

    List<DtReserva> mostrarReservas();
    List<DtPaquete> mostrarPaquetes();
    List<DtReserva> listarReservaDeVuelo(String nombreVuelo);


    // ---------- RUTAS DE VUELO ---------- //
    List<DtRuta> listarRutasDeVuelo(); // Devuelve TODAS las rutas de vuelo
    List<DtRuta> listarRutasDeVuelo(DtAerolinea aerolinea); // Devuelve TODAS las rutas de vuelo de una aerolínea en específico
    List<DtRuta> listarRutasDeVuelo(DtPaquete paquete); // Devuelve TODAS las rutas de un paquete en específico
    DtRuta consultarRuta(String nombre);
    void actualizarEstadoRuta(DtRuta ruta, EstadoRuta estado);

    boolean containsCategoria(DtRuta ruta, String categoria);

    boolean existeRuta(String nombre);
    DtRuta getRutaDeVuelo(String nombre);
    RutaDeVuelo buscarRutaDeVuelo(DtRuta ruta);
    void altaRutaDeVuelo(String nickname, DtRuta datosRuta);

    // ---------- VUELOS ---------- //
    List<DtVuelo> listarVuelos();
    List<DtVuelo> listarVuelos(String nombreRuta); // Busca vuelos por nombre de ruta
    List<DtVuelo> listarVuelos(String nombreRuta, LocalDate fecha); // Busca vuelos por nombre de ruta y fecha opcional
    Vuelo buscarVuelo(DtVuelo vuelo);
    DtVuelo consultarVuelo(String nombre);
    DtVuelo getVuelo(String nombre);
    boolean existeVuelo(String nombre);

    void altaVuelo(DtVuelo vuelo);

    List<DtVuelo> getVuelosRutaDeVuelo(DtRuta ruta);

    // ---------- PAQUETES ---------- //
    List<DtPaquete> listarPaquetes();
    List<DtPaquete> listarPaquetes(DtCliente cliente); // Lista todos los paquetes comprados por un cliente.
    List<DtPaquete> listarPaquetes(DtAerolinea aerolinea);
    List<DtPaquete> listarPaquetesNoComprados();
    List<DtPaquete> listarPaquetesConRutas();
    int agregarRutaAPaquete(DtPaquete paquete, DtRuta ruta, int cantidad, TipoAsiento tipoAsiento);
    DtPaquete getPaquete();
    DtPaquete getPaquete(String nombre);
    Paquete buscarPaquete(DtPaquete paquete);

    boolean paqueteComprado(DtPaquete paquete);
    boolean existePaquete(String nombre);


    void altaPaquete(DtPaquete paquete);

    // ---------- CATEGORIAS ---------- //
    DtCategoria getCategoria(String nombre);
    List<DtCategoria> getCategorias();

    void altaCategoria(DtCategoria categoria);
    DtCategoria buscarCategoria(String nombre);
    List<DtCategoria> buscarCategorias();
    List<DtCategoria> buscarCategoriasPorNombre(List<String> nombres);

    // ---------- CIUDADES ---------- //
    void altaCiudad(DtCiudad ciudad);
    List<DtCiudad> listarCiudades();
    Ciudad buscarCiudad(String nombre, String pais);
    DtCiudad getCiudad(String nombre, String pais);
    List<Ciudad> getCiudades();

    // ---------- COMPRAS ---------- //
    void compraPaquete(DtPaquete paquete, DtCliente cliente);

    // -------- Pasajeros --------- //
    List<DtPasajero> listarPasajeros();
    List<DtPasajero> listarPasajeros(DtReserva reserva);


    void validarTextoSoloLetra(String nombre);

    // ---------- BD ---------- //
    void vaciarBD();
    void cargarDatos();
    UserDao getUserDao();
    RutaDeVueloDao getRutaDeVueloDao();
    CategoriaDao getCategoriaDao();
    CiudadDao getCiudadDao();
    PaqueteDao getPaqueteDao();
    VueloDao getVueloDao();

    List<RutaDeVuelo> getRutasDeVuelo();
}
