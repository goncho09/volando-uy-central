package clases;

public class Usuario {
    private String nickname;
    private String nombre;
    private String email;

    public Usuario(){
        this.nickname = "pedrito12";
        this.nombre = "Pedro";
        this.email = "pedro@gmail.com";
    }

    public void saludar(){
        System.out.println("Hola " + this.nombre);
    }
}

