import datatypes.DtFecha;

public class Main {
    public static void main(String[] args) {
        System.out.println("hgoaññ");
        DtFecha fecha = new DtFecha(1,4,2003);
        DtFecha fecha2 = new DtFecha(2,4,2003);

        System.out.println(fecha.equals(fecha2));

    }
}