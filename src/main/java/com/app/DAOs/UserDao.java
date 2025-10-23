package com.app.DAOs;


import jakarta.persistence.EntityManager;

import com.app.clases.Usuario;
import com.app.clases.Aerolinea;
import com.app.clases.RutaDeVuelo;
import com.app.clases.Cliente;
import com.app.clases.Reserva;
import com.app.clases.CompraPaquete;

import java.util.Map;

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

}
