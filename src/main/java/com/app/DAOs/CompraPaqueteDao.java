package com.app.DAOs;

import com.app.clases.CompraPaquete;
import com.app.clases.Paquete;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CompraPaqueteDao {
    private EntityManager em;

    public CompraPaqueteDao(EntityManager em) {
        this.em = em;
    }

    public List<CompraPaquete> listarCompraPaquete(){
        return em.createQuery("SELECT cp FROM CompraPaquete cp", CompraPaquete.class).getResultList();
    }

    public Paquete buscar(String nombre){
        return em.find(Paquete.class, nombre);
    }

    public void guardar(Paquete p) {
        EntityTransaction tx = em.getTransaction();
        try{ //Se intenta "guardar"
            tx.begin();
            em.persist(p);
            tx.commit();
        }catch(Exception e){ //Si llega a fallar se hace un rollback y se lanza el error a la capa de presentación
            if(tx.isActive()){
                tx.rollback();
            }
            throw e;
        }
    }

    public void actualizar(Paquete p) {
        EntityTransaction tx = em.getTransaction();
        try{ //Se intenta "modificar"
            tx.begin();
            em.merge(p);
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
        Paquete p = em.find(Paquete.class, nombre);
        if (p != null) {
            em.remove(p);
        }
        em.getTransaction().commit();
    }
}
