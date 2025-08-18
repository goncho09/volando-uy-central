import clases.*;
import datatypes.*;
import enums.TipoAsiento;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {

    private JPanel menuPrincipal;
    private JTabbedPane gestionUsuarios;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;
    private JComboBox comboBox2;
    private JTextField textField1;
    private JTextField textField2;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JSpinner spinner3;
    private JSpinner spinner4;
    private JSpinner spinner5;
    private JComboBox comboBox1;
    private JPanel formVuelo;
    private JPanel fechaField;
    private JButton crearVueloButton;

    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Admin Dashboard");
        add(menuPrincipal);


    }

    public static void main(String[] args) {

        new Main();

        ISistema s = Factory.getSistema();


        DtCategoria cat1 = new DtCategoria("Running");
        DtCategoria cat2 = new DtCategoria("Salto Montaña");
        DtCategoria cat3 = new DtCategoria("Tirolesa");

        DtFecha fecha1 = new DtFecha(18,8,2025);

        DtCiudad c1 = new DtCiudad("Montevideo","Uruguay","Aeropuerto de Carrasco","Mucha rambla","www.aeropuerto-carrasco.uy",fecha1);
        DtCiudad c2 = new DtCiudad("Buenos Aires", "Argentina", "Aeropuerto Jorge Newbery", "Puerto Madero", "www.aeroparque.com.ar", fecha1);
        DtCiudad c3 = new DtCiudad("Santiago", "Chile", "Aeropuerto Arturo Merino Benítez", "Cerro San Cristóbal", "www.aeropuertosantiago.cl", fecha1);


        s.altaCategoria(cat1);
        //s.altaCategoria(cat1);
        s.altaCategoria(cat2);
        s.altaCategoria(cat3);

        s.altaCiudad(c1);
        s.altaCiudad(c2);
        s.altaCiudad(c3);

        DtHora hora = new DtHora(10,0);

        RutaDeVuelo ruta1 = new RutaDeVuelo(new DtRuta("Vuelo A1", "Descripcion A1", hora, 100, 200, 20, fecha1,s.getCategorias(), s.getCiudades()));
        RutaDeVuelo ruta2 = new RutaDeVuelo(new DtRuta("Vuelo A2", "Descripcion A2", hora, 120, 220, 25, fecha1, s.getCategorias(), s.getCiudades()));

        List<RutaDeVuelo> rutasPaquete1 = new ArrayList<>();
        rutasPaquete1.add(ruta1);
        rutasPaquete1.add(ruta2);

        RutaEnPaquete rep1 = new RutaEnPaquete(2, TipoAsiento.TURISTA, ruta1);
        RutaEnPaquete rep2 = new RutaEnPaquete(1, TipoAsiento.EJECUTIVO, ruta2);

        List<RutaEnPaquete> rutasEnPaquete1 = new ArrayList<>();
        rutasEnPaquete1.add(rep1);
        rutasEnPaquete1.add(rep2);

        DtPaquete p1 = new DtPaquete("Paquete A", "Descripcion A", 10, 5, 100,rutasPaquete1,rutasEnPaquete1);
        DtPaquete p2 = new DtPaquete("Paquete B", "Descripcion B", 15, 10, 150, rutasPaquete1, rutasEnPaquete1);
        DtPaquete p3 = new DtPaquete("Paquete C", "Descripcion C", 7, 3, 80, rutasPaquete1, rutasEnPaquete1);

        // TEMPORAL
        s.altaPaquete(p1);
        s.altaPaquete(p2);
        s.altaPaquete(p3);
        List <DtPaquete> paquetes = s.listarPaquetes();

        for (int i = 0; i < paquetes.size(); i++) {
            DtPaquete p = paquetes.get(i);
            System.out.println(p.getNombre() + " " + p.getDescripcion() + " " + p.getValidezDias() + " " + p.getDescuento() + " " + p.getCosto());
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}