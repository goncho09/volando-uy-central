package com.app.clases;

import com.app.DAOs.*;
import com.app.datatypes.*;
import com.app.enums.TipoAsiento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Sistema implements ISistema {
    private static Sistema instancia;

    private EntityManagerFactory emf;
    private EntityManager em;
    private UserDao userDao;
    private RutaDeVueloDao rutaDeVueloDao;
    private CategoriaDao categoriaDao;
    private CiudadDao ciudadDao;
    private PaqueteDao paqueteDao;
    private VueloDao vueloDao;
    private ReservaDao reservaDao;
    private PasajerosDao pasajeroDao;
    private CompraPaqueteDao compraDao;

    private Map<String, Categoria> categorias;
    private Map<String, Ciudad> ciudades;
    private Map<String, Usuario> usuarios;
    private Map<String, Paquete> paquetes;
    private Map<String, Vuelo> vuelos;
    private Map<String, RutaDeVuelo> rutasDeVuelo;

    private List<CompraPaquete> compraPaquetes;
    private List<Reserva> reservas;
    private List<DtPasajero> pasajes;

    private List<Vuelo> consultaVuelos = new ArrayList<>();
    private List<Usuario> consultaUsuarios = new ArrayList<>();
    private List<CompraPaquete> compras = new ArrayList<>();

    private Usuario usuarioSeleccionado; // Guarda selección actual
    private Paquete paqueteSeleccionado;
    private DtCliente clienteTemporal;
    private DtAerolinea aerolineaTemporal;
    private Aerolinea aerolineaTemporalClase;
    private DtVuelo vueloTemporal;
    private RutaDeVuelo rutaTemporal;
    private Aerolinea aerolineaTemp;
    private Cliente clienteTemp;
    private DtCompraPaquete compraTemp;
    private DtPaquete paqueteTemp;


    private Sistema() {
        //Inicializar JPA
        this.emf = Persistence.createEntityManagerFactory("pepitoElLaburador");
        this.em = emf.createEntityManager(); //Inicializamos el "controlador" global de la BD
        this.userDao = new UserDao(em); // Inicializamos "controlador" de usuarios
        this.rutaDeVueloDao = new RutaDeVueloDao(em);
        this.categoriaDao = new CategoriaDao(em);
        this.ciudadDao = new CiudadDao(em);
        this.paqueteDao = new PaqueteDao(em);
        this.vueloDao = new VueloDao(em);
        this.reservaDao = new ReservaDao(em);
        this.pasajeroDao = new PasajerosDao(em);
        this.compraDao = new CompraPaqueteDao(em);

        this.categorias = categoriaDao.obtenerCategorias();
        this.ciudades = ciudadDao.obtenerCiudades();
        this.usuarios = userDao.obtenerUsuarios();
        this.paquetes = paqueteDao.obtenerPaquetes();
        this.rutasDeVuelo = rutaDeVueloDao.obtenerRutasDeVuelo();
        this.vuelos = vueloDao.obtenerVuelos();
        this.reservas = reservaDao.listar();
        this.pasajes = pasajeroDao.listar();
        this.compras = compraDao.listar();
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

    public VueloDao getVueloDao() {return this.vueloDao;}

    public List<DtAerolinea> listarAerolineas() {
        List <DtAerolinea> nuevaLista = new ArrayList<>();
        for (Aerolinea a : this.getAerolineas()) {
            nuevaLista.add(a.getDatos());
        }
        return nuevaLista;
    }

    public List<DtRuta> listarRutasDeVuelo(String nickname) {
        List<DtRuta> listaRutas = new ArrayList<>();
        Aerolinea a = (Aerolinea)this.usuarios.get(nickname);
        if(a == null){
            return listaRutas;
        }
        List<RutaDeVuelo> rutasAerolinea = a.getRutasDeVuelo();
        for (RutaDeVuelo r : rutasAerolinea) {
            listaRutas.add(r.getDatos());
        }
        return listaRutas;
    }

    public DtRuta consultarRuta(String nombre) {
        if(!this.rutasDeVuelo.containsKey(nombre)) throw new IllegalArgumentException("No existe esta ruta");
        return  (this.rutasDeVuelo.get(nombre).getDatos());
    }

    public DtVuelo consultarVuelo(String nombre) {
        for (Vuelo v : consultaVuelos) {
            if (v.getNombre().equals(nombre)) {
                return v.getDatos();
            }
        }
        return null;
    }

    public boolean existeRuta(String nombre) {
        for (RutaDeVuelo r : this.getRutasDeVuelo()) {
            if (r.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void altaRutaDeVuelo(String nombreAerolinea, DtRuta datosRuta) {
        Aerolinea aerolinea = (Aerolinea) userDao.buscar(nombreAerolinea);

        if (aerolinea == null) throw new IllegalArgumentException("Aerolinea no existe");

        if(aerolinea.buscarRutaDeVuelo(datosRuta.getNombre())) throw new IllegalArgumentException("Ya existe esa ruta de vuelo en esa aerolinea");

        RutaDeVuelo nuevaRuta = new RutaDeVuelo(datosRuta);

        this.rutasDeVuelo.put(nuevaRuta.getNombre(),nuevaRuta); // Guardas la ruta en el sistema
        aerolinea.addRuta(nuevaRuta); // Dicha ruta la asocia con aerolinea
        this.rutaDeVueloDao.guardar(nuevaRuta); // Persistimos la nuevaRuta
        this.userDao.addRutaDeVuelo(aerolinea, nuevaRuta); // La agregamos a su aerolinea
    }


    public List<DtUsuario> listarUsuarios() {
        List <DtUsuario> usuarios = new ArrayList<>();
        for(Usuario u : this.getUsuarios()){
            usuarios.add(u.getDatos());
        }
        return usuarios;
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

            for (DtReserva r : c.getDtReservas()) {
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

    public List<DtPaquete> listarPaquetesNoComprados() {
        List<DtPaquete> listaPaquetes = new ArrayList<>();
        for(Paquete p : this.getPaquetes()){
            if(p.getRutaEnPaquete().isEmpty()) {
                listaPaquetes.add(p.getDatos());
            }
        }
        return listaPaquetes;

    }

    public List<DtCliente> listarClientes(){
        List<DtCliente> listaClientes = new ArrayList<>();
        for(Cliente c : this.getClientes()){
            listaClientes.add(c.getDatos());
        }
        return listaClientes;
    }

    public List<Ciudad> getCiudades(){
        return new ArrayList<>(this.ciudades.values());
    }

    public List<Paquete> getPaquetes() {
        return new ArrayList<>(this.paquetes.values());
    }

    public List<Vuelo> getVuelos() {
        return new ArrayList<>(this.vuelos.values());
    }

    public List<RutaDeVuelo> getRutasDeVuelo() {
        return new ArrayList<>(this.rutasDeVuelo.values());
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(this.usuarios.values());
    };

    public List<Cliente> getClientes() {
        List<Usuario> usuarios = this.getUsuarios();
        List<Cliente> clientes = new ArrayList<>();
        for(Usuario u : usuarios){
            if(u instanceof Cliente){
                clientes.add((Cliente) u);
            }
        }
        return clientes;
    };

    public List<Aerolinea> getAerolineas() {
        List<Aerolinea> listaAerolineas = new ArrayList<>();
        List <Usuario> listaUsuarios = this.getUsuarios();
        for(Usuario u : listaUsuarios){
            if(u instanceof Aerolinea){
                Aerolinea a =  (Aerolinea) u;
                listaAerolineas.add(a);
            }
        }
        return listaAerolineas;
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
        for (Aerolinea a : this.getAerolineas()) {
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
        List<DtRuta> listaRutas = new ArrayList<>();
        for (RutaDeVuelo r : this.getRutasDeVuelo()) {
            listaRutas.add(r.getDatos());
        }
        return listaRutas;
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

    public void altaCiudad(DtCiudad ciudad) {
        //Entiendo que es una clave compuesta nombre + país; agrego país
        CiudadId id = new CiudadId(ciudad.getNombre(), ciudad.getPais());
        Ciudad existente = ciudadDao.buscar(id);

        if (existente != null) {
            throw new IllegalArgumentException("Ya existe una ciudad con ese nombre en ese país.");
        }

        // Si no existe, crear
        Ciudad c = new Ciudad(ciudad);
        ciudadDao.guardar(c);
        this.ciudades.put(c.getNombre(), c);
    }



    public List<Categoria> getCategorias(){
        if(categoriaDao.listar().isEmpty()) { throw new IllegalArgumentException("No hay categorias.");}
        return categoriaDao.listar();
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
            userDao.actualizar(c);
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
            a.setEmail(aerolinea.getEmail());
            a.setDescripcion(aerolinea.getDescripcion());
            a.setLinkWeb(aerolinea.getLinkWeb());
            userDao.actualizar(a);
            a.mostrarDatos();
        }else{
            throw new IllegalArgumentException("Este usuario es cliente.");
        }
    };

    public void confirmarAltaUsuario(DtUsuario usuario){
        if(usuario instanceof DtCliente){
            Cliente newUser = new Cliente((DtCliente) usuario);
            this.usuarios.put(newUser.getNickname(),newUser);
            userDao.guardar(newUser);
            //newUser.mostrarDatos(); //Debug
        }else if (usuario instanceof DtAerolinea) {
            Aerolinea newUser = new Aerolinea((DtAerolinea) usuario);
            this.usuarios.put(newUser.getNickname(), newUser);
            userDao.guardar(newUser);
            //newUser.mostrarDatos(); //Debug
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
    }

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

    public Ciudad buscarCiudad (String nombre, String pais){
        CiudadId ciudadId = new CiudadId(nombre, pais);
        Ciudad c = this.ciudadDao.buscar(ciudadId);
        if(c == null)  throw new IllegalArgumentException("Esta ciudad no existe.");
        return  c;
    }

    public Ciudad buscarCiudadPorNombreYPais(String nombre, String pais) {
        Ciudad c = this.ciudadDao.buscar(new CiudadId(nombre, pais));
        if (c == null) {
            throw new IllegalArgumentException("No existe una ciudad con ese nombre y país.");
        }
        return c;
    }

    // ---------- ALTA DE VUELO ---------- //
    public void seleccionarAerolineaParaVuelo(String nickname) {
        Aerolinea aerolinea = (Aerolinea) userDao.buscar(nickname);
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
        Paquete paquete = paqueteDao.buscar((nombre));
        if(paquete == null){
            throw new IllegalArgumentException("El paquete no existe");
        }
        this.paqueteSeleccionado = paquete;
    }

    public void seleccionarCliente(String nickname){
        Cliente cliente = (Cliente) userDao.buscar(nickname);
        if(cliente == null){
            throw new IllegalArgumentException(("El cliente no existe"));
        }
        this.clienteTemp = cliente;
    }

    public void ingresarDatosCompra(DtCompraPaquete datosCompra){
        if(paqueteSeleccionado == null){
            throw  new IllegalArgumentException("Debe seleccionar un paquete.");
        }

        if(clienteTemp == null){
            throw new IllegalArgumentException("Debe seleccionar un cliente.");
        }

        this.compraTemp = datosCompra;
    }

    public void confirmarCompraPaquete(){
        if(paqueteSeleccionado == null){
            throw  new IllegalArgumentException("Debe seleccionar un paquete.");
        }

        if(clienteTemp == null){
            throw new IllegalArgumentException("Debe seleccionar un cliente.");
        }

        for(CompraPaquete c : this.clienteTemp.getComprasPaquetes()){
            if (c.getPaquete().equals(paqueteSeleccionado)){
                throw new IllegalArgumentException("El cliente ya ha comprado este paquete.");
            }
        }

        CompraPaquete nuevaCompra = new CompraPaquete(this.compraTemp);
        nuevaCompra.setPaquete(paqueteSeleccionado);
        nuevaCompra.setCliente(clienteTemp);
        nuevaCompra.setCosto(paqueteSeleccionado.getCosto());

        this.compras.add(nuevaCompra);
        this.userDao.addCompraPaquete(clienteTemp, nuevaCompra);

        this.compraTemp = null;
        this.clienteTemp = null;
        this.paqueteSeleccionado = null;
    }

    public void cancelarCompraPaquete(){
        this.compraTemp = null;
        this.clienteTemp = null;
        this.paqueteSeleccionado = null;
    }

    public void agregarRutaAPaquete(String nombrePaquete, String nombreRuta,int cantidad, TipoAsiento tipoAsiento){
        if(!this.paquetes.containsKey(nombrePaquete)){
            throw new IllegalArgumentException("No existe este paquete");
        }
        if(!this.rutasDeVuelo.containsKey(nombreRuta)){
            throw new IllegalArgumentException("No existe esa ruta de vuelo");
        }

        Paquete p = this.paquetes.get(nombrePaquete);
        RutaDeVuelo ruta = this.rutasDeVuelo.get(nombreRuta);

        RutaEnPaquete rp = new RutaEnPaquete(cantidad,tipoAsiento,ruta);

        p.addRutaEnPaquete(rp);
        paqueteDao.addRutaEnPaquete(p, rp);
    }

    public List <DtVuelo> getVuelosRutaDeVuelo(String nombre){
        List <DtVuelo> vuelos = new ArrayList<>();

        for(Vuelo v : this.getVuelos()){
            if(v.getRutaDeVuelo().getNombre().equals(nombre)){
                vuelos.add(v.getDatos());
            }
        }
        return  vuelos;
    }

    public List<DtVuelo> listarVuelos(){
       List<DtVuelo> vuelos = new ArrayList<>();
         for(Vuelo v : this.getVuelos()){
              vuelos.add(v.getDatos());
         }
            return vuelos;
    }

    public List<DtVuelo> listarVuelos(String nombre){
       List<DtVuelo> vuelos = new ArrayList<>();
            for(Vuelo v : this.getVuelos()){
                if(v.getRutaDeVuelo().getNombre().equals(nombre)){
                    vuelos.add(v.getDatos());
                }
            }
                return vuelos;
    }

    public void crearPaqueteDeRutas(DtPaquete datosPaquete){
        Paquete paquete = paqueteDao.buscar((datosPaquete.getNombre()));
        if (paquete == null) { //Si el paquete no existe
            this.paqueteTemp = datosPaquete;
        } else {
            throw new IllegalArgumentException("El paquete ya existe.");
        }
    }

    public void confirmarAltaPaquete() {
        if (this.paqueteTemp == null){
            throw new IllegalArgumentException("Debe ingresar datos del paquete");
        }
        Paquete nuevo = new Paquete(paqueteTemp);

        paqueteDao.guardar(nuevo);

        this.paquetes.put(nuevo.getNombre(), nuevo);
        this.paqueteTemp = null;
    }

    public void cancelarAltaPquete(){
        paqueteTemp = null;
    }

    public void altaReserva(DtReserva reserva) {}

}

