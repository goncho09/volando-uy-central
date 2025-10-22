package com.app.casosDeUso;

import com.app.clases.ISistema;
import com.app.datatypes.DtRuta;
import com.app.datatypes.DtVuelo;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class AltaDeVueloTest {

    static ISistema s;

    @BeforeAll
    static void setUp() {
        s = DummyFactory.crearSistema();
    }

    // ------------------ Alta de Vuelo ----------------- //

    @Test
    void validarNombreVacioAltaVuelo(){
        DtVuelo vuelo = new DtVuelo(
                "",
                LocalDate.now().plusDays(200),
                LocalTime.of(2,30),
                3,
                3,
                "default.png",
                LocalDate.now(),
                s.getRutaDeVuelo("Lima-Montevideo Norte"), // Es una ruta con estado Aprobada.
                0
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaVuelo(vuelo),
                "El sistema no debería crear el vuelo ya que su nombre es vacío."
        );

    }

    @Test
    void validarNombreExistenteAltaVuelo(){
        DtVuelo vuelo = new DtVuelo(
                "Vuelo1", //Este nombre debería existir.
                LocalDate.now().plusDays(200),
                LocalTime.of(2,30),
                3,
                3,
                "default.png",
                LocalDate.now(),
                s.getRutaDeVuelo("Lima-Montevideo Norte"), // Es una ruta con estado Aprobada.
                0
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaVuelo(vuelo),
                "El sistema no debería crear el vuelo ya que su nombre ya existe en el sistema."
        );

    }

    @Test
    void validarFechaInvalidaAltaVuelo(){
        DtVuelo vuelo = new DtVuelo(
                "VueloTestFecha",
                LocalDate.now().minusDays(1), // Fecha inválida
                LocalTime.of(2,30),
                3,
                3,
                "default.png",
                LocalDate.now(),
                s.getRutaDeVuelo("Lima-Montevideo Norte"), // Es una ruta con estado Aprobada.
                0
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaVuelo(vuelo),
                "El sistema no debería crear el vuelo ya que su fecha de salida es 'antigua'."
        );
    }

    @Test
    void validarHoraInvalidaAltaVuelo(){
        DtVuelo vuelo = new DtVuelo(
                "VueloTestHora",
                LocalDate.now().plusDays(200),
                LocalTime.of(0,0), // Hora inválida
                3,
                3,
                "default.png",
                LocalDate.now(),
                s.getRutaDeVuelo("Lima-Montevideo Norte"), // Es una ruta con estado Aprobada.
                0
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaVuelo(vuelo),
                "El sistema no debería crear el vuelo ya que su duración es de 0."
        );
    }

    @Test
    void validarCupoInvalidoAltaVuelo(){
        DtVuelo vuelo = new DtVuelo(
                "VueloTestCupo",
                LocalDate.now().plusDays(200),
                LocalTime.of(2,30),
                0, // Entre ambos cupos
                0, // Deberian sumar 1 o más.
                "default.png",
                LocalDate.now(),
                s.getRutaDeVuelo("Lima-Montevideo Norte"), // Es una ruta con estado Aprobada.
                0
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaVuelo(vuelo),
                "El sistema no debería crear el vuelo ya que su cupo total es de 0."
        );
    }

    @Test
    void validarRutaInvalidaRechazadaAltaVuelo(){
        DtVuelo vuelo = new DtVuelo(
                "VueloTestRutaRechazada",
                LocalDate.now().plusDays(200),
                LocalTime.of(2,30),
                3,
                3,
                "default.png",
                LocalDate.now(),
                s.getRutaDeVuelo("Montevideo Norte-Quito"), // Es una ruta con estado Rechazada.
                0
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaVuelo(vuelo),
                "El sistema no debería crear el vuelo ya que su rutaDeVuelo está rechazada."
        );
    }

    @Test
    void validarRutaInvalidaIngresadaAltaVuelo(){
        DtVuelo vuelo = new DtVuelo(
                "VueloTestRutaIngresada",
                LocalDate.now().plusDays(200),
                LocalTime.of(2,30),
                3,
                3,
                "default.png",
                LocalDate.now(),
                s.getRutaDeVuelo("Santiago-Lima"), // Es una ruta con estado Ingresada.
                0
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaVuelo(vuelo),
                "El sistema no debería crear el vuelo ya que su rutaDeVuelo está Ingresada."
        );
    }

    @Test
    void validarRutaNoExistenteAltaVuelo(){
        DtRuta ruta = new DtRuta( //Esta creación literalmente no sirve ni se persiste en la BD.
                "Esto Es La Ruta Mas Inexistente Del Mundo Jeje",
                "Ruta entre Bogotá y Santiago",
                "Bogotá a Santiago",
                LocalTime.of(5, 0),
                250,
                500,
                30, LocalDate.now(),
                "default.png",
                Arrays.asList(s.buscarCategoria("Negocios")),
                s.buscarCiudad("Santiago", "Chile").getDatos(),
                s.buscarCiudad("Quito", "Ecuador").getDatos()
        );

        DtVuelo vuelo = new DtVuelo(
                "VueloTestFecha",
                LocalDate.now().plusDays(200),
                LocalTime.of(2,30),
                3,
                3,
                "default.png",
                LocalDate.now(),
                ruta, // Es una ruta con estado Ingresada.
                0
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaVuelo(vuelo),
                "El sistema no debería crear el vuelo ya que su rutaDeVuelo no está Ingresada en el sistema."
        );
    }

    @Test
    void validarVueloInexistenteAltaVuelo(){
        DtVuelo vuelo = new DtVuelo(
                "Vuelo11",
                LocalDate.now().plusDays(200),
                LocalTime.of(2,30),
                3,
                3,
                "default.png",
                LocalDate.now(),
                s.getRutaDeVuelo("Rio de Janeiro-Bogotá"), // Es una ruta con estado Aprobada.
                0
        );

        s.altaVuelo(vuelo);

        Assertions.assertDoesNotThrow(() -> s.consultarVuelo("Vuelo11"),"El vuelo debería existir y no lanzar excepción");
    }

}
