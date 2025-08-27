package com.app.clases;

import com.app.DAOs.*;
import com.app.datatypes.*;
import com.app.enums.TipoAsiento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Sistema implements ISistema {
    private static Sistema instancia;

    private EntityManagerFactory emf;
    private EntityManager em;
    private UserDao userDao;
    private RutaDeVueloDao rutaDeVueloDao;

    private Map<String, Categoria> categorias;
    private Map<String, Ciudad> ciudades;
    private Map<String, Usuario> usuarios;
    private Map<String, Paquete> paquetes;
    private Map<String, Vuelo> vuelos;

    private List<Aerolinea> aerolineas = new ArrayList<>();
    private List<RutaDeVuelo> rutas = new ArrayList<>();
    private List<Vuelo> consultaVuelos = new ArrayList<>();
    private List<Usuario> consultaUsuarios = new ArrayList<>();

    private Usuario usuarioSeleccionado; // Guarda selección actual
    private Paquete paqueteSeleccionado;
    private DtCliente clienteTemporal;
    private DtAerolinea aerolineaTemporal;
    private Aerolinea aerolineaTemporalClase;

    private Sistema() {
        //Inicializar JPA
        this.emf = Persistence.createEntityManagerFactory("pepitoElLaburador");
        this.em = emf.createEntityManager(); //Inicializamos el "controlador" global de la BD
        this.userDao = new UserDao(em); // Inicializamos "controlador" de usuarios
        this.rutaDeVueloDao = new RutaDeVueloDao(em);

        this.categorias = new LinkedHashMap<>();
        this.ciudades = new LinkedHashMap<>();
        this.usuarios = new LinkedHashMap<>();
        this.paquetes = new LinkedHashMap<>();
    }

    public static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    public UserDao getUserDao() {
        return this.userDao;
    }

    public RutaDeVueloDao getRutaDeVueloDao(){return this.rutaDeVueloDao;}

    public List<String> listarAerolineas() {
        List<String> nickname = new ArrayList<>();
        for (Aerolinea a : aerolineas) {
            nickname.add(a.getNickname());
        }
        return nickname;
    }

    public List<String> listarRutasDeAerolinea(String nickname) {
        List<String> nombresRutas = new ArrayList<>();
        for (Aerolinea a : aerolineas) {
            if (a.getNickname().equals(nickname)) {
                for (RutaDeVuelo r : a.getRutasDeVuelo()) {
                    nombresRutas.add(r.getNombre());
                }
                break;
            }
        }
        return nombresRutas;
    }

    public DtRuta consultarRuta(String nombre) {
        for (RutaDeVuelo r : rutas) {
            if (r.getNombre().equals(nombre)) {
                // Convertir RutaDeVuelo para mostrar datos
                return new DtRuta(
                        r.getNombre(),
                        r.getDescripcion(),
                        r.getHora(),
                        r.getCostoTurista(),
                        r.getCostoEjecutivo(),
                        r.getEquipajeExtra(),
                        r.getFechaAlta(),
                        r.getCategorias(),
                        r.getCiudades()
                );
            }
        }
        return null;
    }

    public DtVuelo consultarVuelo(String nickname) {
        for (Vuelo v : consultaVuelos) {
            if (v.getNombre().equals(nickname)) {
                return v.getDatos();
            }
        }
        return null;
    }

    public boolean existeRuta(String nombre) {
        for (RutaDeVuelo r : rutas) {
            if (r.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void altaRutaDeVuelo(String nombreAerolinea, DtRuta datosRuta) {
        Aerolinea aerolinea = userDao.buscarAerolinea(nombreAerolinea);

        if (aerolinea == null) return;

        RutaDeVuelo nuevaRuta = new RutaDeVuelo(datosRuta);

        this.rutas.add(nuevaRuta);
        this.rutaDeVueloDao.guardar(nuevaRuta);
        aerolinea.getRutasDeVuelo().add(nuevaRuta);
    }


    public List<DtUsuario> listarUsuarios() {
        return consultaUsuarios.stream()
                .map(u -> {
                    if (u instanceof Cliente) {
                        Cliente c = (Cliente) u;
                        return new DtCliente(
                                c.getNickname(),
                                c.getNombre(),
                                c.getEmail(),
                                c.getApellido(),
                                c.getFechaNacimiento(),
                                c.getNacionalidad(),
                                c.getTipoDocumento(),
                                c.getNumeroDocumento()
                        );
                    } else if (u instanceof Aerolinea) {
                        Aerolinea a = (Aerolinea) u;
                        return new DtAerolinea(
                                a.getNickname(),
                                a.getNombre(),
                                a.getEmail(),
                                a.getDescripcion(),
                                a.getLinkWeb()
                        );
                    }
                    return new DtUsuario(u.getNickname(), u.getNombre(), u.getEmail());
                })
                .collect(Collectors.toList());
    }

    public void elegirUsuario(String nickname) {
        Usuario u = this.usuarios.get(nickname);
        if (u == null) {
            throw new IllegalArgumentException("No existe un usuario con ese nickname.");
        }
        this.usuarioSeleccionado = u;
    }

    public DtUsuario getUsuarioSeleccionado() {
        if (usuarioSeleccionado == null) return null;

        if (usuarioSeleccionado instanceof Cliente) {
            Cliente c = (Cliente) usuarioSeleccionado;
            return new DtCliente(
                    c.getNickname(),
                    c.getNombre(),
                    c.getEmail(),
                    c.getApellido(),
                    c.getFechaNacimiento(),
                    c.getNacionalidad(),
                    c.getTipoDocumento(),
                    c.getNumeroDocumento()
            );
        } else if (usuarioSeleccionado instanceof Aerolinea) {
            Aerolinea a = (Aerolinea) usuarioSeleccionado;
            return new DtAerolinea(
                    a.getNickname(),
                    a.getNombre(),
                    a.getEmail(),
                    a.getDescripcion(),
                    a.getLinkWeb()
            );
        }
        return new DtUsuario(
                usuarioSeleccionado.getNickname(),
                usuarioSeleccionado.getNombre(),
                usuarioSeleccionado.getEmail()
        );
    }

    public List<DtReserva> mostrarReservas() {
        if (usuarioSeleccionado instanceof Cliente) {
            Cliente c = (Cliente) usuarioSeleccionado;
            List<DtReserva> dtReservas = new ArrayList<>();

            for (DtReserva r : c.getReservas()) {
                dtReservas.add(new DtReserva(
                        r.getFecha(),
                        r.getTipoAsiento(),
                        r.getCantPasajes(),
                        r.getEquipajeExtra(),
                        r.getCosto(),
                        r.getPasajeros(),
                        c, // Cliente
                        r.getVuelo(),
                        r.getPaquete()
                ));
            }
            return dtReservas;
        }
        return new ArrayList<>();
    }

    public List<DtPaquete> mostrarPaquetes() {
        List<DtPaquete> paquetes = new ArrayList<>();

        if (usuarioSeleccionado instanceof Cliente) {
            Cliente cliente = (Cliente) usuarioSeleccionado;
            for (DtPaquete paquete : cliente.getPaquetesAdquiridos()) {
                paquetes.add(new DtPaquete(
                        paquete.getNombre(),
                        paquete.getDescripcion(),
                        paquete.getValidezDias(),
                        paquete.getDescuento(),
                        paquete.getCosto()
                ));
            }
        }
        return paquetes;
    }

    public List<DtRuta> mostrarRutasDeVuelo() {
        List<DtRuta> rutas = new ArrayList<>();

        if (usuarioSeleccionado instanceof Aerolinea) {
            Aerolinea aerolinea = (Aerolinea) usuarioSeleccionado;

            // Convertir cada RutaDeVuelo a DtRutaDeVuelo
            for (RutaDeVuelo ruta : aerolinea.getRutasDeVuelo()) {
                rutas.add(convertirRutaADtRuta(ruta));
            }
        }
        return rutas;
    }

    private DtRuta convertirRutaADtRuta(RutaDeVuelo ruta) {
        return new DtRuta(
                ruta.getNombre(),
                ruta.getDescripcion(),
                ruta.getHora(),
                ruta.getCostoTurista(),
                ruta.getCostoEjecutivo(),
                ruta.getEquipajeExtra(),
                ruta.getFechaAlta(),
                ruta.getCategorias(),
                ruta.getCiudades()
        );
    }

    public List<DtPaquete> listarPaquetes() {
        if (this.paquetes.isEmpty()) {
            throw new IllegalArgumentException("No hay paquetes.");
        }
        List<DtPaquete> dtPaquetes = new ArrayList<>();
        for (Map.Entry<String, Paquete> entry : paquetes.entrySet()) {
            Paquete p = entry.getValue();
            DtPaquete paquete = new DtPaquete(p.getNombre(),p.getDescripcion(),p.getValidezDias(),p.getDescuento(),p.getCosto(),p.getRutaEnPaquete());
            dtPaquetes.add(paquete);
        }
        return dtPaquetes;
    }

    public void seleccionarPaquete(String nombre) {
        Paquete paquete = this.paquetes.get(nombre);
        if (paquete == null) {
            throw new IllegalArgumentException("No existe un paquete con ese nombre.");
        }
        this.paqueteSeleccionado = paquete;
    }

    public DtPaquete getPaquete() {
        return this.paqueteSeleccionado.getDatos();
    }

    public void seleccionarAeroLinea(String nickname) {
        Aerolinea aerolinea = null;
        for (Aerolinea a : aerolineas) {
            if (a.getNickname().equals(nickname)) {
                aerolinea = a;
                break;
            }
        }
        if (aerolinea == null) {
            throw new IllegalArgumentException("No existe una aerolinea con ese nombre.");
        }
        this.aerolineaTemporalClase = aerolinea;
    }

    public List<DtRuta> listarRutasDeVuelo() {
        if (this.aerolineaTemporal == null) {
            throw new IllegalArgumentException("No hay una aerolinea seleccionada.");
        }
        if (this.aerolineaTemporal.getRutasDeVuelo().isEmpty()) {
            throw new IllegalArgumentException("No hay rutas de vuelo para esta aerolinea.");
        }
        List<DtRuta> dtRutas = new ArrayList<>();
        for (RutaDeVuelo r : this.aerolineaTemporal.getRutasDeVuelo()) {
            DtRuta ruta = new DtRuta(r.getNombre(), r.getDescripcion(), r.getHora(), r.getCostoTurista(), r.getCostoEjecutivo(), r.getEquipajeExtra(), r.getFechaAlta(), r.getCategorias(), r.getCiudades());
            dtRutas.add(ruta);
        }
        return dtRutas;
    }

    public void seleccionarRutaDeVuelo(String nombre, int cantidad, TipoAsiento tipoAsiento) {
        if (this.aerolineaTemporal == null) {
            throw new IllegalArgumentException("No hay una aerolinea seleccionada.");
        }
        RutaDeVuelo ruta = null;
        for (RutaDeVuelo r : this.aerolineaTemporal.getRutasDeVuelo()) {
            if (r.getNombre().equals(nombre)) {
                ruta = r;
                break;
            }
        }
        if (ruta == null) {
            throw new IllegalArgumentException("No existe una ruta de vuelo con ese nombre para esta aerolinea.");
        }
        if (this.paqueteSeleccionado == null) {
            throw new IllegalArgumentException("No hay un paquete seleccionado.");
        }

        // Verificar si la ruta ya está en el paquete
        for (RutaEnPaquete r : this.paqueteSeleccionado.getRutaEnPaquete()) {
            if (r.getRutaDeVuelo().getNombre().equals(ruta.getNombre())) {
                throw new IllegalArgumentException("La ruta ya está asociada al paquete.");
            }
        }

        RutaEnPaquete tp = new RutaEnPaquete();
        tp.setCantidad(cantidad);
        tp.setTipoAsiento(tipoAsiento);
        this.paqueteSeleccionado.getRutaEnPaquete().add(tp);

    }

    public void agregarRutaDeVueloAPaquete(DtPaquete paquete, DtAerolinea aerolinea, String nombreRuta,int cantidad,TipoAsiento tipoAsiento) {
        listarPaquetes();
        seleccionarPaquete(paquete.getNombre());
        listarAerolineas();
        seleccionarAeroLinea(aerolinea.getNickname());
        listarRutasDeVuelo();
        seleccionarRutaDeVuelo(nombreRuta,cantidad,tipoAsiento);

        this.aerolineaTemporal = null;
        this.paqueteSeleccionado = null;
    }



    //  TEMPORAL
    public void altaPaquete(DtPaquete paquete){
        if(this.paquetes.containsKey(paquete.getNombre())) { throw new IllegalArgumentException("Ya existe un paquete con ese nombre.");}
        Paquete p = new Paquete(paquete);
        this.paquetes.put(p.getNombre(),p);
    }

    public void altaCategoria(DtCategoria categoria){
        if(this.categorias.containsKey(categoria.getNombre())) { throw new IllegalArgumentException("Ya existe una categoría con ese nombre.");}
        Categoria c = new Categoria(categoria);
        this.categorias.put(c.getNombre(),c);
    }

    public void altaCiudad(DtCiudad ciudad){
        Ciudad existe = this.ciudades.get(ciudad.getNombre());
        if(existe != null ) { throw new IllegalArgumentException("Ya existe una ciudad con ese nombre.");}
        Ciudad c = new Ciudad(ciudad);
        this.ciudades.put(c.getNombre(),c);
    }

    public List<Ciudad> getCiudades(){
        if(this.ciudades.isEmpty()) { throw new IllegalArgumentException("No hay paquetes.");}
        List <Ciudad> listaCiudades = new ArrayList<>();
        for (Map.Entry<String, Ciudad> entry : ciudades.entrySet()) {
            Ciudad c = entry.getValue();
            listaCiudades.add(c);
        }
        return listaCiudades;
    }

    public List<Categoria> getCategorias(){
        if(this.categorias.isEmpty()) { throw new IllegalArgumentException("No hay paquetes.");}
        List <Categoria> listaCategorias = new ArrayList<>();
        for (Map.Entry<String, Categoria> entry : categorias.entrySet()) {
            Categoria c = entry.getValue();
            listaCategorias.add(c);
        }
        return listaCategorias;
    }

    public void consultaVuelo(DtVuelo vuelo) {
        Vuelo v = this.vuelos.get(vuelo.getNombre());
        if (v == null) {
            throw new IllegalArgumentException("Vuelo no encontrado.");
        }
    }

    public void registrarCliente(DtCliente cliente){
        if(this.usuarios.containsKey(cliente.getNickname())) {
            throw new IllegalArgumentException("Este usuario ya existe.");}

        for (Usuario existente : this.usuarios.values()) {
            if (existente.getEmail().equals(cliente.getEmail())) {
                throw new IllegalArgumentException("Ya existe un usuario con ese email.");
            }
        }

        this.clienteTemporal = cliente;
    };

    public void modificarCliente(DtCliente cliente){
        Usuario u = this.usuarios.get(cliente.getNickname());
        if(u == null) { throw new IllegalArgumentException("Este usuario no existe.");}

        if (u instanceof Cliente c) {
            c.setNombre(cliente.getNombre());
            c.setApellido(cliente.getApellido());
            c.setFechaNacimiento(cliente.getFechaNacimiento());
            c.setNacionalidad(cliente.getNacionalidad());
            c.setTipoDocumento(cliente.getTipoDocumento());
            c.setNumeroDocumento(cliente.getNumeroDocumento());
        }else{
            throw new IllegalArgumentException("Este usuario es aerolinea.");
        }
    };

    public void registrarAerolinea(DtAerolinea aerolinea){
        if(this.usuarios.containsKey(aerolinea.getNickname())) { throw new IllegalArgumentException("Este usuario ya existe.");}

        for (Usuario existente : this.usuarios.values()) {
            if (existente.getEmail().equals(aerolinea.getEmail())) {
                throw new IllegalArgumentException("Ya existe un usuario con ese email.");
            }
        }

        this.aerolineaTemporal = aerolinea;
    };

    public void modificarAerolinea(DtAerolinea aerolinea){
        Usuario u = this.usuarios.get(aerolinea.getNickname());
        if(u == null) { throw new IllegalArgumentException("Este usuario no existe.");}

        if (u instanceof Aerolinea a) {
            a.setNombre(aerolinea.getNombre());
            a.setDescripcion(aerolinea.getDescripcion());
            a.setLinkWeb(aerolinea.getLinkWeb());
        }else{
            throw new IllegalArgumentException("Este usuario es cliente.");
        }
    };

    public void confirmarAltaUsuario(){
        if(this.clienteTemporal != null){
            Cliente nuevo = new Cliente(this.clienteTemporal);
            this.usuarios.put(nuevo.getNickname(), nuevo);
            userDao.guardar(nuevo);
            this.clienteTemporal = null;
        }else{
            Aerolinea nueva = new Aerolinea(this.aerolineaTemporal);
            this.usuarios.put(nueva.getNickname(),nueva);
            userDao.guardar(nueva);
            this.aerolineaTemporal = null;
        }
    };

    public void cancelarAltaUsuario(){
        this.clienteTemporal = null;
        this.aerolineaTemporal = null;
    }



}
