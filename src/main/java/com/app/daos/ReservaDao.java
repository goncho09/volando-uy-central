package com.app.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import com.app.clases.Reserva;
import com.app.datatypes.DtPasajero;

import java.time.LocalDate;

public class ReservaDao extends BaseDao<Reserva, String> {

    public ReservaDao(EntityManager em) {
        super(em, Reserva.class);
    }

    public Reserva buscar(String nickname, String nombreVuelo, LocalDate fecha) {
        try {
            return super.getEm().createQuery(
                            "SELECT r FROM Reserva r WHERE r.cliente = :cliente AND r.fecha = :fecha AND r.vuelo = :vuelo",
                            Reserva.class
                    )
                    .setParameter("cliente", nickname)
                    .setParameter("fecha", fecha)
                    .setParameter("vuelo", nombreVuelo)
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
