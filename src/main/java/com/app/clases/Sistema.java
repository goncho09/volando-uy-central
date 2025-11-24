package com.app.clases;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Collections;

import com.app.enums.TipoImagen;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import com.app.daos.UserDao;
import com.app.daos.CategoriaDao;
import com.app.daos.CiudadDao;
import com.app.daos.PaqueteDao;
import com.app.daos.VueloDao;
import com.app.daos.ReservaDao;
import com.app.daos.PasajerosDao;
import com.app.daos.CompraPaqueteDao;
import com.app.daos.RutaDeVueloDao;

import com.app.datatypes.DtPasajero;
import com.app.datatypes.DtCategoria;
import com.app.datatypes.DtVuelo;
import com.app.datatypes.DtRuta;
import com.app.datatypes.DtUsuario;
import com.app.datatypes.DtReserva;
import com.app.datatypes.DtPaquete;
import com.app.datatypes.DtCliente;
import com.app.datatypes.DtAerolinea;
import com.app.datatypes.DtCiudad;
import com.app.datatypes.DtCompraPaquete;
import com.app.datatypes.DtRutaEnPaquete;

import com.app.enums.EstadoRuta;
import com.app.enums.MetodoPago;
import com.app.enums.TipoAsiento;

import com.app.utils.AuxiliarFunctions;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;


public class Sistema implements ISistema {
    private static Sistema instancia;

    final private EntityManager em;
    final private UserDao userDao;
    final private RutaDeVueloDao rutaDeVueloDao;
    final private CategoriaDao categoriaDao;
    final private CiudadDao ciudadDao;
    final private PaqueteDao paqueteDao;
    final private VueloDao vueloDao;
    final private ReservaDao reservaDao;
    final private PasajerosDao pasajeroDao;
    final private CompraPaqueteDao compraDao;

    final private AuxiliarFunctions auxiliar;

    private Sistema() {
        // Inicializar JPA
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pepitoElLaburador");
        this.em = emf.createEntityManager(); // Inicializamos el "controlador" global de la BD
        this.auxiliar = new AuxiliarFunctions(this);
        this.userDao = new UserDao(em);
        this.rutaDeVueloDao = new RutaDeVueloDao(em);
        this.categoriaDao = new CategoriaDao(em);
        this.ciudadDao = new CiudadDao(em);
        this.paqueteDao = new PaqueteDao(em);
        this.vueloDao = new VueloDao(em);
        this.reservaDao = new ReservaDao(em);
        this.pasajeroDao = new PasajerosDao(em);
        this.compraDao = new CompraPaqueteDao(em);
    }

    public static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    public boolean existeUsuario(String nickname) {
        return this.userDao.existe(nickname);
    }

    public List<DtAerolinea> listarAerolineas() {
        List<Aerolinea> aerolineas = this.userDao.listarAerolineas();
        List<DtAerolinea> listaAerolineas = new ArrayList<>();
        for (Aerolinea a : aerolineas) {
            listaAerolineas.add(a.getDatos());
        }
        return listaAerolineas;
    }


    public DtUsuario getUsuario(String nickname) {
        Usuario u = this.userDao.buscar(nickname);
        if (u != null) {
            return u.getDatos();
        }
        throw new IllegalArgumentException("No existe un usuario con ese nickname.");
    }

    public List<DtReserva> listarReservasClienteVuelo(DtCliente cliente, DtVuelo vuelo) {
        List<DtReserva> listaReservas = new ArrayList<>();
        Cliente c = this.userDao.buscarCliente(cliente.getNickname());
        if (c == null) {
            return listaReservas;
        }
        for (Reserva r : c.getReservas()) {
            if (r.getVuelo().getNombre().equals(vuelo.getNombre())) {
                listaReservas.add(r.getDatos());
            }
        }
        return listaReservas;
    }

