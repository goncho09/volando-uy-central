package com.app.DAOs;

import com.app.clases.*;
import com.app.clases.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserDao {
    private EntityManager em;

    public UserDao(EntityManager em) {
        this.em = em;
    }

    //Funciones específicas para Usuario (Cliente y Aerolínea)
    public List<Usuario> listarUsuarios(){
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList(); //Nos devuelve todos los usuarios, independientemente de su clase. (Clientes y Aerolineas)
    }

    public Map<String, Usuario> obtenerUsuarios() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class)
                .getResultList()
                .stream()
                .collect(Collectors.toMap(Usuario::getNickname, u-> u));
    }

    public Usuario buscar(String nickname){
        return em.find(Usuario.class, nickname); // Saldrá a buscar a ese usuario en AMBAS clases.
    }

    public void guardar(Usuario usuario) {
        if(usuario instanceof Cliente cliente){
            System.out.println("Guardando Cliente: " + cliente);
            cliente.mostrarDatos();
        }else if(usuario instanceof Aerolinea aerolinea){
            System.out.println("Guardando Aerolinea: " + aerolinea);
            aerolinea.mostrarDatos();
        }

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

    public void addRutaDeVuelo(Aerolinea aerolinea, RutaDeVuelo ruta){
        aerolinea.getRutasDeVuelo().add(ruta);
        actualizar(aerolinea);
    }

    public void addCompraPaquete(Cliente cliente, CompraPaquete cp){
        cliente.getComprasPaquetes().add(cp);
        actualizar(cliente);
    }

    public void addReserva(Cliente cliente, Reserva reserva){
        cliente.getReservas().add(reserva);
        actualizar(cliente);
    }

}
