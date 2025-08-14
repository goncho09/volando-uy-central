package datatypes;

public class DtUsuario {
    protected String nickname;
    protected String nombre;
    protected String email;

    public DtUsuario(String nickname,String nombre, String email){
        this.nickname = nickname;
        this.nombre = nombre;
        this.email = email;
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
