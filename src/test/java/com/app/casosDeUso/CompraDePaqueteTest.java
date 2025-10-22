package com.app.casosDeUso;

import com.app.clases.ISistema;
import com.app.clases.RutaEnPaquete;
import com.app.datatypes.DtCliente;
import com.app.datatypes.DtPaquete;
import com.app.datatypes.DtRuta;
import com.app.datatypes.DtRutaEnPaquete;
import com.app.enums.EstadoRuta;
import com.app.util.DummyFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CompraDePaqueteTest {

    static ISistema s;

    @BeforeAll
    static void setUp() {
        s = DummyFactory.crearSistema();
    }

    // ------------------ Compra de Paquete de Rutas de Vuelo ------------------ //

    @Test
    void compraPaqueteValido() {
        // Ciente que NO tiene paquetes
        DtCliente cliente = s.getCliente("carlosSanchez");
        DtPaquete paquete = s.getPaquete("ExploraSur");

        Assertions.assertFalse(s.clienteTienePaquete(cliente.getNickname(), paquete.getNombre()),
                "El cliente no debería tener este paquete comprado inicialmente");

        s.compraPaquete(paquete, cliente);

        Assertions.assertTrue(s.clienteTienePaquete(cliente.getNickname(), paquete.getNombre()),
                "El cliente debería tener el paquete después de la compra");
    }

    @Test
    void compraPaqueteYaComprado() {
        // Cliente que YA tiene paquetes
        DtCliente cliente = s.getCliente("zacgamer");
        DtPaquete paquete = s.getPaquete("VueloExpress");

        Assertions.assertTrue(s.clienteTienePaquete(cliente.getNickname(), paquete.getNombre()),
                "El cliente ya debería tener este paquete comprado");

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.compraPaquete(paquete, cliente),
                "No debería permitir comprar un paquete que ya se tiene");
    }

    @Test
    void compraPaqueteConClienteInexistente() {
        DtCliente clienteInexistente = new DtCliente(
                "clienteInexistente", "No", "existe@gmail.com", "1234", "default.png",
                "Existe", java.time.LocalDate.of(1990, 1, 1), "Uruguay",
                com.app.enums.TipoDocumento.CEDULA, 12345678);
        DtPaquete paquete = s.getPaquete("ExploraSur");

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.compraPaquete(paquete, clienteInexistente),
                "No debería permitir comprar con cliente inexistente");
    }

    @Test
    void compraPaqueteInexistente() {
        DtCliente cliente = s.getCliente("anaGomez");
        DtPaquete paqueteInexistente = new DtPaquete("PaqueteInexistente", "No existe", 30, 10);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.compraPaquete(paqueteInexistente, cliente),
                "No debería permitir comprar un paquete que no existe");
    }

    @Test
    void compraPaqueteSinRutas() {
        // Paquete existente sin rutas
        DtPaquete paqueteSinRutas = s.getPaquete("AventuraTotal"); // Este paquete no tiene rutas en DummyFactory
        DtCliente cliente = s.getCliente("luisRamirez");

        Assertions.assertDoesNotThrow(
                () -> s.compraPaquete(paqueteSinRutas, cliente),
                "Debería permitir comprar paquetes sin rutas");
    }

    @Test
    void listarPaquetesNoComprados() {
        // Verificar el estado inicial
        List<DtPaquete> paquetesNoComprados = s.listarPaquetesNoComprados();

        Assertions.assertFalse(paquetesNoComprados.isEmpty(),
                "Debería haber paquetes no comprados disponibles");

        // Verificar que los paquetes ya comprados NO están en la lista
        boolean paqueteCompradoEnLista = paquetesNoComprados.stream()
                .anyMatch(p -> p.getNombre().equals("VueloExpress"));
        Assertions.assertFalse(paqueteCompradoEnLista,
                "Los paquetes ya comprados no deberían aparecer en la lista de no comprados");
    }

    @Test
    void compraMultiplePaquetesDiferentes() {
        // Cliente que no tiene paquetes
        DtCliente cliente = s.getCliente("power");

        // Comprar paquetes que están disponibles
        DtPaquete paquete1 = s.getPaquete("ExploraSur");
        DtPaquete paquete2 = s.getPaquete("NegociosPlus");

        // Verificar estado inicial
        Assertions.assertFalse(s.clienteTienePaquete(cliente.getNickname(), paquete1.getNombre()));
        Assertions.assertFalse(s.clienteTienePaquete(cliente.getNickname(), paquete2.getNombre()));

        // Comprar ambos paquetes
        s.compraPaquete(paquete1, cliente);
        s.compraPaquete(paquete2, cliente);

        // Verificar estado final
        Assertions.assertTrue(s.clienteTienePaquete(cliente.getNickname(), paquete1.getNombre()));
        Assertions.assertTrue(s.clienteTienePaquete(cliente.getNickname(), paquete2.getNombre()));
    }

    @Test
    void compraPaqueteConRutasAprobadas() {
        DtCliente cliente = s.getCliente("anaGomez");

        // Paquete con rutas aprobadas
        DtPaquete paqueteConRutas = s.getPaquete("ExploraSur");

        Assertions.assertNotNull(paqueteConRutas, "Debe haber al menos un paquete con rutas");

        // Verificar que están aprobadas
        for (DtRutaEnPaquete rp : paqueteConRutas.getRutaEnPaquete()) {
            DtRuta ruta = rp.getRutaDeVuelo();
            Assertions.assertEquals(EstadoRuta.APROBADA, ruta.getEstado(),
                    "Todas las rutas del paquete deben estar aprobadas");
        }

        // Comprar el paquete
        Assertions.assertDoesNotThrow(
                () -> s.compraPaquete(paqueteConRutas, cliente),
                "Debería permitir comprar paquetes con rutas aprobadas");
    }

    @Test
    void compraPaqueteConRutasRechazadas() {
        DtCliente cliente = s.getCliente("power");

        // Paquete inexistente
        DtPaquete paqueteInexistente = new DtPaquete("PaqueteConRutasRechazadas", "No existe", 30, 10);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.compraPaquete(paqueteInexistente, cliente),
                "No debería permitir comprar paquetes que no existen");
    }

    @Test
    void compraPaqueteConClienteNull() {
        DtPaquete paquete = s.getPaquete("ExploraSur");

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.compraPaquete(paquete, null),
                "No debería permitir compra con cliente null");
    }

    @Test
    void compraPaqueteNull() {
        DtCliente cliente = s.getCliente("power");

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.compraPaquete(null, cliente),
                "No debería permitir compra con paquete null");
    }

    @Test
    void compraPaqueteConDescuentoCero() {
        DtCliente cliente = s.getCliente("anaGomez");
        DtPaquete paquete = s.getPaquete("AventuraTotal"); // Tiene descuento 0 en DummyFactory

        Assertions.assertDoesNotThrow(
                () -> s.compraPaquete(paquete, cliente),
                "Debería permitir comprar paquetes con descuento cero");
    }

    @Test
    void compraPaqueteConAltoDescuento() {
        DtCliente cliente = s.getCliente("carlosSanchez");
        DtPaquete paquete = s.getPaquete("LujoMundial"); // Tiene 20% de descuento

        Assertions.assertDoesNotThrow(
                () -> s.compraPaquete(paquete, cliente),
                "Debería permitir comprar paquetes con alto descuento");
    }

    @Test
    void listarPaquetesClienteSinCompras() {
        // Crear un cliente NUEVO 
        DtCliente cliente = new DtCliente(
                "clienteTestUnico",
                "Test",
                "testunico@test.com",
                "1234",
                "default.png",
                "Usuario",
                java.time.LocalDate.of(1995, 5, 15),
                "Uruguay",
                com.app.enums.TipoDocumento.CEDULA,
                11111111);

        s.registrarCliente(cliente);
 
        // Verificar que no tiene compras
        List<DtPaquete> paquetesCliente = s.listarPaquetes(cliente);

        Assertions.assertTrue(paquetesCliente.isEmpty(),
                "Un cliente nuevo sin compras debería tener lista vacía");
    }

    @Test
    void listarPaquetesClienteConCompras() {
        DtCliente cliente = s.getCliente("zacgamer"); // Tiene VueloExpress

        List<DtPaquete> paquetesCliente = s.listarPaquetes(cliente);

        Assertions.assertFalse(paquetesCliente.isEmpty(),
                "Un cliente con compras debería tener paquetes en su lista");

        boolean tieneVueloExpress = paquetesCliente.stream()
                .anyMatch(p -> p.getNombre().equals("VueloExpress"));
        Assertions.assertTrue(tieneVueloExpress,
                "Debería tener el paquete VueloExpress en su lista");
    }
}