package clases;

import datatypes.DtCategoria;
import datatypes.DtCiudad;
import datatypes.DtVuelo;

public interface ISistema {
    void altaCategoria(DtCategoria categoria);
    void altaCiudad(DtCiudad ciudad);
    void consultaVuelo(DtVuelo vuelo);

}