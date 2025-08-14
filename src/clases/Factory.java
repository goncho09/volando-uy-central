package clases;

public class Factory {
    public static ISistema getSistema() {
        return Sistema.getInstancia();
    }
}
