package clases;

import datatypes.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Sistema implements ISistema {
    private static Sistema instancia;
    private Map<String, Categoria> categorias;
    private Map<String, Ciudad> ciudades;
    private Map<String, Usuario> usuarios;
    private Map<String, Paquete> paquetes;
    private Map<String, Vuelo> vuelos;

    private Paquete paqueteSeleccionado;
    private Cliente clienteTemporal;
    private Aerolinea aerolineaTemporal;

    private Sistema() {
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

    public List<DtPaquete> listarPaquetes(){
        if(this.paquetes.isEmpty()) { throw new IllegalArgumentException("No hay paquetes.");}
        List <DtPaquete> dtPaquetes = new ArrayList<>();
        for (Map.Entry<String, Paquete> entry : paquetes.entrySet()) {
            Paquete p = entry.getValue();
            DtPaquete paquete = new DtPaquete(p.getNombre(),p.getDescripcion(),p.getValidezDias(),p.getDescuento(),p.getCosto(),p.getRutasDeVuelo(),p.getRutaEnPaquete());
            dtPaquetes.add(paquete);
        }
        return dtPaquetes;
    }

    public void seleccionarPaquete(String nombre){
        Paquete paquete  = this.paquetes.get(nombre);
        if(paquete == null ) { throw new IllegalArgumentException("No existe un paquete con ese nombre.");}
        this.paqueteSeleccionado = paquete;
    }

    public DtPaquete getPaquete(){
        return this.paqueteSeleccionado.getDatos();
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
        if(this.usuarios.containsKey(cliente.getNickname())) { throw new IllegalArgumentException("Este usuario ya existe.");}

        for (Usuario existente : this.usuarios.values()) {
            if (existente.getEmail().equals(cliente.getEmail())) {
                throw new IllegalArgumentException("Ya existe un usuario con ese email.");
            }
        }

        this.clienteTemporal = new Cliente(cliente);
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
            this.clienteTemporal = c;
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

        this.aerolineaTemporal = new Aerolinea(aerolinea);
    };

    public void modificarAerolinea(DtAerolinea aerolinea){
        Usuario u = this.usuarios.get(aerolinea.getNickname());
        if(u == null) { throw new IllegalArgumentException("Este usuario no existe.");}

        if (u instanceof Aerolinea a) {
            a.setNombre(aerolinea.getNombre());
            a.setDescripcion(aerolinea.getDescripcion());
            a.setLinkWeb(aerolinea.getLinkWeb());
            this.aerolineaTemporal = a;
        }else{
            throw new IllegalArgumentException("Este usuario es cliente.");
        }
    };

    public void confirmarAltaUsuario(){
        if(this.clienteTemporal != null){
            this.usuarios.put(this.clienteTemporal .getNickname(),this.clienteTemporal);
            this.clienteTemporal = null;
        }else{
            this.usuarios.put(this.aerolineaTemporal.getNickname(),this.aerolineaTemporal);
            this.aerolineaTemporal = null;
        }
    };

    public void cancelarAltaUsuario(){
        this.clienteTemporal = null;
        this.aerolineaTemporal = null;
    }
}
