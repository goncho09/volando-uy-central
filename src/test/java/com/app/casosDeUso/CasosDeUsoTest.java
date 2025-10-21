package com.app.casosDeUso;

import com.app.clases.ISistema;
import com.app.datatypes.DtAerolinea;
import com.app.datatypes.DtCiudad;
import com.app.datatypes.DtCliente;
import com.app.datatypes.DtPaquete;
import com.app.datatypes.DtRuta;
import com.app.datatypes.DtVuelo;
import com.app.enums.EstadoRuta;
import com.app.enums.TipoAsiento;
import com.app.enums.TipoDocumento;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

class CasosDeUsoTest {

    static ISistema s;

    @BeforeAll
    static void setUp() {
        s = DummyFactory.crearSistema();
    }

    // ------------ Alta de Usuario -------------- //

    /* GENERALES */

    @Test
    void validarNicknameVacioAltaUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "",
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
                "El sistema no debería crear al usuario (nickname vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

    ;

    @Test
    void validarNicknameExistenteAltaUsuario() {
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

    @Test
    void validarNombreVacioAltaUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "",
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
                "El sistema no debería crear al usuario (nombre vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

    ;

    @Test
    void validarNombreSoloLetrasAltaUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "Bloody432432",
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
                "El sistema no debería crear al usuario ya que su nombre contiene numeros" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

    ;

    @Test
    void validarEmailVacioAltaUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "Bloody",
                "",
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
                "El sistema no debería crear al usuario (email vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

    ;

    @Test
    void validarEmailExistenteAltaUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "powerEmail",
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
                "El sistema no debería crear un usuario cn el mismo email que otra persona" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

    ;

    @Test
    void validarContraseñaVaciaAltaUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "Bloody",
                "laSanguinaria@gmail.com",
                "",
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
                "El sistema no debería crear al usuario (contraseña vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

    ;
    /* GENERALES */

    /* CLIENTES */

    @Test
    void validarApellidoVacioAltaUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "Pedro",
                "laSanguinaria@gmail.com",
                "1234",
                "default.png",
                "",
                fechaNacimiento,
                "Tokyo",
                TipoDocumento.CEDULA,
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario (apellido vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

    ;

    @Test
    void validarApellidoSoloLetrasAltaUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "Bloody",
                "laSanguinaria@gmail.com",
                "1234",
                "default.png",
                "Mary432432",
                fechaNacimiento,
                "Tokyo",
                TipoDocumento.CEDULA,
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario ya que su apellido contiene numeros" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    @Test
    void validarNacionalidadVacioAltaUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "Pedro",
                "laSanguinaria@gmail.com",
                "1234",
                "default.png",
                "Gomez",
                fechaNacimiento,
                "",
                TipoDocumento.CEDULA,
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario (nacionalidad vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    @Test
    void validarNacionalidadSoloLetrasAltaUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "Bloody",
                "laSanguinaria@gmail.com",
                "1234",
                "default.png",
                "Mary432432",
                fechaNacimiento,
                "Tokyo423432",
                TipoDocumento.CEDULA,
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario ya que su nacionalidad contiene numeros" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    @Test
    void validarCedulaCantidadNumerosAltaUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "Bloody432432",
                "laSanguinaria@gmail.com",
                "1234",
                "default.png",
                "Mary",
                fechaNacimiento,
                "Tokyo",
                TipoDocumento.CEDULA,
                1234567891
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario ya que su CI es invalida" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    @Test
    void validarFechaNacimientoAltaUsuario() {
        LocalDate hoy = LocalDate.now();
        LocalDate fechaFutura = hoy.plusYears(1);
        DtCliente cliente = new DtCliente(
                "power",
                "Bloody",
                "laSanguinaria@gmail.com",
                "1234",
                "default.png",
                "Mary",
                fechaFutura,
                "Tokyo",
                TipoDocumento.CEDULA,
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario ya que su fecha de nacimiento es futura" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    /* CLIENTES */

    /* AEROLINEAS */

    @Test
    void validarDescripcionVaciaAltaUsuario() {
        DtAerolinea aerolinea = new DtAerolinea(
                "aerolinea1",
                "AeroX",
                "aerox@gmail.com",
                "1234",
                "aerox.png",
                "",
                "http://aerox.com"
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarAerolinea(aerolinea), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear a la aerolinea (descripcion vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    /* AEROLINEAS */

    // ------------------ Modificar Datos de Usuario ------------------ //

    /* GENERALES */

    @Test
    void validarNicknameVacioModificarUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "",
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
                () -> s.modificarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario (nickname vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    @Test
    void validarNombreVacioModificarUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "",
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
                () -> s.modificarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario (nombre vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    @Test
    void validarNombreSoloLetrasModificarUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "Bloody432432",
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
                () -> s.modificarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario ya que su nombre contiene numeros" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    @Test
    void validarEmailVacioModificarUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "Bloody",
                "",
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
                () -> s.modificarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario (email vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    @Test
    void validarEmailExistenteModificarUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "powerEmail",
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
                () -> s.modificarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear un usuario cn el mismo email que otra persona" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    @Test
    void validarContraseñaVaciaModificarUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "Bloody",
                "laSanguinaria@gmail.com",
                "",
                "default.png",
                "Mary",
                fechaNacimiento,
                "Tokyo",
                TipoDocumento.CEDULA,
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.modificarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario (contraseña vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    /* GENERALES */

    /* CLIENTES */

    @Test
    void validarApellidoVacioModificarUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "Pedro",
                "laSanguinaria@gmail.com",
                "1234",
                "default.png",
                "",
                fechaNacimiento,
                "Tokyo",
                TipoDocumento.CEDULA,
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.modificarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario (apellido vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    @Test
    void validarApellidoSoloLetrasModificarUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "Bloody",
                "laSanguinaria@gmail.com",
                "1234",
                "default.png",
                "Mary432432",
                fechaNacimiento,
                "Tokyo",
                TipoDocumento.CEDULA,
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.modificarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario ya que su apellido contiene numeros" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    @Test
    void validarNacionalidadVacioModificarUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "Pedro",
                "laSanguinaria@gmail.com",
                "1234",
                "default.png",
                "Gomez",
                fechaNacimiento,
                "",
                TipoDocumento.CEDULA,
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.modificarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario (nacionalidad vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    @Test
    void validarNacionalidadSoloLetrasModificarUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "Bloody",
                "laSanguinaria@gmail.com",
                "1234",
                "default.png",
                "Mary432432",
                fechaNacimiento,
                "Tokyo423432",
                TipoDocumento.CEDULA,
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.modificarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario ya que su nacionalidad contiene numeros" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    @Test
    void validarCedulaCantidadNumerosModificarUsuario() {
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "power",
                "Bloody432432",
                "laSanguinaria@gmail.com",
                "1234",
                "default.png",
                "Mary",
                fechaNacimiento,
                "Tokyo",
                TipoDocumento.CEDULA,
                1234567891
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.modificarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario ya que su CI es invalida" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    @Test
    void validarFechaNacimientoModificarUsuario() {
        LocalDate hoy = LocalDate.now();
        LocalDate fechaFutura = hoy.plusYears(1);
        DtCliente cliente = new DtCliente(
                "power",
                "Bloody",
                "laSanguinaria@gmail.com",
                "1234",
                "default.png",
                "Mary",
                fechaFutura,
                "Tokyo",
                TipoDocumento.CEDULA,
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.modificarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario ya que su fecha de nacimiento es futura" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    /* CLIENTES */

    /* AEROLINEAS */

    @Test
    void validarDescripcionVaciaModificarUsuario() {
        DtAerolinea aerolinea = new DtAerolinea(
                "aerolinea1",
                "AeroX",
                "aerox@gmail.com",
                "1234",
                "aerox.png",
                "",
                "http://aerox.com"
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.modificarAerolinea(aerolinea), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear a la aerolinea (descripcion vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    };

    /* AEROLINEAS */

    // ------------------ Alta de Ruta de Vuelo ------------------ //
    // ------------------ Alta de Vuelo -Reserva de Vuelo ------------------ //
    // ------------------ Alta de Ciudad ------------------ //
    // ------------------ Crear Paquete de Rutas de Vuelo ------------------ //
    // ------------------ Agregar Ruta de Vuelo a Paquete ------------------ //
    // ------------------ Compra de Paquete -Alta de Categorías ------------------ //
    // ------------------ Aceptar/Rechazar Ruta de Vuelo ------------------ //
        static ISistema s;

        @BeforeAll
        static void setUp() {
                s = DummyFactory.crearSistema();
        }

        // ------------ Alta de Usuario -------------- //

        /* GENERALES */

        @Test
        void validarNicknameVacio() {
                LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
                DtCliente cliente = new DtCliente(
                                "",
                                "Bloody",
                                "laSanguinaria@gmail.com",
                                "1234",
                                "default.png",
                                "Mary",
                                fechaNacimiento,
                                "Tokyo",
                                TipoDocumento.CEDULA,
                                12345678);

                Assertions.assertThrows(
                                IllegalArgumentException.class, // Acá definimos el tipo de Throw que debería mandar
                                                                // nuestra función
                                () -> s.registrarCliente(cliente), // Acá colocamos la función que vamos a utilizar
                                "El sistema no debería crear al usuario (nickname vacio) y debe lanzar una excepción" // Mensaje
                                                                                                                      // opcional
                                                                                                                      // por
                                                                                                                      // si
                                                                                                                      // falla
                                                                                                                      // el
                                                                                                                      // test,
                                                                                                                      // qué
                                                                                                                      // debería
                                                                                                                      // ocurrir
                );
        }

        ;

        @Test
        void validarNicknameExistente() {
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
                                12345678);

                Assertions.assertThrows(
                                IllegalArgumentException.class, // Acá definimos el tipo de Throw que debería mandar
                                                                // nuestra función
                                () -> s.registrarCliente(cliente), // Acá colocamos la función que vamos a utilizar
                                "El sistema no debería crear al usuario (mismo nickname) y debe lanzar una excepción" // Mensaje
                                                                                                                      // opcional
                                                                                                                      // por
                                                                                                                      // si
                                                                                                                      // falla
                                                                                                                      // el
                                                                                                                      // test,
                                                                                                                      // qué
                                                                                                                      // debería
                                                                                                                      // ocurrir
                );
        }

        ;

        @Test
        void validarNombreVacio() {
                LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
                DtCliente cliente = new DtCliente(
                                "power",
                                "",
                                "laSanguinaria@gmail.com",
                                "1234",
                                "default.png",
                                "Mary",
                                fechaNacimiento,
                                "Tokyo",
                                TipoDocumento.CEDULA,
                                12345678);

                Assertions.assertThrows(
                                IllegalArgumentException.class, // Acá definimos el tipo de Throw que debería mandar
                                                                // nuestra función
                                () -> s.registrarCliente(cliente), // Acá colocamos la función que vamos a utilizar
                                "El sistema no debería crear al usuario (nombre vacio) y debe lanzar una excepción" // Mensaje
                                                                                                                    // opcional
                                                                                                                    // por
                                                                                                                    // si
                                                                                                                    // falla
                                                                                                                    // el
                                                                                                                    // test,
                                                                                                                    // qué
                                                                                                                    // debería
                                                                                                                    // ocurrir
                );
        }

        ;

        @Test
        void validarNombreSoloLetras() {
                LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
                DtCliente cliente = new DtCliente(
                                "power",
                                "Bloody432432",
                                "laSanguinaria@gmail.com",
                                "1234",
                                "default.png",
                                "Mary",
                                fechaNacimiento,
                                "Tokyo",
                                TipoDocumento.CEDULA,
                                12345678);

                Assertions.assertThrows(
                                IllegalArgumentException.class, // Acá definimos el tipo de Throw que debería mandar
                                                                // nuestra función
                                () -> s.registrarCliente(cliente), // Acá colocamos la función que vamos a utilizar
                                "El sistema no debería crear al usuario ya que su nombre contiene numeros" // Mensaje
                                                                                                           // opcional
                                                                                                           // por si
                                                                                                           // falla el
                                                                                                           // test, qué
                                                                                                           // debería
                                                                                                           // ocurrir
                );
        }

        ;

        @Test
        void validarEmailVacio() {
                LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
                DtCliente cliente = new DtCliente(
                                "power",
                                "Bloody",
                                "",
                                "1234",
                                "default.png",
                                "Mary",
                                fechaNacimiento,
                                "Tokyo",
                                TipoDocumento.CEDULA,
                                12345678);

                Assertions.assertThrows(
                                IllegalArgumentException.class, // Acá definimos el tipo de Throw que debería mandar
                                                                // nuestra función
                                () -> s.registrarCliente(cliente), // Acá colocamos la función que vamos a utilizar
                                "El sistema no debería crear al usuario (email vacio) y debe lanzar una excepción" // Mensaje
                                                                                                                   // opcional
                                                                                                                   // por
                                                                                                                   // si
                                                                                                                   // falla
                                                                                                                   // el
                                                                                                                   // test,
                                                                                                                   // qué
                                                                                                                   // debería
                                                                                                                   // ocurrir
                );
        }

        ;

        @Test
        void validarEmailExistente() {
                LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
                DtCliente cliente = new DtCliente(
                                "powerEmail",
                                "Bloody",
                                "laSanguinaria@gmail.com",
                                "1234",
                                "default.png",
                                "Mary",
                                fechaNacimiento,
                                "Tokyo",
                                TipoDocumento.CEDULA,
                                12345678);

                Assertions.assertThrows(
                                IllegalArgumentException.class, // Acá definimos el tipo de Throw que debería mandar
                                                                // nuestra función
                                () -> s.registrarCliente(cliente), // Acá colocamos la función que vamos a utilizar
                                "El sistema no debería crear un usuario cn el mismo email que otra persona" // Mensaje
                                                                                                            // opcional
                                                                                                            // por si
                                                                                                            // falla el
                                                                                                            // test, qué
                                                                                                            // debería
                                                                                                            // ocurrir
                );
        }

        ;

        @Test
        void validarContraseñaVacia() {
                LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
                DtCliente cliente = new DtCliente(
                                "power",
                                "Bloody",
                                "laSanguinaria@gmail.com",
                                "",
                                "default.png",
                                "Mary",
                                fechaNacimiento,
                                "Tokyo",
                                TipoDocumento.CEDULA,
                                12345678);

                Assertions.assertThrows(
                                IllegalArgumentException.class, // Acá definimos el tipo de Throw que debería mandar
                                                                // nuestra función
                                () -> s.registrarCliente(cliente), // Acá colocamos la función que vamos a utilizar
                                "El sistema no debería crear al usuario (contraseña vacio) y debe lanzar una excepción" // Mensaje
                                                                                                                        // opcional
                                                                                                                        // por
                                                                                                                        // si
                                                                                                                        // falla
                                                                                                                        // el
                                                                                                                        // test,
                                                                                                                        // qué
                                                                                                                        // debería
                                                                                                                        // ocurrir
                );
        }

        ;

        /* GENERALES */

        /* CLIENTES */

        // @Test
        // void crearClienteValido(){
        // LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        // DtCliente cliente = new DtCliente(
        // "nuevoCliente",
        // "Pedro",
        // "nuevo@gmail.com",
        // "1234",
        // "default.png",
        // "Gomez",
        // fechaNacimiento,
        // "Tokyo",
        // TipoDocumento.CEDULA,
        // 12345678
        // );
        // s.registrarCliente(cliente);
        // Cliente c = s.buscarCliente(cliente);
        // Assertions.assertEquals(cliente.getNickname(), c.getNickname());
        // Assertions.assertEquals(cliente.getNombre(), c.getNombre());
        // Assertions.assertEquals(cliente.getEmail(), c.getEmail());
        // Assertions.assertEquals(cliente.getApellido(), c.getApellido());
        // Assertions.assertEquals(cliente.getFechaNacimiento(),
        // c.getFechaNacimiento());
        // Assertions.assertEquals(cliente.getNacionalidad(), c.getNacionalidad());
        // Assertions.assertEquals(0,c.getComprasPaquetes().size());
        // Assertions.assertEquals(0,c.getComprasPaquetes().size());
        //
        // };

        @Test
        void validarApellidoVacio() {
                LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
                DtCliente cliente = new DtCliente(
                                "power",
                                "Pedro",
                                "laSanguinaria@gmail.com",
                                "1234",
                                "default.png",
                                "",
                                fechaNacimiento,
                                "Tokyo",
                                TipoDocumento.CEDULA,
                                12345678);

                Assertions.assertThrows(
                                IllegalArgumentException.class, // Acá definimos el tipo de Throw que debería mandar
                                                                // nuestra función
                                () -> s.registrarCliente(cliente), // Acá colocamos la función que vamos a utilizar
                                "El sistema no debería crear al usuario (apellido vacio) y debe lanzar una excepción" // Mensaje
                                                                                                                      // opcional
                                                                                                                      // por
                                                                                                                      // si
                                                                                                                      // falla
                                                                                                                      // el
                                                                                                                      // test,
                                                                                                                      // qué
                                                                                                                      // debería
                                                                                                                      // ocurrir
                );
        }

        ;

        @Test
        void validarApellidoSoloLetras() {
                LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
                DtCliente cliente = new DtCliente(
                                "power",
                                "Bloody",
                                "laSanguinaria@gmail.com",
                                "1234",
                                "default.png",
                                "Mary432432",
                                fechaNacimiento,
                                "Tokyo",
                                TipoDocumento.CEDULA,
                                12345678);

                Assertions.assertThrows(
                                IllegalArgumentException.class, // Acá definimos el tipo de Throw que debería mandar
                                                                // nuestra función
                                () -> s.registrarCliente(cliente), // Acá colocamos la función que vamos a utilizar
                                "El sistema no debería crear al usuario ya que su apellido contiene numeros" // Mensaje
                                                                                                             // opcional
                                                                                                             // por si
                                                                                                             // falla el
                                                                                                             // test,
                                                                                                             // qué
                                                                                                             // debería
                                                                                                             // ocurrir
                );
        }

        ;

        @Test
        void validarNacionalidadVacio() {
                LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
                DtCliente cliente = new DtCliente(
                                "power",
                                "Pedro",
                                "laSanguinaria@gmail.com",
                                "1234",
                                "default.png",
                                "Gomez",
                                fechaNacimiento,
                                "",
                                TipoDocumento.CEDULA,
                                12345678);

                Assertions.assertThrows(
                                IllegalArgumentException.class, // Acá definimos el tipo de Throw que debería mandar
                                                                // nuestra función
                                () -> s.registrarCliente(cliente), // Acá colocamos la función que vamos a utilizar
                                "El sistema no debería crear al usuario (nacionalidad vacio) y debe lanzar una excepción" // Mensaje
                                                                                                                          // opcional
                                                                                                                          // por
                                                                                                                          // si
                                                                                                                          // falla
                                                                                                                          // el
                                                                                                                          // test,
                                                                                                                          // qué
                                                                                                                          // debería
                                                                                                                          // ocurrir
                );
        }

        ;

        @Test
        void validarNacionalidadSoloLetras() {
                LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
                DtCliente cliente = new DtCliente(
                                "power",
                                "Bloody",
                                "laSanguinaria@gmail.com",
                                "1234",
                                "default.png",
                                "Mary432432",
                                fechaNacimiento,
                                "Tokyo423432",
                                TipoDocumento.CEDULA,
                                12345678);

                Assertions.assertThrows(
                                IllegalArgumentException.class, // Acá definimos el tipo de Throw que debería mandar
                                                                // nuestra función
                                () -> s.registrarCliente(cliente), // Acá colocamos la función que vamos a utilizar
                                "El sistema no debería crear al usuario ya que su nacionalidad contiene numeros" // Mensaje
                                                                                                                 // opcional
                                                                                                                 // por
                                                                                                                 // si
                                                                                                                 // falla
                                                                                                                 // el
                                                                                                                 // test,
                                                                                                                 // qué
                                                                                                                 // debería
                                                                                                                 // ocurrir
                );
        }

        ;

        @Test
        void validarCedulaCantidadNumeros() {
                LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
                DtCliente cliente = new DtCliente(
                                "power",
                                "Bloody432432",
                                "laSanguinaria@gmail.com",
                                "1234",
                                "default.png",
                                "Mary",
                                fechaNacimiento,
                                "Tokyo",
                                TipoDocumento.CEDULA,
                                1234567891);

                Assertions.assertThrows(
                                IllegalArgumentException.class, // Acá definimos el tipo de Throw que debería mandar
                                                                // nuestra función
                                () -> s.registrarCliente(cliente), // Acá colocamos la función que vamos a utilizar
                                "El sistema no debería crear al usuario ya que su CI es invalida" // Mensaje opcional
                                                                                                  // por si falla el
                                                                                                  // test, qué debería
                                                                                                  // ocurrir
                );
        }

        ;

        @Test
        void validarFechaNacimiento() {
                LocalDate hoy = LocalDate.now();
                LocalDate fechaFutura = hoy.plusYears(1);
                DtCliente cliente = new DtCliente(
                                "power",
                                "Bloody",
                                "laSanguinaria@gmail.com",
                                "1234",
                                "default.png",
                                "Mary",
                                fechaFutura,
                                "Tokyo",
                                TipoDocumento.CEDULA,
                                12345678);

                Assertions.assertThrows(
                                IllegalArgumentException.class, // Acá definimos el tipo de Throw que debería mandar
                                                                // nuestra función
                                () -> s.registrarCliente(cliente), // Acá colocamos la función que vamos a utilizar
                                "El sistema no debería crear al usuario ya que su fecha de nacimiento es futura" // Mensaje
                                                                                                                 // opcional
                                                                                                                 // por
                                                                                                                 // si
                                                                                                                 // falla
                                                                                                                 // el
                                                                                                                 // test,
                                                                                                                 // qué
                                                                                                                 // debería
                                                                                                                 // ocurrir
                );
        }

        ;

        /* CLIENTES */

        /* AEROLINEAS */

        @Test
        void validarDescripcionVacia() {
                DtAerolinea aerolinea = new DtAerolinea(
                                "aerolinea1",
                                "AeroX",
                                "aerox@gmail.com",
                                "1234",
                                "aerox.png",
                                "",
                                "http://aerox.com");

                Assertions.assertThrows(
                                IllegalArgumentException.class, // Acá definimos el tipo de Throw que debería mandar
                                                                // nuestra función
                                () -> s.registrarAerolinea(aerolinea), // Acá colocamos la función que vamos a utilizar
                                "El sistema no debería crear a la aerolinea (descripcion vacio) y debe lanzar una excepción" // Mensaje
                                                                                                                             // opcional
                                                                                                                             // por
                                                                                                                             // si
                                                                                                                             // falla
                                                                                                                             // el
                                                                                                                             // test,
                                                                                                                             // qué
                                                                                                                             // debería
                                                                                                                             // ocurrir
                );
        };

        // @Test
        // void crearAerolineaValida() {
        // DtAerolinea aerolinea = new DtAerolinea(
        // "aerolinea1122",
        // "AeroX",
        // "aerox1122@gmail.com",
        // "1234",
        // "aerox.png",
        // "descripcionn",
        // "http://aerox.com"
        // );
        //
        // s.registrarAerolinea(aerolinea);
        // Aerolinea a = s.buscarAerolinea(aerolinea);
        // Assertions.assertEquals(a.getNickname(), aerolinea.getNickname());
        // Assertions.assertEquals(a.getNombre(), aerolinea.getNombre());
        // Assertions.assertEquals(a.getEmail(), aerolinea.getEmail());
        // Assertions.assertEquals(a.getDescripcion(), aerolinea.getDescripcion());
        // Assertions.assertEquals(a.getLinkWeb(), aerolinea.getLinkWeb());
        // Assertions.assertEquals(0, a.getRutasDeVuelo().size());
        // };

        /* AEROLINEAS */

        // ------------------ Modificar Datos de Usuario ------------------ //
        // ------------------ Alta de Ruta de Vuelo ------------------ //
        // ------------------ Alta de Vuelo ----------------- //

        @Test
        void validarNombreVacioAltaVuelo() {
                DtVuelo vuelo = new DtVuelo(
                                "",
                                LocalDate.now().plusDays(200),
                                LocalTime.of(2, 30),
                                3,
                                3,
                                "default.png",
                                LocalDate.now(),
                                s.getRutaDeVuelo("Lima-Montevideo Norte"), // Es una ruta con estado Aprobada.
                                0);

                Assertions.assertThrows(
                                IllegalArgumentException.class,
                                () -> s.altaVuelo(vuelo),
                                "El sistema no debería crear el vuelo ya que su nombre es vacío.");

        }

        @Test
        void validarNombreExistenteAltaVuelo() {
                DtVuelo vuelo = new DtVuelo(
                                "Vuelo1", // Este nombre debería existir.
                                LocalDate.now().plusDays(200),
                                LocalTime.of(2, 30),
                                3,
                                3,
                                "default.png",
                                LocalDate.now(),
                                s.getRutaDeVuelo("Lima-Montevideo Norte"), // Es una ruta con estado Aprobada.
                                0);

                Assertions.assertThrows(
                                IllegalArgumentException.class,
                                () -> s.altaVuelo(vuelo),
                                "El sistema no debería crear el vuelo ya que su nombre ya existe en el sistema.");

        }

        @Test
        void validarFechaInvalidaAltaVuelo() {
                DtVuelo vuelo = new DtVuelo(
                                "VueloTestFecha",
                                LocalDate.now().minusDays(1), // Fecha inválida
                                LocalTime.of(2, 30),
                                3,
                                3,
                                "default.png",
                                LocalDate.now(),
                                s.getRutaDeVuelo("Lima-Montevideo Norte"), // Es una ruta con estado Aprobada.
                                0);

                Assertions.assertThrows(
                                IllegalArgumentException.class,
                                () -> s.altaVuelo(vuelo),
                                "El sistema no debería crear el vuelo ya que su fecha de salida es 'antigua'.");
        }

        @Test
        void validarHoraInvalidaAltaVuelo() {
                DtVuelo vuelo = new DtVuelo(
                                "VueloTestHora",
                                LocalDate.now().plusDays(200),
                                LocalTime.of(0, 0), // Hora inválida
                                3,
                                3,
                                "default.png",
                                LocalDate.now(),
                                s.getRutaDeVuelo("Lima-Montevideo Norte"), // Es una ruta con estado Aprobada.
                                0);

                Assertions.assertThrows(
                                IllegalArgumentException.class,
                                () -> s.altaVuelo(vuelo),
                                "El sistema no debería crear el vuelo ya que su duración es de 0.");
        }

        @Test
        void validarCupoInvalidoAltaVuelo() {
                DtVuelo vuelo = new DtVuelo(
                                "VueloTestCupo",
                                LocalDate.now().plusDays(200),
                                LocalTime.of(2, 30),
                                0, // Entre ambos cupos
                                0, // Deberian sumar 1 o más.
                                "default.png",
                                LocalDate.now(),
                                s.getRutaDeVuelo("Lima-Montevideo Norte"), // Es una ruta con estado Aprobada.
                                0);

                Assertions.assertThrows(
                                IllegalArgumentException.class,
                                () -> s.altaVuelo(vuelo),
                                "El sistema no debería crear el vuelo ya que su cupo total es de 0.");
        }

        @Test
        void validarRutaInvalidaRechazadaAltaVuelo() {
                DtVuelo vuelo = new DtVuelo(
                                "VueloTestRutaRechazada",
                                LocalDate.now().plusDays(200),
                                LocalTime.of(2, 30),
                                3,
                                3,
                                "default.png",
                                LocalDate.now(),
                                s.getRutaDeVuelo("Montevideo Norte-Quito"), // Es una ruta con estado Rechazada.
                                0);

                Assertions.assertThrows(
                                IllegalArgumentException.class,
                                () -> s.altaVuelo(vuelo),
                                "El sistema no debería crear el vuelo ya que su rutaDeVuelo está rechazada.");
        }

        @Test
        void validarRutaInvalidaIngresadaAltaVuelo() {
                DtVuelo vuelo = new DtVuelo(
                                "VueloTestRutaIngresada",
                                LocalDate.now().plusDays(200),
                                LocalTime.of(2, 30),
                                3,
                                3,
                                "default.png",
                                LocalDate.now(),
                                s.getRutaDeVuelo("Santiago-Lima"), // Es una ruta con estado Ingresada.
                                0);

                Assertions.assertThrows(
                                IllegalArgumentException.class,
                                () -> s.altaVuelo(vuelo),
                                "El sistema no debería crear el vuelo ya que su rutaDeVuelo está Ingresada.");
        }

        @Test
        void validarRutaNoExistenteAltaVuelo() {
                DtRuta ruta = new DtRuta( // Esta creación literalmente no sirve ni se persiste en la BD.
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
                                s.buscarCiudad("Quito", "Ecuador").getDatos());

                DtVuelo vuelo = new DtVuelo(
                                "VueloTestFecha",
                                LocalDate.now().plusDays(200),
                                LocalTime.of(2, 30),
                                3,
                                3,
                                "default.png",
                                LocalDate.now(),
                                ruta, // Es una ruta con estado Ingresada.
                                0);

                Assertions.assertThrows(
                                IllegalArgumentException.class,
                                () -> s.altaVuelo(vuelo),
                                "El sistema no debería crear el vuelo ya que su rutaDeVuelo no está Ingresada en el sistema.");
        }

        @Test
        void validarVueloInexistenteAltaVuelo() {
                DtVuelo vuelo = new DtVuelo(
                                "Vuelo11",
                                LocalDate.now().plusDays(200),
                                LocalTime.of(2, 30),
                                3,
                                3,
                                "default.png",
                                LocalDate.now(),
                                s.getRutaDeVuelo("Rio de Janeiro-Bogotá"), // Es una ruta con estado Aprobada.
                                0);

                s.altaVuelo(vuelo);     

                Assertions.assertDoesNotThrow(() -> s.consultarVuelo("Vuelo11"),
                                "El vuelo debería existir y no lanzar excepción");
        }

        // ------------------ Reserva de Vuelo ------------------ //
        // ------------------ Alta de Ciudad ------------------ //
        // ------------------ Crear Paquete de Rutas de Vuelo ------------------ //
        // ------------------ Agregar Ruta de Vuelo a Paquete ------------------ //

        @Test
        void agregarRutaAPaqueteValido() {
                // Obtener un paquete y una ruta APROBADA
                DtPaquete paquete = s.getPaquete("ExploraSur");
                DtRuta ruta = s.getRutaDeVuelo("Montevideo-Buenos Aires"); 

                int cantidadInicial = 0; 
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
                                "www.origen.com", java.time.LocalDate.now());
                DtCiudad ciudadDestino = new DtCiudad("Destino", "Pais", "Aeropuerto Destino", "Descripción destino",
                                "www.destino.com", java.time.LocalDate.now());

                DtRuta rutaInexistente = new DtRuta(
                                "RutaInexistente", "No existe", "No existe corta",
                                java.time.LocalTime.of(2, 0), 100, 200, 15,
                                java.time.LocalDate.now(), "default.png",
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

        // ------------------ Compra de Paquete -Alta de Categorías ------------------
        // //
        // ------------------ Aceptar/Rechazar Ruta de Vuelo ------------------ //

}
