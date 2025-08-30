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
    private CategoriaDao categoriaDao;
    private CiudadDao ciudadDao;
    private PaqueteDao paqueteDao;

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

    private DtVuelo vueloTemporal;
    private RutaDeVuelo rutaTemporal;
    private Aerolinea aerolineaTemp;

    private Cliente clienteTemp;


    private Sistema() {
        //Inicializar JPA
        this.emf = Persistence.createEntityManagerFactory("pepitoElLaburador");
        this.em = emf.createEntityManager(); //Inicializamos el "controlador" global de la BD
        this.userDao = new UserDao(em); // Inicializamos "controlador" de usuarios
        this.rutaDeVueloDao = new RutaDeVueloDao(em);
        this.categoriaDao = new CategoriaDao(em);
        this.ciudadDao = new CiudadDao(em);
        this.paqueteDao = new PaqueteDao(em);

        this.categorias = categoriaDao.obtenerCategorias();
        this.ciudades = ciudadDao.obtenerCiudades();
        this.usuarios = userDao.obtenerUsuarios();
        this.paquetes = paqueteDao.obtenerPaquetes();
        this.vuelos = new LinkedHashMap<>();
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

    public CategoriaDao getCategoriaDao(){ return this.categoriaDao;}

    public CiudadDao getCiudadDao() {return  this.ciudadDao;}

    public PaqueteDao getPaqueteDao() {return this.paqueteDao;}

    public List<DtAerolinea> listarAerolineas() {
        List <DtAerolinea> nuevaLista = new ArrayList<>();
        for (Aerolinea a : this.aerolineas) {
            nuevaLista.add(a.getDatos());
        }
        return nuevaLista;
    }

    public List<DtRuta> listarRutasDeVuelo(String nickname) {
        List<DtRuta> listaRutas = new ArrayList<>();
        Aerolinea a = (Aerolinea)this.usuarios.get(nickname);
        for (RutaDeVuelo r : a.getRutasDeVuelo()) {
            listaRutas.add(r.getDatos());
        }
        return listaRutas;
    }

    public DtRuta consultarRuta(String nombre) {
        for (RutaDeVuelo r : rutas) {
            if (r.getNombre().equals(nombre)) {
                return r.getDatos();
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

        if (aerolinea == null) throw new IllegalArgumentException("Aerolinea no existe");

        if(aerolinea.buscarRutaDeVuelo(datosRuta.getNombre())) throw new IllegalArgumentException("Ya existe esa ruta de vuelo en esa aerolinea");

        RutaDeVuelo nuevaRuta = new RutaDeVuelo(datosRuta);

        this.rutas.add(nuevaRuta); // Guardas la ruta en el sistema
        aerolinea.addRuta(nuevaRuta); // Dicha ruta la asocia con aerolinea
        this.rutaDeVueloDao.guardar(nuevaRuta); // Persistimos la nuevaRuta
        this.userDao.addRutaDeVuelo(aerolinea, nuevaRuta); // La agregamos a su aerolinea
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

    public List<DtCiudad> listarCiudades() {
        List <DtCiudad> listaCiudades = new ArrayList<>();
        for(Ciudad c : this.getCiudades()){
            listaCiudades.add(c.getDatos());
        }
        return listaCiudades;
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
                        paquete.getCosto(),
                        paquete.getRutaEnPaquete()
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
                ruta.getDuracion(),
                ruta.getCostoTurista(),
                ruta.getCostoEjecutivo(),
                ruta.getEquipajeExtra(),
                ruta.getFechaAlta(),
                ruta.getCategorias(),
                ruta.getCiudadOrigen(),
                ruta.getCiudadDestino()
        );
    }

    public List<DtPaquete> listarPaquetes() {
        List<DtPaquete> listaPaquetes = new ArrayList<>();
        for(Paquete p : this.getPaquetes()){
            listaPaquetes.add(p.getDatos());
        }
        return listaPaquetes;
    }

    public List<Paquete> getPaquetes() {
        return new ArrayList<>(this.paquetes.values());
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
            DtRuta ruta = new DtRuta(r.getNombre(), r.getDescripcion(), r.getDuracion(), r.getCostoTurista(), r.getCostoEjecutivo(), r.getEquipajeExtra(), r.getFechaAlta(), r.getCategorias(), r.getCiudadOrigen(),r.getCiudadDestino());
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
        paqueteDao.guardar(p);
        this.paquetes.put(p.getNombre(),p);
    }

    public void altaCategoria(DtCategoria categoria){
        if(categoriaDao.buscar(categoria.getNombre()) != null) { throw new IllegalArgumentException("Ya existe una categoría con ese nombre.");}
        Categoria c = new Categoria(categoria);
        this.categorias.put(c.getNombre(),c);
        categoriaDao.guardar(c);
    }

    public void altaCiudad(DtCiudad ciudad){
        if(this.ciudades.containsKey(ciudad.getNombre())) { throw new IllegalArgumentException("Ya existe una ciudad con ese nombre.");}
        Ciudad c = new Ciudad(ciudad);
        ciudadDao.guardar(c);
        this.ciudades.put(c.getNombre(),c);
    }

    public List<Ciudad> getCiudades(){
        return new ArrayList<>(this.ciudades.values());
    }

    public List<Categoria> getCategorias(){
        if(categoriaDao.listarCategorias().isEmpty()) { throw new IllegalArgumentException("No hay categorias.");}
        return categoriaDao.listarCategorias();
    }

    public void consultaVuelo(DtVuelo vuelo) {
        Vuelo v = this.vuelos.get(vuelo.getNombre());
        if (v == null) {
            throw new IllegalArgumentException("Vuelo no encontrado.");
        }
    }

    public void registrarCliente(DtCliente cliente){
        if(this.usuarios.containsKey(cliente.getNickname()) || userDao.buscar(cliente.getNickname()) != null ) {
            throw new IllegalArgumentException("Este usuario ya existe.");}

        for (Usuario existente : this.usuarios.values()) {
            if (existente.getEmail().equals(cliente.getEmail())) {
                throw new IllegalArgumentException("Ya existe un usuario con ese email.");
            }
        }

        confirmarAltaUsuario(cliente);
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

        confirmarAltaUsuario(aerolinea);
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

    public void confirmarAltaUsuario(DtUsuario usuario){
        if(usuario instanceof DtCliente){
            Cliente newUser = new Cliente((DtCliente) usuario);
            this.usuarios.put(newUser.getNickname(),newUser);
            userDao.guardar(newUser);
        }else if (usuario instanceof DtAerolinea) {
            Aerolinea newUser = new Aerolinea((DtAerolinea) usuario);
            this.usuarios.put(newUser.getNickname(), newUser);
            userDao.guardar(newUser);
        }
    }

    public void confirmarAltaUsuario(){
        if(this.clienteTemporal != null){
            Cliente nuevo = new Cliente(this.clienteTemporal);
            this.usuarios.put(nuevo.getNickname(), nuevo);
            userDao.guardar(nuevo);
            this.clienteTemporal = null;
        }else{
            throw new IllegalArgumentException("Ha ocurrido un error al crear el usuario.");
        }
    };

    public List<Categoria> getCategoriasPorNombre(List<String> nombres) {
        List<Categoria> categoriasSeleccionadas = new ArrayList<>();
        for (String nombreCategoria : nombres) {
            for (Categoria c : this.getCategorias()) {
                if (c.getNombre().equals(nombreCategoria)) {
                    categoriasSeleccionadas.add(c);
                    break; // salimos del loop interno
                }
            }
        }
        return categoriasSeleccionadas;
    }

    public void cancelarAltaUsuario(){
        this.clienteTemporal = null;
        this.aerolineaTemporal = null;
    }

    public Ciudad buscarCiudad (String nombre){
        Ciudad c = this.ciudadDao.buscar(nombre);
        if(c == null)  throw new IllegalArgumentException("Esta ciudad no existe.");
        return  c;
    }

    // ---------- ALTA DE VUELO ---------- //
    public void seleccionarAerolineaParaVuelo(String nickname) {
        Aerolinea aerolinea = userDao.buscarAerolinea(nickname);
        if (aerolinea == null) {
            throw new IllegalArgumentException("La aerolínea no existe.");
        }
        this.aerolineaTemp = aerolinea;
    }

    public void seleccionarRuta(String nombre){
        RutaDeVuelo ruta = null;
        for (RutaDeVuelo r : this.aerolineaTemp.getRutasDeVuelo()) {
            if (r.getNombre().equals(nombre)) {
                ruta = r;
                break;
            }
        }
        if (ruta == null) {
            throw new IllegalArgumentException("La ruta no existe en esta aerolínea.");
        }

        this.rutaTemporal = ruta;
    }

    public void ingresarDatosVuelo(DtVuelo datosVuelo) {
        if (this.rutaTemporal == null) {
            throw new IllegalArgumentException("Debe seleccionar una ruta primero.");
        }
        if (this.vuelos.containsKey(datosVuelo.getNombre())) {
            throw new IllegalArgumentException("Ya existe un vuelo con ese nombre.");
        }
        this.vueloTemporal = datosVuelo;
    }


    public void confirmarAltaVuelo(){
        if(this.vueloTemporal == null){
            throw new IllegalArgumentException("Debe seleccionar un vuelo");
        }
        if(this.aerolineaTemp == null){
            throw new IllegalArgumentException("Debe seleccionar una aerolinea");
        }

        if(this.rutaTemporal == null){
            throw new IllegalArgumentException("Debe seleccionar una ruta");
        }

        Vuelo nuevo = new Vuelo(this.vueloTemporal);
        nuevo.setRutaDeVuelo(this.rutaTemporal);

        this.vuelos.put(nuevo.getNombre(), nuevo);
        this.consultaVuelos.add(nuevo);

        this.vueloTemporal = null;
        this.rutaTemporal = null;
        this.aerolineaTemp = null;
    }

    public void cancelarAlta(){
        this.rutaTemporal = null;
        this.aerolineaTemp = null;
        this.vueloTemporal = null;
    }


    // ---------- COMPRA PAQUETE ---------- //
    public void seleccionarPaqueteCompra(String nombre){

    }

    public void seleccionarCliente(){

    }
}
