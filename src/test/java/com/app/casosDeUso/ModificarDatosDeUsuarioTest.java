package com.app.casosDeUso;

import com.app.clases.ISistema;
import com.app.datatypes.DtAerolinea;
import com.app.datatypes.DtCliente;
import com.app.enums.TipoDocumento;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ModificarDatosDeUsuarioTest {

    static ISistema s;

    @BeforeAll
    static void setUp() {
        s = DummyFactory.crearSistema();
    }

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
    void validarContraseniaVaciaModificarUsuario() {
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
}
