package com.app.casosDeUso;

import com.app.clases.ISistema;
import com.app.datatypes.*;
import com.app.enums.MetodoPago;
import com.app.enums.TipoAsiento;
import com.app.enums.TipoDocumento;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class ReservaDeVueloTest {

    static ISistema s;

    @BeforeAll
    static void setUp() {
        s = DummyFactory.crearSistema();
    }

    // ------------------ Reserva de Vuelo ------------------ //

    @Test
    void validarCantPasajes() {
        DtReserva reserva = new DtReserva(
                LocalDate.now(),
                TipoAsiento.EJECUTIVO,
                0,
                0,
                0,
                Arrays.asList(
                        new DtPasajero("Test", "Pasajes"),
                        new DtPasajero("Test", "Invalidos")
                ),
                s.getCliente("power"),
                s.getVuelo("Vuelo_3"), // Este vuelo debe existir y no estar reservado por "power"
                MetodoPago.PAQUETE,
                s.getPaquete("AventuraTotal") // Este paquete debe existir y con cantidad 1
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaReserva(reserva),
                "El sistema no debería crear una reserva con pasaje < 1"
        );
    }

    @Test
    void validarCantPasajesNegativa() {
        DtReserva reserva = new DtReserva(
                LocalDate.now(),
                TipoAsiento.EJECUTIVO,
                -10,
                0,
                0,
                Arrays.asList(
                        new DtPasajero("Test", "Pasajes"),
                        new DtPasajero("Test", "Invalidos")
                ),
                s.getCliente("power"),
                s.getVuelo("Vuelo_3"), // Este vuelo debe existir y no estar reservado por "power"
                MetodoPago.PAQUETE,
                s.getPaquete("AventuraTotal") // Este paquete debe existir y con cantidad 1
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaReserva(reserva),
                "El sistema no debería crear una reserva con pasaje < 1"
        );
    }

    @Test
    void validarCantPasajesMayorAMaxReservaVuelo() {
        DtReserva reserva = new DtReserva(
                LocalDate.now(),
                TipoAsiento.EJECUTIVO,
                95, // El Vuelo_3 debería tener 94 reservas restantes.
                0,
                0,
                Arrays.asList(
                        new DtPasajero("Test", "Pasajes"),
                        new DtPasajero("Test", "Invalidos")
                ),
                s.getCliente("power"),
                s.getVuelo("Vuelo_3"), // Este vuelo debe existir y no estar reservado por "power"
                MetodoPago.PAQUETE,
                s.getPaquete("AventuraTotal") // Este paquete debe existir y con cantidad 1
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaReserva(reserva),
                "El sistema no debería crear una reserva con cantPasajes > maxReserva (del vuelo)"
        );
    }

    @Test
    void validarCantPasajesMayorCantPasajeros() {
        DtReserva reserva = new DtReserva(
                LocalDate.now(),
                TipoAsiento.EJECUTIVO,
                3,
                0,
                0,
                Arrays.asList(
                        new DtPasajero("Test", "Pasajes"),
                        new DtPasajero("Test", "Invalidos")
                ),
                s.getCliente("power"),
                s.getVuelo("Vuelo_3"), // Este vuelo debe existir y no estar reservado por "power"
                MetodoPago.PAQUETE,
                s.getPaquete("AventuraTotal") // Este paquete debe existir y con cantidad 1
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaReserva(reserva),
                "El sistema no debería crear una reserva con cantPasajes != pasajeros.size()"
        );
    }

    @Test
    void validarCantPasajesMenorCantPasajeros() {
        DtReserva reserva = new DtReserva(
                LocalDate.now(),
                TipoAsiento.EJECUTIVO,
                1,
                0,
                0,
                Arrays.asList(
                        new DtPasajero("Test", "Pasajes"),
                        new DtPasajero("Test", "Invalidos")
                ),
                s.getCliente("power"),
                s.getVuelo("Vuelo_3"), // Este vuelo debe existir y no estar reservado por "power"
                MetodoPago.PAQUETE,
                s.getPaquete("AventuraTotal") // Este paquete debe existir y con cantidad 1
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaReserva(reserva),
                "El sistema no debería crear una reserva con cantPasajes != pasajeros.size()"
        );
    }

    @Test
    void validarReservaClienteInexistente() {
        DtCliente clienteInexistente = new DtCliente(
                "doesNotExist",
                "No",
                "imNotReal@gmail.com",
                "irreal",
                "imagen.404",
                "Existo",
                LocalDate.of(1969,12,15),
                "null",
                TipoDocumento.CEDULA,
                12345678
        );

        DtReserva reserva = new DtReserva(
                LocalDate.now(),
                TipoAsiento.EJECUTIVO,
                2,
                0,
                0,
                Arrays.asList(
                        new DtPasajero("Test", "Pasajes"),
                        new DtPasajero("Test", "Invalidos")
                ),
                clienteInexistente,
                s.getVuelo("Vuelo_3"), // Este vuelo debe existir y no estar reservado por "power"
                MetodoPago.PAQUETE,
                s.getPaquete("AventuraTotal") // Este paquete debe existir y con cantidad 1
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaReserva(reserva),
                "El sistema no debería crear una reserva con un cliente que no existe"
        );
    }

    @Test
    void validarReservaDeUnVueloYaReservadoPorElCliente() {
        DtReserva reserva = new DtReserva(
                LocalDate.now(),
                TipoAsiento.EJECUTIVO,
                2,
                0,
                0,
                Arrays.asList(
                        new DtPasajero("Test", "Pasajes"),
                        new DtPasajero("Test", "Invalidos")
                ),
                s.getCliente("power"),
                s.getVuelo("Vuelo_7"), // Este vuelo debe existir y estar reservado por "power"
                MetodoPago.PAQUETE,
                s.getPaquete("AventuraTotal") // Este paquete debe existir y con cantidad 1
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaReserva(reserva),
                "El sistema no debería crear una reserva para un vuelo ya reservado anteriormente por el cliente"
        );
    }

    @Test
    void validarReservaDeUnVueloInexistente() {
        DtVuelo vueloInexistente = new DtVuelo(
                "NoSeVolar",
                LocalDate.now().plusDays(10),
                LocalTime.of(2, 45),
                100,
                20,
                "vuelo.null",
                LocalDate.now(),
                s.getRutaDeVuelo("Montevideo-Lima"),
                0
        );

        DtReserva reserva = new DtReserva(
                LocalDate.now(),
                TipoAsiento.EJECUTIVO,
                2,
                0,
                0,
                Arrays.asList(
                        new DtPasajero("Test", "Pasajes"),
                        new DtPasajero("Test", "Invalidos")
                ),
                s.getCliente("power"),
                vueloInexistente,
                MetodoPago.PAQUETE,
                s.getPaquete("AventuraTotal") // Este paquete debe existir y con cantidad 1
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaReserva(reserva),
                "El sistema no debería crear una reserva para un vuelo que no existe"
        );
    }

    @Test
    void validarMetodoDePagoCorrespondaConElPago() {
        DtReserva reserva = new DtReserva(
                LocalDate.now(),
                TipoAsiento.EJECUTIVO,
                2,
                0,
                0,
                Arrays.asList(
                        new DtPasajero("Test", "Pasajes"),
                        new DtPasajero("Test", "Invalidos")
                ),
                s.getCliente("power"),
                s.getVuelo("Vuelo_3"), // Este vuelo debe existir y no estar reservado por "power"
                MetodoPago.GENERAL,
                s.getPaquete("AventuraTotal") // Este paquete debe existir y con cantidad 1
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaReserva(reserva),
                "El sistema no debería crear una reserva con método de pago 'General' si recibe un paquetePago"
        );
    }

    @Test
    void validarMetodoDePagoPaqueteConPaqueteInexistente() {
        DtPaquete paqueteInexistente = new DtPaquete(
          "PaqueteNotFoundXD",
          "No se encontró una descripcion",
          4,
          70
        );

        DtReserva reserva = new DtReserva(
                LocalDate.now(),
                TipoAsiento.EJECUTIVO,
                2,
                0,
                0,
                Arrays.asList(
                        new DtPasajero("Test", "Pasajes"),
                        new DtPasajero("Test", "Invalidos")
                ),
                s.getCliente("power"),
                s.getVuelo("Vuelo_3"), // Este vuelo debe existir y no estar reservado por "power"
                MetodoPago.PAQUETE,
                paqueteInexistente
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaReserva(reserva),
                "El sistema no debería crear una reserva si se paga con un paquete inexistente."
        );
    }

    @Test
    void validarMetodoDePagoPaqueteConClienteSinPaquete() {
        DtReserva reserva = new DtReserva(
                LocalDate.now(),
                TipoAsiento.EJECUTIVO,
                2,
                0,
                0,
                Arrays.asList(
                        new DtPasajero("Test", "Pasajes"),
                        new DtPasajero("Test", "Invalidos")
                ),
                s.getCliente("power"),
                s.getVuelo("Vuelo_3"), // Este vuelo debe existir y no estar reservado por "power"
                MetodoPago.PAQUETE,
                s.getPaquete("LujoMundial") // Este paquete debe existir y con cantidad 1
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaReserva(reserva),
                "El sistema no debería crear una reserva con método de pago 'Paquete' si recibe un paquetePago que no es del cliente"
        );
    }

    @Test
    void validarVueloConPaquetePago() {
        DtReserva reserva = new DtReserva(
                LocalDate.now(),
                TipoAsiento.EJECUTIVO,
                2,
                0,
                0,
                Arrays.asList(
                        new DtPasajero("Test", "Pasajes"),
                        new DtPasajero("Test", "Invalidos")
                ),
                s.getCliente("power"),
                s.getVuelo("Vuelo_5"), // Este vuelo debe existir, no estar reservado por "power" y no compartir rutas con el paquete
                MetodoPago.PAQUETE,
                s.getPaquete("AventuraTotal") // Este paquete debe existir y con cantidad 1
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaReserva(reserva),
                "El sistema no debería crear una reserva si el cliente trata de pagar con un paquete cuyas rutas no pertenezca el vuelo"
        );
    }

    // Fin Test Fallidos

    @Test
    void validarVueloConPagoGeneral() {
        DtReserva reserva = new DtReserva(
                LocalDate.now(),
                TipoAsiento.EJECUTIVO,
                2,
                0,
                0,
                Arrays.asList(
                        new DtPasajero("Chainsaw", "Man"),
                        new DtPasajero("Bloody", "Mary")
                ),
                s.getCliente("power"),
                s.getVuelo("Vuelo_4"), // Este vuelo debe existir y no estar reservado por "power"
                MetodoPago.GENERAL
        );

        Assertions.assertDoesNotThrow(
                () -> s.altaReserva(reserva),
                "El sistema debería crear la reserva con coste 'normal'"
        );
    }

    @Test
    void validarCantPasajesMayorCantPaquete() {
        DtReserva reserva = new DtReserva(
                LocalDate.now(),
                TipoAsiento.EJECUTIVO,
                4,
                0,
                0,
                Arrays.asList(
                        new DtPasajero("Denji", "Csm"),
                        new DtPasajero("Makima", "Iris"),
                        new DtPasajero("Reze", "¡Boom!"),
                        new DtPasajero("Aki", "Kon")
                ),
                s.getCliente("power"),
                s.getVuelo("Vuelo_3"), // Este vuelo debe existir y no estar reservado por "power"
                MetodoPago.PAQUETE,
                s.getPaquete("AventuraTotal") // Este paquete debe existir y con cantidad 1
        );

        Assertions.assertDoesNotThrow(
                () -> s.altaReserva(reserva),
                "El sistema debería crear una reserva con costo reducido debido al paquete (coste total - 1 de pasaje)."
        );
    }


}
