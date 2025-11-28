package com.app.soap;

import javax.jws.WebService;
import javax.xml.ws.WebServiceException;

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
        try {
            return sistema.existeUsuario(nickname);
        } catch (Exception e) {
            System.out.println("Error existeUsuario: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public boolean existeUsuarioEmail(String email) {
        try {
            return sistema.existeUsuarioEmail(email);
        } catch (Exception e) {
            System.out.println("Error existeUsuarioEmail: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public String getTipoUsuario(String nickname){
        try {
            return sistema.getTipoUsuario(nickname);
        }catch (Exception e){
            System.out.println("Error getTipoUsuario: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public boolean validarUsuario(String nickname, String password) {
        try {
            return sistema.validarUsuario(nickname, password);
        } catch (Exception e) {
            System.out.println("Error validarUsuario: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public void registrarAerolinea(DtAerolinea aerolinea) {
        try {
            sistema.registrarAerolinea(aerolinea);
        } catch (Exception e) {
            System.out.println("Error registrarAerolinea: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public void modificarAerolinea(DtAerolinea aerolinea) {
        try {
            sistema.modificarAerolinea(aerolinea);
        } catch (Exception e) {
            System.out.println("Error modificarAerolinea: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public void modificarUsuarioImagen(DtUsuario user, String urlImagen) {
        try {
            sistema.modificarUsuarioImagen(user, urlImagen);
        } catch (Exception e) {
            System.out.println("Error modificarAerolineaImagen: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public void registrarCliente(DtCliente cliente) {
        try {
            sistema.registrarCliente(cliente);
        } catch (Exception e) {
            System.out.println("Error registrarCliente: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public void modificarCliente(DtCliente cliente) {
        try {
            sistema.modificarCliente(cliente);
        } catch (Exception e) {
            System.out.println("Error modificarCliente: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public DtUsuario getUsuario(String nickname) {
        try {
            return sistema.getUsuario(nickname);
        } catch (Exception e) {
            System.out.println("Error getUsuario: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public DtCliente getCliente(String nickname) {
        try {
            return sistema.getCliente(nickname);
        } catch (Exception e) {
            System.out.println("Error getCliente: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public DtAerolinea getAerolinea(String nickname) {
        try {
            return sistema.getAerolinea(nickname);
        } catch (Exception e) {
            System.out.println("Error getAerolinea: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public List<DtUsuario> listarUsuarios() {
        try {
            return sistema.listarUsuarios();
        } catch (Exception e) {
            System.out.println("Error listarUsuarios: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public List<DtAerolinea> listarAerolineas() {
        try {
            return sistema.listarAerolineas();
        } catch (Exception e) {
            System.out.println("Error listarAerolineas: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public void seguirUsuario(String usuarioSeguidor, String usuarioASeguir) {
        try {
            sistema.seguirUsuario(usuarioSeguidor, usuarioASeguir);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error seguirUsuario: " + e.getMessage());
            throw new WebServiceException("Ha ocurrido un error al seguir el usuario.");
        }
    }

    @Override
    public void dejarDeSeguirUsuario(String usuarioSeguidor, String usuarioASeguir) {
        try {
            sistema.dejarDeSeguirUsuario(usuarioSeguidor, usuarioASeguir);
        } catch (Exception e) {
            System.out.println("Error dejarDeSeguirUsuario: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

// ------------ RESERVAS ----------- //

    @Override
    public void altaReserva(DtReserva reserva) {
        try {
            sistema.altaReserva(reserva);
        } catch (Exception e) {
            System.out.println("Error altaReserva: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public DtReserva getReservaCliente(DtVuelo vuelo, DtCliente cliente, String fechaReserva) {
        try {
            return sistema.getReservaCliente(vuelo, cliente, LocalDate.parse(fechaReserva));
        } catch (Exception e) {
            System.out.println("Error getReservaCliente: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public DtReserva getReservaAerolinea(DtVuelo vuelo, DtAerolinea aerolinea, String fechaReserva) {
        try {
            return sistema.getReservaAerolinea(vuelo, aerolinea, LocalDate.parse(fechaReserva));
        } catch (Exception e) {
            System.out.println("Error getReservaAerolinea: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public List<DtReserva> listarReservasCliente(String nickname) {
        try {
            return sistema.listarReservasCliente(nickname);
        } catch (Exception e) {
            System.out.println("Error listarReservasCliente: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public List<DtReserva> listarReservasAerolinea(String nickname) {
        try {
            return sistema.listarReservasAerolinea(nickname);
        } catch (Exception e) {
            System.out.println("Error listarReservasAerolinea: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public List<DtReserva> listarReservasClienteVuelo(DtCliente cliente, DtVuelo vuelo) {
        try {
            return sistema.listarReservasClienteVuelo(cliente, vuelo);
        } catch (Exception e) {
            System.out.println("Error listarReservasClienteVuelo: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public DtReserva realizarCheckin(DtVuelo vuelo, String fechaReserva, String nicknameCliente) {
        try {
            return sistema.realizarCheckin(vuelo, LocalDate.parse(fechaReserva), nicknameCliente);
        } catch (Exception e) {
            System.out.println("Error realizarCheckin: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public byte[] crearPDFReserva(DtReserva reserva) {
        try {
            return sistema.crearPDFReserva(reserva);
        } catch (Exception e) {
            System.out.println("Error crearPDFReserva: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

// ---------- RUTAS DE VUELO ---------- //

    @Override
    public void finalizarRuta(DtRuta ruta) {
        try {
            sistema.finalizarRuta(ruta);
        } catch (Exception e) {
            System.out.println("Error finalizarRuta: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public boolean puedeFinalizar(String nombreRuta) {
        try {
            return sistema.puedeFinalizar(nombreRuta);
        } catch (Exception e) {
            System.out.println("Error puedeFinalizar: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public boolean rutaContieneCategoria(DtRuta ruta, String categoria) {
        try {
            return sistema.rutaContieneCategoria(ruta, categoria);
        } catch (Exception e) {
            System.out.println("Error rutaContieneCategoria: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public boolean existeRuta(String nombre) {
        try {
            return sistema.existeRuta(nombre);
        } catch (Exception e) {
            System.out.println("Error existeRuta: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public void altaRutaDeVuelo(String nickname, DtRuta datosRuta) {
        try {
            sistema.altaRutaDeVuelo(nickname, datosRuta);
        } catch (Exception e) {
            System.out.println("Error altaRutaDeVuelo: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public DtRuta getRutaDeVuelo(String nombre) {
        try {
            return sistema.getRutaDeVuelo(nombre);
        } catch (Exception e) {
            System.out.println("Error getRutaDeVuelo: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public List<DtRuta> buscarRutaDeVuelos(String patron) {
        try {
            return sistema.buscarRutaDeVuelos(patron);
        } catch (Exception e) {
            System.out.println("Error buscarRutaDeVuelos: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public List<DtRuta> listarRutasDeVuelo() {
        try {
            return sistema.listarRutasDeVuelo();
        } catch (Exception e) {
            System.out.println("Error listarRutasDeVuelo: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public void aumentarVisita(String nombre) {
        try {
            sistema.aumentarVisita(nombre);
        } catch (Exception e) {
            System.out.println("Error aumentarVisita: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

// ---------- VUELOS ---------- //

    @Override
    public boolean existeVuelo(String nombre) {
        try {
            return sistema.existeVuelo(nombre);
        } catch (Exception e) {
            System.out.println("Error existeVuelo: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public void altaVuelo(DtVuelo vuelo) {
        try {
            sistema.altaVuelo(vuelo);
        } catch (Exception e) {
            System.out.println("Error altaVuelo: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public DtVuelo getVuelo(String nombre) {
        try {
            return sistema.getVuelo(nombre);
        } catch (Exception e) {
            System.out.println("Error getVuelo: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public List<DtVuelo> listarVuelosRuta(String nombreRuta) {
        try {
            return sistema.listarVuelosRuta(nombreRuta);
        } catch (Exception e) {
            System.out.println("Error listarVuelosRuta: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public List<DtVuelo> listarVuelosRutaFecha(String nombreRuta, String fecha) {
        try {
            return sistema.listarVuelosRutaFecha(nombreRuta, LocalDate.parse(fecha));
        } catch (Exception e) {
            System.out.println("Error listarVuelosRutaFecha: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public List<DtVuelo> getVuelosRutaDeVuelo(DtRuta ruta) {
        try {
            return sistema.getVuelosRutaDeVuelo(ruta);
        } catch (Exception e) {
            System.out.println("Error getVuelosRutaDeVuelo: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

// ---------- PAQUETES ---------- //

    @Override
    public boolean estaPaqueteComprado(DtPaquete paquete) {
        try {
            return sistema.estaPaqueteComprado(paquete);
        } catch (Exception e) {
            System.out.println("Error estaPaqueteComprado: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public boolean existePaquete(String nombre) {
        try {
            return sistema.existePaquete(nombre);
        } catch (Exception e) {
            System.out.println("Error existePaquete: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public void altaPaquete(DtPaquete paquete) {
        try {
            sistema.altaPaquete(paquete);
        } catch (Exception e) {
            System.out.println("Error altaPaquete: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public DtPaquete getPaquete(String nombre) {
        try {
            return sistema.getPaquete(nombre);
        } catch (Exception e) {
            System.out.println("Error getPaquete: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public List<DtPaquete> buscarPaquetes(String patron) {
        try {
            return sistema.buscarPaquetes(patron);
        } catch (Exception e) {
            System.out.println("Error buscarPaquetes: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public List<DtPaquete> listarPaquetes() {
        try {
            return sistema.listarPaquetes();
        } catch (Exception e) {
            System.out.println("Error listarPaquetes: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public List<DtPaquete> listarPaquetesCliente(String nickname) {
        try {
            return sistema.listarPaquetesCliente(nickname);
        } catch (Exception e) {
            System.out.println("Error listarPaquetesCliente: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public List<DtPaquete> listarPaquetesAerolinea(String nickname) {
        try {
            return sistema.listarPaquetesAerolinea(nickname);
        } catch (Exception e) {
            System.out.println("Error listarPaquetesAerolinea: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public List<DtPaquete> listarPaquetesNoComprados() {
        try {
            return sistema.listarPaquetesNoComprados();
        } catch (Exception e) {
            System.out.println("Error listarPaquetesNoComprados: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

// ---------- CATEGORIAS ---------- //

    @Override
    public List<DtCategoria> listarCategorias() {
        try {
            return sistema.listarCategorias();
        } catch (Exception e) {
            System.out.println("Error listarCategorias: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public List<DtCategoria> buscarCategorias() {
        try {
            return sistema.buscarCategorias();
        } catch (Exception e) {
            System.out.println("Error buscarCategorias: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public List<DtCategoria> buscarCategoriasPorNombre(List<String> nombres) {
        try {
            return sistema.buscarCategoriasPorNombre(nombres);
        } catch (Exception e) {
            System.out.println("Error buscarCategoriasPorNombre: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

// ---------- CIUDADES ---------- //

    @Override
    public void altaCiudad(DtCiudad ciudad) {
        try {
            sistema.altaCiudad(ciudad);
        } catch (Exception e) {
            System.out.println("Error altaCiudad: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public DtCiudad getCiudad(String nombre, String pais) {
        try {
            return sistema.getCiudad(nombre, pais);
        } catch (Exception e) {
            System.out.println("Error getCiudad: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public List<DtCiudad> listarCiudades() {
        try {
            return sistema.listarCiudades();
        } catch (Exception e) {
            System.out.println("Error listarCiudades: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    // ---------- COMPRAS ---------- //

    @Override
    public void compraPaquete(DtPaquete paquete, DtCliente cliente) {
        try {
            sistema.compraPaquete(paquete, cliente);
        } catch (Exception e) {
            System.out.println("Error compraPaquete: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }

    // -------- Auxiliar --------- //
    @Override
    public String guardarImagen(byte[] data, TipoImagen tipoImagen){
        try{
            System.out.println("Subiendo imagen para un/a " + tipoImagen.toString());

            String extension = sistema.detectarExtension(data);
            File tempFile = File.createTempFile("upload_", extension);
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(data);
            }
            File imagenGuardada = AuxiliarFunctions.guardarImagen(tempFile, tipoImagen);
            System.out.println("Se ha guardado la imágen con éxito");
            return imagenGuardada.getName();
        }catch(Exception e){
            throw new WebServiceException("Ha ocurrido un error al subir la imagen: " + e.getMessage());
        }
    }

    @Override
    public byte[] obtenerImagen(String nombre, TipoImagen tipo){
        try {
            return AuxiliarFunctions.obtenerImagen(nombre, tipo);
        } catch (Exception e) {
            System.out.println("Error obtenerImagen: " + e.getMessage());
            throw new WebServiceException(e.getMessage());
        }
    }


}