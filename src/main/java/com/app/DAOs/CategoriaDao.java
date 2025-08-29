package com.app.DAOs;

import com.app.clases.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CategoriaDao {
    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public List<Categoria> listarCategorias(){
        return em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
    }

    public Map<String, Categoria> obtenerCategorias() {
        return em.createQuery("SELECT c FROM Categoria c", Categoria.class)
                .getResultList()
                .stream()
                .collect(Collectors.toMap(Categoria::getNombre, c -> c));
    }


    public Categoria buscar(String nombre){
        return em.find(Categoria.class, nombre);
    }

    public void guardar(Categoria c) {
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

    public void actualizar(Categoria c) {
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
        Categoria c = em.find(Categoria.class, nombre);
        if (c != null) {
            em.remove(c);
        }
        em.getTransaction().commit();
    }
}
