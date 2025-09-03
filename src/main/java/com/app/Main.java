package com.app;

import com.app.clases.*;
import com.app.datatypes.*;
import com.app.enums.*;

import javax.swing.*;
import java.awt.*;
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
    private JComboBox JComboBoxAerolineaConsultaVuelo;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JButton consultarVueloButton;
    private JTextField textField3;
    private JComboBox comboBox6;
    private JTextField descripcionAltaPaquete;
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
    private JComboBox JComboBoxCiudadDestino;
    private JSpinner SpinnerCostoTurista;
    private JSpinner SpínnerCostoEjecutivo;
    private JSpinner SpinnerCostoEquipaje;
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
    private JSpinner JSpinnerPeriodoAltaPaquete;
    private JSpinner JSpinnerDescuentoAltaPaquete;
    private JTextField nombreAltaPaquete;
    private JComboBox JComboBoxPaqueteComprarPaquete;
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
    private JTextField nombreAltaRutaDeVuelo;
    private JTextField descripcionAltaRutaDeVuelo;
    private JComboBox JComboBoxCiudadOrigen;
    private JSpinner SpinnerMinutoAltaRutaDeVuelo;
    private JSpinner SpinnerHoraAltaRutaDeVuelo;
    private JComboBox JComboBoxAerolineaAltaRutaVuelo;
    private JComboBox JComboBoxPaqueteConsultaPaqueteRutaVuelo;
    private JButton JButtonConsultarPaquete;
    private JPanel JPanelCategorias;
    private JSpinner JSpinnerCostoAltaPaquete;
    private JComboBox JComboBoxAerolineaConsultaRuta;
    private JComboBox JComboBoxRutaVueloAgregarRuta;
    private JButton ButtonConfirmarAgregarRutaPaquete;
    private JSpinner JSpinnerCantidadAgregarRuta;
    private JComboBox JComboBoxTipoAsientoAgregarRutaPaquete;
    private JComboBox JComboBoxPaqueteAgregarRuta;
    private JComboBox JComboBoxAerolineaAgregarRuta;


    public Main() {

        //Inicializar Sistema
        s = Factory.getSistema();

        //Inicializar Auxiliar
        this.auxiliar = new auxiliarFunctions(s);

        //Settear JFrame principal
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
        JComboBoxAerolineaAltaRutaVuelo.setModel(auxiliar.getComboAerolineaModel());
        JComboBoxPaqueteConsultaPaqueteRutaVuelo.setModel(auxiliar.getComboPaqueteModel());
        JComboBoxPaqueteComprarPaquete.setModel(auxiliar.getComboPaqueteModel());
        JComboBoxAerolineaConsultaVuelo.setModel(auxiliar.getComboAerolineaModel());
        JComboBoxAerolineaConsultaRuta.setModel(auxiliar.getComboAerolineaModel());
        JComboBoxPaqueteAgregarRuta.setModel(auxiliar.getComboPaqueteNoCompradoModel());
        JComboBoxAerolineaAgregarRuta.setModel(auxiliar.getComboAerolineaModel());
        JComboBoxRutaVueloAgregarRuta.setModel(auxiliar.getComboRutaDeVueloAerolineaModel());

        JPanelCategorias.setLayout(new GridLayout(0, 2, 5, 5));

        // Es para mostrar las categorias en alta ruta vuelo
        List<JCheckBox> checkboxes = new ArrayList<>();
        for (Categoria c : s.getCategorias()) {
            JCheckBox check = new JCheckBox(c.getNombre());
            JPanelCategorias.add(check);
            checkboxes.add(check);
        }


        // Configurar JSpinner
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
        JSpinnerDescuentoAltaPaquete.setModel(new SpinnerNumberModel(0,0,100,1));
        JSpinnerPeriodoAltaPaquete.setModel(new SpinnerNumberModel(1,1,1000000,1));
        JSpinnerCostoAltaPaquete.setModel(new SpinnerNumberModel(1.0,1.0,1000000.0,1.0));
        JSpinnerCantidadAgregarRuta.setModel(new SpinnerNumberModel(1,1,1000000,1));

        //¡Cargar datitos!
        auxiliar.cargarUsuariosComboBox();
        auxiliar.cargarAerolineasComboBox();
        auxiliar.cargarRutasDeVueloComboBox();
        auxiliar.cargarCiudadesComboBox();
        auxiliar.cargarPaqueteComboBox();
        auxiliar.cargarPaqueteNoCompradoComboBox();

        JComboBoxSeleccionarUsuarioModificar.setSelectedIndex(-1);
        JComboBoxSeleccionarUsuarioConsultar.setSelectedIndex(-1);
        JComboBoxAerolineaAltaVuelo.setSelectedIndex(-1);
        JComboBoxRutaVueloAltaVuelo.setSelectedIndex(-1);
        JComboBoxCiudadOrigen.setSelectedIndex(-1);
        JComboBoxCiudadDestino.setSelectedIndex(-1);
        JComboBoxAerolineaAltaRutaVuelo.setSelectedIndex(-1);
        JComboBoxPaqueteConsultaPaqueteRutaVuelo.setSelectedIndex(-1);
        JComboBoxPaqueteComprarPaquete.setSelectedIndex(-1);
        JComboBoxAerolineaConsultaVuelo.setSelectedIndex(-1);
        JComboBoxAerolineaConsultaRuta.setSelectedIndex(-1);
        JComboBoxPaqueteAgregarRuta.setSelectedIndex(-1);
        JComboBoxAerolineaAgregarRuta.setSelectedIndex(-1);
        JComboBoxRutaVueloAgregarRuta.setSelectedIndex(-1);

        consultarVueloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // USAR DATOS VERDADEROS
                LocalDate fechaPrueba = LocalDate.of(2025,12,3);
                LocalTime horaPrueba = LocalTime.of(10, 30);
                LocalDate fechaAltaPrueba = LocalDate.of(2025,8,18);
                LocalTime hora = LocalTime.of(10,0);
                LocalDate fecha1 = LocalDate.of(2025,8,18);
                RutaDeVuelo ruta1 = new RutaDeVuelo(new DtRuta("Vuelo A1", "Descripcion A1", hora, 100, 200, 20, fecha1,s.getCategorias(),s.getCiudades().get(0),s.getCiudades().get(1)));
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
                try {
                    if (auxiliar.estanVaciosJTextField(nombreAltaCiudad, paisAltaCiudad, aeropuertoAltaCiudad, descripcionAltaCiudad)) {
                        new dialogMessage("Faltan argumentos");
                        return;
                    }
                    s.altaCiudad(new DtCiudad(nombreAltaCiudad.getText(), paisAltaCiudad.getText(),aeropuertoAltaCiudad.getText(),descripcionAltaCiudad.getText(),webAltaCiudad.getText(),LocalDate.now()));

                    new dialogMessage("Ciudad registrada exitosamente: " + nombreAltaCiudad.getText());

                    auxiliar.cargarCiudadesComboBox();

                } catch (IllegalArgumentException ex) {
                    new dialogMessage("Error " + ex.getMessage()); //de validacion
            } catch (Exception ex) {
                new dialogMessage("Error al registrar ciudad: " + ex.getMessage());
                }
            }
        });


        hacerReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(auxiliar.estanVaciosJComboBox(aerolineaReserva, rutaDeVueloReserva, vueloReserva, clienteReserva, tipoAsientoReserva)){
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
            try {
                if (JComboBoxSeleccionarUsuarioConsultar.getSelectedItem() == null) {
                    new dialogMessage("Seleccione un usuario");
                    return;
                }
                String nickname = JComboBoxSeleccionarUsuarioConsultar.getSelectedItem().toString();

                s.elegirUsuario(nickname);
                DtUsuario dtUsuario = s.getUsuarioSeleccionado();

                if (dtUsuario == null) {
                    new dialogMessage("Usuario no encontrado");
                    return;
                }

                new dataUsuario(dtUsuario);
            }catch (Exception ex) {
                new dialogMessage("Error al consultar: " + ex.getMessage());
                ex.printStackTrace();
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
                        s.consultarVuelo(dtVuelo.getNombre());
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
                    if (auxiliar.estanVaciosJTextField(nombreAltaRutaDeVuelo, descripcionAltaRutaDeVuelo) || auxiliar.estanVaciosJComboBox(JComboBoxAerolineaAltaRutaVuelo, JComboBoxCiudadOrigen, JComboBoxCiudadDestino)) {
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
                    List<Categoria> categorias = s.getCategorias();

                    for (int i = 0; i < categorias.size(); i++) {
                        if (checkboxes.get(i).isSelected()) {
                            nombresCategorias.add(categorias.get(i).getNombre());
                        }
                    }

                    try {
                        s.altaRutaDeVuelo(JComboBoxAerolineaAltaRutaVuelo.getSelectedItem().toString(), new DtRuta(nombreAltaRutaDeVuelo.getText(), descripcionAltaRutaDeVuelo.getText(), horaRuta, costoTurista, costoEjecutivo, costoEquipaje, LocalDate.now(), s.getCategoriasPorNombre(nombresCategorias), s.buscarCiudad(JComboBoxCiudadOrigen.getSelectedItem().toString()), s.buscarCiudad(JComboBoxCiudadDestino.getSelectedItem().toString())));
                    }catch (Exception ex){
                        new dialogMessage(ex.getMessage());
                    }
                    new dialogMessage("Ruta de vuelo creada correctamente.");
                    auxiliar.limpiarJTextField(nombreAltaRutaDeVuelo, descripcionAltaRutaDeVuelo);
                    for (int i = 0; i < categorias.size(); i++) {
                        if (checkboxes.get(i).isSelected()) {
                            checkboxes.get(i).setSelected(false);
                        }
                    }

                } catch (Exception ex) {
                    new dialogMessage(ex.getMessage());
                }
            }
        });
        JButtonConsultarPaquete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auxiliar.estanVaciosJComboBox(JComboBoxPaqueteConsultaPaqueteRutaVuelo)) {
                    new dialogMessage("Falta ingresar algún dato.");
                    return;
                }
                    try{
                        s.seleccionarPaquete(JComboBoxPaqueteConsultaPaqueteRutaVuelo.getSelectedItem().toString());
                        JFrame paquete = new dataPaquete(s.getPaquete());
                        setEnabled(false);

                        paquete.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e){
                                setEnabled(true);
                            };
                        });
                    } catch (Exception ex){
                        new dialogMessage(ex.getMessage());
                    }
            }
        });
        CONFIRMARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(auxiliar.estanVaciosJTextField(nombreAltaPaquete,descripcionAltaPaquete)){
                    new dialogMessage("Falta ingresar campos de texto");
                    return;
                }
                try {
                    Integer periodo = (Integer) JSpinnerPeriodoAltaPaquete.getValue();
                    Integer descuento = (Integer) JSpinnerDescuentoAltaPaquete.getValue();
                    float costo = ((Double) JSpinnerCostoAltaPaquete.getValue()).floatValue();

                    if(periodo < 1){
                        new dialogMessage("El período debe ser 1 o más.");
                        return;
                    }

                    if(costo < 1){
                        new dialogMessage("El costo debe ser 1 o más.");
                        return;
                    }

                    s.altaPaquete(new DtPaquete(nombreAltaPaquete.getText(),descripcionAltaPaquete.getText(),periodo,descuento,costo, new ArrayList<>()));

                    new dialogMessage("Paquete creado correctamente.");
                    return;
                } catch (Exception ex){
                    new dialogMessage(ex.getMessage());
                }
            }
        });

        ButtonConfirmarAgregarRutaPaquete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(auxiliar.estanVaciosJComboBox(JComboBoxPaqueteAgregarRuta,JComboBoxAerolineaAgregarRuta,JComboBoxRutaVueloAgregarRuta,JComboBoxTipoAsientoAgregarRutaPaquete)){
                    new dialogMessage("Falta ingresar campos de texto");
                    return;
                }

                try{
                    Integer cantidad = (Integer) JSpinnerCantidadAgregarRuta.getValue();
                    s.agregarRutaAPaquete(JComboBoxPaqueteAgregarRuta.getSelectedItem().toString(),JComboBoxRutaVueloAgregarRuta.getSelectedItem().toString(),cantidad, TipoAsiento.valueOf(JComboBoxTipoAsientoAgregarRutaPaquete.getSelectedItem().toString()));

                    new dialogMessage("Ruta de vuelo agregada a paquete correctamente.");
                    return;
                } catch (Exception ex) {
                    new dialogMessage(ex.getMessage());
                }
            }
        });

        JComboBoxAerolineaAgregarRuta.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                auxiliar.cargarRutasDeVueloComboBoxAerolinea(JComboBoxAerolineaAgregarRuta.getSelectedItem().toString());
            }
        });
    }



    public static void main(String[] args) {
        new Main();
    }
}