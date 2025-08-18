package clases;

import datatypes.DtCategoria;
import java.util.LinkedHashMap;
import java.util.Map;

public class Sistema implements ISistema {
    private static Sistema instancia;
    private Map<String, Categoria> categorias;

    private Sistema() {
        this.categorias = new LinkedHashMap<>();
    }

    public static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    @Override
    public void ejecutar() {
        System.out.println("Sistema ejecutando...");
    }

    public void altaCategoria(DtCategoria categoria){
        Categoria existe = this.categorias.get(categoria.getNombre());
        if(existe != null ) { throw new IllegalArgumentException("Ya existe una categoría con ese nombre.");}
        Categoria c = new Categoria(categoria);
        this.categorias.put(c.getNombre(),c);

    }
}
