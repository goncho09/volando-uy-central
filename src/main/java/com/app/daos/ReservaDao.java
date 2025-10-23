package com.app.daos;

import com.app.clases.Reserva;
import com.app.datatypes.DtPasajero;
import jakarta.persistence.EntityManager;

public class ReservaDao extends BaseDao<Reserva, String> {

    public ReservaDao(EntityManager em) {
        super(em, Reserva.class);
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
