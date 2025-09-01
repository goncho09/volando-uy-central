package com.app.DAOs;

import com.app.clases.Vuelo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


    public class VueloDao {
        private EntityManager em;

        public VueloDao(EntityManager em) {
            this.em = em;
        }

        public List<Vuelo> listarVuelos(){
            return em.createQuery("SELECT v FROM Vuelo v", Vuelo.class).getResultList();
        }


        public Map<String, Vuelo> obtenerVuelos() {
            return em.createQuery("SELECT v FROM Vuelo v", Vuelo.class)
                    .getResultList()
                    .stream()
                    .collect(Collectors.toMap(Vuelo::getNombre, p -> p));
        }

        public Vuelo buscar(String nombre){
            return em.find(Vuelo.class, nombre);
        }

        public void guardar(Vuelo v) {
            EntityTransaction tx = em.getTransaction();
            try{ //Se intenta "guardar"
                tx.begin();
                em.persist(v);
                tx.commit();
            }catch(Exception e){ //Si llega a fallar se hace un rollback y se lanza el error a la capa de presentación
                if(tx.isActive()){
                    tx.rollback();
                }
                throw e;
            }
        }

        public void actualizar(Vuelo v) {
            EntityTransaction tx = em.getTransaction();
            try{ //Se intenta "modificar"
                tx.begin();
                em.merge(v);
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
            Vuelo v = em.find(Vuelo.class, nombre);
            if (v != null) {
                em.remove(v);
            }
            em.getTransaction().commit();
        }

    }


