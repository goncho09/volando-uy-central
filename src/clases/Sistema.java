package clases;

public class Sistema implements ISistema {
    private static Sistema instancia;

    private Sistema() { }

    public static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    @Override
    public void ejecutar() {
        System.out.println("Sistema ejecutando...");
    }
}
