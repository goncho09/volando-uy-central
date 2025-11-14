package com.app.daos;

import com.app.clases.Cliente;
import com.app.clases.Vuelo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import com.app.clases.Reserva;
import com.app.datatypes.DtPasajero;

import java.time.LocalDate;

public class ReservaDao extends BaseDao<Reserva, String> {

    public ReservaDao(EntityManager em) {
        super(em, Reserva.class);
    }

    public Reserva buscar(Cliente cliente, Vuelo vuelo, LocalDate fecha) {
        try {
            return super.getEm().createQuery(
                            "SELECT r FROM Reserva r WHERE r.cliente = :cliente AND r.fecha = :fecha AND r.vuelo = :vuelo",
                            Reserva.class
                    )
                    .setParameter("cliente", cliente)
                    .setParameter("fecha", fecha)
                    .setParameter("vuelo", vuelo)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void addPasajero(Reserva reserva, DtPasajero dtPasajero) {
        reserva.getPasajeros().add(dtPasajero);
        actualizar(reserva);
    }

    public void removePasajero(Reserva reserva, DtPasajero dtPasajero) {
        reserva.getPasajeros().remove(dtPasajero);
        actualizar(reserva);
    }

}
