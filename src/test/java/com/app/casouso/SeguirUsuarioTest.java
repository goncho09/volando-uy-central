package com.app.casouso;

import com.app.clases.ISistema;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SeguirUsuarioTest {

    static ISistema s;

    @BeforeAll
    static void setUp() {
        s = DummyFactory.crearSistema();
    }

    @Test
    void seguirUsuarioExistente() {
        int cantSeguidos = s.getUsuario("crystalMotus").getSeguidos().size();
        int cantSeguidores= s.getUsuario("power").getSeguidores().size();

        s.seguirUsuario("crystalMotus", "power");

        assertEquals(cantSeguidos + 1, s.getUsuario("crystalMotus").getSeguidos().size(),
                "La cantidad de seguidos debería aumentar en 1");

        assertEquals(cantSeguidores + 1, s.getUsuario("power").getSeguidores().size(),
                "La cantidad de seguidores debería aumentar en 1");
    }

    @Test
    void seguirUsuarioInexistente() {
        assertThrows(Exception.class, () ->
                        s.seguirUsuario("usuarioFantasma", "power"),
                "Debería lanzar excepción al intentar seguir un usuario inexistente"
        );
    }

    @Test
    void seguidorInexistenteNoPuedeSeguir() {
        assertThrows(Exception.class, () ->
                        s.seguirUsuario("crystalMotus", "usuarioFantasma"),
                "Un usuario inexistente no debería poder seguir"
        );
    }

    @Test
    void noPuedeSeguirseASiMismo() {
        assertThrows(Exception.class, () ->
                        s.seguirUsuario("power", "power"),
                "No debería poder seguirse a sí mismo"
        );
    }

    @Test
    void noPuedeSeguirDosVeces() {
        s.seguirUsuario("aeroDream", "sunFly");

        int cant = s.getUsuario("aeroDream").getSeguidores().size();

        assertThrows(Exception.class, () ->
                        s.seguirUsuario("aeroDream", "sunFly"),
                "No debería poder seguir dos veces al mismo usuario"
        );

        assertEquals(cant, s.getUsuario("aeroDream").getSeguidores().size(),
                "La cantidad de seguidores no debería cambiar");
    }

    @Test
    void dejarDeSeguirUsuario() {
        s.seguirUsuario("zacgamer", "blueWing");

        int cant = s.getUsuario("blueWing").getSeguidores().size();

        s.dejarDeSeguirUsuario("zacgamer", "blueWing");

        assertEquals(cant - 1, s.getUsuario("zacgamer").getSeguidos().size(),
                "La cantidad de seguidores debería disminuir en 1");

        assertEquals(cant - 1, s.getUsuario("blueWing").getSeguidores().size(),
                "La cantidad de seguidores debería disminuir en 1");
    }

    @Test
    void dejarDeSeguirUsuarioQueNoSigo() {
        assertThrows(Exception.class, () ->
                        s.dejarDeSeguirUsuario("luisRamirez", "carlosSanchez"),
                "No debería poder dejar de seguir a un usuario que no se sigue"
        );
    }

    @Test
    void dejarDeSeguirUsuarioInexistente() {
        assertThrows(Exception.class, () ->
                        s.dejarDeSeguirUsuario("usuarioFantasma", "power"),
                "No debería poder dejar de seguir a un usuario inexistente"
        );
    }

    @Test
    void usuarioInexistenteNoPuedeDejarDeSeguir() {
        assertThrows(Exception.class, () ->
                        s.dejarDeSeguirUsuario("crystalMotus", "usuarioFantasma"),
                "Un usuario inexistente no puede dejar de seguir"
        );
    }
}
