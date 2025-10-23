package com.app.casouso;

import com.app.clases.ISistema;
import com.app.datatypes.DtPaquete;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class CrearPaqueteDeRutasDeVueloTest {

    static ISistema s;

    @BeforeAll
    static void setUp() {
        s = DummyFactory.crearSistema();
    }

    // ------------------ Crear Paquete de Rutas de Vuelo ------------------ //

    @Test
    void validarPaqueteExistente(){
        DtPaquete paquete = new DtPaquete(
                "TurismoCultural",
                "descripcion",
                10,
                10f);

        Assertions.assertThrows(
            IllegalArgumentException.class, () -> s.altaPaquete(paquete),
                "El sistema no debería crear un paquete ya existente."
        );
    }

    @Test
    void validarNombreVacioPaquete(){
        DtPaquete paquete = new DtPaquete(
                "",
                "descripcion",
                10,
                10f);

        Assertions.assertThrows(
                IllegalArgumentException.class, () -> s.altaPaquete(paquete),
                "El sistema no debería crear un paquete con nombre vacio."
        );
    }

    @Test
    void altaPaqueteValidezCero() {
        DtPaquete paquete = new DtPaquete("PaqueteValidez0", "Desc", 0, 0.1f);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaPaquete(paquete),
                "No debería permitir validez 0"
        );
    }

    @Test
    void altaPaqueteValidezNegativa() {
        DtPaquete paquete = new DtPaquete("PaqueteValidezNeg", "Desc", -5, 0.1f);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaPaquete(paquete),
                "No debería permitir validez negativa"
        );
    }

    @Test
    void altaPaqueteDescuentoNegativa() {
        DtPaquete paquete = new DtPaquete("PaqueteValidezNeg", "Desc", -5, -1f);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaPaquete(paquete),
                "No debería permitir descuento negativo"
        );
    }

}
