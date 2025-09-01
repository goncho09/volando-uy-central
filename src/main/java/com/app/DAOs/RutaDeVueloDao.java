package com.app.DAOs;

import com.app.clases.Categoria;
import com.app.clases.Ciudad;
import com.app.clases.RutaDeVuelo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RutaDeVueloDao {
    private EntityManager em;

    public RutaDeVueloDao(EntityManager em) {
        this.em = em;
    }

    public List<RutaDeVuelo> listarRutasDeVuelo(){
        return em.createQuery("SELECT rt FROM RutaDeVuelo rt", RutaDeVuelo.class).getResultList();
    }

    public Map<String, RutaDeVuelo> obtenerRutasDeVuelo() {
        return em.createQuery("SELECT r FROM RutaDeVuelo r", RutaDeVuelo.class)
                .getResultList()
                .stream()
                .collect(Collectors.toMap(RutaDeVuelo::getNombre, r -> r));
    }

    public RutaDeVuelo buscar(String nombre){
        return em.find(RutaDeVuelo.class, nombre);
    }



    public void guardar(RutaDeVuelo rt) {
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

    public void addCategoria(RutaDeVuelo rt, Categoria categoria) {
        rt.getCategorias().add(categoria);
        actualizar(rt);
    }

    public void removeCategoria(RutaDeVuelo rt, Categoria categoria) {
        rt.getCategorias().remove(categoria);
        actualizar(rt);
    }
}
