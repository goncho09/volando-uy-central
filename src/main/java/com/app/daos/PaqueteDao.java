package com.app.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.app.datatypes.DtPaquete;
import jakarta.persistence.EntityManager;

import com.app.clases.Paquete;
import com.app.clases.RutaEnPaquete;

import com.app.datatypes.DtRutaEnPaquete;

public class PaqueteDao extends BaseDao<Paquete, String> {

    public PaqueteDao(EntityManager em) {
        super(em, Paquete.class);
    }


    public void addRutaEnPaquete(Paquete p, RutaEnPaquete rp){
        p.addRutaEnPaquete(rp);
        actualizar(p);
    }

    public void removeRutaEnPaquete(Paquete p, RutaEnPaquete rp){
        p.getRutaEnPaquete().remove(rp);
        actualizar(p);
    }

    public int actualizarCantidadRutaEnPaquete(Paquete p, DtRutaEnPaquete rp, int cantidad){
        rp.setCantidad(rp.getCantidad() + cantidad);
        actualizar(p);
        return  rp.getCantidad();
    }

    public List<DtPaquete> buscarPorPatron(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String patron = "%" + texto.trim().toLowerCase() + "%";
        String inicio = texto.trim().toLowerCase() + "%";

        String jpql = """
        SELECT p FROM Paquete p
        WHERE LOWER(p.nombre) LIKE :patron
           OR LOWER(p.descripcion) LIKE :patron
        ORDER BY 
            CASE WHEN LOWER(p.nombre) LIKE :inicio THEN 0 ELSE 1 END,
            p.nombre
        """;

        List<Paquete> resultados = getEm().createQuery(jpql, Paquete.class)
                .setParameter("patron", patron)
                .setParameter("inicio", inicio)
                .getResultList();

        List<DtPaquete> dtos = new ArrayList<>();
        for (Paquete p : resultados) {
            dtos.add(p.getDatos());
        }

        return dtos;
    }

}
