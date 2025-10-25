package com.app.casouso;

import com.app.clases.ISistema;
import com.app.datatypes.DtAerolinea;
import com.app.datatypes.DtCliente;
import com.app.enums.TipoDocumento;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class AltaUsuarioTest {

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
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario (nickname vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

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
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario (mismo nickname) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

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
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario (nombre vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

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
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario ya que su nombre contiene numeros" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

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
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario (email vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

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
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear un usuario cn el mismo email que otra persona" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

    @Test
    void validarContraseniaVacia() {
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

    /* GENERALES */

    /* CLIENTES */

    @Test
    void crearClienteValido(){
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        DtCliente cliente = new DtCliente(
                "nuevoCliente",
                "Pedro",
                "nuevo@gmail.com",
                "1234",
                "default.png",
                "Gomez",
                fechaNacimiento,
                "Tokyo",
                TipoDocumento.CEDULA,
                12345678
        );

        s.registrarCliente(cliente);

        Assertions.assertEquals(
                s.buscarCliente(cliente.getNickname()).getDatos().getNickname(),
                s.getCliente(cliente.getNickname()).getNickname(),
                "El sistema crea al cliente"
        );
    };

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
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario (apellido vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

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
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario ya que su apellido contiene numeros" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

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
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario (nacionalidad vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

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
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario ya que su nacionalidad contiene numeros" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

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
                1234567891
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario ya que su CI es invalida" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

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
                12345678
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarCliente(cliente), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear al usuario ya que su fecha de nacimiento es futura" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

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
                "http://aerox.com"
        );

        Assertions.assertThrows(
                IllegalArgumentException.class, //Acá definimos el tipo de Throw que debería mandar nuestra función
                () -> s.registrarAerolinea(aerolinea), //Acá colocamos la función que vamos a utilizar
                "El sistema no debería crear a la aerolinea (descripcion vacio) y debe lanzar una excepción" //Mensaje opcional por si falla el test, qué debería ocurrir
        );
    }

    @Test
    void crearAerolineaValida() {
        DtAerolinea aerolinea = new DtAerolinea(
                "aerolinea1122",
                "AeroX",
                "aerox1122@gmail.com",
                "1234",
                "aerox.png",
                "descripcionn",
                "http://aerox.com"
        );

        s.registrarAerolinea(aerolinea);

        Assertions.assertEquals(
                s.buscarAerolinea(aerolinea.getNickname()).getDatos().getNickname(),
                s.getAerolinea(aerolinea.getNickname()).getNickname(),
                "El sistema crea a la aerolinea"
        );
    };

    /* AEROLINEAS */

}
