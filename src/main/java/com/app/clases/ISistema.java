package com.app.clases;

import java.time.LocalDate;
import java.util.List;

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
    boolean existeUsuario(String nickname);
    boolean existeUsuarioEmail(String email);
    boolean clienteTienePaquete(String nickname, String nombrePaquete);
    boolean validarUsuario(String nickname, String password);
    boolean aerolineaTieneRuta(DtAerolinea aerolinea, String nombre);
    String getTipoUsuario(String nickname);
    void registrarAerolinea(DtAerolinea aerolinea);
    void modificarAerolinea(DtAerolinea aerolinea);
    void modificarUsuarioImagen(DtUsuario user, String urlImagen);
    void registrarCliente(DtCliente cliente);
    void modificarCliente(DtCliente cliente);
    Aerolinea buscarAerolinea(String nickname);
    Cliente buscarCliente(String nickname);
    DtUsuario getUsuario(String nickname);
    DtCliente getCliente(String nickname);
    DtAerolinea getAerolinea(String nickname);
    List<DtUsuario> listarUsuarios();
    List<DtCliente> listarClientes();
    List<DtAerolinea> listarAerolineas();
    void seguirUsuario(String usuarioSeguidor, String usuarioASeguir);
    void dejarDeSeguirUsuario(String usuarioSeguidor, String usuarioASeguir);

    // ------------ RESERVAS ----------- //
    void altaReserva(DtReserva reserva); //Reserva de un Vuelo
    DtReserva buscarReserva(DtReserva reserva);
    DtReserva getReservaCliente(DtVuelo vuelo, DtCliente cliente, LocalDate fechaReserva);
    DtReserva getReservaAerolinea(DtVuelo vuelo, DtAerolinea aerolinea, LocalDate fechaReserva);
    List<DtReserva> listarReservasVuelo(String nombreVuelo); // Devuelve las reservas de un vuelo en específico
    List<DtReserva> listarReservasCliente(String nickname); // Devuelve las reservas de un cliente en específico
    List<DtReserva> listarReservasAerolinea(String nickname); // Devuelve las reservas de una aerolínea en específico
    List<DtReserva> listarReservasClienteVuelo(DtCliente cliente, DtVuelo vuelo); // Devuelve las reservas de un cliente en un vuelo en específico
    DtReserva realizarCheckin (DtVuelo vuelo, LocalDate fechaReserva, String nicknameCliente);
    byte[] crearPDFReserva(DtReserva reserva);

    // ---------- RUTAS DE VUELO ---------- //
    boolean rutaContieneCategoria(DtRuta ruta, String categoria);
    boolean existeRuta(String nombre);
    void actualizarEstadoRuta(DtRuta ruta, EstadoRuta estado);
    void altaRutaDeVuelo(String nickname, DtRuta datosRuta);
    DtRuta getRutaDeVuelo(String nombre);
    List<DtRuta> listarRutasDeVuelo(); // Devuelve TODAS las rutas de vuelo
    List<DtRuta> listarRutasDeVueloPaquete(String paquete); // Devuelve TODAS las rutas de un paquete en específico

    // ---------- VUELOS ---------- //
    boolean existeVuelo(String nombre);
    void altaVuelo(DtVuelo vuelo);
    DtVuelo getVuelo(String nombre);
    List<DtVuelo> listarVuelos();
    List<DtVuelo> listarVuelosRuta(String nombreRuta); // Busca vuelos por nombre de ruta
    List<DtVuelo> listarVuelosRutaFecha(String nombreRuta, LocalDate fecha); // Busca vuelos por nombre de ruta y fecha opcional
    List<DtVuelo> getVuelosRutaDeVuelo(DtRuta ruta);

    // ---------- PAQUETES ---------- //
    boolean estaPaqueteComprado(DtPaquete paquete);
    boolean existePaquete(String nombre);
    int agregarRutaAPaquete(DtPaquete paquete, DtRuta ruta, int cantidad, TipoAsiento tipoAsiento);
    void altaPaquete(DtPaquete paquete);
    DtPaquete getPaquete(String nombre);
    List<DtPaquete> listarPaquetes();
    List<DtPaquete> listarPaquetesCliente(String nickname); // Lista todos los paquetes comprados por un cliente.
    List<DtPaquete> listarPaquetesAerolinea(String nickname);
    List<DtPaquete> listarPaquetesNoComprados();
    List<DtPaquete> listarPaquetesConRutas();


    // ---------- CATEGORIAS ---------- //
    void altaCategoria(DtCategoria categoria);
    DtCategoria getCategoria(String nombre);
    List<DtCategoria> listarCategorias();
    List<DtCategoria> buscarCategorias();
    List<DtCategoria> buscarCategoriasPorNombre(List<String> nombres);

    // ---------- CIUDADES ---------- //
    void altaCiudad(DtCiudad ciudad);
    DtCiudad getCiudad(String nombre, String pais);
    List<DtCiudad> listarCiudades();

    // ---------- COMPRAS ---------- //
    void compraPaquete(DtPaquete paquete, DtCliente cliente);

    // -------- Pasajeros --------- //
    List<DtPasajero> listarPasajeros(DtReserva reserva);

    // -------- Auxiliares --------- //

    String detectarExtension(byte[] data);

    // ---------- BD ---------- //
    void vaciarBD();
}
