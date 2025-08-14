package datatypes;

public class DtVuelo {
    private String nombre;
    private DtFecha fecha;
    private DtHora duracion;
    private int maxTuristas;
    private int maxEjecutivos;
    private DtFecha fechaAlta;

    public DtVuelo(String nombre, DtFecha fecha, DtHora duracion, int maxTuristas, int maxEjecutivos, DtFecha fechaAlta) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.duracion = duracion;
        this.maxTuristas = maxTuristas;
        this.maxEjecutivos = maxEjecutivos;
        this.fechaAlta = fechaAlta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DtFecha getFecha() {
        return fecha;
    }

    public void setFecha(DtFecha fecha) {
        this.fecha = fecha;
    }

    public DtHora getDuracion() {
        return duracion;
    }

    public void setDuracion(DtHora duracion) {
        this.duracion = duracion;
    }

    public int getMaxTuristas() {
        return maxTuristas;
    }

    public void setMaxTuristas(int maxTuristas) {
        this.maxTuristas = maxTuristas;
    }

    public int getMaxEjecutivos() {
        return maxEjecutivos;
    }

    public void setMaxEjecutivos(int maxEjecutivos) {
        this.maxEjecutivos = maxEjecutivos;
    }

    public DtFecha getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(DtFecha fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
