package com.app.daos;

import java.util.List;
import java.util.Map;

import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.persistence.EntityManager;

import com.app.clases.Usuario;
import com.app.clases.Aerolinea;
import com.app.clases.RutaDeVuelo;
import com.app.clases.Cliente;
import com.app.clases.Reserva;
import com.app.clases.CompraPaquete;

public class UserDao extends BaseDao<Usuario, String> {

    public UserDao(EntityManager em) {
        super(em, Usuario.class);
    }

    public Map<String, Usuario> obtenerUsuarios() {
        return super.obtener(Usuario::getNickname);
    }


    // ----------- AEROLINEAS ----------- //

    public void addRutaDeVuelo(Aerolinea aerolinea, RutaDeVuelo ruta){
        aerolinea.getRutasDeVuelo().add(ruta);
        actualizar(aerolinea);
    }

    public void removeRutaDeVuelo(Aerolinea aerolinea, RutaDeVuelo ruta){
        aerolinea.getRutasDeVuelo().remove(ruta);
        actualizar(aerolinea);
    }

    public Aerolinea busarAerolinea(String nickname){
        return super.getEm().find(Aerolinea.class, nickname);
    }

    public List<Aerolinea> listarAerolineas() {
        return super.getEm().createQuery("SELECT a FROM Aerolinea a", Aerolinea.class)
                .getResultList();
    }

    public Map<String, Aerolinea> obtenerAerolineas(Function<Aerolinea, String> keyMapper) {
        return listarAerolineas()
                .stream()
                .collect(Collectors.toMap(keyMapper, e -> e));
    }

    public boolean existeAerolinea(String nickname) {
        Aerolinea aerolinea = super.getEm().find(Aerolinea.class, nickname);
        return aerolinea != null;
    }

    // ------------ CLIENTES ----------- //

    public void addCompraPaquete(Cliente cliente, CompraPaquete cp){
        cliente.getComprasPaquetes().add(cp);
        actualizar(cliente);
    }

    public void removeCompraPaquete(Cliente cliente, CompraPaquete cp){
        cliente.getComprasPaquetes().remove(cp);
        actualizar(cliente);
    }

    public void addReserva(Cliente cliente, Reserva reserva){
        cliente.getReservas().add(reserva);
        actualizar(cliente);
    }

    public void removeReserva(Cliente cliente, Reserva reserva){
        cliente.getReservas().remove(reserva);
        actualizar(cliente);
    }

    public Cliente buscarCliente(String nickname){
        return super.getEm().find(Cliente.class, nickname);
    }

    public List<Cliente> listarClientes() {
        return super.getEm().createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList();
    }

    public Map<String, Cliente> obtenerClientes(Function<Cliente, String> keyMapper) {
        return listarClientes()
                .stream()
                .collect(Collectors.toMap(keyMapper, e -> e));
    }

    public boolean existeCliente(String nickname) {
        Cliente cliente = super.getEm().find(Cliente.class, nickname);
        return cliente != null;
    }

}
