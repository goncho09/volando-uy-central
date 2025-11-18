package com.app.casouso;

import com.app.clases.ISistema;
import com.app.datatypes.DtRuta;
import com.app.enums.EstadoRuta;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class AltaDeRutaDeVueloTest {

    static ISistema s;

    @BeforeAll
    static void setUp() {
        s = DummyFactory.crearSistema();
    }

    // ------------------ Alta de Ruta de Vuelo ------------------ //

    @Test
    void validarNombreVacioAltaRutaDeVuelo() {

        DtRuta ruta = new DtRuta(
                "",
                "Ruta de Vuelo Fallida :c",
                "Ruta no funcional",
                LocalTime.of(2, 30).toString(),
                150,
                300,
                50,
                LocalDate.now().toString(),
                "default.png",
                Arrays.asList(s.getCategoria("Turismo"), s.getCategoria("Lujo")),
                s.getCiudad("Lima", "Perú"),
                s.getCiudad("Montevideo", "Uruguay")
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaRutaDeVuelo("aeroDream", ruta),
                "El sistema no debería crear la ruta de vuelo ya que su nombre es vacío."
        );
    }

    @Test
    void validarNombreExistenteAltaRutaDeVuelo() {

        DtRuta ruta = new DtRuta(
                "Montevideo-Buenos Aires",// Este nombre ya existe.
                "Ruta de Vuelo Fallida :c",
                "Ruta no funcional",
                LocalTime.of(2, 30).toString(),
                150,
                300,
                50,
                LocalDate.now().toString(),
                "default.png",
                Arrays.asList(s.getCategoria("Turismo"), s.getCategoria("Lujo")),
                s.getCiudad("Lima", "Perú"),
                s.getCiudad("Montevideo", "Uruguay")
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaRutaDeVuelo("aeroDream", ruta),
                "El sistema no debería crear la ruta de vuelo ya que su nombre ya existe."
        );
    }
    @Test
    void ValidarHoraInvalidaALtaRutaDeVuelo() {
        DtRuta ruta = new DtRuta(
                "Ruta-Fallida-Hora",
                "Ruta de Vuelo Fallida :c",
                "Ruta no funcional",
                LocalTime.of(0, 0).toString(), // Hora inválida
                150,
                300,
                50,
                LocalDate.now().toString(),
                "default.png",
                Arrays.asList(s.getCategoria("Turismo"), s.getCategoria("Lujo")),
                s.getCiudad("Lima", "Perú"),
                s.getCiudad("Montevideo", "Uruguay")
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaRutaDeVuelo("aeroDream", ruta),
                "El sistema no debería crear la ruta de vuelo ya que su duración es nada."
        );
    }
    @Test
    void ValidarCiudadesIgualesALtaRutaDeVuelo() {
        DtRuta ruta = new DtRuta(
                "Ruta-Fallida-Ciudades",
                "Ruta de Vuelo Fallida :c",
                "Ruta no funcional",
                LocalTime.of(2, 30).toString(),
                150,
                300,
                50,
                LocalDate.now().toString(),
                "default.png",
                Arrays.asList(s.getCategoria("Turismo"), s.getCategoria("Lujo")),
                s.getCiudad("Lima", "Perú"),
                s.getCiudad("Lima", "Perú") // Ciudades iguales
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaRutaDeVuelo("aeroDream", ruta),
                "El sistema no debería crear la ruta de vuelo ya que las ciudades de origen y destino son iguales."
        );
    }
    @Test
    void ValidarCategoriasVaciasALtaRutaDeVuelo() {
        DtRuta ruta = new DtRuta(
                "Ruta-Fallida-Categorias",
                "Ruta de Vuelo Fallida :c",
                "Ruta no funcional",
                LocalTime.of(2, 30).toString(),
                150,
                300,
                50,
                LocalDate.now().toString(),
                "default.png",
                Arrays.asList(), // Categorías vacías
                s.getCiudad("Lima", "Perú"),
                s.getCiudad("Montevideo", "Uruguay")
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaRutaDeVuelo("aeroDream", ruta),
                "El sistema no debería crear la ruta de vuelo ya que no se han asignado categorías."
        );
    }
    @Test
    void validarEquipajeExtraNegativoAltaRutaDeVuelo() {

        DtRuta ruta = new DtRuta(
                "Ruta-Fallida-Equipaje",
                "Ruta de Vuelo Fallida :c",
                "Ruta no funcional",
                LocalTime.of(2, 30).toString(),
                150,
                300,
                -10, // Equipaje extra negativo
                LocalDate.now().toString(),
                "default.png",
                Arrays.asList(s.getCategoria("Turismo"), s.getCategoria("Lujo")),
                s.getCiudad("Lima", "Perú"),
                s.getCiudad("Montevideo", "Uruguay")
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaRutaDeVuelo("aeroDream", ruta),
                "El sistema no debería crear la ruta de vuelo ya que el costo de equipaje extra es negativo."
        );
    }
    @Test
    void validarCostoTuristaNegativoAltaRutaDeVuelo() {

        DtRuta ruta = new DtRuta(
                "Ruta-Fallida-CostoTurista",
                "Ruta de Vuelo Fallida :c",
                "Ruta no funcional",
                LocalTime.of(2, 30).toString(),
                -150, // Costo turista negativo
                300,
                50,
                LocalDate.now().toString(),
                "default.png",
                Arrays.asList(s.getCategoria("Turismo"), s.getCategoria("Lujo")),
                s.getCiudad("Lima", "Perú"),
                s.getCiudad("Montevideo", "Uruguay")
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaRutaDeVuelo("aeroDream", ruta),
                "El sistema no debería crear la ruta de vuelo ya que el costo en clase turista es negativo."
        );
    }
    @Test
    void validarCostoEjecutivoNegativoAltaRutaDeVuelo() {
        DtRuta ruta = new DtRuta(
                "Ruta-Fallida-CostoEjecutivo",
                "Ruta de Vuelo Fallida :c",
                "Ruta no funcional",
                LocalTime.of(2, 30).toString(),
                150,
                -300, // Costo ejecutivo negativo queremos plata
                50,
                LocalDate.now().toString(),
                "default.png",
                Arrays.asList(s.getCategoria("Turismo"), s.getCategoria("Lujo")),
                s.getCiudad("Lima", "Perú"),
                s.getCiudad("Montevideo", "Uruguay")
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaRutaDeVuelo("aeroDream", ruta),
                "El sistema no debería crear la ruta de vuelo ya que el costo en clase ejecutivo es negativo y queremos plata >:D."
        );
    }
    @Test
    void validarCostoCeroAltaRutaDeVuelo() {

        DtRuta ruta = new DtRuta(
                "Ruta-Fallida-CostoCero",
                "Ruta de Vuelo Fallida :c",
                "Ruta no funcional",
                LocalTime.of(2, 30).toString(),
                0, // Costo turista cero
                0, // Costo ejecutivo cero
                50,
                LocalDate.now().toString(),
                "default.png",
                Arrays.asList(s.getCategoria("Turismo"), s.getCategoria("Lujo")),
                s.getCiudad("Lima", "Perú"),
                s.getCiudad("Montevideo", "Uruguay")
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaRutaDeVuelo("aeroDream", ruta),
                "El sistema no debería crear la ruta de vuelo ya que los costos no pueden ser cero."
        );
    }
    @Test
    void validarCostoFloatAltaRutaDeVuelo() {

        DtRuta ruta = new DtRuta(
                "Ruta-Fallida-CostoFloat",
                "Ruta de Vuelo Fallida :c",
                "Ruta no funcional",
                LocalTime.of(2, 30).toString(),
                150.75324123f, // Costo turista float
                300.50865123f, // Costo ejecutivo float
                50,
                LocalDate.now().toString(),
                "default.png",
                Arrays.asList(s.getCategoria("Turismo"), s.getCategoria("Lujo")),
                s.getCiudad("Lima", "Perú"),
                s.getCiudad("Montevideo", "Uruguay")
        );

        // Esta ruta debería ser creada exitosamente, ya que los costos en float son válidos.
        Assertions.assertDoesNotThrow(
                () -> s.altaRutaDeVuelo("aeroDream", ruta),
                "El sistema debería crear la ruta de vuelo correctamente con costos en formato float."
        );
    }

}