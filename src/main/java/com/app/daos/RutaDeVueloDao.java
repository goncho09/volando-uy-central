package com.app.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.app.datatypes.DtRuta;
import jakarta.persistence.EntityManager;

import com.app.clases.RutaDeVuelo;
import jakarta.persistence.TypedQuery;

public class RutaDeVueloDao extends BaseDao<RutaDeVuelo, String> {

    public RutaDeVueloDao(EntityManager em) {
        super(em, RutaDeVuelo.class);
    }

    public Map<String, RutaDeVuelo> obtenerRutasDeVuelo() {
        return super.obtener(RutaDeVuelo::getNombre);
    }

    public List<DtRuta> getTop5() {
        List<DtRuta> top5 = new ArrayList<>();
        List<RutaDeVuelo> rutas = getEm().createQuery("SELECT r FROM RutaDeVuelo r ORDER BY r.vecesVisitada DESC", RutaDeVuelo.class).setMaxResults(5).getResultList();
        ;
        for (RutaDeVuelo r : rutas) {
            top5.add(r.getDatos());
        }
        ;
        return top5;
    }

    public List<DtRuta> buscarPorNombre(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String patron = "%" + texto.trim().toLowerCase() + "%";
        String inicio = texto.trim().toLowerCase() + "%";

        String jpql = """
        SELECT r FROM RutaDeVuelo r
        WHERE LOWER(r.nombre) LIKE :patron
           OR LOWER(r.descripcion) LIKE :patron
        ORDER BY 
            CASE WHEN LOWER(r.nombre) LIKE :inicio THEN 0 ELSE 1 END,
            r.nombre
        """;

        List<RutaDeVuelo> resultados = getEm().createQuery(jpql, RutaDeVuelo.class)
                .setParameter("patron", patron)
                .setParameter("inicio", inicio)
                .getResultList();

        // SIN stream, SIN map, SIN Collectors → for clásico
        List<DtRuta> dtos = new ArrayList<>();
        for (RutaDeVuelo r : resultados) {
            dtos.add(r.getDatos());  // usamos getDatos() como corresponde
        }

        return dtos;
    }
}