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

    //agrego un buscar por nombre y país
    public Ciudad buscarPorNombreYPais(String nombre, String pais) {
        try {
            return em.createQuery("SELECT c FROM Ciudad c WHERE c.nombre = :nombre AND c.pais = :pais", Ciudad.class)
                    .setParameter("nombre", nombre)
                    .setParameter("pais", pais)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void guardar(Ciudad c) {
        EntityTransaction tx = em.getTransaction();
        try{ //Se intenta "guardar"
            tx.begin();
            em.persist(c);
            tx.commit();
        }catch(Exception e){ //Si llega a fallar se hace un rollback y se lanza el error a la capa de presentación
            if(tx.isActive()){
                tx.rollback();
            }
            throw e;
        }
    }

    public void actualizar(Ciudad c) {
        EntityTransaction tx = em.getTransaction();
        try{ //Se intenta "modificar"
            tx.begin();
            em.merge(c);
            tx.commit();
        }catch(Exception e){ //Si llega a fallar se hace un rollback y se lanza el error a la capa de presentación
            if(tx.isActive()){
                tx.rollback();
            }
            throw e;
        }
    }

    public void eliminar(String nombre) {
        em.getTransaction().begin();
        Ciudad c = em.find(Ciudad.class, nombre);
        if (c != null) {
            em.remove(c);
        }
        em.getTransaction().commit();
    }
}
