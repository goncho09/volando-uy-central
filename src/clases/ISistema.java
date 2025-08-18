package clases;

import datatypes.DtCategoria;
import datatypes.DtCiudad;
import datatypes.DtPaquete;

import java.util.LinkedHashMap;
import java.util.List;

public interface ISistema {
    List<DtPaquete> listarPaquetes();
    void seleccionarPaquete(String nombre);
    DtPaquete getPaquete();

    void altaCategoria(DtCategoria categoria);
    void altaCiudad(DtCiudad ciudad);

    // TEMPORAL
    List<Ciudad> getCiudades();
    void altaPaquete(DtPaquete paquete);
    List<Categoria> getCategorias();
}