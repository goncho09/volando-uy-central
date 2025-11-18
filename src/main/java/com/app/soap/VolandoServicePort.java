package com.app.soap;

import java.io.File;
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
import com.app.enums.TipoImagen;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface VolandoServicePort {

    //Aquí están SOLO los métodos que queremos "compartir" o dar como servicio a nuestra web.

    // ---------- USUARIOS ---------- //
    @WebMethod
    boolean existeUsuario(String nickname);

    @WebMethod
    boolean existeUsuarioEmail(String email);

//    boolean clienteTienePaquete(String nickname, String nombrePaquete);

    @WebMethod
    boolean validarUsuario(String nickname, String password);

//    boolean aerolineaTieneRuta(DtAerolinea aerolinea, String nombre);

    @WebMethod
    void registrarAerolinea(DtAerolinea aerolinea);

    @WebMethod
    void modificarAerolinea(DtAerolinea aerolinea);

    @WebMethod
    void modificarAerolineaImagen(DtAerolinea aerolinea, String urlImagen);

    @WebMethod
    void registrarCliente(DtCliente cliente);

    @WebMethod
    void modificarCliente(DtCliente cliente);

    @WebMethod
    void modificarClienteImagen(DtCliente cliente, String urlImagen);

//    Aerolinea buscarAerolinea(String nickname);
//    Cliente buscarCliente(String nickname);

    @WebMethod
    DtUsuario getUsuario(String nickname);

    @WebMethod
    DtCliente getCliente(String nickname);

    @WebMethod
    DtAerolinea getAerolinea(String nickname);

    @WebMethod
    List<DtUsuario> listarUsuarios();

//    List<DtCliente> listarClientes();

    @WebMethod
    List<DtAerolinea> listarAerolineas();

    @WebMethod
    void seguirUsuario(String usuarioSeguidor, String usuarioASeguir);

    @WebMethod
    void dejarDeSeguirUsuario(String usuarioSeguidor, String usuarioASeguir);


    // ------------ RESERVAS ----------- //
    @WebMethod
    void altaReserva(DtReserva reserva); //Reserva de un Vuelo

//    DtReserva buscarReserva(DtReserva reserva);

    @WebMethod
    DtReserva getReservaCliente(DtVuelo vuelo, DtCliente cliente, String fechaReserva);

    @WebMethod
    DtReserva getReservaAerolinea(DtVuelo vuelo, DtAerolinea aerolinea, String fechaReserva);

//    List<DtReserva> listarReservasVuelo(String nombreVuelo); // Devuelve las reservas de un vuelo en específico

    @WebMethod
    List<DtReserva> listarReservasCliente(String nickname); // Devuelve las reservas de un cliente en específico

    @WebMethod
    List<DtReserva> listarReservasAerolinea(String nickname); // Devuelve las reservas de una aerolínea en específico

    @WebMethod
    List<DtReserva> listarReservasClienteVuelo(DtCliente cliente, DtVuelo vuelo); // Devuelve las reservas de un cliente en un vuelo en específico


    // ---------- RUTAS DE VUELO ---------- //
    @WebMethod
    boolean rutaContieneCategoria(DtRuta ruta, String categoria);

    @WebMethod
    boolean existeRuta(String nombre);

//    void actualizarEstadoRuta(DtRuta ruta, EstadoRuta estado);

    @WebMethod
    void altaRutaDeVuelo(String nickname, DtRuta datosRuta);

    @WebMethod
    DtRuta getRutaDeVuelo(String nombre);

    @WebMethod
    List<DtRuta> listarRutasDeVuelo(); // Devuelve TODAS las rutas de vuelo

//    List<DtRuta> listarRutasDeVueloPaquete(String paquete); // Devuelve TODAS las rutas de un paquete en específico

    // ---------- VUELOS ---------- //
    @WebMethod
    boolean existeVuelo(String nombre);

    @WebMethod
    void altaVuelo(DtVuelo vuelo);

    @WebMethod
    DtVuelo getVuelo(String nombre);

//    List<DtVuelo> listarVuelos();

    @WebMethod
    List<DtVuelo> listarVuelosRuta(String nombreRuta); // Busca vuelos por nombre de ruta

    @WebMethod
    List<DtVuelo> listarVuelosRutaFecha(String nombreRuta, String fecha); // Busca vuelos por nombre de ruta y fecha opcional

    @WebMethod
    List<DtVuelo> getVuelosRutaDeVuelo(DtRuta ruta);

    // ---------- PAQUETES ---------- //
    @WebMethod
    boolean estaPaqueteComprado(DtPaquete paquete);

    @WebMethod
    boolean existePaquete(String nombre);

//    int agregarRutaAPaquete(DtPaquete paquete, DtRuta ruta, int cantidad, TipoAsiento tipoAsiento);

    @WebMethod
    void altaPaquete(DtPaquete paquete);

    @WebMethod
    DtPaquete getPaquete(String nombre);

    @WebMethod
    List<DtPaquete> listarPaquetes();

    @WebMethod
    List<DtPaquete> listarPaquetesCliente(String nickname); // Lista todos los paquetes comprados por un cliente.

    @WebMethod
    List<DtPaquete> listarPaquetesAerolinea(String nickname);

    @WebMethod
    List<DtPaquete> listarPaquetesNoComprados();

//    List<DtPaquete> listarPaquetesConRutas();


    // ---------- CATEGORIAS ---------- //
//    void altaCategoria(DtCategoria categoria);
//    DtCategoria getCategoria(String nombre);

    @WebMethod
    List<DtCategoria> listarCategorias();

    @WebMethod
    List<DtCategoria> buscarCategorias();

    @WebMethod
    List<DtCategoria> buscarCategoriasPorNombre(List<String> nombres);

    // ---------- CIUDADES ---------- //
    @WebMethod
    void altaCiudad(DtCiudad ciudad);

    @WebMethod
    DtCiudad getCiudad(String nombre, String pais);

    @WebMethod
    List<DtCiudad> listarCiudades();

    // ---------- COMPRAS ---------- //
    @WebMethod
    void compraPaquete(DtPaquete paquete, DtCliente cliente);

    // -------- Pasajeros --------- //
//        List<DtPasajero> listarPasajeros(DtReserva reserva);

    // -------- Auxiliar --------- //
    @WebMethod
    String guardarImagen(byte[] data, TipoImagen tipoImagen);

}
