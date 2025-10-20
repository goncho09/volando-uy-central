package com.app.casosDeUso;

import com.app.clases.ISistema;
import com.app.datatypes.DtCliente;
import com.app.enums.TipoDocumento;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.*;
import java.time.LocalDate;

class CasosDeUsoTest {

    static ISistema s;

    @BeforeAll
    static void setUp() {
        s = DummyFactory.crearSistema();
    }

    // ------------ Alta de Usuario -------------- //

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

    // ------------------ Modificar Datos de Usuario ------------------ //
    // ------------------ Alta de Ruta de Vuelo ------------------ //
    // ------------------ Alta de Vuelo -Reserva de Vuelo ------------------ //
    // ------------------ Alta de Ciudad ------------------ //
    // ------------------ Crear Paquete de Rutas de Vuelo ------------------ //
    // ------------------ Agregar Ruta de Vuelo a Paquete ------------------ //
    // ------------------ Compra de Paquete -Alta de Categorías ------------------ //
    // ------------------ Aceptar/Rechazar Ruta de Vuelo ------------------ //

}
