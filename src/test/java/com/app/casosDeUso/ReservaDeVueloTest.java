package com.app.casosDeUso;

import com.app.clases.ISistema;
import com.app.datatypes.DtReserva;
import com.app.enums.MetodoPago;
import com.app.enums.TipoAsiento;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

public class ReservaDeVueloTest {

    static ISistema s;

    @BeforeAll
    static void setUp() {
        s = DummyFactory.crearSistema();
    }

    // ------------------ Reserva de Vuelo ------------------ //

//    @Test
//    void crearReservaDeVuelo() {
//        DtReserva reserva = new DtReserva(
//                LocalDate.now(),
//                TipoAsiento.TURISTA,
//                2,
//                1,
//                Arrays.asList(pasajeros.get(0), pasajeros.get(1)),
//                clientes.get(1),
//                vuelos.get(0),
//                MetodoPago.PAQUETE,
//                paquetes.get(0)
//        );
//
//        s.altaReserva(reserva);
//    }


}
