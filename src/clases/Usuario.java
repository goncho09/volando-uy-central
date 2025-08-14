package clases;

import datatypes.DtUsuario;

public class Usuario {
    protected String nickname;
    protected String nombre;
    protected String email;

    public Usuario(DtUsuario usuario){
        this.nickname = usuario.getNickname();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
    }

    public String getNickname() {
        return nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

