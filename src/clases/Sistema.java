package clases;

import datatypes.DtCategoria;
import datatypes.DtCiudad;

import java.util.LinkedHashMap;
import java.util.Map;

public class Sistema implements ISistema {
    private static Sistema instancia;
    private Map<String, Categoria> categorias;
    private Map<String, Ciudad> ciudades;

    private Sistema() {
        this.categorias = new LinkedHashMap<>();
        this.ciudades = new LinkedHashMap<>();
    }

    public static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
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
}
