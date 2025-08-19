package datatypes;

import clases.CompraPaquete;
import enums.TipoDocumento;
import java.util.List;


public class DtCliente extends DtUsuario{
    private String apellido;
    private DtFecha fechaNacimiento;
    private String nacionalidad;
    private TipoDocumento tipoDocumento;
    private int numeroDocumento;
    private List<CompraPaquete> comprasPaquetes;

    public DtCliente(String nickname, String nombre, String email,String apellido, DtFecha fechaNacimiento, String nacionalidad, TipoDocumento tipoDocumento, int numeroDocumento, List<CompraPaquete> comprasPaquetes) {
        super(nickname, nombre, email);
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.comprasPaquetes = comprasPaquetes;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public DtFecha getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(DtFecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public List<CompraPaquete> getComprasPaquetes() {
        return comprasPaquetes;
    }

    public void setComprasPaquetes(List<CompraPaquete> comprasPaquetes) {
        this.comprasPaquetes = comprasPaquetes;
    }

}
