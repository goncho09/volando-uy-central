package clases;

import datatypes.*;

import java.util.List;

public interface ISistema {

    List<DtPaquete> listarPaquetes();
    void seleccionarPaquete(String nombre);
    DtPaquete getPaquete();

    void altaCategoria(DtCategoria categoria);

    void altaCiudad(DtCiudad ciudad);

    void consultaVuelo(DtVuelo vuelo);

    void registrarCliente(DtCliente cliente);
    void modificarCliente(DtCliente cliente);
    void registrarAerolinea(DtAerolinea aerolinea);
    void modificarAerolinea(DtAerolinea aerolinea);
    void cancelarAltaUsuario();
    void confirmarAltaUsuario();

    // TEMPORAL
    List<Ciudad> getCiudades();
    void altaPaquete(DtPaquete paquete);
    List<Categoria> getCategorias();
}