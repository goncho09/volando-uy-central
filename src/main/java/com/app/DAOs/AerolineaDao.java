package com.app.DAOs;

import com.app.clases.Aerolinea;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AerolineaDao {
        private EntityManager em;

        public AerolineaDao(EntityManager em) {
            this.em = em;
        }

        public List<Aerolinea> listarAerolineas(){
            return em.createQuery("SELECT a FROM Aerolinea a", Aerolinea.class).getResultList();
        }

        public Map<String, Aerolinea> obtenerAerolineas() {
            return em.createQuery("SELECT a FROM Aerolinea a", Aerolinea.class)
                    .getResultList()
                    .stream()
                    .collect(Collectors.toMap(Aerolinea::getNickname, r -> r));
        }

        public Aerolinea buscar(String nickname){
            return em.find(Aerolinea.class, nickname);
        }

        public void guardar(Aerolinea a) {
            EntityTransaction tx = em.getTransaction();
            try{ //Se intenta "guardar"
                tx.begin();
                em.persist(a);
                tx.commit();
            }catch(Exception e){ //Si llega a fallar se hace un rollback y se lanza el error a la capa de presentación
                if(tx.isActive()){
                    tx.rollback();
                }
                throw e;
            }
        }

        public void actualizar(Aerolinea a) {
            EntityTransaction tx = em.getTransaction();
            try{ //Se intenta "modificar"
                tx.begin();
                em.merge(a);
                tx.commit();
            }catch(Exception e){ //Si llega a fallar se hace un rollback y se lanza el error a la capa de presentación
                if(tx.isActive()){
                    tx.rollback();
                }
                throw e;
            }
        }

        public void eliminar(String nickname) {
            em.getTransaction().begin();
            Aerolinea a = em.find(Aerolinea.class, nickname);
            if (a != null) {
                em.remove(a);
            }
            em.getTransaction().commit();
        }

    }


