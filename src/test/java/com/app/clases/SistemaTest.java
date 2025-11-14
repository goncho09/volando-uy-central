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
        List<DtPaquete> paqueteList = s.listarPaquetesCliente(s.getCliente("zacgamer").getNickname()); // zacgamer tiene paquetes

        Assertions.assertNotNull(paqueteList, "La lista no debe ser null");
    }

    @Test
    void listarPaquetesClienteExistenteSinPaquetes(){
        List<DtPaquete> paqueteList = s.listarPaquetesCliente(s.getCliente("nuevoCliente").getNickname()); // nuevoCliente NO tiene paquetes

        Assertions.assertTrue(
                paqueteList == null || paqueteList.isEmpty(),
                "La lista debe estar vacía o nula"
        );

    }

    @Test
    void listarVuelosExistentes(){
        List<DtVuelo> listVuelo = s.listarVuelos();

        Assertions.assertNotNull(listVuelo);
    }

    @Test
    void listarVuelosDeRuta(){
        List<DtVuelo> listVuelo = s.listarVuelosRuta("Bogotá-Montevideo"); // Esta ruta existe

        Assertions.assertNotNull(listVuelo);
    }

    @Test
    void listarVuelosDeRutaInexistente(){
        List<DtVuelo> listVuelo = s.listarVuelosRuta("Esta ruta no existe uwu"); // Esta ruta existe

        Assertions.assertNotNull(listVuelo);
    }

    @Test
    void listarVuelosDeRutaConFechaValdia(){
        List<DtVuelo> listVuelo = s.listarVuelosRutaFecha("Bogotá-Montevideo", LocalDate.now()); // Se supone que los vuelos de dicha ruta están programados par luego de "ahora"

        Assertions.assertNotNull(listVuelo);
    }

    @Test
    void listarVuelosDeRutaConFechaInvaldia(){
        List<DtVuelo> listVuelo = s.listarVuelosRutaFecha("Bogotá-Montevideo", LocalDate.now().plusDays(700));

        Assertions.assertTrue(
                listVuelo == null || listVuelo.isEmpty(),
                "La lista debe ser nula o vacía."
        );
    }

    @Test
    void validarUsuarioCredenciales(){
        Assertions.assertTrue(s.validarUsuario("power","1234"),"El cliente debe existir");
    }

    @Test
    void usuarioExiste(){
        Assertions.assertFalse(s.existeUsuario("power12321"),"El cliente no existe");
    }

    @Test
    void contieneCategoria(){
        Assertions.assertTrue(s.rutaContieneCategoria(s.getRutaDeVuelo("Bogotá-Montevideo"),"Aventura"),"La categoria existe en esa ruta");
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
        List<DtPaquete> packages = s.listarPaquetesAerolinea(s.getAerolinea("skyHigh").getNickname());
        Assertions.assertTrue(
                packages == null || packages.isEmpty(),
                "La lista debe ser nula o vacía."
        );
    }

    @Test
    void getReservaCliente(){
        Assertions.assertNotNull(s.getReservaCliente(s.getVuelo("Vuelo_7"),s.getCliente("power"), LocalDate.now().plusDays(1)));
    }

    @Test
    void getReservaAerolinea(){
        Assertions.assertNotNull(s.getReservaAerolinea(s.getVuelo("Vuelo_7"),s.getAerolinea("crystalMotus"), LocalDate.now().plusDays(1)));
    }

    @Test
    void listarReservasClienteVuelo(){
        Assertions.assertNotNull(s.listarReservasClienteVuelo(s.getCliente("power"), s.getVuelo("Vuelo_7")));
    }

}
