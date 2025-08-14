package clases;



public class Aerolinea extends Usuario {
    private String descripcion;
    private String linkWeb;

    public Aerolinea(String nickname,String nombre, String email,String descripcion, String linkWeb){
        super(nickname, nombre, email);
        this.descripcion = descripcion;
        this.linkWeb = linkWeb;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLinkWeb() {
        return linkWeb;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setLinkWeb(String linkWeb) {
        this.linkWeb = linkWeb;
    }
}