    public boolean aerolineaTieneRuta(DtAerolinea aerolinea, String nombre) {
        Aerolinea a = this.userDao.buscarAerolinea(aerolinea.getNickname());
        if (a == null) {
            return false;
        }
        for (RutaDeVuelo r : a.getRutasDeVuelo()) {
            if (r.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public List<DtReserva> listarReservasAerolinea(String nickname) {
        List<DtReserva> listaReservas = new ArrayList<>();
        Aerolinea a = this.userDao.buscarAerolinea(nickname);
        if (a == null) {
            return listaReservas;
        }

        for (Reserva r : this.reservaDao.listar()) {
            if (this.aerolineaTieneRuta(a.getDatos(), r.getVuelo().getRutaDeVuelo().getNombre())) {
                listaReservas.add(r.getDatos());
            }
        }
        return listaReservas;
    }

    public DtCliente getCliente(String nickname) {
        Cliente c = this.userDao.buscarCliente(nickname);
        if (c == null) {
            throw new IllegalArgumentException("No existe un usuario con ese nickname.");
        }
        return c.getDatos();
    }

    public boolean existeRuta(String nombre) {
        return this.rutaDeVueloDao.existe(nombre);
    }

    public void altaRutaDeVuelo(String nombreAerolinea, DtRuta datosRuta) {
        if (nombreAerolinea == null || nombreAerolinea.isEmpty())
            throw new IllegalArgumentException("Aerolínea no puede ser vacía");

        if (datosRuta.getNombre() == null || datosRuta.getNombre().isEmpty())
            throw new IllegalArgumentException("Nombre de ruta no puede ser vacío");

        if (LocalTime.parse(datosRuta.getDuracion()).isBefore(LocalTime.of(0, 1))) {
            throw new IllegalArgumentException("La duración debe ser > 0 minutos");
        }

        if (this.rutaDeVueloDao.existe(datosRuta.getNombre()))
            throw new IllegalArgumentException("Ya existe esa ruta de vuelo.");

        if (datosRuta.getCategorias() == null || datosRuta.getCategorias().isEmpty()) {
            throw new IllegalArgumentException("Debe tener al menos una categoria");
        }

        if (datosRuta.getCostoTurista() <= 0 || datosRuta.getCostoEjecutivo() <= 0
                || datosRuta.getEquipajeExtra() < 0) {
            throw new IllegalArgumentException("Costos no pueden ser negativos o 0");
        }

        // VALIDACIÓN DE URL DE VIDEO
        if (datosRuta.getUrlVideo() != null && !datosRuta.getUrlVideo().trim().isEmpty()) {
            if (!esUrlValida(datosRuta.getUrlVideo())) {
                throw new IllegalArgumentException("La URL del video no es válida");
            }
        }

        Aerolinea aerolinea = userDao.buscarAerolinea(nombreAerolinea);

        List<Categoria> categoriaList = new ArrayList<>();

        for (DtCategoria c : datosRuta.getCategorias()) {
            Categoria nuevaCategoria = this.categoriaDao.buscar(c.getNombre());
            if (nuevaCategoria == null) {
                throw new IllegalArgumentException("Categoria no existe");
            }
            categoriaList.add(nuevaCategoria);
        }


        Ciudad origen = this.ciudadDao.buscar(new CiudadId(datosRuta.getCiudadOrigen().getNombre(),
                datosRuta.getCiudadOrigen().getPais()));
        if (origen == null) {
            throw new IllegalArgumentException("Ciudad Origen no existe");
        }

        Ciudad destino = this.ciudadDao.buscar(new CiudadId(datosRuta.getCiudadDestino().getNombre(),
                datosRuta.getCiudadDestino().getPais()));
        if (destino == null || destino == origen) {
            throw new IllegalArgumentException("Ciudad Destino no existe o es igual a la de Origen");
        }

        RutaDeVuelo nuevaRuta = new RutaDeVuelo(datosRuta, categoriaList, origen, destino);

        nuevaRuta.setUrlVideo(datosRuta.getUrlVideo());

        if (aerolinea.getRutasDeVuelo().contains(nuevaRuta))
            throw new IllegalArgumentException("Ya existe esa ruta de vuelo en esa aerolínea");

        this.rutaDeVueloDao.guardar(nuevaRuta); // Persistimos la nuevaRuta
        this.userDao.addRutaDeVuelo(aerolinea, nuevaRuta); // La agregamos a su aerolinea
    }

    public boolean validarUsuario(String nickname, String password) {
        Usuario u = this.userDao.buscar(nickname);
        if (u == null)
            return false;
        return u.getPassword().equals(password);
    }

    public List<DtUsuario> listarUsuarios() {
        List<DtUsuario> listaUsuarios = new ArrayList<>();
        for (Usuario u : this.userDao.listar()) {
            System.out.println(u.getDatos());
            listaUsuarios.add(u.getDatos());
        }
        return listaUsuarios;
    }

    public List<DtCiudad> listarCiudades() {
        List<DtCiudad> listaCiudades = new ArrayList<>();
        for (Ciudad c : this.ciudadDao.listar()) {
            listaCiudades.add(c.getDatos());
        }
        return listaCiudades;
    }

    public List<DtPaquete> listarPaquetes() {
        List<DtPaquete> listaPaquetes = new ArrayList<>();
        for (Paquete p : this.paqueteDao.listar()) {
            listaPaquetes.add(p.getDatos());
        }
        return listaPaquetes;
    }

    public List<DtPaquete> listarPaquetesCliente(String nickname) {
        Cliente cliente = this.userDao.buscarCliente(nickname);
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado");
        }
        List<DtPaquete> listaPaquetes = new ArrayList<>();
        for (CompraPaquete cp : cliente.getComprasPaquetes()) {
            listaPaquetes.add(cp.getPaquete().getDatos());
        }
        return listaPaquetes;
    }

    public List<DtPaquete> listarPaquetesAerolinea(String nickname) {
        Aerolinea aerolinea = this.buscarAerolinea(nickname);
        if (aerolinea == null) {
            throw new RuntimeException("Aerolínea no encontrada");
        }

        List<DtRuta> rutasAerolinea = aerolinea.getRutasDeVueloDatos();
        if (rutasAerolinea == null || rutasAerolinea.isEmpty()) {
            return Collections.emptyList();
        }

        // Conjunto de nombres de rutas de la aerolínea para búsqueda rápida
        Set<String> nombresRutas = rutasAerolinea.stream()
                .map(DtRuta::getNombre)
                .collect(Collectors.toSet());

        // Filtrar paquetes que contengan alguna de esas rutas
        return this.listarPaquetes().stream()
                .filter(p -> p.getRutaEnPaquete().stream()
                        .anyMatch(rp -> nombresRutas.contains(rp.getRutaDeVuelo().getNombre())))
                .collect(Collectors.toList());
    }

    public List<DtPaquete> listarPaquetesNoComprados() {
        List<DtPaquete> listaPaquetes = new ArrayList<>();
        for (Paquete p : this.paqueteDao.listar()) {
            if (!estaPaqueteComprado(p.getDatos())) {
                listaPaquetes.add(p.getDatos());
            }
        }
        return listaPaquetes;

    }

    public List<DtPaquete> listarPaquetesConRutas() {
        List<DtPaquete> listaPaquetes = new ArrayList<>();
        for (Paquete p : this.paqueteDao.listar()) {
            if (!p.getRutaEnPaquete().isEmpty()) {
                listaPaquetes.add(p.getDatos());
            }
        }
        return listaPaquetes;
    }

    public List<DtCliente> listarClientes() {
        List<DtCliente> listaClientes = new ArrayList<>();
        for (Cliente c : this.userDao.listarClientes()) {
            listaClientes.add(c.getDatos());
        }
        return listaClientes;
    }


    public List<DtRuta> listarRutasDeVuelo() {
        List<DtRuta> listaRutas = new ArrayList<>();
        for (RutaDeVuelo r : this.rutaDeVueloDao.listar()) {
            listaRutas.add(r.getDatos());
        }
        return listaRutas;
    }

    public List<DtRuta> listarRutasDeVueloPaquete(String nombrePaquete) {
        Paquete paquete = this.paqueteDao.buscar(nombrePaquete);
        if (paquete == null) {
            throw new IllegalArgumentException("No existe un paquete con ese nombre.");
        }
        List<DtRuta> listaRutas = new ArrayList<>();

        List<DtRutaEnPaquete> rutasDelPaquete = paquete.getRutaEnPaqueteDatos();
        for (DtRutaEnPaquete r : rutasDelPaquete) {
            listaRutas.add(r.getRutaDeVuelo());
        }
        return listaRutas;
    }


    public void altaPaquete(DtPaquete paquete) {
        if (paquete.getNombre() == null || paquete.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        if (paquete.getValidezDias() <= 0) {
            throw new IllegalArgumentException("La validez del paquete debe ser mayor a 0");
        }

        if (this.paqueteDao.existe(paquete.getNombre())) {
            throw new IllegalArgumentException("Ya existe un paquete con ese nombre.");
        }


        Paquete p = new Paquete(paquete);
        paqueteDao.guardar(p);
    }

    public boolean estaPaqueteComprado(DtPaquete paquete) {
        boolean esComprado = false;
        for (CompraPaquete cp : this.compraDao.listar()) {
            if (cp.getPaquete().getNombre().equals(paquete.getNombre())) {
                esComprado = true;
                break;
            }
        }
        return esComprado;
    }

    public boolean existePaquete(String nombre) {
        return this.paqueteDao.existe(nombre);
    }

    public boolean existeUsuarioEmail(String email) {
        for (Usuario u : this.userDao.listar()) {
            if (u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private boolean esUrlValida(String url) {
        if (url == null || url.trim().isEmpty()) {
            return true; // URL vacía es válida (campo opcional)
        }
        try {
            new java.net.URL(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void altaCategoria(DtCategoria categoria) {
        if (categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío");
        }

        // Primera validación: buscar en DAO
        if (categoriaDao.existe(categoria.getNombre())) {
            throw new IllegalArgumentException("Ya existe una categoría con ese nombre.");
        }

        Categoria c = new Categoria(categoria);
        categoriaDao.guardar(c);
    }

    public void altaCiudad(DtCiudad ciudad) {
        CiudadId id = new CiudadId(ciudad.getNombre(), ciudad.getPais());

        if (ciudad.getNombre() == null || ciudad.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        if (ciudad.getPais() == null || ciudad.getPais().trim().isEmpty()) {
            throw new IllegalArgumentException("El país no puede estar vacío");
        }

        if (ciudadDao.existe(id)) {
            throw new IllegalArgumentException("Ya existe una ciudad con ese nombre en ese país.");
        }

        Ciudad c = new Ciudad(ciudad);
        ciudadDao.guardar(c);
    }

    public List<DtCategoria> listarCategorias() {
        List<DtCategoria> categorias = new ArrayList<>();
        for (Categoria c : this.categoriaDao.listar()) {
            categorias.add(c.getDatos());
        }
        return categorias;
    }

    public List<DtCategoria> buscarCategorias() {
        List<DtCategoria> categoriaList = new ArrayList<>();
        for (Categoria c : categoriaDao.listar()) {
            categoriaList.add(c.getDatos());
        }
        return categoriaList;
    }

    public void registrarCliente(DtCliente cliente) {
        if (userDao.existeCliente(cliente.getNickname())) {
            throw new IllegalArgumentException("Este usuario ya existe.");
        }

        if (existeUsuarioEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email.");
        }

        if (cliente.getNombre().isEmpty() || cliente.getApellido().isEmpty() || cliente.getNickname().isEmpty()
                || cliente.getEmail().isEmpty() || cliente.getPassword().isEmpty()
                || cliente.getNacionalidad().isEmpty() || cliente.getNumeroDocumento() == 0
                || cliente.getUrlImage().isEmpty()) {
            throw new IllegalArgumentException("Los campos no pueden estar vacíos.");
        }

        Cliente clienteNuevo = new Cliente(cliente);
        userDao.guardar(clienteNuevo);
    }

    public void modificarCliente(DtCliente cliente) {
        Cliente c = this.userDao.buscarCliente(cliente.getNickname());
        if (c == null) {
            throw new IllegalArgumentException("Este cliente no existe.");
        }

        if (cliente.getNickname().isEmpty() || cliente.getEmail().isEmpty() || cliente.getNombre().isEmpty()
                || cliente.getApellido().isEmpty() || cliente.getNacionalidad().isEmpty() || cliente.getNumeroDocumento() == 0) {
            throw new IllegalArgumentException("Los campos no pueden estar vacíos.");
        }


        this.auxiliar.validarTextoSoloLetra(cliente.getNombre());
        this.auxiliar.validarTextoSoloLetra(cliente.getApellido());
        this.auxiliar.validarTextoSoloLetra(cliente.getNacionalidad());
        LocalDate fechaNac = LocalDate.parse(cliente.getFechaNacimiento());

        if (fechaNac.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser en el futuro.");
        }

        c.setUrlImage(cliente.getUrlImage());
        c.setNombre(cliente.getNombre());
        c.setApellido(cliente.getApellido());
        c.setFechaNacimiento(fechaNac);
        c.setNacionalidad(cliente.getNacionalidad());
        c.setTipoDocumento(cliente.getTipoDocumento());
        c.setNumeroDocumento(cliente.getNumeroDocumento());
        userDao.actualizar(c);
    }

    public DtAerolinea getAerolinea(String nickname) {
        Aerolinea a = this.userDao.buscarAerolinea(nickname);
        if (a == null) {
            throw new IllegalArgumentException("No existe un usuario con ese nickname. [" + nickname + "]");
        }
        return a.getDatos();
    }

    public void registrarAerolinea(DtAerolinea aerolinea) {
        if (this.userDao.existeAerolinea(aerolinea.getNickname())) {
            throw new IllegalArgumentException("Este usuario ya existe.");
        }
        if (existeUsuarioEmail(aerolinea.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email.");
        }
        if (aerolinea.getDescripcion().isEmpty() || aerolinea.getNombre().isEmpty()
                || aerolinea.getNickname().isEmpty() || aerolinea.getEmail().isEmpty()
                || aerolinea.getPassword() == null || aerolinea.getPassword().isEmpty()
                || aerolinea.getUrlImage().isEmpty()) {
            throw new IllegalArgumentException("Los campos no pueden estar vacíos.");
        }

        Aerolinea nuevaAerolinea = new Aerolinea(aerolinea);
        userDao.guardar(nuevaAerolinea);
    }

    public void modificarAerolinea(DtAerolinea aerolinea) {
        Aerolinea aerolineaExistente = this.userDao.buscarAerolinea(aerolinea.getNickname());

        if (aerolineaExistente == null) {
            throw new IllegalArgumentException("Este usuario no existe.");
        }

        this.auxiliar.validarTextoSoloLetra(aerolinea.getNombre());

        if (aerolinea.getNickname().isEmpty() || aerolinea.getDescripcion().isEmpty() || aerolinea.getEmail().isEmpty()
                || aerolinea.getNombre().isEmpty()) {
            throw new IllegalArgumentException("Los campos no pueden estar vacíos.");
        }

        aerolineaExistente.setNombre(aerolinea.getNombre());
        aerolineaExistente.setUrlImage(aerolinea.getUrlImage());
        aerolineaExistente.setDescripcion(aerolinea.getDescripcion());
        aerolineaExistente.setLinkWeb(aerolinea.getLinkWeb());
        userDao.actualizar(aerolineaExistente);
    }

    public void modificarUsuarioImagen(DtUsuario user, String urlImagen) {
        Usuario u = this.userDao.buscar(user.getNickname());
        if (u == null) {
            throw new IllegalArgumentException("Este usuario no existe.");
        }
        System.out.println("Borrando la imagen: " + u.getUrlImage());
        AuxiliarFunctions.borrarImagen(u.getUrlImage(), TipoImagen.USUARIO);
        System.out.println("Seteando la imagen: " + urlImagen);
        u.setUrlImage(urlImagen);
        userDao.actualizar(u);
    }

    public List<DtCategoria> buscarCategoriasPorNombre(List<String> nombres) {
        List<DtCategoria> categoriasSeleccionadas = new ArrayList<>();
        for (String nombreCategoria : nombres) {
            for (Categoria c : this.categoriaDao.listar()) {
                if (c.getNombre().equals(nombreCategoria)) {
                    categoriasSeleccionadas.add(c.getDatos());
                    break;
                }
            }
        }
        return categoriasSeleccionadas;
    }

    public DtCiudad getCiudad(String nombre, String pais) {
        Ciudad c = this.ciudadDao.buscar(new CiudadId(nombre, pais));
        if (c == null) {
            throw new IllegalArgumentException("La ciudad no existe.");
        }
        return c.getDatos();
    }

    public DtReserva getReservaCliente(DtVuelo vuelo, DtCliente cliente, LocalDate fechaReserva) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no existe");
        }
        Cliente c = this.userDao.buscarCliente(cliente.getNickname());
        for (Reserva r : c.getReservas()) {
            if (r.getVuelo().getNombre().equals(vuelo.getNombre()) && r.getFecha().equals(fechaReserva)) {
                return r.getDatos();
            }
        }
        throw new IllegalArgumentException("No existe esta reserva para el cliente y vuelo indicados.");
    }

    public DtReserva getReservaAerolinea(DtVuelo vuelo, DtAerolinea aerolinea, LocalDate fechaReserva) {
        if (aerolinea == null) {
            throw new IllegalArgumentException("La aerolinea no existe.");
        }
        List<DtReserva> reservasAerolinea = this.listarReservasAerolinea(aerolinea.getNickname());
        for (DtReserva r : reservasAerolinea) {
            if (r.getVuelo().getNombre().equals(vuelo.getNombre()) && r.getFecha().equals(fechaReserva)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No existe esta reserva para la aerolínea y vuelo indicados.");
    }

    // ---------- ALTA DE VUELO ---------- //

    public boolean existeVuelo(String nombre) {
        return this.vueloDao.existe(nombre);
    }

    public DtRuta getRutaDeVuelo(String nombre) {
        RutaDeVuelo ruta = this.rutaDeVueloDao.buscar(nombre);
        if (ruta == null) {
            throw new IllegalArgumentException("No existe una ruta de vuelo con ese nombre.");
        }
        return ruta.getDatos();
    }

    public void altaVuelo(DtVuelo vuelo) {
        if (vuelo == null || vuelo.getNombre().isEmpty()) {
            throw new IllegalArgumentException("Ha ocurrido un error.");
        }
        if (this.existeVuelo(vuelo.getNombre())) {
            throw new IllegalArgumentException("Ya existe un vuelo con ese nombre.");
        }
        if (vuelo.getDuracion().equals(LocalTime.of(0, 0))) {
            throw new IllegalArgumentException("La duración debe ser mayor a 0.");
        }

        RutaDeVuelo ruta = this.buscarRutaDeVuelo(vuelo.getRutaDeVuelo().getNombre());

        if (ruta == null || ruta.getEstado() != EstadoRuta.APROBADA) {
            throw new IllegalArgumentException("Debe seleccionar una ruta válida");
        }

        Vuelo nuevo = new Vuelo(vuelo, ruta);

        this.vueloDao.guardar(nuevo);
    }

    public DtVuelo getVuelo(String nombre) {
        Vuelo vuelo = this.vueloDao.buscar(nombre);
        if (vuelo == null) {
            throw new IllegalArgumentException("No existe un vuelo con ese nombre.");
        }
        return vuelo.getDatos();
    }

    // ---------- COMPRA PAQUETE ---------- //
    public Cliente buscarCliente(String nickname) {
        Cliente c = this.userDao.buscarCliente(nickname);
        if (c == null) {
            throw new IllegalArgumentException("El cliente no existe");
        }
        return c;
    }

    public Paquete buscarPaquete(String nombre) {
        Paquete p = this.paqueteDao.buscar(nombre);
        if (p == null) {
            throw new IllegalArgumentException("El paquete no existe");
        }
        return p;
    }

    public RutaDeVuelo buscarRutaDeVuelo(String nombre) {
        RutaDeVuelo r = this.rutaDeVueloDao.buscar(nombre);
        if (r == null) {
            throw new IllegalArgumentException("La ruta de vuelo no existe");
        }
        return r;
    }

    public void compraPaquete(DtPaquete paquete, DtCliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        if (paquete == null) {
            throw new IllegalArgumentException("El paquete no puede ser nulo");
        }

        Cliente c = this.userDao.buscarCliente(cliente.getNickname());
        Paquete p = this.paqueteDao.buscar(paquete.getNombre());

        if (c == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        if (p == null) {
            throw new IllegalArgumentException("El paquete no puede ser nulo");
        }

        for (CompraPaquete cp : c.getComprasPaquetes()) {
            if (cp.getPaquete().equals(p)) {
                throw new IllegalArgumentException("El cliente ya ha comprado este paquete.");
            }
        }

        LocalDate vencimiento = LocalDate.now().plusDays(p.getValidezDias());

        CompraPaquete nuevaCompra = new CompraPaquete(new DtCompraPaquete(LocalDate.now().toString(), vencimiento.toString(), p.getCosto()),
                p, c);

        userDao.addCompraPaquete(c, nuevaCompra);
    }

    public int agregarRutaAPaquete(DtPaquete paquete, DtRuta dataRuta, int cantidad, TipoAsiento tipoAsiento) {
        // Validar parámetros nulos
        if (paquete == null || dataRuta == null || cantidad <= 0 || tipoAsiento == null) {
            throw new IllegalArgumentException("Parámetros inválidos.");
        }
        // Validar que el paquete exista
        Paquete p = this.paqueteDao.buscar(paquete.getNombre());

        if (p == null) {
            throw new IllegalArgumentException("El paquete no existe.");
        }

        // Validar que la ruta existe y está aprobada
        RutaDeVuelo ruta = this.rutaDeVueloDao.buscar(dataRuta.getNombre());

        if (ruta == null) {
            throw new IllegalArgumentException("La ruta no existe.");
        }
        if (ruta.getEstado() != EstadoRuta.APROBADA) {
            throw new IllegalArgumentException("La ruta no está aprobada aún.");
        }

        float nuevoCosto = p.getCosto();

        if (tipoAsiento == TipoAsiento.EJECUTIVO) {
            nuevoCosto += ruta.getCostoEjecutivo() * cantidad;
        } else if (tipoAsiento == TipoAsiento.TURISTA) {
            nuevoCosto += ruta.getCostoTurista() * cantidad;
        }

        for (DtRutaEnPaquete rep : p.getRutaEnPaqueteDatos()) {
            if (rep.getRutaDeVuelo().getNombre().equals(ruta.getNombre()) && rep.getTipoAsiento() == tipoAsiento) {
                p.setCosto(nuevoCosto);
                System.out.println(nuevoCosto);
                return paqueteDao.actualizarCantidadRutaEnPaquete(p, rep, cantidad);
            }
        }

        RutaEnPaquete rp = new RutaEnPaquete(cantidad, tipoAsiento, ruta);
        p.setCosto(nuevoCosto);

        paqueteDao.addRutaEnPaquete(p, rp);

        return 0;
    }

    public List<DtVuelo> getVuelosRutaDeVuelo(DtRuta ruta) {
        List<DtVuelo> vuelos = new ArrayList<>();

        for (Vuelo v : this.vueloDao.listar()) {
            if (v.getRutaDeVuelo().getNombre().equals(ruta.getNombre())) {
                vuelos.add(v.getDatos());
            }
        }
        return vuelos;
    }

    public DtPaquete getPaquete(String nombre) {
        Paquete p = this.paqueteDao.buscar(nombre);
        if (p == null) {
            throw new IllegalArgumentException("El paquete no existe");
        }
        return p.getDatos();
    }

    public List<DtVuelo> listarVuelos() {
        List<DtVuelo> vuelos = new ArrayList<>();
        for (Vuelo v : this.vueloDao.listar()) {
            vuelos.add(v.getDatos());
        }
        return vuelos;
    }

    public List<DtVuelo> listarVuelosRuta(String nombreRuta) {
        List<DtVuelo> vuelos = new ArrayList<>();
        for (Vuelo v : this.vueloDao.listar()) {
            if (v.getRutaDeVuelo().getNombre().equals(nombreRuta) && v.getFecha().isAfter(LocalDate.now())) {
                vuelos.add(v.getDatos());
            }
        }
        return vuelos;
    }

    public List<DtVuelo> listarVuelosRutaFecha(String nombreRuta, LocalDate fecha) {
        List<DtVuelo> vuelos = new ArrayList<>();
        for (Vuelo v : this.vueloDao.listar()) {
            boolean condicionFecha = fecha == null || v.getFecha().isAfter(fecha);
            if (v.getRutaDeVuelo().getNombre().equals(nombreRuta) && v.getFecha().isAfter(LocalDate.now()) && condicionFecha) {
                vuelos.add(v.getDatos());
            }
        }
        return vuelos;
    }

    public Vuelo buscarVuelo(String nombre) {
        Vuelo v = this.vueloDao.buscar(nombre);
        if (v == null) {
            throw new IllegalArgumentException("El vuelo no existe.");
        }
        return v;
    }

    public void altaReserva(DtReserva reserva) {
        Cliente cliente = this.buscarCliente(reserva.getCliente().getNickname());
        Vuelo vuelo = this.buscarVuelo(reserva.getVuelo().getNombre());

        float costoAsiento = reserva.getTipoAsiento() == TipoAsiento.EJECUTIVO
                ? vuelo.getRutaDeVuelo().getCostoEjecutivo()
                : vuelo.getRutaDeVuelo().getCostoTurista();
        int cantPasajes = reserva.getCantPasajes();
        float costoEquipaje = reserva.getEquipajeExtra() * vuelo.getRutaDeVuelo().getEquipajeExtra();
        float costo = (costoAsiento * cantPasajes) + costoEquipaje;

        if (reserva.getMetodoPago() == MetodoPago.PAQUETE) {
            int pasajesRestantes = cantPasajes;
            DtPaquete paqueteCompra = reserva.getPaquetePago();

            if (paqueteCompra == null || !clienteTienePaquete(cliente.getNickname(), paqueteCompra.getNombre())) {
                throw new IllegalArgumentException("El paquete no fue comprado por el cliente o no existe.");
            }

            Paquete paq = buscarPaquete(paqueteCompra.getNombre());
            RutaEnPaquete rutaEnPaquete = null;
            for (RutaEnPaquete rep : paq.getRutaEnPaquete()) {
                if (rep.getRutaDeVuelo().getNombre().equals(vuelo.getRutaDeVuelo().getNombre())) {
                    rutaEnPaquete = rep;
                }
            }

            if (rutaEnPaquete == null) {
                throw new IllegalArgumentException("El paquete no tiene la ruta especificada.");
            }

            int descontar = Math.min(pasajesRestantes, rutaEnPaquete.getCantidad());
            rutaEnPaquete.setCantidad(rutaEnPaquete.getCantidad() - descontar);
            pasajesRestantes -= descontar;

            costo = pasajesRestantes
                    * (reserva.getTipoAsiento() == TipoAsiento.EJECUTIVO ? vuelo.getRutaDeVuelo().getCostoEjecutivo()
                    : vuelo.getRutaDeVuelo().getCostoTurista());
            costo += reserva.getEquipajeExtra() * vuelo.getRutaDeVuelo().getEquipajeExtra();
        } else {
            if (reserva.getPaquetePago() != null) {
                throw new IllegalArgumentException("Método de pago General (efectivo) y se recibe un paquetePago.");
            }
        }

        if (cliente.existeVueloReserva(vuelo)) {
            throw new IllegalArgumentException(
                    "Ya existe una reserva para este vuelo. Cambie el Cliente, Aerolínea o RutaDeVuelo.");
        }

        if (cantPasajes != reserva.getPasajeros().size()) {
            throw new IllegalArgumentException("La cantidad de pasajeros no coincide");
        }

        DtReserva r = new DtReserva(
                reserva.getFecha(),
                reserva.getTipoAsiento(),
                cantPasajes,
                reserva.getEquipajeExtra(),
                costo,
                reserva.getPasajeros(),
                cliente.getDatos(),
                vuelo.getDatos(),
                reserva.getMetodoPago(),
                reserva.getPaquetePago(),
                false
        );

        Reserva nuevaReserva = new Reserva(r, cliente, vuelo); // 'r' es DtReserva
        this.reservaDao.guardar(nuevaReserva);
        this.userDao.addReserva(cliente, nuevaReserva);

        vuelo.setCantReservas(vuelo.getCantReservas() + 1);
        this.vueloDao.actualizar(vuelo);

        for (DtPasajero p : reserva.getPasajeros()) {
            this.pasajeroDao.guardar(p);
        }

    }

    public List<DtReserva> listarReservasVuelo(String nombreVuelo) {
        Vuelo v = this.buscarVuelo(nombreVuelo);
        List<DtReserva> listaReservas = new ArrayList<>();
        for (Reserva r : this.reservaDao.listar()) {
            if (r.getVuelo().getNombre().equals(v.getNombre())) {
                listaReservas.add(r.getDatos());
            }
        }
        return listaReservas;
    }

    public List<DtReserva> listarReservasCliente(String nickname) {
        Cliente c = this.buscarCliente(nickname);
        List<DtReserva> listaReservas = new ArrayList<>();
        for (Reserva r : this.reservaDao.listar()) {
            if (r.getCliente().getNickname().equals(c.getNickname())) {
                listaReservas.add(r.getDatos());
            }
        }
        return listaReservas;
    }

    public DtReserva buscarReserva(DtReserva reserva) {
        Reserva r = this.reservaDao.buscar(
                this.userDao.buscarCliente(reserva.getCliente().getNickname()),
                this.vueloDao.buscar(reserva.getVuelo().getNombre()),
                LocalDate.parse(reserva.getFecha()));
        if (r == null) {
            throw new IllegalArgumentException("La reserva no existe");
        }
        return r.getDatos();
    }


    public List<DtPasajero> listarPasajeros(DtReserva reserva) {
        DtReserva r = this.buscarReserva(reserva);
        return r.getPasajeros();
    }


    public Aerolinea buscarAerolinea(String nickname) {
        Aerolinea aerolinea = this.userDao.buscarAerolinea(nickname);
        if (aerolinea == null) {
            throw new IllegalArgumentException("La aerolinea no existe");
        }
        return aerolinea;
    }

    public void actualizarEstadoRuta(DtRuta ruta, EstadoRuta estado) {

        RutaDeVuelo rdv = this.buscarRutaDeVuelo(ruta.getNombre());

        if (rdv.getEstado() != EstadoRuta.INGRESADA) {
            throw new IllegalArgumentException("No se puede cambiar una ruta que a sido rechazada o aprobada.");
        }

        rdv.setEstado(estado);
        rutaDeVueloDao.actualizar(rdv);
    }

    public Categoria buscarCategoria(String nombre) {
        return this.categoriaDao.buscar(nombre);
    }

    public boolean rutaContieneCategoria(DtRuta ruta, String categoria) {
        if (!this.existeRuta(ruta.getNombre())) {
            throw new IllegalArgumentException("La ruta no existe");
        }
        for (DtCategoria c : ruta.getCategorias()) {
            if (c.getNombre().equals(categoria)) {
                return true;
            }
        }
        return false;
    }

    public boolean clienteTienePaquete(String nickname, String nombrePaquete) {
        Cliente cliente = this.buscarCliente(nickname);
        Paquete paquete = this.buscarPaquete(nombrePaquete);

        for (CompraPaquete paquetesComprados : cliente.getComprasPaquetes()) {
            if (paquetesComprados.getPaquete().getNombre().equals(paquete.getNombre())) {
                return true;
            }
        }
        return false;
    }

    public void seguirUsuario(String usuarioSeguidor, String usuarioASeguir) {
        Usuario sigueA = this.userDao.buscar(usuarioSeguidor);
        Usuario loSiguen = this.userDao.buscar(usuarioASeguir);

        if (sigueA == null || loSiguen == null) {
            throw new IllegalArgumentException("Alguno de los usuarios no existe");
        }

        if (sigueA.getNickname().equals(loSiguen.getNickname())) {
            throw new IllegalArgumentException("No se puede seguir a uno mismo");
        }

        if (sigueA.sigueA(loSiguen.getNickname())) {
            throw new IllegalArgumentException("Ya sigues a este usuario");
        }

        this.userDao.agregarSeguidorySeguido(sigueA,loSiguen);
    }

    public void dejarDeSeguirUsuario(String usuarioSeguidor, String usuarioADejarDeSeguir) {
        Usuario usuarioDejaSeguir = this.userDao.buscar(usuarioSeguidor);
        Usuario usuarioDejadoSeguir = this.userDao.buscar(usuarioADejarDeSeguir);

        if (usuarioDejaSeguir == null || usuarioDejadoSeguir == null) {
            throw new IllegalArgumentException("Alguno de los usuarios no existe");
        }

        if (!usuarioDejaSeguir.sigueA(usuarioDejadoSeguir.getNickname())) {
            throw new IllegalArgumentException("No sigues a este usuario");
        }

        this.userDao.eliminarSeguidorYSeguido(usuarioDejaSeguir, usuarioDejadoSeguir);
    }

    public DtCategoria getCategoria(String nombre) {
        return this.categoriaDao.buscar(nombre).getDatos();
    }

    public DtReserva realizarCheckin(DtVuelo vuelo, LocalDate fechaReserva, String nicknameCliente) {
        Cliente cliente = this.buscarCliente(nicknameCliente);
        Vuelo v = this.buscarVuelo(vuelo.getNombre());

        Reserva reserva = this.reservaDao.buscar(cliente, v, fechaReserva);

        if (reserva == null) {
            throw new IllegalArgumentException("No existe la reserva para realizar el check-in.");
        }

        if (reserva.getCheckin()) {
            throw new IllegalArgumentException("El check-in ya ha sido realizado para esta reserva.");
        }

        reserva.setCheckin(true);
        this.reservaDao.actualizar(reserva);

        return reserva.getDatos();
    }

    public byte[] crearPDFReserva(DtReserva reservaCompleta) {
        try {
            if (!reservaCompleta.getCheckin()) {
                throw new IllegalArgumentException("El check-in no ha sido realizado para esta reserva.");
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            Document document = new Document(PageSize.A4.rotate());

            PdfWriter.getInstance(document, baos);

            document.open();

            Font titulo = new Font(Font.FontFamily.HELVETICA, 36, Font.BOLD, new BaseColor(0, 102, 204));
            Font subtitulo = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, BaseColor.DARK_GRAY);
            Font normal = new Font(Font.FontFamily.HELVETICA, 14);
            Font negrita = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);

            Paragraph p = new Paragraph("VOLANDO.UY", titulo);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(10);
            document.add(p);

            p = new Paragraph("BOARDING PASS", subtitulo);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(30);
            document.add(p);

            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(90);
            tabla.setHorizontalAlignment(Element.ALIGN_CENTER);

            agregarFila(tabla, "Vuelo", reservaCompleta.getVuelo().getNombre(), negrita, normal);
            agregarFila(tabla, "Fecha de vuelo", reservaCompleta.getVuelo().getFecha(), negrita, normal);
            agregarFila(tabla, "Ruta",
                    reservaCompleta.getVuelo().getRutaDeVuelo().getCiudadOrigen().getNombre() + " ✈ " +
                            reservaCompleta.getVuelo().getRutaDeVuelo().getCiudadDestino().getNombre(), negrita, normal);
            agregarFila(tabla, "Duración", reservaCompleta.getVuelo().getDuracion(), negrita, normal);
            agregarFila(tabla, "Asiento", reservaCompleta.getTipoAsiento().toString(), negrita, normal);
            agregarFila(tabla, "Pasajeros", String.valueOf(reservaCompleta.getCantPasajes()), negrita, normal);

            if (reservaCompleta.getEquipajeExtra() > 0) {
                agregarFila(tabla, "Equipaje extra", reservaCompleta.getEquipajeExtra() + " valijas", negrita, normal);
            }

            document.add(tabla);

            document.add(new Paragraph("\nPasajeros:", negrita));
            for (DtPasajero pasajero : reservaCompleta.getPasajeros()) {
                Paragraph pa = new Paragraph("• " + pasajero.getNombre() + " " + pasajero.getApellido(), normal);
                pa.setIndentationLeft(40);
                document.add(pa);
            }

            p = new Paragraph("\nGracias por volar con Volando.uy", negrita);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingBefore(40);
            document.add(p);

            p = new Paragraph("Presenta este boarding pass en el aeropuerto", normal);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            document.close();

            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF: " + e.getMessage());
        }
    }

    private void agregarFila(PdfPTable tabla, String label, String valor, Font labelFont, Font valueFont) {
        PdfPCell celdaLabel = new PdfPCell(new Phrase(label, labelFont));
        celdaLabel.setBorder(Rectangle.NO_BORDER);
        celdaLabel.setPadding(8);
        celdaLabel.setBackgroundColor(new BaseColor(240, 248, 255));

        PdfPCell celdaValor = new PdfPCell(new Phrase(valor, valueFont));
        celdaValor.setBorder(Rectangle.NO_BORDER);
        celdaValor.setPadding(8);

        tabla.addCell(celdaLabel);
        tabla.addCell(celdaValor);
    }

    public String getTipoUsuario(String nickname){
       try{
           buscarAerolinea(nickname);
           return "Aerolinea";
       }catch(Exception e){
           try{
               buscarCliente(nickname);
               return "Cliente";
           }catch(Exception ex){
               System.out.println("El usuario no es ni cliente ni aerolinea.");
               throw new IllegalArgumentException("El usuario no existe.");
           }
       }
    }

    public String detectarExtension(byte[] data) {
        if (data == null || data.length < 8) return ".bin";

        // PNG
        if (data[0] == (byte)0x89 &&
                data[1] == 0x50 &&
                data[2] == 0x4E &&
                data[3] == 0x47 &&
                data[4] == 0x0D &&
                data[5] == 0x0A &&
                data[6] == 0x1A &&
                data[7] == 0x0A) {
            return ".png";
        }

        // JPG/JPEG
        if (data[0] == (byte)0xFF &&
                data[1] == (byte)0xD8) {
            return ".jpg";
        }

        // GIF
        if (data[0] == 'G' &&
                data[1] == 'I' &&
                data[2] == 'F') {
            return ".gif";
        }

        // BMP
        if (data[0] == 'B' && data[1] == 'M') {
            return ".bmp";
        }

        // WEBP (RIFF)
        if (data[0] == 'R' && data[1] == 'I' && data[2] == 'F' && data[3] == 'F') {
            return ".webp";
        }

        return ".bin"; // fallback si no se reconoce
    }


    public void vaciarBD() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.createQuery("DELETE FROM Aerolinea").executeUpdate();
        em.createQuery("DELETE FROM Reserva").executeUpdate();
        em.createQuery("DELETE FROM CompraPaquete").executeUpdate();
        em.createQuery("DELETE FROM Cliente").executeUpdate();
        em.createQuery("DELETE FROM DtPasajero ").executeUpdate();
        em.createQuery("DELETE FROM Vuelo").executeUpdate();
        em.createQuery("DELETE FROM Usuario").executeUpdate();
        em.createQuery("DELETE FROM RutaEnPaquete").executeUpdate();
        em.createQuery("DELETE FROM RutaDeVuelo ").executeUpdate();
        em.createQuery("DELETE FROM Categoria").executeUpdate();
        em.createQuery("DELETE FROM Ciudad").executeUpdate();
        em.createQuery("DELETE FROM Paquete").executeUpdate();
        tx.commit();
    }
}
