package clases;

import datatypes.DtCategoria;
import datatypes.DtCiudad;
import datatypes.DtPaquete;
import datatypes.DtVuelo;

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
        Paquete existe = this.paquetes.get(paquete.getNombre());
        if(existe != null ) { throw new IllegalArgumentException("Ya existe un paquete con ese nombre.");}
        Paquete p = new Paquete(paquete);
        this.paquetes.put(p.getNombre(),p);
    }

    public void altaCategoria(DtCategoria categoria){
        Categoria existe = this.categorias.get(categoria.getNombre());
        if(existe != null ) { throw new IllegalArgumentException("Ya existe una categoría con ese nombre.");}
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
}
