package com.app.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.app.datatypes.DtRuta;
import jakarta.persistence.EntityManager;

import com.app.clases.RutaDeVuelo;

public class RutaDeVueloDao extends BaseDao<RutaDeVuelo, String> {

    public RutaDeVueloDao(EntityManager em) {
        super(em, RutaDeVuelo.class);
    }

    public Map<String, RutaDeVuelo> obtenerRutasDeVuelo() {
        return super.obtener(RutaDeVuelo::getNombre);
    }

    public List<DtRuta> getTop5(){
        List<DtRuta> top5 = new ArrayList<>();
        List<RutaDeVuelo> rutas = getEm().createQuery("SELECT r FROM RutaDeVuelo r ORDER BY r.vecesVisitada DESC", RutaDeVuelo.class).setMaxResults(5).getResultList();;
        for(RutaDeVuelo r : rutas){
            top5.add(r.getDatos());
        };
        return top5;
    }
}
