package datatypes;

public class DtCiudad {
    private String nombre;
    private String ciudad;
    private String aeropuerto;
    private String descripcion;
    private String sitioWeb;
    private DtFecha fechaAlta;

    public DtCiudad(String nombre, String ciudad, String aeropuerto, String descripcion, String sitioWeb, DtFecha fechaAlta) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.aeropuerto = aeropuerto;
        this.descripcion = descripcion;
        this.sitioWeb = sitioWeb;
        this.fechaAlta = fechaAlta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(String aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public DtFecha getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(DtFecha fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
