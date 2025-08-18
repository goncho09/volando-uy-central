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

        System.out.println("hgoaññ");
        DtFecha fecha = new DtFecha(1,4,2003);
        DtFecha fecha2 = new DtFecha(2,4,2003);

        System.out.println(fecha.equals(fecha2));

        ISistema s1 = Factory.getSistema();

        s1.ejecutar();

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}