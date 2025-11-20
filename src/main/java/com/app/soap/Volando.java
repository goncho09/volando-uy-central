package com.app.soap;

import javax.jws.WebService;
import com.app.clases.Factory;
import com.app.clases.ISistema;
import com.app.datatypes.*;
import com.app.enums.TipoImagen;
import com.app.utils.AuxiliarFunctions;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

@WebService(endpointInterface = "com.app.soap.VolandoServicePort")
public class Volando implements VolandoServicePort {

    private static ISistema sistema = Factory.getSistema();

    // Aquí solamente se deben "publicar" las funciones que se utilizarán por fuera de la app

    // ---------- USUARIOS ---------- //

    @Override
    public boolean existeUsuario(String nickname) {
        return sistema.existeUsuario(nickname);
    }

    @Override
    public boolean existeUsuarioEmail(String email) {
        return sistema.existeUsuarioEmail(email);
    }

    @Override
    public boolean validarUsuario(String nickname, String password) {
        return sistema.validarUsuario(nickname, password);
    }

    @Override
    public void registrarAerolinea(DtAerolinea aerolinea) {
        sistema.registrarAerolinea(aerolinea);
    }

    @Override
    public void modificarAerolinea(DtAerolinea aerolinea) {
        sistema.modificarAerolinea(aerolinea);
    }

    @Override
    public void modificarAerolineaImagen(DtAerolinea aerolinea, String urlImagen) {
        sistema.modificarAerolineaImagen(aerolinea, urlImagen);
    }

    @Override
    public void registrarCliente(DtCliente cliente) {
        sistema.registrarCliente(cliente);
    }

    @Override
    public void modificarCliente(DtCliente cliente) {
        sistema.modificarCliente(cliente);
    }

    @Override
    public void modificarClienteImagen(DtCliente cliente, String urlImagen) {
        sistema.modificarClienteImagen(cliente, urlImagen);
    }

    @Override
    public DtUsuario getUsuario(String nickname) {
        return sistema.getUsuario(nickname);
    }

    @Override
    public DtCliente getCliente(String nickname) {
        return sistema.getCliente(nickname);
    }

    @Override
    public DtAerolinea getAerolinea(String nickname) {
        return sistema.getAerolinea(nickname);
    }

    @Override
    public List<DtUsuario> listarUsuarios() {
        return sistema.listarUsuarios();
    }

    @Override
    public List<DtAerolinea> listarAerolineas() {
        return sistema.listarAerolineas();
    }

    @Override
    public void seguirUsuario(String usuarioSeguidor, String usuarioASeguir) {
        sistema.seguirUsuario(usuarioSeguidor, usuarioASeguir);
    }

    @Override
    public void dejarDeSeguirUsuario(String usuarioSeguidor, String usuarioASeguir) {
        sistema.dejarDeSeguirUsuario(usuarioSeguidor, usuarioASeguir);
    }

// ------------ RESERVAS ----------- //

    @Override
    public void altaReserva(DtReserva reserva) {
        sistema.altaReserva(reserva);
    }

    @Override
    public DtReserva getReservaCliente(DtVuelo vuelo, DtCliente cliente, String fechaReserva) {
        return sistema.getReservaCliente(vuelo, cliente, LocalDate.parse(fechaReserva));
    }

    @Override
    public DtReserva getReservaAerolinea(DtVuelo vuelo, DtAerolinea aerolinea, String fechaReserva) {
        return sistema.getReservaAerolinea(vuelo, aerolinea, LocalDate.parse(fechaReserva));
    }

    @Override
    public List<DtReserva> listarReservasCliente(String nickname) {
        return sistema.listarReservasCliente(nickname);
    }

    @Override
    public List<DtReserva> listarReservasAerolinea(String nickname) {
        return sistema.listarReservasAerolinea(nickname);
    }

    @Override
    public List<DtReserva> listarReservasClienteVuelo(DtCliente cliente, DtVuelo vuelo) {
        return sistema.listarReservasClienteVuelo(cliente, vuelo);
    }

    @Override
    public DtReserva realizarCheckin (DtVuelo vuelo, String fechaReserva, String nicknameCliente){
        return sistema.realizarCheckin(vuelo, LocalDate.parse(fechaReserva), nicknameCliente);
    }

    @Override
    public byte[] crearPDFReserva(DtReserva reserva) {
        return sistema.crearPDFReserva(reserva);
    }

// ---------- RUTAS DE VUELO ---------- //

