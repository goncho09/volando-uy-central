package com.app.casosDeUso;

import com.app.clases.ISistema;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.BeforeAll;

public class AceptarRechazarVueloTest {

    static ISistema s;

    @BeforeAll
    static void setUp() {
        s = DummyFactory.crearSistema();
    }


}
