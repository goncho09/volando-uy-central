package com.app;

import com.app.clases.*;
import com.app.datatypes.*;
import com.app.enums.*;

import jakarta.persistence.*;
import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private JPanel JPanelRegistrarCliente;
    private JPanel JPanelRegistrarAerolinea;
    private JTextField nickname;
    private JTextField nombre;
    private JTextField correoElectronico;
    private JTextField Apellido;
    private JTextField nacionalidad;
    private JComboBox tipoDocumento;
    private JTextField documento;
    private JButton confirmarAltaCliente;
    private JButton cancelarAltaCliente;
    private JTextField nicknameAerolinea;
    private JTextField nombreAerolinea;
    private JTextField correoAerolinea;
    private JTextField sitioWebAerolinea;
    private JTextField descripcion;
    private JButton cancelarAltaAerolinea;
    private JButton confirmarAltaAerolinea;
    private JTabbedPane tabbedPane4;
    //private JTextField textField3;
    //private JComboBox comboBox6;
    private JComboBox comboBox7;
    private JSpinner spinner6;
    private JSpinner spinner7;
    private JSpinner spinner8;
    private JComboBox comboBox8;
    private JComboBox userType;
    private JPanel reservaPanel;
    private JComboBox aerolineaReserva;
    private JComboBox rutaDeVueloReserva;
    private JComboBox vueloReserva;
    private JButton verVueloButton;
    private JComboBox clienteReserva;
    private JComboBox tipoAsientoReserva;
    private JSpinner pasajesReserva;
    private JSpinner equipajeExtraReserva;
    private JButton hacerReservaButton;
    private JSpinner spinner9;
    private JSpinner spinner10;
    private JTextField textField4;
    private JComboBox comboBox9;
    private JComboBox comboBox10;
    private JButton CONFIRMARButton1;
    private JButton CANCELARButton1;
    //private JComboBox comboBox9;
    private ISistema s;


    public Main() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Admin Dashboard");
        add(menuPrincipal);
        setVisible(true);

        //Settear visibles false
        JPanelRegistrarAerolinea.setVisible(false);

        s = Factory.getSistema();


        DtCategoria cat1 = new DtCategoria("Running");
        DtCategoria cat2 = new DtCategoria("Salto Montaña");
        DtCategoria cat3 = new DtCategoria("Tirolesa");

        LocalDate fecha1 = LocalDate.of(2025,8,18);
        LocalDate fecha2 = LocalDate.of(2025,8,19);
        LocalTime hora1 = LocalTime.of(10, 30);

        DtCiudad c1 = new DtCiudad("Montevideo","Uruguay","Aeropuerto de Carrasco","Mucha rambla","www.aeropuerto-carrasco.uy",fecha1);
        DtCiudad c2 = new DtCiudad("Buenos Aires", "Argentina", "Aeropuerto Jorge Newbery", "Puerto Madero", "www.aeroparque.com.ar", fecha1);
        DtCiudad c3 = new DtCiudad("Santiago", "Chile", "Aeropuerto Arturo Merino Benítez", "Cerro San Cristóbal", "www.aeropuertosantiago.cl", fecha1);

        s.altaCategoria(cat1);
