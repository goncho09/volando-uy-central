import datatypes.*;
import clases.ISistema;
import clases.Factory;

import javax.swing.*;

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

        ISistema s1 = Factory.getSistema();


        DtCategoria cat1 = new DtCategoria("Running");
        DtCategoria cat2 = new DtCategoria("Salto Montaña");
        DtCategoria cat3 = new DtCategoria("Tirolesa");

        DtFecha fecha1 = new DtFecha(18,8,2025);
        DtFecha fecha2 = new DtFecha(19,8,2025);
        DtHora hora1 = new DtHora(10, 30);

        DtCiudad c1 = new DtCiudad("Montevideo","Uruguay","Aeropuerto de Carrasco","Mucha rambla","www.aeropuerto-carrasco.uy",fecha1);
        DtCiudad c2 = new DtCiudad("Buenos Aires", "Argentina", "Aeropuerto Jorge Newbery", "Puerto Madero", "www.aeroparque.com.ar", fecha1);
        DtCiudad c3 = new DtCiudad("Santiago", "Chile", "Aeropuerto Arturo Merino Benítez", "Cerro San Cristóbal", "www.aeropuertosantiago.cl", fecha1);

        /*
        DtVuelo vuelo1 = new DtVuelo("Vuelo1", fecha1, hora1, 45, 15, fecha2);
        DtVuelo vuelo2 = new DtVuelo("Vuelo2", fecha1, hora1, 60, 20, fecha2);
        DtVuelo vuelo3 = new DtVuelo("Vuelo3", fecha1, hora1, 30, 10, fecha2);
         */

        //Alta de categorías
        s1.altaCategoria(cat1);
        //s1.altaCategoria(cat1);
        s1.altaCategoria(cat2);
        s1.altaCategoria(cat3);

        //Alta de ciudades
        s1.altaCiudad(c1);
        s1.altaCiudad(c2);
        s1.altaCiudad(c3);

        /*Consulta de vuelos esto no va funciona porque no estan las rutas de vuelo
        s1.consultaVuelo(vuelo1);
        s1.consultaVuelo(vuelo2);
        s1.consultaVuelo(vuelo3);
        */
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}