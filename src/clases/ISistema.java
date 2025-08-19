package clases;

import datatypes.*;

import java.util.List;
import java.util.List;
import java.util.Set;

public interface ISistema {
    void ejecutar();
    // Consulta de Ruta de Vuelo
    List<String> listarAerolineas();
    List<String> listarRutasDeAerolinea(String nickname);
    DtRuta consultarRuta(String nombre);
    DtVuelo consultarVuelo(String nombre);

    // Alta de Ruta de Vuelo
    boolean existeRuta(String nombre);
    void altaRutaDeVuelo(String nickname, DtRuta datosRuta);

    //Consulta de Usuario
    // Consulta de Usuario
    List<DtUsuario> listarUsuarios();
    void elegirUsuario(String nickname);
    DtUsuario getUsuarioSeleccionado();
    List<DtReserva> mostrarReservas();
    List<DtPaquete> mostrarPaquetes();
    List<DtRuta> mostrarRutasDeVuelo();

}