//        s.altaCategoria(cat1);
        s.altaCategoria(cat2);
        s.altaCategoria(cat3);

        s.altaCiudad(c1);
        s.altaCiudad(c2);
        s.altaCiudad(c3);

        LocalTime hora = LocalTime.of(10,0);

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

        DtPaquete p1 = new DtPaquete("Paquete A", "Descripcion A", 10, 5, 100,rutasEnPaquete1);
        DtPaquete p2 = new DtPaquete("Paquete B", "Descripcion B", 15, 10, 150,  rutasEnPaquete1);
        DtPaquete p3 = new DtPaquete("Paquete C", "Descripcion C", 7, 3, 80, rutasEnPaquete1);

        // TEMPORAL
        s.altaPaquete(p1);
        s.altaPaquete(p2);
        s.altaPaquete(p3);
        List<DtPaquete> paquetes = s.listarPaquetes();

        for (DtPaquete p : paquetes) {
            System.out.println(p.getNombre() + " " + p.getDescripcion() + " " + p.getValidezDias() + " " + p.getDescuento() + " " + p.getCosto());
        }

        DtCliente cliente1 = new DtCliente("gonzalo95", "Gonzalo", "maria88@hotmail.com", "Larrica", fecha1, "Uruguay", TipoDocumento.CEDULA, 51234567);
        DtCliente cliente2 = new DtCliente("gonzalo945", "María", "maria88@hotmail.com", "Fernández", fecha2, "Argentina", TipoDocumento.PASAPORTE, 98765432);
        DtCliente cliente3 = new DtCliente("juan2000", "Juan", "juan2000@yahoo.com", "Pérez", fecha2, "Chile", TipoDocumento.CEDULA_EXTRANJERA, 45678901);

        s.registrarCliente(cliente1);
        s.registrarCliente(cliente3);
        s.registrarCliente(cliente2);

        consultarVueloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate fechaPrueba = LocalDate.of(2025,12,3);
                LocalTime horaPrueba = LocalTime.of(10, 30);
                LocalDate fechaAltaPrueba = LocalDate.of(2025,8,18);
                DtVuelo dataVuelo = new DtVuelo("A1", fechaPrueba, horaPrueba, 160, 40, fechaAltaPrueba,ruta1);

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
                if(auxiliarFunctions.estanVaciosJTextField(nombreAltaCiudad, paisAltaCiudad, aeropuertoAltaCiudad, descripcionAltaCiudad)){
                    JDialog err = new errorMessage("Faltan argumentos");
                    return;
                }
                s.altaCiudad(new DtCiudad(nombreAltaCiudad.getText(), paisAltaCiudad.getText(),aeropuertoAltaCiudad.getText(),descripcionAltaCiudad.getText(),webAltaCiudad.getText(),LocalDate.now()));

            }
        });


        hacerReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(auxiliarFunctions.estanVaciosJComboBox(aerolineaReserva, rutaDeVueloReserva, vueloReserva, clienteReserva, tipoAsientoReserva)){
                    System.out.println("Faltan argumentos");
                    JDialog err = new errorMessage("Faltan argumentos");
                    return;
                }
                // Valores que maneja la reserva
                String aerolinea = aerolineaReserva.getSelectedItem().toString();
                String rutaDeVuelo = rutaDeVueloReserva.getSelectedItem().toString();
                String vuelo = vueloReserva.getSelectedItem().toString();
                String cliente = clienteReserva.getSelectedItem().toString();
                String tipoAsiento = tipoAsientoReserva.getSelectedItem().toString();
                int pasajes = (Integer) pasajesReserva.getValue();
                int equipajeExtra = (Integer) equipajeExtraReserva.getValue();

                if(pasajes <= 0){
                    JDialog err = new errorMessage("Pasajes de reservas debe ser mayor a 0");
                    return;
                }

                // Esto se puede comentar, solamente lo vamos a usar para verificar el botón.
                System.out.println("===== Debug Reserva =====");
                System.out.printf("%-15s: %s%n", "Aerolinea", aerolinea);
                System.out.printf("%-15s: %s%n", "Ruta de Vuelo", rutaDeVuelo);
                System.out.printf("%-15s: %s%n", "Vuelo", vuelo);
                System.out.printf("%-15s: %s%n", "Cliente", cliente);
                System.out.printf("%-15s: %s%n", "Tipo Asiento", tipoAsiento);
                System.out.printf("%-15s: %d%n", "Pasajes", pasajes);
                System.out.printf("%-15s: %d%n", "Equipaje Extra", equipajeExtra);
                System.out.println("=========================");


            }
        });
        userType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        userType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    String tipoUsuario = userType.getSelectedItem().toString();
                    if(tipoUsuario.equals("Cliente")){
                        JPanelRegistrarAerolinea.setVisible(false);
                        JPanelRegistrarCliente.setVisible(true);
                    }else{
                        JPanelRegistrarAerolinea.setVisible(true);
                        JPanelRegistrarCliente.setVisible(false);
                    }

                }
            }
        });

        //REGISTRAR USUARIO
        confirmarAltaCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String tipoUsuario = userType.getSelectedItem().toString();
                    if (tipoUsuario.equals("Cliente")) {
                        if (nickname.getText().isEmpty() || nombre.getText().isEmpty() ||
                                correoElectronico.getText().isEmpty() || Apellido.getText().isEmpty() ||
                                nacionalidad.getText().isEmpty() ||// tipoDocumento.getSelectedItem() == null ||
                                documento.getText().isEmpty()) {

                            errorMessage errorDialog = new errorMessage("Faltan argumentos");
                            return;
                        }
                        // Crear DtCliente
                        LocalDate fecha1 = LocalDate.of (2025, 8, 25);
                        TipoDocumento documentoT = tipoDocumento.getSelectedItem().toString().equals("cedula") ?
                                TipoDocumento.CEDULA : TipoDocumento.PASAPORTE;

                        DtCliente cliente = new DtCliente(
                                nickname.getText(), nombre.getText(), correoElectronico.getText(),
                                Apellido.getText(), fecha1, nacionalidad.getText(),
                                documentoT, Integer.parseInt(documento.getText())
                        );
                        s.registrarCliente(cliente);
                        System.out.println("Cliente registrado: " + nickname.getText());

                    } else if (tipoUsuario.equals("Aerolinea")) {
                        if (nickname.getText().isEmpty() || nombre.getText().isEmpty() ||
                                correoElectronico.getText().isEmpty() || descripcion.getText().isEmpty() ||
                                sitioWebAerolinea.getText().isEmpty()) {

                            errorMessage errorDialog = new errorMessage("Faltan argumentos");
                            return;
                        }
                        // Crear DtAerolinea
                        DtAerolinea aerolinea = new DtAerolinea(
                                nickname.getText(), nombre.getText(), correoElectronico.getText(),
                                descripcion.getText(), sitioWebAerolinea.getText()
                        );
                        s.registrarAerolinea(aerolinea);
                        System.out.println("Aerolínea registrada: " + nickname.getText());
                    }
                    // Actualizar ComboBox de consulta
                    comboBox6.removeAllItems();
                    java.util.List<DtUsuario> usuarios = s.listarUsuarios();
                    for (DtUsuario usuario : usuarios) {
                        comboBox6.addItem(usuario.getNickname());
                    }
                } catch (Exception ex) {
                        errorMessage errorDialog = new errorMessage("Error al registrar: " + ex.getMessage());
                        errorDialog.setVisible(true);
                        ex.printStackTrace();
                    }
                }
            });

        //CONSULTAR USUARIO
        consultarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox6.getSelectedItem() == null) {
                    errorMessage errorDialog = new errorMessage("Seleccione un usuario");
                    errorDialog.setVisible(true);
                    return;
                }
                String nicknameSeleccionado = comboBox6.getSelectedItem().toString();

                s.elegirUsuario(nicknameSeleccionado);
                DtUsuario dtUsuario = s.getUsuarioSeleccionado();

                textField3.setText(dtUsuario.getNickname());
                textField5.setText(dtUsuario.getNombre());
                textField7.setText(dtUsuario.getEmail());

                if (dtUsuario instanceof DtCliente) {
                    DtCliente cliente = (DtCliente) dtUsuario;
                    textField6.setText(cliente.getApellido());
                } else if (dtUsuario instanceof DtAerolinea) {
                    DtAerolinea aerolinea = (DtAerolinea) dtUsuario;

                }
            }
        });
    }

    public static void main(String[] args) {
        new Main();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiUnidadPersistencia");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Query q = em.createNativeQuery("SELECT 1");
        Object result = q.getSingleResult();
        System.out.println("Conexión OK, resultado: " + result);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    private void createUIComponents() {};
}