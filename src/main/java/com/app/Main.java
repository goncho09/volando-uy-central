package com.app;

import com.app.clases.*;
import com.app.datatypes.*;
import com.app.enums.*;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {

    //Declaración de variables "importantes"
    private ISistema s;
    private auxiliarFunctions auxiliar;

    //Declaració de JavaSwing
    private JPanel menuPrincipal;
    private JTabbedPane gestionUsuarios;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;
    private JComboBox JComboBoxRutaVueloAltaVuelo;
    private JTextField nombreAltaVuelo;
    private JSpinner JSpinnerTuristasAltaVuelo;
    private JSpinner JSpinnerEjecutivosAltaVuelo;
    private JSpinner JSpinnerDiaAltaVuelo;
    private JSpinner JSpinnerAñoAltaVuelo;
    private JSpinner JSpinnerMesAltaVuelo;
    private JComboBox JComboBoxAerolineaAltaVuelo;
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
    private JTextField nicknameRegistrarCliente;
    private JTextField nombreRegistrarCliente;
    private JTextField correoElectronicoRegistrarCliente;
    private JTextField apellidoRegistrarCliente;
    private JTextField nacionalidadRegistrarCliente;
    private JComboBox tipoDocumentoRegistrarCliente;
    private JTextField documentoRegistrarCliente;
    private JButton confirmarAltaCliente;
    private JButton cancelarAltaCliente;
    private JTextField nicknameRegistrarAerolinea;
    private JTextField nombreRegistrarAerolinea;
    private JTextField correoRegistrarAerolinea;
    private JTextField sitioWebRegistrarAerolinea;
    private JTextField descripcionRegistrarAerolinea;
    private JButton cancelarAltaAerolinea;
    private JButton confirmarAltaAerolinea;
    private JTabbedPane tabbedPane4;
    //private JTextField textField3;
    //private JComboBox comboBox6;
    private JComboBox JComboBoxCiudadDestino;
    private JSpinner SpinnerCostoTurista;
    private JSpinner SpínnerCostoEjecutivo;
    private JSpinner SpinnerCostoEquipaje;
    private JComboBox JComboBoxCategoriaAltaRutaDeVuelo;
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
    private JPanel JPanelModificarAerolinea;
    private JButton JButtonModificarAerolinea;
    private JTextField nicknameModificarAerolinea;
    private JPanel JPanelModificarCliente;
    private JTextField nicknameModificarCliente;
    private JComboBox JComboBoxSeleccionarUsuarioModificar;
    private JTextField nombreClienteModificar;
    private JTextField correoClienteModificar;
    private JTextField apellidoClienteModificar;
    private JSpinner fechaDiaClienteModificar;
    private JSpinner fechaMesClienteModificar;
    private JSpinner fechaAnioClienteModificar;
    private JTextField nacionalidadClienteModificar;
    private JComboBox tipoDocumentoClienteModificar;
    private JComboBox JComboBoxSeleccionarUsuarioConsultar;
    private JSpinner JSpinnerDuracionAltaVuelo;
    private JTextField nombreAltaCategoría;
    private JButton confirmarAltaCategoria;
    private JSpinner fechaDiaRegistrarCliente;
    private JSpinner fechaMesRegistrarCliente;
    private JSpinner fechaAnioRegistrarCliente;
    private JButton crearRutaDeVuelo;
    private JComboBox JComboBox;
    //private JComboBox comboBox9;
    private JTextField nombreAltaRutaDeVuelo;
    private JTextField descripcionAltaRutaDeVuelo;
    private JComboBox JComboBoxCiudadOrigen;
    private JSpinner SpinnerMinutoAltaRutaDeVuelo;
    private JSpinner SpinnerHoraAltaRutaDeVuelo;
    private JComboBox JComboBoxAerolineaAltaRutaVuelo;


    public Main() {

        //Inicializar Sistema
        s = Factory.getSistema();

        //Inicializar Auxiliar
        this.auxiliar = new auxiliarFunctions(s.getUserDao(),s.getRutaDeVueloDao(),s.getCiudadDao(),s.getCategoriaDao());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Admin Dashboard");
        add(menuPrincipal);
        setVisible(true);

        //Settear swing elements visibles false
        JPanelRegistrarAerolinea.setVisible(false);
        JPanelModificarAerolinea.setVisible(false);
        JPanelModificarCliente.setVisible(false);


        //Settear swing elements isEditable false
        nicknameModificarAerolinea.setEditable(false);
        nicknameModificarCliente.setEditable(false);

        //Settear modelos JComboBox
        JComboBoxSeleccionarUsuarioModificar.setModel(auxiliar.getComboUserModel());
        JComboBoxSeleccionarUsuarioConsultar.setModel(auxiliar.getComboUserModel());
        JComboBoxAerolineaAltaVuelo.setModel(auxiliar.getComboAerolineaModel());
        JComboBoxRutaVueloAltaVuelo.setModel(auxiliar.getComboRutaDeVueloModel());
        JComboBoxCiudadOrigen.setModel(auxiliar.getComboCiudadOrigenModel());
        JComboBoxCiudadDestino.setModel(auxiliar.getComboCiudadDestinoModel());
        JComboBoxCategoriaAltaRutaDeVuelo.setModel(auxiliar.getComboCategoriaModel());
        JComboBoxAerolineaAltaRutaVuelo.setModel(auxiliar.getComboAerolineaModel());

        //Settear no selección JComboBox
        JComboBoxSeleccionarUsuarioModificar.setSelectedIndex(-1);
        JComboBoxAerolineaAltaVuelo.setSelectedIndex(-1);
        JComboBoxRutaVueloAltaVuelo.setSelectedIndex(-1);


        JSpinnerDiaAltaVuelo.setModel(new SpinnerNumberModel(1, 1, 31, 1));
        JSpinnerMesAltaVuelo.setModel(new SpinnerNumberModel(1, 1, 12, 1));
        JSpinnerAñoAltaVuelo.setModel(new SpinnerNumberModel(2025, 2025, 2030, 1));
        JSpinnerDuracionAltaVuelo.setModel(new SpinnerNumberModel(1, 1, 12, 1));
        JSpinnerEjecutivosAltaVuelo.setModel(new SpinnerNumberModel(1, 1, 100, 1));
        JSpinnerTuristasAltaVuelo.setModel(new SpinnerNumberModel(1, 1, 100, 1));
        SpinnerCostoTurista.setModel(new SpinnerNumberModel(1,1,1000000,1));
        SpínnerCostoEjecutivo.setModel(new SpinnerNumberModel(1,1,1000000,1));
        SpinnerCostoEquipaje.setModel(new SpinnerNumberModel(1,1,1000000,1));
        SpinnerHoraAltaRutaDeVuelo.setModel(new SpinnerNumberModel(0,0,200,1));
        SpinnerMinutoAltaRutaDeVuelo.setModel(new SpinnerNumberModel(0,0,59,1));

//        s.registrarAerolinea(new DtAerolinea("aviones123","aviones america","avines@gmail.com","aerolinea famosa"));
//        s.confirmarAltaUsuario();

        LocalTime hora = LocalTime.of(10,0);

        LocalDate fecha1 = LocalDate.of(2025,8,18);
        LocalDate fecha2 = LocalDate.of(2025,8,19);
        LocalTime hora1 = LocalTime.of(10, 30);

        DtCategoria cat1 = new DtCategoria("Running");
        DtCategoria cat2 = new DtCategoria("Salto Montaña");
        DtCategoria cat3 = new DtCategoria("Tirolesa");

        DtCiudad c1 = new DtCiudad("Montevideo","Uruguay","Aeropuerto de Carrasco","Mucha rambla","www.aeropuerto-carrasco.uy",fecha1);
        DtCiudad c2 = new DtCiudad("Buenos Aires", "Argentina", "Aeropuerto Jorge Newbery", "Puerto Madero", "www.aeroparque.com.ar", fecha1);
        DtCiudad c3 = new DtCiudad("Santiago", "Chile", "Aeropuerto Arturo Merino Benítez", "Cerro San Cristóbal", "www.aeropuertosantiago.cl", fecha1);

        s.altaCiudad(c1);
        s.altaCiudad(c2);
        s.altaCiudad(c3);


        RutaDeVuelo ruta1 = new RutaDeVuelo(new DtRuta("Vuelo A1", "Descripcion A1", hora, 100, 200, 20, fecha1,s.getCategorias(),s.getCiudades().get(0),s.getCiudades().get(1)));
        RutaDeVuelo ruta2 = new RutaDeVuelo(new DtRuta("Vuelo A2", "Descripcion A2", hora, 120, 220, 25, fecha1, s.getCategorias(), s.getCiudades().get(1),s.getCiudades().get(2)));


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

        DtCliente cliente1 = new DtCliente("gonzalo95", "Gonzalo", "maria88@hotmail.com", "Larrica", fecha1, "Uruguay", TipoDocumento.CEDULA, 51234567);
        DtCliente cliente2 = new DtCliente("gonzalo945", "María", "maria89@hotmail.com", "Fernández", fecha2, "Argentina", TipoDocumento.PASAPORTE, 98765432);
        DtCliente cliente3 = new DtCliente("juan2000", "Juan", "juan2000@yahoo.com", "Pérez", fecha2, "Chile", TipoDocumento.CEDULA_EXTRANJERA, 45678901);

        try {
            s.registrarCliente(cliente1);
            s.registrarCliente(cliente3);
            s.registrarCliente(cliente2);
        }catch(Exception e){
            e.printStackTrace();
            new dialogMessage(e.getMessage());
        }
        //¡Cargar datitos!
        auxiliar.cargarUsuariosComboBox();
        auxiliar.cargarAerolineasComboBox();
        auxiliar.cargarRutasDeVueloComboBox();
        auxiliar.cargarCiudadesComboBox();
        auxiliar.cargarCategoriaComboBox();


        consultarVueloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate fechaPrueba = LocalDate.of(2025,12,3);
                LocalTime horaPrueba = LocalTime.of(10, 30);
                LocalDate fechaAltaPrueba = LocalDate.of(2025,8,18);
                DtVuelo dataVuelo = new DtVuelo("A1", fechaPrueba, horaPrueba, 160, 40, fechaAltaPrueba, ruta1);

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
                if(auxiliar.estanVaciosJTextField(nombreAltaCiudad, paisAltaCiudad, aeropuertoAltaCiudad, descripcionAltaCiudad)){
                    new dialogMessage("Faltan argumentos");
                    return;
                }
                s.altaCiudad(new DtCiudad(nombreAltaCiudad.getText(), paisAltaCiudad.getText(),aeropuertoAltaCiudad.getText(),descripcionAltaCiudad.getText(),webAltaCiudad.getText(),LocalDate.now()));

            }
        });


        hacerReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(auxiliar.estanVaciosJComboBox(aerolineaReserva, rutaDeVueloReserva, vueloReserva, clienteReserva, tipoAsientoReserva)){
                    System.out.println("Faltan argumentos");
                    new dialogMessage("Faltan argumentos");
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
                    new dialogMessage("Pasajes de reservas debe ser mayor a 0");
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
                        if (auxiliar.estanVaciosJTextField(nicknameRegistrarCliente, nombreRegistrarCliente, correoElectronicoRegistrarCliente, apellidoRegistrarCliente, nacionalidadRegistrarCliente, documentoRegistrarCliente) || auxiliar.estanVaciosJComboBox(tipoDocumentoRegistrarCliente)){
                            new dialogMessage("Faltan argumentos");
                            return;
                        }
                        // Crear DtCliente
                        LocalDate fecha1 = LocalDate.of(2025, 8, 25);
                        TipoDocumento documentoT = tipoDocumentoRegistrarCliente.getSelectedItem().toString().equals("Cedula") ?
                                TipoDocumento.CEDULA : TipoDocumento.PASAPORTE;

                        DtCliente cliente = new DtCliente(
                                nicknameRegistrarCliente.getText(), nombreRegistrarCliente.getText(), correoElectronicoRegistrarCliente.getText(),
                                apellidoRegistrarCliente.getText(), fecha1, nacionalidadRegistrarCliente.getText(),
                                documentoT, Integer.parseInt(documentoRegistrarCliente.getText())
                        );
                        try {
                            s.registrarCliente(cliente);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            s.cancelarAltaUsuario();
                            new dialogMessage(ex.getMessage());
                        }
                        System.out.println("Cliente registrado: " + nicknameRegistrarCliente.getText());
                        auxiliar.cargarUsuariosComboBox();
                    }

                } catch (Exception ex) {
                        ex.printStackTrace();
                        new dialogMessage(ex.getMessage());
                    }
                }
            });

        //CONSULTAR USUARIO
        consultarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox6.getSelectedItem() == null) {
                    dialogMessage errorDialog = new dialogMessage("Seleccione un usuario");
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
        JComboBoxSeleccionarUsuarioModificar.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    Usuario user = (Usuario)JComboBoxSeleccionarUsuarioModificar.getSelectedItem();
                    if(user instanceof Cliente){ // Pregunta si mi usuario es un cliente
                        Cliente cliente = (Cliente) user;
                        LocalDate fechaCliente = cliente.getFechaNacimiento();
                        JPanelModificarCliente.setVisible(true);
                        JPanelModificarAerolinea.setVisible(false);
                        nicknameModificarCliente.setText(cliente.getNickname());
                        nombreClienteModificar.setText(cliente.getNombre());
                        apellidoClienteModificar.setText(cliente.getApellido());
                        correoClienteModificar.setText(cliente.getEmail());
                        fechaDiaClienteModificar.setValue(fechaCliente.getDayOfMonth());
                        fechaMesClienteModificar.setValue(fechaCliente.getMonthValue());
                        fechaAnioClienteModificar.setValue(fechaCliente.getYear());
                        nacionalidadClienteModificar.setText(cliente.getNacionalidad());
                        //tipoDocumentoClienteModificar.setSelectedItem(cliente.getTipoDocumento()); <- Investigar este wey

                    }else if(user instanceof Aerolinea){
                        Aerolinea aerolinea = (Aerolinea) user;
                        JPanelModificarCliente.setVisible(false);
                        JPanelModificarAerolinea.setVisible(true);
                    }else{
                        JPanelModificarAerolinea.setVisible(false);
                        JPanelModificarCliente.setVisible(false);
                    }
                }
            }
        });
        crearVueloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(auxiliar.estanVaciosJComboBox(JComboBoxAerolineaAltaVuelo,JComboBoxRutaVueloAltaVuelo) || auxiliar.estanVaciosJTextField(nombreAltaVuelo)){
                        new dialogMessage("Faltan argumentos");
                        return;
                    }

                    String aerolinea = JComboBoxAerolineaAltaVuelo.getSelectedItem().toString();
                    String ruta = JComboBoxRutaVueloAltaVuelo.getSelectedItem().toString();
                    String nombre = nombreAltaVuelo.getText();

                    LocalDate fecha = LocalDate.of((Integer)JSpinnerAñoAltaVuelo.getValue(), (Integer)JSpinnerMesAltaVuelo.getValue(), (Integer)JSpinnerDiaAltaVuelo.getValue());
                    LocalTime hora = LocalTime.of(10, 0);

                    DtVuelo dtVuelo = new DtVuelo(nombre, fecha, hora, (Integer)JSpinnerTuristasAltaVuelo.getValue(), (Integer)JSpinnerEjecutivosAltaVuelo.getValue(), LocalDate.now(), null);

                    s.seleccionarAerolineaParaVuelo(aerolinea);
                    s.seleccionarRuta(ruta);
                    s.ingresarDatosVuelo(dtVuelo);
                    s.confirmarAltaVuelo();

                    //pa verificar que se creo
                    try {
                        s.consultaVuelo(dtVuelo);
                        new dialogMessage("Vuelo creado y verificado correctamente");
                    } catch (IllegalArgumentException ex) {
                        new dialogMessage("Error: el vuelo no se registró correctamente");
                    }

                    new dialogMessage("Vuelo creado correctamente");
                    auxiliar.limpiarJTextField(nombreAltaVuelo);

                } catch (Exception ex) {
                    new dialogMessage(ex.getMessage());
                }
            }
        });


        confirmarAltaCategoria.addActionListener(e -> {
            try{
                if(auxiliar.estanVaciosJTextField(nombreAltaCategoría)){
                    new dialogMessage("Ingrese un nombre de categoría");
                    return;
                }
                s.altaCategoria(new DtCategoria(nombreAltaCategoría.getText()));
                new dialogMessage("Categoria creada correctamente");
                nombreAltaCategoría.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
                new dialogMessage(ex.getMessage());
            }
        });
        confirmarAltaAerolinea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(auxiliar.estanVaciosJComboBox(userType)){
                        System.out.println("Ta vacio");
                        return;
                    }
                    String tipoUsuario = userType.getSelectedItem().toString();
                    if (tipoUsuario.equals("Aerolinea")) {
                        if (auxiliar.estanVaciosJTextField(nombreRegistrarAerolinea)) {

                            new dialogMessage("Faltan argumentos");
                            return;
                        }
                        // Crear DtAerolinea
                        DtAerolinea aerolinea = new DtAerolinea(
                                nicknameRegistrarAerolinea.getText(), nombreRegistrarAerolinea.getText(), correoRegistrarAerolinea.getText(),
                                descripcionRegistrarAerolinea.getText(), sitioWebRegistrarAerolinea.getText()
                        );
                        try{
                            s.registrarAerolinea(aerolinea);
                        }catch (Exception ex){
                            ex.printStackTrace();
                            s.cancelarAltaUsuario();
                            new dialogMessage(ex.getMessage());
                        }
                        System.out.println("Aerolínea registrada: " + nicknameRegistrarCliente.getText());
                    }
                    auxiliar.cargarUsuariosComboBox();
            }catch (Exception ex) {
                    ex.printStackTrace();
                    new dialogMessage(ex.getMessage());
                }
            }
        });
        cancelarAltaCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //new dialogMessage(ex.getMessage());
            }
        });
        crearRutaDeVuelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (auxiliar.estanVaciosJTextField(nombreAltaRutaDeVuelo, descripcionAltaRutaDeVuelo) || auxiliar.estanVaciosJComboBox(JComboBoxAerolineaAltaRutaVuelo, JComboBoxCategoriaAltaRutaDeVuelo, JComboBoxCiudadOrigen, JComboBoxCiudadDestino)) {
                        new dialogMessage("Faltan argumentos");
                        return;
                    }

                    if (JComboBoxCiudadOrigen.getSelectedItem().toString().equals(JComboBoxCiudadDestino.getSelectedItem().toString())) {
                        new dialogMessage("La ciudad de origen no puede ser igual a la ciudad de destino");
                        return;
                    }

                    Integer hora = (Integer) SpinnerHoraAltaRutaDeVuelo.getValue();
                    Integer minuto = (Integer) SpinnerMinutoAltaRutaDeVuelo.getValue();

                    if (hora == 0 && minuto == 0) {
                        new dialogMessage("La ruta de vuelo no puede durar 0 horas y 0 minutos");
                        return;
                    }

                    LocalTime horaRuta = LocalTime.of(hora, minuto);
                    Integer costoTurista = (Integer) SpinnerCostoTurista.getValue();
                    Integer costoEjecutivo = (Integer) SpínnerCostoEjecutivo.getValue();
                    Integer costoEquipaje = (Integer) SpinnerCostoEquipaje.getValue();
                    List<String> nombresCategorias = new ArrayList<String>();
                    nombresCategorias.add(JComboBoxCategoriaAltaRutaDeVuelo.getSelectedItem().toString());

                    s.altaRutaDeVuelo(JComboBoxAerolineaAltaRutaVuelo.getSelectedItem().toString(), new DtRuta(nombreAltaRutaDeVuelo.getText(), descripcionAltaRutaDeVuelo.getText(), horaRuta, costoTurista, costoEjecutivo, costoEquipaje, LocalDate.now(), s.getCategoriasPorNombre(nombresCategorias), s.buscarCiudad(JComboBoxCiudadOrigen.getSelectedItem().toString()), s.buscarCiudad(JComboBoxCiudadDestino.getSelectedItem().toString())));
                    new dialogMessage("Ruta de vuelo creada correctamente.");
                    auxiliar.limpiarJTextField(nombreAltaRutaDeVuelo, descripcionAltaRutaDeVuelo);
                } catch (Exception ex) {
                    new dialogMessage(ex.getMessage());
                }
            }
        });
    }



    public static void main(String[] args) {
        new Main();
    }
}