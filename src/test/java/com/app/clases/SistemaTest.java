package com.app.clases;

import com.app.datatypes.DtCliente;
import com.app.enums.TipoDocumento;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.*;
import java.time.LocalDate;

class SistemaTest {

    static ISistema s;

    @BeforeAll
    static void setUp() {
        s = DummyFactory.crearSistema();
    }

    @Test
    void registrarClienteExistente(){
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "Bloody",
                "laSanguinaria@gmail.com",
                "1234",
                "default.png",
                "Mary",
                fechaNacimiento,
                "Tokyo",
                TipoDocumento.CEDULA,
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario (mismo nickname) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };
}
