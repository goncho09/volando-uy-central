package com.app.clases;

import com.app.datatypes.*;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class SistemaTest {

    static ISistema s;

    @BeforeAll
    static void setUp() {
        s = DummyFactory.crearSistema();
    }

    @Test
    void listarUsuarios(){

        List<DtUsuario> usuarios = s.listarUsuarios();

        Assertions.assertNotNull(usuarios, "La lista no debe ser null (hay datos cargados)");
    }

    @Test
    void listarAerolineas(){
        List<DtAerolinea> aeroList = s.listarAerolineas();

        Assertions.assertNotNull(aeroList, "La lista no debe ser null");
    }

    @Test
    void listarClientes(){
        List<DtCliente> clientList = s.listarClientes();

        Assertions.assertNotNull(clientList, "La lista no debe ser null");
    }

    @Test
    void listarPaquetes(){
        List<DtPaquete> paqueteList = s.listarPaquetes();

        Assertions.assertNotNull(paqueteList, "La lista no debe ser null");
    }

    @Test
    void listarPaquetesClienteExistenteConPaquetes(){
        List<DtPaquete> paqueteList = s.listarPaquetes(s.getCliente("zacgamer")); // zacgamer tiene paquetes

        Assertions.assertNotNull(paqueteList, "La lista no debe ser null");
    }

    @Test
    void listarPaquetesClienteExistenteSinPaquetes(){
        List<DtPaquete> paqueteList = s.listarPaquetes(s.getCliente("carlosSanchez")); // carlosSanchez NO tiene paquetes

        Assertions.assertEquals(
                paqueteList == null || paqueteList.isEmpty(),
                paqueteList,
                "La lista debe estar vacia o nula"
        );
    }

    @Test
    void listarVuelosExistentes(){
        List<DtVuelo> listVuelo = s.listarVuelos();

        Assertions.assertNotNull(listVuelo);
    }

    @Test
    void listarVuelosDeRuta(){
        List<DtVuelo> listVuelo = s.listarVuelos("Bogotá-Montevideo"); // Esta ruta existe

        Assertions.assertNotNull(listVuelo);
    }

    @Test
    void listarVuelosDeRutaInexistente(){
        List<DtVuelo> listVuelo = s.listarVuelos("Esta ruta no existe uwu"); // Esta ruta existe

        Assertions.assertNotNull(listVuelo);
    }

    @Test
    void listarVuelosDeRutaConFechaValdia(){
        List<DtVuelo> listVuelo = s.listarVuelos("Bogotá-Montevideo", LocalDate.now()); // Se supone que los vuelos de dicha ruta están programados par luego de "ahora"

        Assertions.assertNotNull(listVuelo);
    }

    @Test
    void listarVuelosDeRutaConFechaInvaldia(){
        List<DtVuelo> listVuelo = s.listarVuelos("Bogotá-Montevideo", LocalDate.now().plusDays(700));

        Assertions.assertEquals(
                listVuelo == null || listVuelo.isEmpty(),
                listVuelo,
                "La lista debe ser nula o vacía."
        );
    }

    @Test
    void validarUsuarioCredenciales(){
        Assertions.assertTrue(s.validarUsuario("power","1234"),"El cliente debe existir");
    }

    @Test
    void usuarioExiste(){
        Assertions.assertFalse(s.existeUsuarioNickname("power12321"),"El cliente no existe");
    }

    @Test
    void contieneCategoria(){
        Assertions.assertTrue(s.containsCategoria(s.getRutaDeVuelo("Bogotá-Montevideo"),"Aventura"),"La categoria existe en esa ruta");
    }

    @Test
    void existeRuta(){
        Assertions.assertFalse(s.existeRuta("pedirololjdjsadjasjsa"),"La ruta no debe existir");
    }

    @Test
    void existeVuelo(){
        Assertions.assertFalse(s.existeVuelo("pedirololjdjsadjasjsa"),"El vuelo no debe existir");
    }

    @Test
    void existePaquete(){
        Assertions.assertFalse(s.existePaquete("pedirololjdjsadjasjsa"),"El paquete no debe existir");
    }

    @Test
    void getVuelosRutaDeVuelo(){
        List<DtVuelo> listVuelo = s.getVuelosRutaDeVuelo(s.getRutaDeVuelo("Bogotá-Montevideo"));
        Assertions.assertNotNull(listVuelo);
    }

    @Test
    void listarPaquetesAerolinea(){
        List<DtPaquete> packages = s.listarPaquetes(s.getAerolinea("skyHigh"));
        Assertions.assertEquals(
                packages == null || packages.isEmpty(),
                packages,
                "La lista debe ser nula o vacía."
        );
    }

    @Test
    void getReservaCliente(){
        Assertions.assertNotNull(s.getReservaCliente(s.getVuelo("Vuelo_6"),s.getCliente("zacgamer"),LocalDate.of(2025,10,28)));
    }

    @Test
    void getReservaAerolinea(){
        Assertions.assertNotNull(s.getReservaAerolinea(s.getVuelo("Vuelo_6"),s.getAerolinea("crystalMotus"),LocalDate.of(2025,10,28)));
    }
}
