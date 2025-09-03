package com.app.DAOs;

import com.app.clases.Ciudad;
import jakarta.persistence.EntityManager;

import java.util.Map;

public class CiudadDao extends BaseDao<Ciudad, String>{

    public CiudadDao(EntityManager em) {
        super(em, Ciudad.class);
    }

    public Map<String, Ciudad> obtenerCiudades() {
        return super.obtener(Ciudad::getNombre);
    }

}
