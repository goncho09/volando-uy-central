package com.app.casouso;

import com.app.clases.ISistema;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.BeforeAll;

public class AltaCategoriaTest {

    static ISistema s;

    @BeforeAll
    static void setUp() {
        s = DummyFactory.crearSistema();
    }
}
