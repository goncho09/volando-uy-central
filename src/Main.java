import datatypes.DtCategoria;
import datatypes.DtCiudad;
import datatypes.DtFecha;
import clases.ISistema;
import clases.Factory;

import javax.swing.*;

public class Main extends JFrame {

    private JPanel menuPrincipal;
    private JTabbedPane gestionUsuarios;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;

    public Main() {
        add(menuPrincipal);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Admin Dashboard");
    }

    public static void main(String[] args) {

        new Main();

        ISistema s1 = Factory.getSistema();


        DtCategoria cat1 = new DtCategoria("Running");
        DtCategoria cat2 = new DtCategoria("Salto Montaña");
        DtCategoria cat3 = new DtCategoria("Tirolesa");

        DtFecha fecha1 = new DtFecha(18,8,2025);

        DtCiudad c1 = new DtCiudad("Montevideo","Uruguay","Aeropuerto de Carrasco","Mucha rambla","www.aeropuerto-carrasco.uy",fecha1);
        DtCiudad c2 = new DtCiudad("Buenos Aires", "Argentina", "Aeropuerto Jorge Newbery", "Puerto Madero", "www.aeroparque.com.ar", fecha1);
        DtCiudad c3 = new DtCiudad("Santiago", "Chile", "Aeropuerto Arturo Merino Benítez", "Cerro San Cristóbal", "www.aeropuertosantiago.cl", fecha1);


        s1.altaCategoria(cat1);
        //s1.altaCategoria(cat1);
        s1.altaCategoria(cat2);
        s1.altaCategoria(cat3);

        s1.altaCiudad(c1);
        s1.altaCiudad(c2);
        s1.altaCiudad(c3);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}