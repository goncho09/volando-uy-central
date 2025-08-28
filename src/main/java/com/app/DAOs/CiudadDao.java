package com.app.DAOs;

import com.app.clases.Ciudad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class CiudadDao {
    private EntityManager em;

    public CiudadDao(EntityManager em) {
        this.em = em;
    }

    public List<Ciudad> listarCiudades(){
        return em.createQuery("SELECT c FROM Ciudad c", Ciudad.class).getResultList();
    }


    public Ciudad buscar(String nombre){
        return em.find(Ciudad.class, nombre);
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
