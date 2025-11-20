package com.app.casouso;

import com.app.clases.ISistema;
import com.app.datatypes.DtCliente;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class SeguirUsuarioTest {

    static ISistema s;

    @BeforeAll
    static void setUp() {
        s = DummyFactory.crearSistema();
    }

    @Test
    void validarSiguioUsuario() {
        DtCliente seguidor = s.getCliente("power");
        DtCliente seguido = s.getCliente("anaGomez");
        s.seguirUsuario(seguidor.getNickname(), seguido.getNickname());

        Assertions.assertTrue(seguidor.sigueA(seguido.getNickname()));
    }
}