    @Override
    public boolean rutaContieneCategoria(DtRuta ruta, String categoria) {
        return sistema.rutaContieneCategoria(ruta, categoria);
    }

    @Override
    public boolean existeRuta(String nombre) {
        return sistema.existeRuta(nombre);
    }

    @Override
    public void altaRutaDeVuelo(String nickname, DtRuta datosRuta) {
        sistema.altaRutaDeVuelo(nickname, datosRuta);
    }

    @Override
    public DtRuta getRutaDeVuelo(String nombre) {
        return sistema.getRutaDeVuelo(nombre);
    }

    @Override
    public List<DtRuta> listarRutasDeVuelo() {
        return sistema.listarRutasDeVuelo();
    }

// ---------- VUELOS ---------- //

    @Override
    public boolean existeVuelo(String nombre) {
        return sistema.existeVuelo(nombre);
    }

    @Override
    public void altaVuelo(DtVuelo vuelo) {
        sistema.altaVuelo(vuelo);
    }

    @Override
    public DtVuelo getVuelo(String nombre) {
        return sistema.getVuelo(nombre);
    }

    @Override
    public List<DtVuelo> listarVuelosRuta(String nombreRuta) {
        return sistema.listarVuelosRuta(nombreRuta);
    }

    @Override
    public List<DtVuelo> listarVuelosRutaFecha(String nombreRuta, String fecha) {
        return sistema.listarVuelosRutaFecha(nombreRuta, LocalDate.parse(fecha));
    }

    @Override
    public List<DtVuelo> getVuelosRutaDeVuelo(DtRuta ruta) {
        return sistema.getVuelosRutaDeVuelo(ruta);
    }

// ---------- PAQUETES ---------- //

    @Override
    public boolean estaPaqueteComprado(DtPaquete paquete) {
        return sistema.estaPaqueteComprado(paquete);
    }

    @Override
    public boolean existePaquete(String nombre) {
        return sistema.existePaquete(nombre);
    }

    @Override
    public void altaPaquete(DtPaquete paquete) {
        sistema.altaPaquete(paquete);
    }

    @Override
    public DtPaquete getPaquete(String nombre) {
        return sistema.getPaquete(nombre);
    }

    @Override
    public List<DtPaquete> listarPaquetes() {
        return sistema.listarPaquetes();
    }

    @Override
    public List<DtPaquete> listarPaquetesCliente(String nickname) {
        return sistema.listarPaquetesCliente(nickname);
    }

    @Override
    public List<DtPaquete> listarPaquetesAerolinea(String nickname) {
        return sistema.listarPaquetesAerolinea(nickname);
    }

    @Override
    public List<DtPaquete> listarPaquetesNoComprados() {
        return sistema.listarPaquetesNoComprados();
    }

// ---------- CATEGORIAS ---------- //

    @Override
    public List<DtCategoria> listarCategorias() {
        return sistema.listarCategorias();
    }

    @Override
    public List<DtCategoria> buscarCategorias() {
        return sistema.buscarCategorias();
    }

    @Override
    public List<DtCategoria> buscarCategoriasPorNombre(List<String> nombres) {
        return sistema.buscarCategoriasPorNombre(nombres);
    }

// ---------- CIUDADES ---------- //

    @Override
    public void altaCiudad(DtCiudad ciudad) {
        sistema.altaCiudad(ciudad);
    }

    @Override
    public DtCiudad getCiudad(String nombre, String pais) {
        return sistema.getCiudad(nombre, pais);
    }

    @Override
    public List<DtCiudad> listarCiudades() {
        return sistema.listarCiudades();
    }

// ---------- COMPRAS ---------- //

    @Override
    public void compraPaquete(DtPaquete paquete, DtCliente cliente) {
        sistema.compraPaquete(paquete, cliente);
    }


    // -------- Auxiliar --------- //
    @Override
    public String guardarImagen(byte[] data, TipoImagen tipoImagen){
        try{
            File tempFile = File.createTempFile("upload_", ".bin");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(data);
            }
            File imagenGuardada = AuxiliarFunctions.guardarImagen(tempFile, tipoImagen);
            return imagenGuardada.getName();
        }catch(Exception e){
            throw new IllegalArgumentException("Ha ocurrido un error al subir la imagen");
        }
    }

}