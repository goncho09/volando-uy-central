package com.app.DAOs;

import com.app.clases.Aerolinea;
import com.app.clases.Cliente;
import com.app.clases.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class UserDao {
    private EntityManager em;

    public UserDao(EntityManager em) {
        this.em = em;
    }

    //Funciones específicas para Usuario (Cliente y Aerolínea)
    public List<Usuario> listarUsuarios(){
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList(); //Nos devuelve todos los usuarios, independientemente de su clase. (Clientes y Aerolineas)
    }

    public List<Cliente> listarClientes(){
        return em.createQuery("SELECT u FROM Cliente u", Cliente.class).getResultList(); // Retorna los usuarios con clase "Cliente".
    }

    public List<Aerolinea> listarAerolineas(){
        return em.createQuery("SELECT u FROM Aerolinea u", Aerolinea.class).getResultList(); // Retorna los usuarios con clase "Aerolínea" ñehehe.
    }

    public Usuario buscar(String nickname){
        return em.find(Usuario.class, nickname); // Saldrá a buscar a ese usuario en AMBAS clases.
    }

    public Aerolinea buscarAerolinea(String nickname){
        return em.find(Aerolinea.class, nickname); // Saldrá a buscar a ese usuario en AMBAS clases.
    }

    public void guardar(Usuario usuario) {
        System.out.println("Guardando usuario:" + usuario);
        EntityTransaction tx = em.getTransaction();
        try{ //Se intenta "guardar"
            tx.begin();
            em.persist(usuario);
            tx.commit();
        }catch(Exception e){ //Si llega a fallar se hace un rollback y se lanza el error a la capa de presentación
            if(tx.isActive()){
                tx.rollback();
            }
            throw e;
        }
    }

    public void actualizar(Usuario usuario) {
        EntityTransaction tx = em.getTransaction();
        try{ //Se intenta "modificar"
            tx.begin();
            em.merge(usuario);
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
        Usuario usuario = em.find(Usuario.class, nickname);
        if (usuario != null) {
            em.remove(usuario);
        }
        em.getTransaction().commit();
    }

}
