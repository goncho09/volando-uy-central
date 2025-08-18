package clases;

import datatypes.DtCategoria;
import datatypes.DtCiudad;
import datatypes.DtPaquete;

import java.util.LinkedHashMap;
import java.util.List;
import datatypes.DtVuelo;

public interface ISistema {
    List<DtPaquete> listarPaquetes();
    void seleccionarPaquete(String nombre);
    DtPaquete getPaquete();

    void altaCategoria(DtCategoria categoria);
    void altaCiudad(DtCiudad ciudad);
    void consultaVuelo(DtVuelo vuelo);

    // TEMPORAL
    List<Ciudad> getCiudades();
    void altaPaquete(DtPaquete paquete);
    List<Categoria> getCategorias();
}