package com.app.casouso;

import com.app.clases.ISistema;
import com.app.datatypes.DtCategoria;
import com.app.util.DummyFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AltaCategoriaTest {


    static ISistema s;

    @BeforeAll
    static void setUp() {
        s = DummyFactory.crearSistema();
    }

    @Test
void altaCategoriaValida() {
    String nombreUnico = "CategoriaTest_" + System.currentTimeMillis() + "_" + Math.random();
    DtCategoria categoria = new DtCategoria(nombreUnico);

    // Verificar que no existe previamente
    Assertions.assertDoesNotThrow(
            () -> s.altaCategoria(categoria),
            "Debería permitir crear una categoría válida");

    // Verificar que se agregó a la lista
    List<DtCategoria> categorias = s.buscarCategorias();
    boolean encontrada = categorias.stream()
            .anyMatch(c -> c.getNombre().equals(nombreUnico));
    Assertions.assertTrue(encontrada, "La categoría '" + nombreUnico + "' debería estar en la lista");
}

    @Test
    void altaCategoriaNombreVacio() {
        DtCategoria categoria = new DtCategoria("");

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaCategoria(categoria),
                "No debería permitir categoría con nombre vacío");
    }

    @Test
    void altaCategoriaNombreExistente() {
        // Usar una categoría que ya existe en DummyFactory
        DtCategoria categoria = new DtCategoria("Turismo");

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaCategoria(categoria),
                "No debería permitir categoría con nombre existente");
    }

    @Test
    void altaCategoriaNombreNull() {
        DtCategoria categoria = new DtCategoria(null);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaCategoria(categoria),
                "No debería permitir categoría con nombre null");
    }

    @Test
    void listarCategoriasNoVacio() {
        List<DtCategoria> categorias = s.buscarCategorias();

        Assertions.assertFalse(categorias.isEmpty(),
                "Debería haber categorías en el sistema");
    }

    @Test
    void altaCategoriaConEspacios() {
        DtCategoria categoria = new DtCategoria("  CategoriaConEspacios  ");

        Assertions.assertDoesNotThrow(
                () -> s.altaCategoria(categoria),
                "Debería permitir categoría con espacios");
    }

    @Test
    void altaCategoriaCaracteresEspeciales() {
        DtCategoria categoria = new DtCategoria("Categoría-Con-Carácteres!");

        Assertions.assertDoesNotThrow(
                () -> s.altaCategoria(categoria),
                "Debería permitir categoría con caracteres especiales");
    }

    @Test
    void buscarCategoriaExistente() {
        // Buscar una categoría que sabemos existe en DummyFactory
        List<DtCategoria> categorias = s.buscarCategorias();
        DtCategoria categoria = categorias.get(0);

        Assertions.assertNotNull(categoria, "Debería encontrar categorías existentes");
        Assertions.assertNotNull(categoria.getNombre(), "La categoría debería tener nombre");
    }

    @Test
    void altaCategoriaNombreMuyLargo() {
        String nombreLargo = "A".repeat(100); // 100 caracteres
        DtCategoria categoria = new DtCategoria(nombreLargo);

        Assertions.assertDoesNotThrow(
                () -> s.altaCategoria(categoria),
                "Debería permitir categorías con nombres largos");
    }

    @Test
    void altaCategoriaNombreConNumeros() {
        DtCategoria categoria = new DtCategoria("Categoria123");

        Assertions.assertDoesNotThrow(
                () -> s.altaCategoria(categoria),
                "Debería permitir categorías con números en el nombre");
    }

    @Test
    void altaCategoriaCaseSensitive() {
        DtCategoria categoria1 = new DtCategoria("NUEVACATEGORIA");
        DtCategoria categoria2 = new DtCategoria("nuevacategoria");

        // Crear primera categoría
        Assertions.assertDoesNotThrow(
                () -> s.altaCategoria(categoria1),
                "Debería permitir crear categoría en mayúsculas");

        // Intentar crear misma categoría en minúsculas (depende si es case-sensitive)
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaCategoria(categoria2),
                "No debería permitir categoría duplicada (case-sensitive)");
    }

    @Test
    void altaMultiplesCategoriasDiferentes() {
        DtCategoria categoria1 = new DtCategoria("CategoriaA");
        DtCategoria categoria2 = new DtCategoria("CategoriaB");
        DtCategoria categoria3 = new DtCategoria("CategoriaC");

        // Crear múltiples categorías diferentes
        Assertions.assertDoesNotThrow(
                () -> s.altaCategoria(categoria1),
                "Debería permitir primera categoría");

        Assertions.assertDoesNotThrow(
                () -> s.altaCategoria(categoria2),
                "Debería permitir segunda categoría");

        Assertions.assertDoesNotThrow(
                () -> s.altaCategoria(categoria3),
                "Debería permitir tercera categoría");

        // Verificar que todas están en la lista
        List<DtCategoria> categorias = s.buscarCategorias();
        long count = categorias.stream()
                .filter(c -> c.getNombre().equals("CategoriaA") ||
                        c.getNombre().equals("CategoriaB") ||
                        c.getNombre().equals("CategoriaC"))
                .count();
        Assertions.assertEquals(3, count, "Deberían estar las 3 categorías nuevas");
    }

    @Test
    void altaCategoriaConTildes() {
        DtCategoria categoria = new DtCategoria("CategoríaConTildesíeéó");

        Assertions.assertDoesNotThrow(
                () -> s.altaCategoria(categoria),
                "Debería permitir categorías con tildes");
    }

    @Test
    void altaCategoriaNombreSoloEspacios() {
        DtCategoria categoria = new DtCategoria("   ");

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> s.altaCategoria(categoria),
                "No debería permitir categoría con solo espacios");
    }

    @Test
    void listarCategoriasContieneCategoriasBase() {
        List<DtCategoria> categorias = s.buscarCategorias();

        // Verificar que contiene las categorías base del DummyFactory
        boolean tieneTurismo = categorias.stream()
                .anyMatch(c -> c.getNombre().equals("Turismo"));
        boolean tieneNegocios = categorias.stream()
                .anyMatch(c -> c.getNombre().equals("Negocios"));
        boolean tieneAventura = categorias.stream()
                .anyMatch(c -> c.getNombre().equals("Aventura"));

        Assertions.assertTrue(tieneTurismo, "Debería tener categoría Turismo");
        Assertions.assertTrue(tieneNegocios, "Debería tener categoría Negocios");
        Assertions.assertTrue(tieneAventura, "Debería tener categoría Aventura");
    }

    @Test
    void altaCategoriaYVerificarPersistencia() {
        DtCategoria categoria = new DtCategoria("CategoriaPersistente");

        // Crear categoría
        s.altaCategoria(categoria);

        // Verificar que persiste en listados
        List<DtCategoria> categorias1 = s.buscarCategorias();
        boolean encontrada1 = categorias1.stream()
                .anyMatch(c -> c.getNombre().equals("CategoriaPersistente"));

        // Simular "recarga" del sistema (en realidad mismo sistema por @BeforeAll)
        List<DtCategoria> categorias2 = s.buscarCategorias();
        boolean encontrada2 = categorias2.stream()
                .anyMatch(c -> c.getNombre().equals("CategoriaPersistente"));

        Assertions.assertTrue(encontrada1 && encontrada2,
                "La categoría debería persistir entre consultas");
    }

    @Test
    void altaCategoriaNombreConGuiones() {
        DtCategoria categoria = new DtCategoria("Categoria-Con-Guiones");

        Assertions.assertDoesNotThrow(
                () -> s.altaCategoria(categoria),
                "Debería permitir categorías con guiones");
    }

    @Test
    void altaCategoriaNombreConUnderscore() {
        DtCategoria categoria = new DtCategoria("Categoria_Con_Underscore");

        Assertions.assertDoesNotThrow(
                () -> s.altaCategoria(categoria),
                "Debería permitir categorías con underscore");
    }

    @Test
    void altaCategoriaYUsarEnRuta() {
        // Primero crear categoría
        DtCategoria nuevaCategoria = new DtCategoria("CategoriaParaRuta");
        s.altaCategoria(nuevaCategoria);

        // Verificar que está disponible para usar
        List<DtCategoria> categoriasDisponibles = s.buscarCategorias();
        boolean disponible = categoriasDisponibles.stream()
                .anyMatch(c -> c.getNombre().equals("CategoriaParaRuta"));

        Assertions.assertTrue(disponible,
                "La categoría nueva debería estar disponible para usar en rutas");
    }
}

