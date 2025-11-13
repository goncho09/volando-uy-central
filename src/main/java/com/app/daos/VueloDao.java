package com.app.daos;

import com.app.clases.Vuelo;
import jakarta.persistence.EntityManager;

import java.util.Map;


    public class VueloDao extends BaseDao<Vuelo, String> {

        public VueloDao(EntityManager em) {
            super(em, Vuelo.class);
        }


    }


