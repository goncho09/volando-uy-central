package com.app.daos;

import java.util.Map;

import jakarta.persistence.EntityManager;

import com.app.clases.Categoria;
import com.app.clases.RutaDeVuelo;

public class RutaDeVueloDao extends BaseDao<RutaDeVuelo, String> {

    public RutaDeVueloDao(EntityManager em) {
        super(em, RutaDeVuelo.class);
    }

    public Map<String, RutaDeVuelo> obtenerRutasDeVuelo() {
        return super.obtener(RutaDeVuelo::getNombre);
    }

    public void addCategoria(RutaDeVuelo rt, Categoria categoria) {
        rt.getCategorias().add(categoria);
        actualizar(rt);
    }

    public void removeCategoria(RutaDeVuelo rt, Categoria categoria) {
        rt.getCategorias().remove(categoria);
        actualizar(rt);
    }
}
