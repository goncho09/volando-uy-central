package com.app.DAOs;

import com.app.clases.RutaDeVuelo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class RutaDeVueloDao {
    private EntityManager em;

    public RutaDeVueloDao(EntityManager em) {
        this.em = em;
    }

    public List<RutaDeVuelo> listarRutasDeVuelo(){
        return em.createQuery("SELECT rt FROM RutaDeVuelo rt", RutaDeVuelo.class).getResultList();
    }


    public RutaDeVuelo buscar(String nombre){
        return em.find(RutaDeVuelo.class, nombre);
    }

    public void guardar(RutaDeVuelo rt) {
        System.out.println("Guardando ruta de vuelo:" + rt);
        EntityTransaction tx = em.getTransaction();
        try{ //Se intenta "guardar"
            tx.begin();
            em.persist(rt);
            tx.commit();
        }catch(Exception e){ //Si llega a fallar se hace un rollback y se lanza el error a la capa de presentación
            if(tx.isActive()){
                tx.rollback();
            }
            throw e;
        }
    }

    public void actualizar(RutaDeVuelo rt) {
        EntityTransaction tx = em.getTransaction();
        try{ //Se intenta "modificar"
            tx.begin();
            em.merge(rt);
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
        RutaDeVuelo rt = em.find(RutaDeVuelo.class, nombre);
        if (rt != null) {
            em.remove(rt);
        }
        em.getTransaction().commit();
    }
}
