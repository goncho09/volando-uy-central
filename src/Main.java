import clases.*;
import enums.TipoAsiento;
import datatypes.*;
import enums.TipoDocumento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private JPanel vueloPanel;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JButton consultarVueloButton;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox6;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton CANCELARButton;
    private JButton CONFIRMARButton;
    private JButton consultarUsuarioButton;
    private JTextField webAltaCiudad;
    private JButton btnConfirmarAltaCiudad;
    private JTextField nombreAltaCiudad;
    private JTextField paisAltaCiudad;
    private JTextField aeropuertoAltaCiudad;
    private JTextField descripcionAltaCiudad;
    private JSpinner diaAltaciudad;
    private JSpinner mesAltaCiudad;
    private JSpinner añoAltaCiudad;
    private ISistema s;


    public Main() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Admin Dashboard");
        add(menuPrincipal);

        // Limitar Spinners
        diaAltaciudad.setModel(new SpinnerNumberModel(1, 1, 31, 1));
        mesAltaCiudad.setModel(new SpinnerNumberModel(1, 1, 12, 1));
        añoAltaCiudad.setModel(new SpinnerNumberModel(2025, 1900, 2100, 1));
        // Que no se puedan editar
        ((JSpinner.DefaultEditor) diaAltaciudad.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) mesAltaCiudad.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) añoAltaCiudad.getEditor()).getTextField().setEditable(false);

        s = Factory.getSistema();


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

        s.altaCategoria(cat1);
//        s.altaCategoria(cat1);
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

        DtCliente cliente1 = new DtCliente("gonzalo95", "Gonzalo", "maria88@hotmail.com", "Larrica", fecha1, "Uruguay", TipoDocumento.CEDULA, 51234567);
        DtCliente cliente2 = new DtCliente("gonzalo945", "María", "maria88@hotmail.com", "Fernández", fecha2, "Argentina", TipoDocumento.PASAPORTE, 98765432);
        DtCliente cliente3 = new DtCliente("juan2000", "Juan", "juan2000@yahoo.com", "Pérez", fecha2, "Chile", TipoDocumento.CEDULA_EXTRANJERA, 45678901);

        s.registrarCliente(cliente1);
        s.registrarCliente(cliente3);
        s.registrarCliente(cliente2);

         /*Consulta de vuelos esto no va funciona porque no estan las rutas de vuelo
        s1.consultaVuelo(vuelo1);
        s1.consultaVuelo(vuelo2);
        s1.consultaVuelo(vuelo3);
        */

        consultarVueloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DtFecha fechaPrueba = new DtFecha(3,12,2025);
                DtHora horaPrueba = new DtHora(10, 30);
                DtFecha fechaAltaPrueba = new DtFecha(18,8,2025);
                DtVuelo dataVuelo = new DtVuelo("A1", fechaPrueba, horaPrueba, 160, 40, fechaAltaPrueba);

                JFrame vuelo = new dataVuelo(dataVuelo);
                setEnabled(false);

                vuelo.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e){
                        setEnabled(true);
                    };
                });

            }
        });

        btnConfirmarAltaCiudad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sitioWeb = webAltaCiudad.getText();
                int dia = (Integer) diaAltaciudad.getValue();
                int mes = (Integer) mesAltaCiudad.getValue();
                int año = (Integer) añoAltaCiudad.getValue();
                if(nombreAltaCiudad.getText().isEmpty() && paisAltaCiudad.getText().isEmpty() && aeropuertoAltaCiudad.getText().isEmpty() && descripcionAltaCiudad.getText().isEmpty() && dia != 0 && mes != 0 && año != 0){
                    System.out.println("faltan argumentos");
                }else{
                    s.altaCiudad(new DtCiudad(nombreAltaCiudad.getText(), paisAltaCiudad.getText(),aeropuertoAltaCiudad.getText(),descripcionAltaCiudad.getText(),sitioWeb,new DtFecha(dia,mes,año)));
                }
            }
        });
    }

    public static void main(String[] args) {

        new Main();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}