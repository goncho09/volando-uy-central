package com.app.casouso;

import com.app.clases.ISistema;
import com.app.datatypes.DtCiudad;
import com.app.datatypes.DtPaquete;
import com.app.datatypes.DtRuta;
import com.app.enums.TipoAsiento;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AgregarRutaDeVueloAPaqueteTest {

        static ISistema s;

        @BeforeAll
        static void setUp() {
                s = DummyFactory.crearSistema();
        }

        // ------------------ Agregar Ruta de Vuelo a Paquete -------------- //

        @Test
        void agregarRutaAPaqueteValido() {
                // Obtener un paquete y una ruta APROBADA
                DtPaquete paquete = s.getPaquete("ExploraSur");
                DtRuta ruta = s.getRutaDeVuelo("Montevideo-Buenos Aires");

                float costoInicial = paquete.getCosto();

                // Ejecutar el caso de uso
                int resultado = s.agregarRutaAPaquete(paquete, ruta, 2, TipoAsiento.TURISTA);

                // Verificaciones
                Assertions.assertEquals(0, resultado, "Debería retornar 0 indicando éxito");

                // Verificar que el costo se actualizó correctamente
                DtPaquete paqueteActualizado = s.getPaquete("ExploraSur");
                float costoEsperado = costoInicial + (ruta.getCostoTurista() * 2);
                Assertions.assertEquals(costoEsperado, paqueteActualizado.getCosto(), 0.01,
                                "El costo del paquete debería haberse actualizado correctamente");
        }

        @Test
        void agregarRutaAPaqueteConCantidadCero() {
                DtPaquete paquete = s.getPaquete("VueloExpress");
                DtRuta ruta = s.getRutaDeVuelo("Buenos Aires-Santiago");

                Assertions.assertThrows(
                                IllegalArgumentException.class,
                                () -> s.agregarRutaAPaquete(paquete, ruta, 0, TipoAsiento.EJECUTIVO),
                                "No debería permitir agregar una ruta con cantidad 0");
        }

        @Test
        void agregarRutaAPaqueteConCantidadNegativa() {
                DtPaquete paquete = s.getPaquete("NegociosPlus");
                DtRuta ruta = s.getRutaDeVuelo("Santiago-Lima");

                Assertions.assertThrows(
                                IllegalArgumentException.class,
                                () -> s.agregarRutaAPaquete(paquete, ruta, -1, TipoAsiento.TURISTA),
                                "No debería permitir agregar una ruta con cantidad negativa");
        }

        @Test
        void agregarRutaAPaqueteConPaqueteInexistente() {
                DtPaquete paqueteInexistente = new DtPaquete("PaqueteInexistente", "No existe", 30, 10);
                DtRuta ruta = s.getRutaDeVuelo("Lima-São Paulo");

                Assertions.assertThrows(
                                IllegalArgumentException.class,
                                () -> s.agregarRutaAPaquete(paqueteInexistente, ruta, 3, TipoAsiento.EJECUTIVO),
                                "No debería permitir agregar ruta a un paquete que no existe");
        }

        @Test
        void agregarRutaAPaqueteConRutaInexistente() {
                DtPaquete paquete = s.getPaquete("FamiliaFeliz");

                // Crear DtCiudad correctamente con todos los parámetros
                DtCiudad ciudadOrigen = new DtCiudad("Origen", "Pais", "Aeropuerto Origen", "Descripción origen",
                                "www.origen.com", java.time.LocalDate.now().toString());
                DtCiudad ciudadDestino = new DtCiudad("Destino", "Pais", "Aeropuerto Destino", "Descripción destino",
                                "www.destino.com", java.time.LocalDate.now().toString());

                DtRuta rutaInexistente = new DtRuta(
                                "RutaInexistente", "No existe", "No existe corta",
                                java.time.LocalTime.of(2, 0).toString(), 100, 200, 15,
                                java.time.LocalDate.now().toString(), "default.png",
                                java.util.Arrays.asList(),
                                ciudadOrigen,
                                ciudadDestino);

                Assertions.assertThrows(
                                IllegalArgumentException.class,
                                () -> s.agregarRutaAPaquete(paquete, rutaInexistente, 2, TipoAsiento.TURISTA),
                                "No debería permitir agregar una ruta que no existe");
        }

        @Test
        void agregarRutaAPaqueteConRutaRechazada() {
                DtPaquete paquete = s.getPaquete("AventuraTotal");
                DtRuta rutaRechazada = s.getRutaDeVuelo("Montevideo Norte-Quito"); // Ruta en estado RECHAZADA

                Assertions.assertThrows(
                                IllegalArgumentException.class,
                                () -> s.agregarRutaAPaquete(paquete, rutaRechazada, 1, TipoAsiento.EJECUTIVO),
                                "No debería permitir agregar una ruta que está rechazada");
        }

        @Test
        void agregarRutaAPaqueteConRutaIngresada() {
                DtPaquete paquete = s.getPaquete("LujoMundial");
                DtRuta rutaIngresada = s.getRutaDeVuelo("Santiago-Lima"); // Ruta en estado INGRESADA

                Assertions.assertThrows(
                                IllegalArgumentException.class,
                                () -> s.agregarRutaAPaquete(paquete, rutaIngresada, 1, TipoAsiento.EJECUTIVO),
                                "No debería permitir agregar una ruta que está ingresada (no confirmada)");
        }

        @Test
        void agregarMismaRutaDiferenteTipoAsiento() {
                DtPaquete paquete = s.getPaquete("TurismoCultural");
                DtRuta ruta = s.getRutaDeVuelo("Montevideo-Buenos Aires");

                // Agregar primero con tipo turista
                s.agregarRutaAPaquete(paquete, ruta, 2, TipoAsiento.TURISTA);
                float costoDespuesTurista = s.getPaquete("TurismoCultural").getCosto();

                // Agregar la misma ruta con tipo ejecutivo
                s.agregarRutaAPaquete(paquete, ruta, 1, TipoAsiento.EJECUTIVO);
                DtPaquete paqueteFinal = s.getPaquete("TurismoCultural");

                float costoEsperado = costoDespuesTurista + (ruta.getCostoEjecutivo() * 1);
                Assertions.assertEquals(costoEsperado, paqueteFinal.getCosto(), 0.01,
                                "Debería permitir agregar la misma ruta con diferente tipo de asiento");
        }

        @Test
        void actualizarCantidadRutaExistente() {
                DtPaquete paquete = s.getPaquete("EscapadaRomántica");
                DtRuta ruta = s.getRutaDeVuelo("Montevideo-Buenos Aires");

                // Agregar inicialmente
                s.agregarRutaAPaquete(paquete, ruta, 2, TipoAsiento.TURISTA);
                float costoInicial = s.getPaquete("EscapadaRomántica").getCosto();

                // Actualizar cantidad de la misma ruta
                s.agregarRutaAPaquete(paquete, ruta, 3, TipoAsiento.TURISTA);
                DtPaquete paqueteActualizado = s.getPaquete("EscapadaRomántica");

                float costoEsperado = costoInicial + (ruta.getCostoTurista() * 3);
                Assertions.assertEquals(costoEsperado, paqueteActualizado.getCosto(), 0.01,
                                "Debería actualizar la cantidad de una ruta ya existente en el paquete");
        }

        @Test
        void agregarRutaAPaqueteConTipoAsientoNulo() {
                DtPaquete paquete = s.getPaquete("WorkAndTravel");
                DtRuta ruta = s.getRutaDeVuelo("Montevideo-Buenos Aires");

                Assertions.assertThrows(
                                IllegalArgumentException.class,
                                () -> s.agregarRutaAPaquete(paquete, ruta, 2, null),
                                "No debería permitir tipo de asiento nulo");
        }

}
