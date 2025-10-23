package com.app.casouso;

import com.app.clases.ISistema;
import com.app.datatypes.DtCiudad;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class AltaCiudadTest {

    static ISistema s;

    @BeforeAll
    static void setUp() {
        s = DummyFactory.crearSistema();
    }

    // ------------------ Alta de Ciudad ------------------ //

    @Test
    void validarNombreVacioAltaCiudad() {
        DtCiudad ciudad = new DtCiudad("", "Uruguay", "Carrasco", "Descripción", "sitioWeb", LocalDate.now());

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaCiudad(ciudad),
                "El sistema no debería crear una ciudad ya que su nombre es vacío."
        );
    }

    @Test
    void validarPaisVacioAltaCiudad() {
        DtCiudad ciudad = new DtCiudad("Canelones", "", "Aeropuerto", "Descripción", "SitioWeb", LocalDate.now());

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaCiudad(ciudad),
                "El sistema no debería crear una ciudad con país vacío."
        );
    }

    @Test
    void validarCiudadExistente() {
        DtCiudad ciudad = new DtCiudad("Buenos Aires", "Argentina", "Aeropuerto", "Descripción", "SitioWeb", LocalDate.now());

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaCiudad(ciudad),
                "El sistema no debería crear una ciudad que ya existe."
        );
    }

    @Test
    void validarAltaCiudad() {
        DtCiudad ciudad = new DtCiudad(
                "Valencia",
                "España",
                "Aeropuerto de Valencia-Manises",
                "Ciudad portuaria con una mezcla de arquitectura moderna y antigua.",
                "www.visitvalencia.com",
                LocalDate.now()
        );

        Assertions.assertDoesNotThrow(
                () -> s.altaCiudad(ciudad),
                "El sistema debería crear una nueva ciudad válida."
        );

        Assertions.assertNotNull(
                s.buscarCiudad("São Paulo", "Brasil"),
                "La ciudad debería existir luego de ser creada."
        );
    }
}
