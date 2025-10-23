package com.app.casosDeUso;

import com.app.clases.ISistema;
import com.app.datatypes.DtRuta;
import com.app.datatypes.DtVuelo;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.app.enums.EstadoRuta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;


public class AceptarRechazarVueloTest {

    static ISistema s;

    @BeforeAll
    static void setUp() {
        s = DummyFactory.crearSistema();
    }

    // ------------------ Aceptar Rechazar Rutas De Vuelo ------------------ //

    @Test
    void CambioDeEstadoAprobado() {

        DtRuta rutaCreada = s.consultarRuta("Rio de Janeiro-Asunción");
        s.actualizarEstadoRuta(rutaCreada, EstadoRuta.APROBADA);
        rutaCreada = s.consultarRuta("Rio de Janeiro-Asunción");

        Assertions.assertEquals(
                EstadoRuta.APROBADA,
                rutaCreada.getEstado(),
                "El estado de la ruta debería ser APROBADO"
        ) ;

    }

    @Test
    void CambioDeEstadoRechazado() {

        DtRuta rutaCreada = s.consultarRuta("Santiago-Lima");
        s.actualizarEstadoRuta(rutaCreada, EstadoRuta.RECHAZADA);
        rutaCreada = s.consultarRuta("Santiago-Lima");
        Assertions.assertEquals(
                EstadoRuta.RECHAZADA,
                rutaCreada.getEstado(),
                "El estado de la ruta debería ser RECHAZADO"
        );
    }

    @Test
    void ActualizarEstadoAlmismoEstado(){
        DtRuta rutaCreada = s.consultarRuta("Santiago-Lima");
        s.actualizarEstadoRuta(rutaCreada, EstadoRuta.INGRESADA);
        rutaCreada = s.consultarRuta("Santiago-Lima");

        Assertions.assertEquals(
                EstadoRuta.INGRESADA,
                rutaCreada.getEstado(),
                "El estado de la ruta debería ser INGRESADA el mismo que deberia tener"
        );
    }

    @Test
    void ActualizarEstadoRutaNoExistente(){
        DtRuta rutaNoExistente = new DtRuta(
                "Ruta Inexistente",
                "Descripcion de ruta inexistente",
                "Descripcion corta de ruta inexistente",
                LocalTime.of(2, 0),
                150.0f,
                300.0f,
                20.0f,
                LocalDate.now(),
                "http://imagenruta.com/ruta_inexistente.jpg",
                Arrays.asList(),
                s.getCiudad("Lima", "Perú"),
                s.getCiudad("Montevideo", "Uruguay")
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.actualizarEstadoRuta(rutaNoExistente, EstadoRuta.APROBADA),
                "actualizarEstadoRuta debería lanzar una excepción para una ruta que no existe en el sistema"
        );

    }
}
