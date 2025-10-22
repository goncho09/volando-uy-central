package com.app.casosDeUso;

import com.app.clases.ISistema;
import com.app.datatypes.DtPasajero;
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
//    void validarCantPasajes() {
//        DtReserva reserva = new DtReserva(
//                LocalDate.now(),
//                TipoAsiento.EJECUTIVO,
//                0, // El rango debe ser 1 - maxReserva
//                1,
//                0,
//                Arrays.asList(
//                        new DtPasajero("Test", "Pasajes"),
//                        new DtPasajero("Test", "Invalidos")
//                        ),
//                s.getCliente("power"),
//                s.getVuelo("Vuelo_4"), // Este vuelo debe existir y no estar reservado por "power"
//                MetodoPago.PAQUETE,
//                s.getPaquete("")
//        );
//
//        s.altaReserva(reserva);
//    }


}
