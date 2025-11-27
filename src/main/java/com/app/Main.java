package com.app;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.app.soap.Publicador;
import com.app.utils.AuxiliarFunctions;

import com.formdev.flatlaf.FlatLightLaf;

import java.nio.file.Files;
import java.nio.file.Path;

import java.io.File;

import java.time.LocalDate;
import java.time.LocalTime;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.app.clases.ISistema;
import com.app.clases.Factory;

import com.app.datatypes.DtVuelo;
import com.app.datatypes.DtCiudad;
import com.app.datatypes.DtCliente;
import com.app.datatypes.DtPaquete;
import com.app.datatypes.DtPasajero;
import com.app.datatypes.DtReserva;
import com.app.datatypes.DtUsuario;
import com.app.datatypes.DtAerolinea;
import com.app.datatypes.DtRuta;
import com.app.datatypes.DtCategoria;

import com.app.interfaces.VentanaMensaje;
import com.app.interfaces.SubirImagen;
import com.app.interfaces.InfoRutaDeVuelo;
import com.app.interfaces.InfoVuelo;
import com.app.interfaces.InfoPaquete;
import com.app.interfaces.AgregarPasajero;
import com.app.interfaces.InfoUsuario;

import com.app.enums.TipoAsiento;
import com.app.enums.MetodoPago;
import com.app.enums.TipoImagen;
import com.app.enums.TipoDocumento;
import com.app.enums.EstadoRuta;

public class Main extends JFrame {

    //Declaración de variables "importantes"
    private static ISistema s;
    private AuxiliarFunctions auxiliar;
    private List<JCheckBox> checkboxes;

    // Para gestión imagenes
    private File imagenTemporalUsuario;
    private File imagenTemporalVuelo;
    private File imagenTemporalRuta;

    //Declaración de JavaSwing
    private JPanel menuPrincipal;
    private JTabbedPane gestionUsuarios;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;
    private JComboBox jComboBoxRutaVueloAltaVuelo;
    private JTextField nombreAltaVuelo;
    private JSpinner jSpinnerTuristasAltaVuelo;
    private JSpinner jSpinnerEjecutivosAltaVuelo;
    private JSpinner jSpinnerDiaAltaVuelo;
    private JSpinner jSpinnerAnioAltaVuelo;
    private JSpinner jSpinnerMesAltaVuelo;
    private JComboBox jComboBoxAerolineaAltaVuelo;
    private JPanel formVuelo;
    private JPanel fechaField;
    private JButton crearVueloButton;
    private JPanel vueloPanel;
    private JComboBox jComboBoxConsultaVueloAerolinea;
    private JComboBox jComboBoxConsultaVueloRutaDeVuelo;
    private JComboBox jComboBoxConsultaVueloVuelo;
    private JButton consultarVueloButton;
    private JTextField descripcionAltaPaquete;
    private JButton cANCELARButton;
    private JButton cONFIRMARButton;
    private JButton consultarUsuarioButton;
    private JTextField webAltaCiudad;
    private JButton btnConfirmarAltaCiudad;
    private JTextField nombreAltaCiudad;
    private JTextField paisAltaCiudad;
    private JTextField aeropuertoAltaCiudad;
    private JTextField descripcionAltaCiudad;
    private JPanel jPanelRegistrarCliente;
    private JPanel jPanelRegistrarAerolinea;
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
    private JComboBox jComboBoxCiudadDestino;
    private JSpinner spinnerCostoTurista;
    private JSpinner spinnerCostoEjecutivo;
    private JSpinner spinnerCostoEquipaje;
    private JComboBox userType;
    private JPanel reservaPanel;
    private JComboBox jComboBoxseleccionarAerolineaReserva;
    private JComboBox jComboBoxrutaDeVueloReserva;
    private JComboBox jComboBoxvueloReserva;
    private JButton jButtonVerVueloReservaButton;
    private JComboBox jComboBoxSeleccionarClienteReserva;
    private JComboBox jComboBoxtipoAsientoReserva;
    private JSpinner jSpinnerCantPasajesReserva;
    private JSpinner jSpinnerCantEquipajeExtraReserva;
    private JButton hacerReservaButton;
    private JSpinner jSpinnerPeriodoAltaPaquete;
    private JSpinner jSpinnerDescuentoAltaPaquete;
    private JTextField nombreAltaPaquete;
    private JComboBox jComboBoxPaqueteComprarPaquete;
    private JComboBox jComboBoxClienteCompraPaquete;
    private JButton buttonConfirmarCompraPaquete;
    private JButton cANCELARButton1;
    private JPanel jPanelModificarAerolinea;
    private JButton jButtonModificarAerolinea;
    private JTextField nicknameModificarAerolinea;
    private JPanel jPanelModificarCliente;
    private JTextField nicknameModificarCliente;
    private JComboBox jComboBoxSeleccionarUsuarioModificar;
    private JTextField nombreClienteModificar;
    private JTextField correoClienteModificar;
    private JTextField apellidoClienteModificar;
    private JSpinner fechaDiaClienteModificar;
    private JSpinner fechaMesClienteModificar;
    private JSpinner fechaAnioClienteModificar;
    private JTextField nacionalidadClienteModificar;
    private JComboBox tipoDocumentoClienteModificar;
    private JComboBox jComboBoxSeleccionarUsuarioConsultar;
    private JSpinner jSpinnerDuracionAltaVueloHora;
    private JSpinner jSpinnerDuracionAltaVueloMinuto;
    private JTextField nombreAltaCategoria;
    private JButton confirmarAltaCategoria;
    private JSpinner fechaDiaRegistrarCliente;
    private JSpinner fechaMesRegistrarCliente;
    private JSpinner fechaAnioRegistrarCliente;
    private JButton crearRutaDeVuelo;
    private JTextField nombreAltaRutaDeVuelo;
    private JTextField descripcionAltaRutaDeVuelo;
    private JComboBox jComboBoxCiudadOrigen;
    private JSpinner spinnerMinutoAltaRutaDeVuelo;
    private JSpinner spinnerHoraAltaRutaDeVuelo;
    private JComboBox jComboBoxAerolineaAltaRutaVuelo;
    private JComboBox jComboBoxPaqueteConsultaPaqueteRutaVuelo;
    private JButton jButtonConsultarPaquete;
    private JPanel jPanelCategorias;
    private JComboBox jComboBoxAerolineaConsultaRuta;
    private JComboBox jComboBoxRutaVueloAgregarRuta;
    private JButton buttonConfirmarAgregarRutaPaquete;
    private JSpinner jSpinnerCantidadAgregarRuta;
    private JComboBox jComboBoxTipoAsientoAgregarRutaPaquete;
    private JComboBox jComboBoxPaqueteAgregarRuta;
    private JComboBox jComboBoxAerolineaAgregarRuta;
    private JComboBox jComboBoxRutaVueloConsultaRuta;
    private JButton buttonConsultarRutaVuelo;
    private JTextField nombreModificarAerolinea;
    private JTextField correoModificarAerolinea;
    private JTextField linkWebModificarAerolinea;
    private JTextField descripcionModificarAerolinea;
    private JButton confirmarModificarCliente;
    private JTextField numeroDocumentoModificarCliente;
    private JButton cancelarAltaCategori;
    private JPasswordField registrarContrasenaCliente;
    private JPanel clienteSubirImagePanel;
    private JPasswordField registrarContrasenaCliente2;
    private JButton subirImagenButton;
    private JLabel selectedFileClienteRegistrar;
    private JPanel modificarClienteImagenPanel;
    private JButton modificarClienteImagen;
    private JButton subirImagenImagenAerolineaButton;
    private JPanel aerolineaSubirImagenPanel;
    private JPasswordField registrarAerolineaContrasena;
    private JPasswordField registrarAerolineaContrasena2;
    private JLabel selectedFileAerolineaRegistrar;
    private JPanel modificarAerolineaImagenPanel;
    private JButton modificarAerolineaImagenButton;
    private JButton button1;
    private JLabel selectedFileVueloCrear;
    private JButton button2;
    private JLabel selectedFileRutaCrear;
    private JComboBox seleccionarAerolineaAceptarRechazarRuta;
    private JComboBox seleccionarRutaAceptarRechazarRuta;
    private JButton aprobarButtonAceptarRechazarRuta;
    private JButton rechazarButtonAceptarRechazarRuta;
    private JLabel estadoRutaText;
    private JTextField descripcionCortaAltaRutaDeVuelo;
    private JRadioButton pagoGeneralRadioButton;
    private JRadioButton pagoConPaqueteRadioButton;
    private JComboBox jComboBoxSeleccionarPaqueteReserva;
    private JList listaCategorias;
    private JPanel jPanelTop5Rutas;
    private JTextField videoUrlAltaRutaDeVuelo;


    public Main() {
        //Inicializar Sistema
//        s = Factory.getSistema();
//        s.cargarDatos();

        //Inicializar contenido principal de la app
        $$$setupUI$$$();
        add(menuPrincipal);

        // Configuración del JFrame

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Admin Dashboard");
        setVisible(true);

        //Inicializar Auxiliar
        this.auxiliar = new AuxiliarFunctions(s);
        this.checkboxes = new ArrayList<>();

        //Inicializar carpetas
        AuxiliarFunctions.initFolders();

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (IllegalArgumentException | UnsupportedLookAndFeelException ex) {
            System.err.println("No se pudo cargar FlatLaf: " + ex);
        }

        SwingUtilities.updateComponentTreeUI(this);

        //Settear swing elements visibles false
        jPanelRegistrarAerolinea.setVisible(false);
        jPanelModificarAerolinea.setVisible(false);
        jPanelModificarCliente.setVisible(false);

        //Settear swing elements isEditable false
        nicknameModificarAerolinea.setEditable(false);
        nicknameModificarCliente.setEditable(false);
        correoModificarAerolinea.setEditable(false);
        correoClienteModificar.setEditable(false);

        //Settear swing elements isEnabled false
        jComboBoxrutaDeVueloReserva.setEnabled(false);
        jComboBoxvueloReserva.setEnabled(false);
        jComboBoxSeleccionarClienteReserva.setEnabled(false);
        jComboBoxtipoAsientoReserva.setEnabled(false);
        jComboBoxSeleccionarPaqueteReserva.setEnabled(false);
        jButtonVerVueloReservaButton.setEnabled(false);
        aprobarButtonAceptarRechazarRuta.setEnabled(false);
        rechazarButtonAceptarRechazarRuta.setEnabled(false);

        //Settear modelos JComboBox
        jComboBoxSeleccionarUsuarioModificar.setModel(auxiliar.getComboUserModel());
        jComboBoxSeleccionarUsuarioConsultar.setModel(auxiliar.getComboUserModel());

        jComboBoxClienteCompraPaquete.setModel(auxiliar.getComboClienteModel());
        jComboBoxSeleccionarClienteReserva.setModel(auxiliar.getComboClienteModel());

        jComboBoxAerolineaAltaVuelo.setModel(auxiliar.getComboAerolineaModel());
        jComboBoxAerolineaAltaRutaVuelo.setModel(auxiliar.getComboAerolineaModel());
        jComboBoxAerolineaConsultaRuta.setModel(auxiliar.getComboAerolineaModel());
        jComboBoxseleccionarAerolineaReserva.setModel(auxiliar.getComboAerolineaModel());
        jComboBoxAerolineaAgregarRuta.setModel(auxiliar.getComboAerolineaModel());
        jComboBoxConsultaVueloAerolinea.setModel(auxiliar.getComboAerolineaModel());
        jComboBoxAerolineaConsultaRuta.setModel(auxiliar.getComboAerolineaModel());
        seleccionarAerolineaAceptarRechazarRuta.setModel(auxiliar.getComboAerolineaModel());

        jComboBoxRutaVueloAltaVuelo.setModel(auxiliar.getComboRutaDeVueloModel());

        jComboBoxCiudadOrigen.setModel(auxiliar.getComboCiudadOrigenModel());

        jComboBoxCiudadDestino.setModel(auxiliar.getComboCiudadDestinoModel());

        jComboBoxPaqueteConsultaPaqueteRutaVuelo.setModel(auxiliar.getComboPaqueteModel());

        jComboBoxPaqueteComprarPaquete.setModel(auxiliar.getComboPaquetesConRutasModel());

        jComboBoxPaqueteAgregarRuta.setModel(auxiliar.getComboPaqueteNoCompradoModel());

        jComboBoxRutaVueloAgregarRuta.setModel(auxiliar.getComboRutaDeVueloAerolineaModel());
        jComboBoxRutaVueloConsultaRuta.setModel(auxiliar.getComboRutaDeVueloAerolineaModel());
        jComboBoxrutaDeVueloReserva.setModel(auxiliar.getComboRutaDeVueloAerolineaModel());
        jComboBoxConsultaVueloRutaDeVuelo.setModel(auxiliar.getComboRutaDeVueloAerolineaModel());
        jComboBoxRutaVueloAltaVuelo.setModel(auxiliar.getComboRutaDeVueloAerolineaModel());
        seleccionarRutaAceptarRechazarRuta.setModel(auxiliar.getComboRutaDeVueloAerolineaModel());

        jComboBoxvueloReserva.setModel(auxiliar.getComboVueloRutaDeVueloModel());
        jComboBoxConsultaVueloVuelo.setModel(auxiliar.getComboVueloRutaDeVueloModel());

        listaCategorias.setModel(auxiliar.getModeloCategorias());
        listaCategorias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);


        // Configurar JSpinner
        jSpinnerDiaAltaVuelo.setModel(new SpinnerNumberModel(1, 1, 31, 1));

        jSpinnerMesAltaVuelo.setModel(new SpinnerNumberModel(1, 1, 12, 1));

        jSpinnerAnioAltaVuelo.setModel(new SpinnerNumberModel(2025, 2025, 2030, 1));

        jSpinnerEjecutivosAltaVuelo.setModel(new SpinnerNumberModel(1, 1, 100, 1));
        jSpinnerTuristasAltaVuelo.setModel(new SpinnerNumberModel(1, 1, 100, 1));
        jSpinnerCantPasajesReserva.setModel(new SpinnerNumberModel(1, 1, 100, 1));
        jSpinnerCantEquipajeExtraReserva.setModel(new SpinnerNumberModel(1, 1, 100, 1));

        spinnerCostoTurista.setModel(new SpinnerNumberModel(1, 1, 1_000_000, 1));
        spinnerCostoEjecutivo.setModel(new SpinnerNumberModel(1, 1, 1_000_000, 1));
        spinnerCostoEquipaje.setModel(new SpinnerNumberModel(1, 1, 1_000_000, 1));

        jSpinnerCantidadAgregarRuta.setModel(new SpinnerNumberModel(1, 1, 1_000_000, 1));

        spinnerHoraAltaRutaDeVuelo.setModel(new SpinnerNumberModel(0, 0, 24, 1));
        jSpinnerDuracionAltaVueloHora.setModel(new SpinnerNumberModel(0, 0, 24, 1));

        spinnerMinutoAltaRutaDeVuelo.setModel(new SpinnerNumberModel(0, 0, 59, 1));
        jSpinnerDuracionAltaVueloMinuto.setModel(new SpinnerNumberModel(0, 0, 59, 1));

        jSpinnerDescuentoAltaPaquete.setModel(new SpinnerNumberModel(0, 0, 100, 1));
        jSpinnerPeriodoAltaPaquete.setModel(new SpinnerNumberModel(1, 1, 1_000_000, 1));

        auxiliar.cargarTodosLosDatos();

        jComboBoxSeleccionarUsuarioModificar.setSelectedIndex(-1);
        jComboBoxSeleccionarUsuarioConsultar.setSelectedIndex(-1);
        jComboBoxAerolineaAltaVuelo.setSelectedIndex(-1);
        jComboBoxRutaVueloAltaVuelo.setSelectedIndex(-1);
        jComboBoxCiudadOrigen.setSelectedIndex(-1);
        jComboBoxCiudadDestino.setSelectedIndex(-1);
        jComboBoxAerolineaAltaRutaVuelo.setSelectedIndex(-1);
        jComboBoxPaqueteConsultaPaqueteRutaVuelo.setSelectedIndex(-1);
        jComboBoxseleccionarAerolineaReserva.setSelectedIndex(-1);
        jComboBoxrutaDeVueloReserva.setSelectedIndex(-1);
        jComboBoxvueloReserva.setSelectedIndex(-1);
        jComboBoxSeleccionarClienteReserva.setSelectedIndex(-1);
        jComboBoxtipoAsientoReserva.setSelectedIndex(-1);
        jComboBoxPaqueteComprarPaquete.setSelectedIndex(-1);
        jComboBoxConsultaVueloAerolinea.setSelectedIndex(-1);
        jComboBoxAerolineaConsultaRuta.setSelectedIndex(-1);
        jComboBoxPaqueteAgregarRuta.setSelectedIndex(-1);
        jComboBoxAerolineaAgregarRuta.setSelectedIndex(-1);
        jComboBoxRutaVueloAgregarRuta.setSelectedIndex(-1);



        consultarVueloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auxiliar.estanVaciosJComboBox(jComboBoxConsultaVueloAerolinea, jComboBoxConsultaVueloRutaDeVuelo, jComboBoxConsultaVueloVuelo)) {
                    new VentanaMensaje("Faltan argumentos");
                    return;
                }

                String aerolinea = jComboBoxConsultaVueloAerolinea.getSelectedItem().toString();
                String rutaDeVuelo = jComboBoxConsultaVueloRutaDeVuelo.getSelectedItem().toString();
                String vueloStr = jComboBoxConsultaVueloVuelo.getSelectedItem().toString();
                DtVuelo dtVuelo = (DtVuelo) jComboBoxConsultaVueloVuelo.getSelectedItem();

                System.out.println("Aerolinea: " + aerolinea);
                System.out.println("Ruta de Vuelo: " + rutaDeVuelo);
                System.out.println("Vuelo: " + vueloStr);

                InfoVuelo vuelo = new InfoVuelo(dtVuelo, auxiliar);
                setEnabled(false);

                vuelo.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        setEnabled(true);
                    }

                    ;
                });

            }
        });

        btnConfirmarAltaCiudad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auxiliar.estanVaciosJTextField(nombreAltaCiudad, paisAltaCiudad, aeropuertoAltaCiudad, descripcionAltaCiudad)) {
                    new VentanaMensaje("Faltan argumentos");
                    return;
                }
                try {
                    s.altaCiudad(new DtCiudad(nombreAltaCiudad.getText(), paisAltaCiudad.getText(), aeropuertoAltaCiudad.getText(), descripcionAltaCiudad.getText(), webAltaCiudad.getText(), LocalDate.now().toString()));
                    new VentanaMensaje("Ciudad registrada exitosamente: " + nombreAltaCiudad.getText());
                    auxiliar.cargarCiudadesComboBox();
                } catch (IllegalArgumentException ex) {
                    new VentanaMensaje("Error " + ex.getMessage()); //de validacion
                }
            }
        });


        hacerReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auxiliar.estanVaciosJComboBox(jComboBoxseleccionarAerolineaReserva, jComboBoxrutaDeVueloReserva, jComboBoxvueloReserva, jComboBoxSeleccionarClienteReserva, jComboBoxtipoAsientoReserva)) {
                    new VentanaMensaje("Faltan argumentos");
                    return;
                }
                try {
                    // Valores que maneja la reserva
                    DtVuelo vuelo = s.getVuelo(jComboBoxvueloReserva.getSelectedItem().toString());
                    DtCliente cliente = s.getCliente(jComboBoxSeleccionarClienteReserva.getSelectedItem().toString());
                    TipoAsiento tipoAsiento = jComboBoxtipoAsientoReserva.getSelectedItem().toString() == "Turista" ? TipoAsiento.TURISTA : TipoAsiento.EJECUTIVO;

                    int pasajes = (Integer) jSpinnerCantPasajesReserva.getValue();

                    if (pasajes <= 0) {
                        new VentanaMensaje("Pasajes de reservas debe ser mayor a 0");
                        return;
                    }

                    int equipajeExtra = (Integer) jSpinnerCantEquipajeExtraReserva.getValue();

                    LocalDate fecha = LocalDate.now();

                    MetodoPago metodoPago;
                    DtPaquete paqueteSeleccionado;

                    if (pagoConPaqueteRadioButton.isSelected()) {
                        metodoPago = MetodoPago.PAQUETE;
                        if (jComboBoxSeleccionarPaqueteReserva.getSelectedItem() == null) {
                            new VentanaMensaje("Debe seleccionar un paquete");
                            return;
                        }
                        paqueteSeleccionado = s.getPaquete(jComboBoxSeleccionarPaqueteReserva.getSelectedItem().toString());
                    } else {
                        paqueteSeleccionado = null;
                        if (pagoGeneralRadioButton.isSelected()) {
                            metodoPago = MetodoPago.GENERAL;
                        } else {
                            new VentanaMensaje("Debe seleccionar un método de pago");
                            return;
                        }
                    }

                    AgregarPasajero ventanaPasajes = new AgregarPasajero(pasajes);
                    setEnabled(false);

                    ventanaPasajes.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            setEnabled(true);
                            List<DtPasajero> listaPasajes = ventanaPasajes.getPasajes();
                            if (listaPasajes.size() == pasajes) {
                                DtReserva reserva;

                                if (metodoPago == MetodoPago.PAQUETE) {
                                    reserva = new DtReserva(
                                            fecha.toString(),
                                            tipoAsiento,
                                            pasajes,
                                            equipajeExtra,
                                            0,
                                            listaPasajes,
                                            cliente,
                                            vuelo,
                                            metodoPago,
                                            paqueteSeleccionado,
                                            false
                                    );
                                } else {
                                    reserva = new DtReserva(
                                            fecha.toString(),
                                            tipoAsiento,
                                            pasajes,
                                            equipajeExtra,
                                            0,
                                            listaPasajes,
                                            cliente,
                                            vuelo,
                                            metodoPago,
                                            false
                                    );
                                }

                                try {
                                    s.altaReserva(reserva);
                                    new VentanaMensaje("Reserva realizada exitosamente");
                                } catch (IllegalArgumentException ex) {
                                    new VentanaMensaje(ex.getMessage());
                                }
                            } else {
                                new VentanaMensaje("La cantidad de pasajes no concuerda..");
                            }
                        }
                    });
                } catch (IllegalArgumentException ex) {
                    new VentanaMensaje("Error: " + ex.getMessage());
                }

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
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String tipoUsuario = userType.getSelectedItem().toString();
                    if (tipoUsuario.equals("Cliente")) {
                        jPanelRegistrarAerolinea.setVisible(false);
                        jPanelRegistrarCliente.setVisible(true);
                    } else {
                        jPanelRegistrarAerolinea.setVisible(true);
                        jPanelRegistrarCliente.setVisible(false);
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
                        if (auxiliar.estanVaciosJTextField(nicknameRegistrarCliente, nombreRegistrarCliente, correoElectronicoRegistrarCliente, registrarContrasenaCliente, registrarContrasenaCliente2, apellidoRegistrarCliente, nacionalidadRegistrarCliente, documentoRegistrarCliente) || auxiliar.estanVaciosJComboBox(tipoDocumentoRegistrarCliente)) {
                            new VentanaMensaje("Faltan argumentos");
                            return;
                        }

                        auxiliar.validarCorreo(correoElectronicoRegistrarCliente.getText());
                        auxiliar.validarDocumento(documentoRegistrarCliente.getText());

                        // Crear DtCliente
                        int dia = (Integer) fechaDiaRegistrarCliente.getValue();
                        int mes = (Integer) fechaMesRegistrarCliente.getValue();
                        int anio = (Integer) fechaAnioRegistrarCliente.getValue();
                        LocalDate fecha;

                        try {
                            fecha = LocalDate.of(anio, mes, dia);
                            if (!auxiliar.esFechaValida(fecha)) {
                                new VentanaMensaje("Debes ingresar una fecha válida..");
                                return;
                            }
                        } catch (IllegalArgumentException ex) {
                            new VentanaMensaje("Debes ingresar una fecha válida..");
                            return;
                        }

                        TipoDocumento documentoT = tipoDocumentoRegistrarCliente.getSelectedItem().toString().equals("Cedula") ?
                                TipoDocumento.CEDULA : TipoDocumento.PASAPORTE;

                        if (!registrarContrasenaCliente.getText().equals(registrarContrasenaCliente2.getText())) {
                            new VentanaMensaje("Las contraseñas no coinciden.");
                            return;
                        }

                        String contrasena = registrarContrasenaCliente.getText();

                        if (imagenTemporalUsuario == null) {
                            new VentanaMensaje("Debes ingresar una imagen.");
                            return;
                        }

                        File imagenGuardada = AuxiliarFunctions.guardarImagen(imagenTemporalUsuario, TipoImagen.USUARIO);
                        imagenTemporalUsuario = null;

                        if (imagenGuardada == null) {
                            new VentanaMensaje("Ocurrió un error al guardar la imagen");
                            return;
                        }

                        String urlImage = imagenGuardada.getName();

                        DtCliente cliente = new DtCliente(
                                nicknameRegistrarCliente.getText(),
                                nombreRegistrarCliente.getText(),
                                correoElectronicoRegistrarCliente.getText(),
                                contrasena,
                                urlImage,
                                apellidoRegistrarCliente.getText(),
                                fecha.toString(),
                                nacionalidadRegistrarCliente.getText(),
                                documentoT,
                                Integer.parseInt(documentoRegistrarCliente.getText())
                        );

                        try {
                            s.registrarCliente(cliente);
                            new VentanaMensaje("Cliente creado correctamente.");
                            auxiliar.cargarUsuariosComboBox(); //Cada vez que se agregue un usuario, actualice todos los datos
                        } catch (IllegalArgumentException ex) {
                            AuxiliarFunctions.borrarImagen(urlImage, TipoImagen.USUARIO);
                            selectedFileClienteRegistrar.setText("No se ha seleccionado archivo.");
                            new VentanaMensaje(ex.getMessage());
                        }
                        auxiliar.cargarUsuariosComboBox();
                        imagenTemporalUsuario = null;
                        selectedFileClienteRegistrar.setText("No se ha seleccionado archivo");

                    }
                } catch (IllegalArgumentException | IOException ex) {
                    new VentanaMensaje(ex.getMessage());
                }
            }
        });

        //CONSULTAR USUARIO
        consultarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (jComboBoxSeleccionarUsuarioConsultar.getSelectedItem() == null) {
                        new VentanaMensaje("Seleccione un usuario");
                        return;
                    }
                    String nickname = jComboBoxSeleccionarUsuarioConsultar.getSelectedItem().toString();

                    DtUsuario dtUsuario = s.getUsuario(nickname);

                    InfoUsuario ventanaUsuario = new InfoUsuario(dtUsuario, auxiliar);
                    setEnabled(false);

                    ventanaUsuario.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            setEnabled(true);
                        }

                        ;
                    });

                } catch (IllegalArgumentException ex) {
                    new VentanaMensaje("Error al consultar: " + ex.getMessage());
                }
            }

        });
        jComboBoxSeleccionarUsuarioModificar.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    DtUsuario user = s.getUsuario(jComboBoxSeleccionarUsuarioModificar.getSelectedItem().toString());

                    if (user instanceof DtCliente cliente) { // Pregunta si mi usuario es un cliente
                        LocalDate fechaCliente = LocalDate.parse(cliente.getFechaNacimiento());
                        int tipo = cliente.getTipoDocumento() == TipoDocumento.CEDULA ? 0 : 1;

                        jPanelModificarCliente.setVisible(true);
                        jPanelModificarAerolinea.setVisible(false);
                        nicknameModificarCliente.setText(cliente.getNickname());
                        nombreClienteModificar.setText(cliente.getNombre());
                        apellidoClienteModificar.setText(cliente.getApellido());
                        correoClienteModificar.setText(cliente.getEmail());
                        fechaDiaClienteModificar.setValue(fechaCliente.getDayOfMonth());
                        fechaMesClienteModificar.setValue(fechaCliente.getMonthValue());
                        fechaAnioClienteModificar.setValue(fechaCliente.getYear());
                        nacionalidadClienteModificar.setText(cliente.getNacionalidad());
                        tipoDocumentoClienteModificar.setSelectedIndex(tipo);
                        numeroDocumentoModificarCliente.setText(String.valueOf(cliente.getNumeroDocumento()));
                        ImageIcon profileImage;

                        try {
                            Path userImg = AuxiliarFunctions.getImagePath(cliente.getUrlImage(), TipoImagen.USUARIO);
                            if (!Files.exists(userImg)) {
                                throw new IllegalArgumentException("No se encontro la imagen.");
                            }
                            profileImage = new ImageIcon(userImg.toAbsolutePath().toString());
                        } catch (IllegalArgumentException ex) {
                            profileImage = new ImageIcon(getClass().getResource("/pictures/users/default.png"));
                        }

                        AuxiliarFunctions.mostrarFoto(modificarClienteImagenPanel, profileImage, 100, 100, TipoImagen.USUARIO);

                    } else if (user instanceof DtAerolinea aerolinea) {
                        jPanelModificarCliente.setVisible(false);
                        jPanelModificarAerolinea.setVisible(true);
                        nicknameModificarAerolinea.setText(aerolinea.getNickname());
                        nombreModificarAerolinea.setText(aerolinea.getNombre());
                        correoModificarAerolinea.setText(aerolinea.getEmail());
                        linkWebModificarAerolinea.setText(aerolinea.getLinkWeb());
                        descripcionModificarAerolinea.setText(aerolinea.getDescripcion());
                        ImageIcon profileImage;

                        try {
                            Path userImg = AuxiliarFunctions.getImagePath(aerolinea.getUrlImage(), TipoImagen.USUARIO);
                            if (!Files.exists(userImg)) {
                                throw new IllegalArgumentException("No se encontro la imagen");
                            }
                            profileImage = new ImageIcon(userImg.toAbsolutePath().toString());
                        } catch (IllegalArgumentException ex) {
                            profileImage = new ImageIcon(getClass().getResource("/pictures/users/default.png"));
                        }

                        AuxiliarFunctions.mostrarFoto(modificarAerolineaImagenPanel, profileImage, 100, 100, TipoImagen.USUARIO);
                    } else {
                        jPanelModificarAerolinea.setVisible(false);
                        jPanelModificarCliente.setVisible(false);
                    }
                }
            }
        });
        crearVueloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auxiliar.estanVaciosJComboBox(jComboBoxAerolineaAltaVuelo, jComboBoxRutaVueloAltaVuelo) || auxiliar.estanVaciosJTextField(nombreAltaVuelo)) {
                    new VentanaMensaje("Faltan argumentos");
                    return;
                }
                try {

                    String ruta = jComboBoxRutaVueloAltaVuelo.getSelectedItem().toString();
                    String nombre = nombreAltaVuelo.getText();

                    auxiliar.validarNombreVuelo(nombre);

                    DtRuta rutaDeVuelo = s.getRutaDeVuelo(ruta);

                    LocalDate fecha = LocalDate.of((Integer) jSpinnerAnioAltaVuelo.getValue(), (Integer) jSpinnerMesAltaVuelo.getValue(), (Integer) jSpinnerDiaAltaVuelo.getValue());
                    LocalTime hora = LocalTime.of((Integer) jSpinnerDuracionAltaVueloHora.getValue(), (Integer) jSpinnerDuracionAltaVueloMinuto.getValue());

                    if (fecha.isBefore(LocalDate.now())) {
                        new VentanaMensaje("La fecha debe ser para el futuro");
                        return;
                    }

                    if (imagenTemporalVuelo == null) {
                        new VentanaMensaje("Debes ingresar una imagen.");
                        return;
                    }

                    File imagenGuardada = AuxiliarFunctions.guardarImagen(imagenTemporalVuelo, TipoImagen.VUELO);
                    imagenTemporalVuelo = null;

                    if (imagenGuardada == null) {
                        new VentanaMensaje("Ocurrió un error al guardar la imagen");
                        return;
                    }

                    String urlImage = imagenGuardada.getName();

                    DtVuelo dtVuelo = new DtVuelo(nombre, fecha.toString(), hora.toString(), (Integer) jSpinnerTuristasAltaVuelo.getValue(), (Integer) jSpinnerEjecutivosAltaVuelo.getValue(), urlImage, LocalDate.now().toString(), rutaDeVuelo, 0);

                    try {
                        s.altaVuelo(dtVuelo);
                        new VentanaMensaje("Vuelo creado correctamente");
                        auxiliar.limpiarJTextField(nombreAltaVuelo);
                        auxiliar.cargarAerolineasComboBox();
                        imagenTemporalVuelo = null;
                        selectedFileVueloCrear.setText("No se ha seleccionado archivo");
                    } catch (IllegalArgumentException ex) {
                        AuxiliarFunctions.borrarImagen(urlImage, TipoImagen.VUELO);
                        new VentanaMensaje("Error: " + ex.getMessage());
                    }
                } catch (IllegalArgumentException | IOException ex) {
                    new VentanaMensaje(ex.getMessage());
                }
            }
        });

        confirmarAltaCategoria.addActionListener(e -> {
            if (auxiliar.estanVaciosJTextField(nombreAltaCategoria)) {
                new VentanaMensaje("Ingrese un nombre de categoría");
                return;
            }
            try {
                s.altaCategoria(new DtCategoria(nombreAltaCategoria.getText()));
                new VentanaMensaje("Categoria creada correctamente");
                nombreAltaCategoria.setText("");
                cargarCategorias();
            } catch (IllegalArgumentException ex) {
                new VentanaMensaje(ex.getMessage());
            }
        });
        confirmarAltaAerolinea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auxiliar.estanVaciosJComboBox(userType)) {
                    new VentanaMensaje("Seleccione un tipo de usuario");
                    return;
                }
                try {
                    String tipoUsuario = userType.getSelectedItem().toString();
                    if (tipoUsuario.equals("Aerolinea")) {
                        if (auxiliar.estanVaciosJTextField(nicknameRegistrarAerolinea, nombreRegistrarAerolinea, correoRegistrarAerolinea, registrarAerolineaContrasena, registrarAerolineaContrasena2, descripcionRegistrarAerolinea)) {
                            new VentanaMensaje("Faltan argumentos");
                            return;
                        }
                        auxiliar.validarCorreo(correoRegistrarAerolinea.getText());

                        if (!registrarAerolineaContrasena.getText().equals(registrarAerolineaContrasena2.getText())) {
                            new VentanaMensaje("Las contraseñas no coinciden.");
                            return;
                        }

                        String contrasena = registrarAerolineaContrasena.getText();

                        if (imagenTemporalUsuario == null) {
                            new VentanaMensaje("Debes ingresar una imagen.");
                            return;
                        }

                        File imagenGuardada = AuxiliarFunctions.guardarImagen(imagenTemporalUsuario, TipoImagen.USUARIO);
                        imagenTemporalUsuario = null;

                        if (imagenGuardada == null) {
                            new VentanaMensaje("Ocurrió un error al guardar la imagen");
                            return;
                        }

                        String urlImage = imagenGuardada.getName();

                        DtAerolinea aerolinea = new DtAerolinea(
                                nicknameRegistrarAerolinea.getText(),
                                nombreRegistrarAerolinea.getText(),
                                correoRegistrarAerolinea.getText(),
                                contrasena,
                                urlImage,
                                descripcionRegistrarAerolinea.getText(),
                                sitioWebRegistrarAerolinea.getText()
                        );
                        try {
                            s.registrarAerolinea(aerolinea);
                            new VentanaMensaje("Aerolínea creada correctamente.");
                        } catch (IllegalArgumentException ex) {
                            AuxiliarFunctions.borrarImagen(urlImage, TipoImagen.USUARIO);
                            selectedFileAerolineaRegistrar.setText("No se ha seleccionado archivo.");
                            new VentanaMensaje(ex.getMessage());
                        }
                    }
                    auxiliar.cargarUsuariosComboBox();
                    auxiliar.cargarAerolineasComboBox();
                    imagenTemporalUsuario = null;
                    selectedFileAerolineaRegistrar.setText("No se ha seleccionado archivo");
                } catch (IllegalArgumentException | IOException ex) {
                    new VentanaMensaje(ex.getMessage());
                }
            }
        });
        cancelarAltaCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                auxiliar.limpiarJTextField(nicknameRegistrarCliente, nombreRegistrarCliente, correoElectronicoRegistrarCliente, registrarContrasenaCliente, registrarContrasenaCliente2, apellidoRegistrarCliente, nacionalidadRegistrarCliente, documentoRegistrarCliente);
                tipoDocumentoRegistrarCliente.setSelectedIndex(-1);
                fechaDiaRegistrarCliente.setValue(1);
                fechaMesRegistrarCliente.setValue(1);
                fechaAnioRegistrarCliente.setValue(2024);
                imagenTemporalUsuario = null;
                selectedFileClienteRegistrar.setText("No se ha seleccionado archivo");
            }
        });

        crearRutaDeVuelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // OBTENER URL DE VIDEO
                String urlVideo = videoUrlAltaRutaDeVuelo.getText().trim();
                if (urlVideo.isEmpty()) {
                    urlVideo = "";
                }

                try {
                    if (auxiliar.estanVaciosJTextField(nombreAltaRutaDeVuelo, descripcionAltaRutaDeVuelo, descripcionCortaAltaRutaDeVuelo) || auxiliar.estanVaciosJComboBox(jComboBoxAerolineaAltaRutaVuelo, jComboBoxCiudadOrigen, jComboBoxCiudadDestino)) {
                        new VentanaMensaje("Faltan argumentos");
                        return;
                    }

                    if (jComboBoxCiudadOrigen.getSelectedItem().toString().equals(jComboBoxCiudadDestino.getSelectedItem().toString())) {
                        new VentanaMensaje("La ciudad de origen no puede ser igual a la ciudad de destino");
                        return;
                    }

                    Integer hora = (Integer) spinnerHoraAltaRutaDeVuelo.getValue();
                    Integer minuto = (Integer) spinnerMinutoAltaRutaDeVuelo.getValue();

                    LocalTime horaRuta = LocalTime.of(hora, minuto);
                    Integer costoTurista = (Integer) spinnerCostoTurista.getValue();
                    Integer costoEjecutivo = (Integer) spinnerCostoEjecutivo.getValue();
                    Integer costoEquipaje = (Integer) spinnerCostoEquipaje.getValue();
                    List<String> nombresCategorias = listaCategorias.getSelectedValuesList();

                    if(nombresCategorias == null || nombresCategorias.isEmpty()){
                        new VentanaMensaje("No has seleccionado categorías");
                        return;
                    }

                    DtCiudad ciudadOrigen = (DtCiudad) jComboBoxCiudadOrigen.getSelectedItem();
                    DtCiudad ciudadDestino = (DtCiudad) jComboBoxCiudadDestino.getSelectedItem();

                    String urlImage;

                    if (imagenTemporalRuta == null) {
                        int opcion = JOptionPane.showConfirmDialog(
                                null,
                                "¿Quieres crear la ruta de vuelo sin una imagen?",
                                "Confirmación",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE
                        );
                        if (opcion == JOptionPane.NO_OPTION) {
                            return;
                        } else {
                            urlImage = null;
                        }
                    } else {
                        File imagenGuardada = AuxiliarFunctions.guardarImagen(imagenTemporalRuta, TipoImagen.RUTA);
                        imagenTemporalRuta = null;

                        if (imagenGuardada == null) {
                            new VentanaMensaje("Ocurrió un error al guardar la imagen");
                            return;
                        }

                        urlImage = imagenGuardada.getName();
                    }

                    DtRuta ruta = new DtRuta(nombreAltaRutaDeVuelo.getText(),
                            descripcionAltaRutaDeVuelo.getText(),
                            descripcionCortaAltaRutaDeVuelo.getText(),
                            horaRuta.toString(),
                            costoTurista,
                            costoEjecutivo,
                            costoEquipaje,
                            LocalDate.now().toString(),
                            urlImage,
                            urlVideo,
                            s.buscarCategoriasPorNombre(nombresCategorias),
                            ciudadOrigen,
                            ciudadDestino,
                            0
                    );


                    s.altaRutaDeVuelo(jComboBoxAerolineaAltaRutaVuelo.getSelectedItem().toString(), ruta);
                    auxiliar.cargarRutasDeVueloComboBox();
                    imagenTemporalRuta = null;
                    selectedFileRutaCrear.setText("No se ha seleccionado Archivo");
                    new VentanaMensaje("Ruta de vuelo creada correctamente.");

                    auxiliar.limpiarJTextField(nombreAltaRutaDeVuelo, descripcionAltaRutaDeVuelo);
                    cargarCategorias();

                } catch (IllegalArgumentException | IOException ex) {
                    new VentanaMensaje(ex.getMessage());
                }
            }
        });

        jButtonConsultarPaquete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auxiliar.estanVaciosJComboBox(jComboBoxPaqueteConsultaPaqueteRutaVuelo)) {
                    new VentanaMensaje("Falta ingresar algún dato.");
                    return;
                }

                // OBTENER URL DE VIDEO
                String urlVideo = videoUrlAltaRutaDeVuelo.getText().trim();
                if (urlVideo.isEmpty()) {
                    urlVideo = "";
                }
                try {
                    DtPaquete dataPaquete = s.getPaquete(jComboBoxPaqueteConsultaPaqueteRutaVuelo.getSelectedItem().toString());

                    JFrame paquete = new InfoPaquete(dataPaquete, auxiliar);
                    setEnabled(false);

                    paquete.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            setEnabled(true);
                        }

                        ;
                    });
                } catch (IllegalArgumentException ex) {
                    new VentanaMensaje(ex.getMessage());
                }
            }
        });

        cONFIRMARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auxiliar.estanVaciosJTextField(nombreAltaPaquete, descripcionAltaPaquete)) {
                    new VentanaMensaje("Falta ingresar campos.");
                    return;
                }
                try {
                    Integer periodo = (Integer) jSpinnerPeriodoAltaPaquete.getValue();
                    Integer descuento = (Integer) jSpinnerDescuentoAltaPaquete.getValue();

                    if (periodo < 1) {
                        new VentanaMensaje("El período debe ser 1 o más.");
                        return;
                    }

                    s.altaPaquete(new DtPaquete(nombreAltaPaquete.getText(), descripcionAltaPaquete.getText(), periodo, descuento, 0, new ArrayList<>()));
                    new VentanaMensaje("Paquete creado correctamente.");
                    auxiliar.cargarPaqueteComboBox();
                    auxiliar.cargarPaqueteNoCompradoComboBox();
                    auxiliar.limpiarJTextField(nombreAltaPaquete, descripcionAltaPaquete);
                    jSpinnerPeriodoAltaPaquete.setValue(1);
                    jSpinnerDescuentoAltaPaquete.setValue(0);
                } catch (IllegalArgumentException ex) {
                    new VentanaMensaje(ex.getMessage());
                }
            }
        });

        buttonConfirmarAgregarRutaPaquete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auxiliar.estanVaciosJComboBox(jComboBoxPaqueteAgregarRuta, jComboBoxAerolineaAgregarRuta, jComboBoxRutaVueloAgregarRuta, jComboBoxTipoAsientoAgregarRutaPaquete)) {
                    new VentanaMensaje("Falta ingresar campos de texto");
                    return;
                }

                try {
                    Integer cantidad = (Integer) jSpinnerCantidadAgregarRuta.getValue();
                    int res = s.agregarRutaAPaquete((DtPaquete) jComboBoxPaqueteAgregarRuta.getSelectedItem(), (DtRuta) jComboBoxRutaVueloAgregarRuta.getSelectedItem(), cantidad, TipoAsiento.valueOf(jComboBoxTipoAsientoAgregarRutaPaquete.getSelectedItem().toString()));
                    if (res != 0) new VentanaMensaje("La ruta ya existía en el paquete, cantidad modificada a: " + res);
                    else new VentanaMensaje("Ruta de vuelo agregada a paquete correctamente.");
                    auxiliar.cargarPaquetesConRutasComboBox();
                } catch (IllegalArgumentException ex) {
                    new VentanaMensaje(ex.getMessage());
                }
            }
        });

        jComboBoxAerolineaAgregarRuta.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        DtAerolinea a = s.getAerolinea(jComboBoxAerolineaAgregarRuta.getSelectedItem().toString());
                        if (a == null || a.toString().equals("N/A")) {
                            new VentanaMensaje("Ha ocurrido un error..");
                            return;
                        }
                        auxiliar.cargarRutasDeVueloComboBoxAerolinea(a.getNickname());
                    } catch (IllegalArgumentException ex) {
                        new VentanaMensaje(ex.getMessage());
                    }
                }
            }
        });

        jComboBoxAerolineaConsultaRuta.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        DtAerolinea a = s.getAerolinea(jComboBoxAerolineaConsultaRuta.getSelectedItem().toString());
                        if (a == null || a.toString().equals("N/A")) {
                            new VentanaMensaje("Ha ocurrido un error..");
                            return;
                        }
                        auxiliar.cargarRutasDeVueloComboBoxAerolinea(a.getNickname());
                    } catch (IllegalArgumentException ex) {
                        new VentanaMensaje(ex.getMessage());
                    }
                }
            }
        });

        buttonConsultarRutaVuelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auxiliar.estanVaciosJComboBox(jComboBoxAerolineaConsultaRuta, jComboBoxRutaVueloConsultaRuta)) {
                    new VentanaMensaje("Seleccione todos los campos");
                    return;
                }
                try {
                    String nombreRuta = jComboBoxRutaVueloConsultaRuta.getSelectedItem().toString();
                    InfoRutaDeVuelo ventanaRuta = new InfoRutaDeVuelo(s.getRutaDeVuelo(nombreRuta), auxiliar);
                    setEnabled(false);

                    ventanaRuta.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            setEnabled(true);
                        }

                        ;
                    });

                } catch (IllegalArgumentException ex) {
                    new VentanaMensaje(ex.getMessage());
                }
            }
        });

        // MODIFICAR CLIENTE
        confirmarModificarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auxiliar.estanVaciosJComboBox(jComboBoxSeleccionarUsuarioModificar) || auxiliar.estanVaciosJTextField(nombreClienteModificar, apellidoClienteModificar, correoClienteModificar, nacionalidadClienteModificar, numeroDocumentoModificarCliente)) {
                    new VentanaMensaje("Los campos no pueden quedar vacíos..");
                    return;
                }
                try {
                    int dia = (Integer) fechaDiaClienteModificar.getValue();
                    int mes = (Integer) fechaMesClienteModificar.getValue();
                    int anio = (Integer) fechaAnioClienteModificar.getValue();
                    LocalDate fecha = null;

                    try {
                        fecha = LocalDate.of(anio, mes, dia);
                        if (!auxiliar.esFechaValida(fecha)) {
                            new VentanaMensaje("Debes ingresar una fecha válida..");
                            return;
                        }
                    } catch (IllegalArgumentException ex) {
                        new VentanaMensaje("Debes ingresar una fecha válida..");
                        return;
                    }

                    TipoDocumento tipoDocumento = tipoDocumentoClienteModificar.getSelectedItem().toString().equals("Cedula") ? TipoDocumento.CEDULA : TipoDocumento.PASAPORTE;

                    String urlImage = "/src/main/pictures/users/default.png";

                    DtCliente cliente = new DtCliente(
                            nicknameModificarCliente.getText(),
                            nombreClienteModificar.getText(),
                            correoClienteModificar.getText(),
                            urlImage,
                            apellidoClienteModificar.getText(),
                            fecha.toString(),
                            nacionalidadClienteModificar.getText(),
                            tipoDocumento,
                            Integer.parseInt(numeroDocumentoModificarCliente.getText())
                    );

                    s.modificarCliente(cliente);
                    auxiliar.cargarUsuariosComboBox(cliente.getNickname()); // Funcion para actualizar valores.
                    new VentanaMensaje("Cliente actualizado correctamente.");
                } catch (IllegalArgumentException ex) {
                    new VentanaMensaje(ex.getMessage());
                }
            }
        });

        jButtonModificarAerolinea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auxiliar.estanVaciosJTextField(nombreModificarAerolinea, correoModificarAerolinea, descripcionModificarAerolinea)) {
                    new VentanaMensaje("No puedes dejar los campos vacios..");
                    return;
                }

                try {
                    String link = auxiliar.estanVaciosJTextField(linkWebModificarAerolinea) ? "" : linkWebModificarAerolinea.getText();

                    String urlImage = "/src/main/pictures/users/default.png";


                    DtAerolinea aerolinea = new DtAerolinea(
                            nicknameModificarAerolinea.getText(),
                            nombreModificarAerolinea.getText(),
                            correoModificarAerolinea.getText(),
                            urlImage,
                            descripcionModificarAerolinea.getText(),
                            link
                    );

                    s.modificarAerolinea(aerolinea);
                    auxiliar.cargarUsuariosComboBox(aerolinea.getNickname());
                    new VentanaMensaje("Aerolinea actualizado correctamente.");
                } catch (IllegalArgumentException ex) {
                    new VentanaMensaje(ex.getMessage());
                }
            }
        });

        jComboBoxseleccionarAerolineaReserva.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        DtAerolinea aerolinea = s.getAerolinea(jComboBoxseleccionarAerolineaReserva.getSelectedItem().toString());
                        if (aerolinea == null) {
                            new VentanaMensaje("Ha ocurrido un error..");
                            return;
                        }
                        auxiliar.cargarRutasDeVueloComboBoxAerolinea(aerolinea.getNickname());
                        jComboBoxrutaDeVueloReserva.setEnabled(true);
                    } catch (IllegalArgumentException ex) {
                        new VentanaMensaje(ex.getMessage());
                    }
                }
            }
        });

        pagoConPaqueteRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    jComboBoxSeleccionarPaqueteReserva.setEnabled(true);
                } else {
                    jComboBoxSeleccionarPaqueteReserva.setEnabled(false);
                    jComboBoxSeleccionarPaqueteReserva.setSelectedIndex(-1);
                }
            }
        });

        jComboBoxSeleccionarClienteReserva.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        DtCliente cliente = s.getCliente(jComboBoxSeleccionarClienteReserva.getSelectedItem().toString());
                        auxiliar.cargarPaqueteClienteComboBox(cliente.getNickname());
                        jComboBoxSeleccionarPaqueteReserva.setModel(auxiliar.getComboPaqueteClienteModel());
                        jComboBoxSeleccionarPaqueteReserva.setEnabled(true);
                    } catch (IllegalArgumentException ex) {
                        new VentanaMensaje(ex.getMessage());
                    }
                }
            }
        });

        jComboBoxrutaDeVueloReserva.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        DtRuta rutaSeleccionada = (DtRuta) jComboBoxrutaDeVueloReserva.getSelectedItem();
                        if(rutaSeleccionada == null || rutaSeleccionada.toString().equals("N/A")){
                            return;
                        }
                        DtRuta ruta = s.getRutaDeVuelo(rutaSeleccionada.toString());
                        auxiliar.cargarVuelosComboBoxRuta(ruta.getNombre());
                        jComboBoxvueloReserva.setEnabled(true);
                    } catch (IllegalArgumentException ex) {
                        new VentanaMensaje(ex.getMessage());
                    }
                }
            }
        });

        jButtonVerVueloReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auxiliar.estanVaciosJComboBox(jComboBoxvueloReserva)) {
                    new VentanaMensaje("Ha ocurrido un error..");
                    return;
                }

                try {
                    DtVuelo vuelo = s.getVuelo(jComboBoxvueloReserva.getSelectedItem().toString());
                    InfoVuelo ventanaVuelo = new InfoVuelo(vuelo, auxiliar);
                    setEnabled(false);

                    ventanaVuelo.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            setEnabled(true);
                        }

                        ;
                    });
                } catch (IllegalArgumentException ex) {
                    new VentanaMensaje(ex.getMessage());
                }
            }
        });

        jComboBoxvueloReserva.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    jButtonVerVueloReservaButton.setEnabled(!jComboBoxvueloReserva.getSelectedItem().toString().equals("N/A"));
                    jComboBoxSeleccionarClienteReserva.setEnabled(true);
                    jComboBoxtipoAsientoReserva.setEnabled(true);
                }
            }
        });
        jComboBoxConsultaVueloAerolinea.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                    DtAerolinea aerolinea = s.getAerolinea(jComboBoxConsultaVueloAerolinea.getSelectedItem().toString());
                    auxiliar.cargarRutasDeVueloComboBoxAerolinea(aerolinea.getNickname());
                    } catch (IllegalArgumentException ex) {
                        new VentanaMensaje(ex.getMessage());
                    }
                }
            }
        });

        jComboBoxConsultaVueloRutaDeVuelo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    DtRuta rutaSeleccionada = (DtRuta) jComboBoxConsultaVueloRutaDeVuelo.getSelectedItem();
                    if(rutaSeleccionada == null || rutaSeleccionada.toString().equals("N/A")){
                        return;
                    }
                    DtRuta ruta = s.getRutaDeVuelo(rutaSeleccionada.toString());
                    auxiliar.cargarVuelosComboBoxRuta(ruta.getNombre());

                }
            }
        });

        cancelarAltaCategori.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombreAltaCategoria.setText("");
            }
        });

        buttonConfirmarCompraPaquete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auxiliar.estanVaciosJComboBox(jComboBoxPaqueteComprarPaquete, jComboBoxClienteCompraPaquete)) {
                    new VentanaMensaje("Complete todos los campos.");
                    return;
                }

                try {
                    DtPaquete paquete = s.getPaquete(jComboBoxPaqueteComprarPaquete.getSelectedItem().toString());
                    DtCliente cliente = s.getCliente(jComboBoxClienteCompraPaquete.getSelectedItem().toString());
                    s.compraPaquete(paquete, cliente);
                    new VentanaMensaje("Compra de paquete realizada exitosamente.");
                } catch (IllegalArgumentException ex) {
                    new VentanaMensaje(ex.getMessage());
                }
            }
        });

        jComboBoxAerolineaAltaVuelo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    DtAerolinea a = s.getAerolinea(jComboBoxAerolineaAltaVuelo.getSelectedItem().toString());
                    auxiliar.cargarRutasDeVueloComboBoxAerolinea(a.getNickname());
                }
            }
        });
        subirImagenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagenTemporalUsuario = null;
                SubirImagen ventanaImagen = new SubirImagen(TipoImagen.USUARIO);
                setEnabled(false);

                ventanaImagen.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setEnabled(true);
                        imagenTemporalUsuario = ventanaImagen.getImagen();
                        if (imagenTemporalUsuario != null) {
                            selectedFileClienteRegistrar.setText(imagenTemporalUsuario.getName());
                        } else {
                            selectedFileClienteRegistrar.setText("No se ha seleccionado archivo.");
                        }
                    }

                    ;
                });
            }
        });
        modificarClienteImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagenTemporalUsuario = null;
                SubirImagen ventanaImagen = new SubirImagen(TipoImagen.USUARIO);
                setEnabled(false);

                ventanaImagen.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setEnabled(true);
                        imagenTemporalUsuario = ventanaImagen.getImagen();

                        if (jComboBoxSeleccionarUsuarioModificar.getSelectedItem() == null) {
                            new VentanaMensaje("Seleccione un usuario para modificar su imagen.");
                            return;
                        }

                        if (imagenTemporalUsuario != null) {
                            try {
                                DtCliente cliente = s.getCliente(jComboBoxSeleccionarUsuarioModificar.getSelectedItem().toString());

                                File imagen = AuxiliarFunctions.guardarImagen(imagenTemporalUsuario, TipoImagen.USUARIO);
                                s.modificarUsuarioImagen(cliente, imagen.getName());
                                auxiliar.cargarUsuariosComboBox(cliente.getNickname());
                                new VentanaMensaje("Imagen actualizada correctamente.");
                            } catch (IllegalArgumentException | IOException ex) {
                                new VentanaMensaje(ex.getMessage());
                            }
                        } else {
                            new VentanaMensaje("Se ha cancelado la operación");
                        }
                    }

                    ;
                });
            }
        });

        subirImagenImagenAerolineaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagenTemporalUsuario = null;
                SubirImagen ventanaImagen = new SubirImagen(TipoImagen.USUARIO);
                setEnabled(false);

                ventanaImagen.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setEnabled(true);
                        imagenTemporalUsuario = ventanaImagen.getImagen();
                        if (imagenTemporalUsuario != null) {
                            selectedFileAerolineaRegistrar.setText(imagenTemporalUsuario.getName());
                        } else {
                            selectedFileAerolineaRegistrar.setText("No se ha seleccionado archivo.");
                        }
                    }

                    ;
                });
            }
        });

        modificarAerolineaImagenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagenTemporalUsuario = null;
                SubirImagen ventanaImagen = new SubirImagen(TipoImagen.USUARIO);
                setEnabled(false);

                ventanaImagen.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setEnabled(true);
                        imagenTemporalUsuario = ventanaImagen.getImagen();


                        if (imagenTemporalUsuario != null) {
                            try {
                                DtAerolinea aerolinea = s.getAerolinea(jComboBoxSeleccionarUsuarioModificar.getSelectedItem().toString());

                                File imagen = AuxiliarFunctions.guardarImagen(imagenTemporalUsuario, TipoImagen.USUARIO);
                                s.modificarUsuarioImagen(aerolinea, imagen.getName());
                                auxiliar.cargarUsuariosComboBox(aerolinea.getNickname());
                                new VentanaMensaje("Imagen actualizada correctamente.");
                            } catch (IllegalArgumentException | IOException ex) {
                                new VentanaMensaje(ex.getMessage());
                            }
                        } else {
                            new VentanaMensaje("Se ha cancelado la operación");
                        }
                    }

                    ;
                });
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagenTemporalVuelo = null;
                SubirImagen ventanaImagen = new SubirImagen(TipoImagen.VUELO);
                setEnabled(false);

                ventanaImagen.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setEnabled(true);
                        imagenTemporalVuelo = ventanaImagen.getImagen();
                        if (imagenTemporalVuelo != null) {
                            selectedFileVueloCrear.setText(imagenTemporalVuelo.getName());
                        } else {
                            selectedFileVueloCrear.setText("No se ha seleccionado archivo.");
                        }
                    }

                    ;
                });
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagenTemporalRuta = null;
                SubirImagen ventanaImagen = new SubirImagen(TipoImagen.RUTA);
                setEnabled(false);

                ventanaImagen.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setEnabled(true);
                        imagenTemporalRuta = ventanaImagen.getImagen();
                        if (imagenTemporalRuta != null) {
                            selectedFileRutaCrear.setText(imagenTemporalRuta.getName());
                        } else {
                            selectedFileRutaCrear.setText("No se ha seleccionado archivo.");
                        }
                    }

                    ;
                });
            }
        });
        seleccionarRutaAceptarRechazarRuta.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    DtRuta rutaSeleccionada = (DtRuta) seleccionarRutaAceptarRechazarRuta.getSelectedItem();

                    if (rutaSeleccionada == null) {
                        new VentanaMensaje("Ha ocurrido un error..");
                        return;
                    }

                    if(rutaSeleccionada.toString().equals("N/A")){
                        aprobarButtonAceptarRechazarRuta.setEnabled(false);
                        rechazarButtonAceptarRechazarRuta.setEnabled(false);
                        estadoRutaText.setText("No se ha seleccionado una ruta.");
                        return;
                    }

                    DtRuta ruta = s.getRutaDeVuelo(rutaSeleccionada.toString());
                    if (ruta != null && ruta.getEstado().toString().equals("INGRESADA")) {
                        aprobarButtonAceptarRechazarRuta.setEnabled(true);
                        rechazarButtonAceptarRechazarRuta.setEnabled(true);
                        estadoRutaText.setText(ruta.getEstado().toString());
                    } else {
                        aprobarButtonAceptarRechazarRuta.setEnabled(false);
                        rechazarButtonAceptarRechazarRuta.setEnabled(false);
                        estadoRutaText.setText(ruta.getEstado().toString());
                    }
                }
            }
        });
        aprobarButtonAceptarRechazarRuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auxiliar.estanVaciosJComboBox(seleccionarAerolineaAceptarRechazarRuta, seleccionarRutaAceptarRechazarRuta)) {
                    new VentanaMensaje("Debe seleccionar una aerolínea y una ruta para realizar esta acción");
                    return;
                }

                try {
                    DtRuta ruta = s.getRutaDeVuelo(seleccionarRutaAceptarRechazarRuta.getSelectedItem().toString());
                    DtAerolinea aerolinea = s.getAerolinea(seleccionarAerolineaAceptarRechazarRuta.getSelectedItem().toString());

                    s.actualizarEstadoRuta(ruta, EstadoRuta.APROBADA);
                    auxiliar.cargarRutasDeVueloComboBoxAerolinea(aerolinea.getNickname(), ruta.getNombre());
                } catch (IllegalArgumentException ex) {
                    new VentanaMensaje(ex.getMessage());
                }
            }
        });
        rechazarButtonAceptarRechazarRuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (auxiliar.estanVaciosJComboBox(seleccionarAerolineaAceptarRechazarRuta, seleccionarRutaAceptarRechazarRuta)) {
                    new VentanaMensaje("Debe seleccionar una aerolínea y una ruta para realizar esta acción");
                    return;
                }

                try {
                    DtRuta ruta = s.getRutaDeVuelo(seleccionarRutaAceptarRechazarRuta.getSelectedItem().toString());
                    DtAerolinea aerolinea = s.getAerolinea(seleccionarAerolineaAceptarRechazarRuta.getSelectedItem().toString());
                    s.actualizarEstadoRuta(ruta, EstadoRuta.RECHAZADA);
                    auxiliar.cargarRutasDeVueloComboBoxAerolinea(aerolinea.getNickname(), ruta.getNombre());
                } catch (IllegalArgumentException ex) {
                    new VentanaMensaje(ex.getMessage());
                }
            }
        });
        tabbedPane4.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = tabbedPane4.getSelectedIndex();
                String title = tabbedPane4.getTitleAt(index);

                if(title.equals("Ingresar")){
                    auxiliar.cargarAerolineasComboBox();
                    cargarCategorias();
                }
                if(title.equals("Aceptar/Rechazar") || title.equals("Consultar")){
                    auxiliar.cargarAerolineasComboBox();
                }

                if (title.equals("Top5")) {
                    jPanelTop5Rutas.removeAll();
                    jPanelTop5Rutas.setLayout(new GridLayout(0, 1, 5, 5)); // una columna, filas dinámicas

                    for(DtRuta r : s.listarRutasDeVueloTop5()){
                        JLabel label = new JLabel(
                                r.getNombre() + " | Visitada: " + r.getVecesVisitada() + " veces"
                        );
                        jPanelTop5Rutas.add(label);
                    };

                    jPanelTop5Rutas.revalidate();
                    jPanelTop5Rutas.repaint();
                }
            }
        });
        tabbedPane1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = tabbedPane1.getSelectedIndex();
                String title = tabbedPane1.getTitleAt(index);

                if (title.equals("Consultar") || title.equals("Modificar")) {
                    auxiliar.cargarUsuariosComboBox();
                }
            }
        });
        tabbedPane3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = tabbedPane3.getSelectedIndex();
                String title = tabbedPane3.getTitleAt(index);

                if (title.equals("Comprar")) {
                    auxiliar.cargarPaqueteNoCompradoComboBox();
                    auxiliar.cargarClientesComboBox();
                }
                if(title.equals("Consultar")){
                    auxiliar.cargarPaqueteComboBox();
                }
                if(title.equals("Agregar ruta")){
                    auxiliar.cargarPaqueteNoCompradoComboBox();
                    auxiliar.cargarAerolineasComboBox();
                }
            }
        });
        tabbedPane2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = tabbedPane2.getSelectedIndex();
                String title = tabbedPane2.getTitleAt(index);

                if (title.equals("Crear") || title.equals("Consulta")) {
                    auxiliar.cargarAerolineasComboBox();
                }
                if(title.equals("Reservar")){
                    auxiliar.cargarAerolineasComboBox();
                    auxiliar.cargarClientesComboBox();
                }
            }
        });
    }

    public void cargarCategorias() {
        auxiliar.cargarCategorias();

        jPanelCategorias.removeAll();
        jPanelCategorias.setLayout(new GridBagLayout());

        JScrollPane scroll = new JScrollPane(listaCategorias);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipady = 50;

        jPanelCategorias.add(scroll, gbc);

        jPanelCategorias.revalidate();
        jPanelCategorias.repaint();
    }


    public static void main(String[] args) {
        s = Factory.getSistema();
        Publicador.publicar();
        SwingUtilities.invokeLater(() -> new Main());
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        menuPrincipal = new JPanel();
        menuPrincipal.setLayout(new GridBagLayout());
        menuPrincipal.setAlignmentX(0.0f);
        menuPrincipal.setAlignmentY(0.0f);
        menuPrincipal.setAutoscrolls(true);
        gestionUsuarios = new JTabbedPane();
        gestionUsuarios.setTabPlacement(2);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        menuPrincipal.add(gestionUsuarios, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        gestionUsuarios.addTab("Usuarios", panel1);
        tabbedPane1 = new JTabbedPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(tabbedPane1, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        tabbedPane1.addTab("Registrar", panel2);
        final JLabel label1 = new JLabel();
        label1.setText("Registrar Usuario");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(20, 0, 0, 20);
        panel2.add(label1, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 20, 20, 50);
        panel2.add(panel3, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Seleccionar Tipo");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(label2, gbc);
        jPanelRegistrarAerolinea = new JPanel();
        jPanelRegistrarAerolinea.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(jPanelRegistrarAerolinea, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Nickname:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarAerolinea.add(label3, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Nombre:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarAerolinea.add(label4, gbc);
        nombreRegistrarAerolinea = new JTextField();
        nombreRegistrarAerolinea.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelRegistrarAerolinea.add(nombreRegistrarAerolinea, gbc);
        nicknameRegistrarAerolinea = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelRegistrarAerolinea.add(nicknameRegistrarAerolinea, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("Correo electronico:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarAerolinea.add(label5, gbc);
        correoRegistrarAerolinea = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelRegistrarAerolinea.add(correoRegistrarAerolinea, gbc);
        descripcionRegistrarAerolinea = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelRegistrarAerolinea.add(descripcionRegistrarAerolinea, gbc);
        final JLabel label6 = new JLabel();
        label6.setText("Sitio web (link):");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarAerolinea.add(label6, gbc);
        sitioWebRegistrarAerolinea = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelRegistrarAerolinea.add(sitioWebRegistrarAerolinea, gbc);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 0, 0);
        jPanelRegistrarAerolinea.add(panel4, gbc);
        cancelarAltaAerolinea = new JButton();
        cancelarAltaAerolinea.setText("CANCELAR");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(cancelarAltaAerolinea, gbc);
        confirmarAltaAerolinea = new JButton();
        confirmarAltaAerolinea.setText("CONFIRMAR");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(confirmarAltaAerolinea, gbc);
        final JLabel label7 = new JLabel();
        label7.setText("Descripcion:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarAerolinea.add(label7, gbc);
        final JLabel label8 = new JLabel();
        label8.setText("Subir Imagen:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarAerolinea.add(label8, gbc);
        aerolineaSubirImagenPanel = new JPanel();
        aerolineaSubirImagenPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        jPanelRegistrarAerolinea.add(aerolineaSubirImagenPanel, gbc);
        subirImagenImagenAerolineaButton = new JButton();
        subirImagenImagenAerolineaButton.setText("⇪");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        aerolineaSubirImagenPanel.add(subirImagenImagenAerolineaButton, gbc);
        final JLabel label9 = new JLabel();
        label9.setText("Contraseña:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarAerolinea.add(label9, gbc);
        final JLabel label10 = new JLabel();
        label10.setText("Repetir Contraseña:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarAerolinea.add(label10, gbc);
        registrarAerolineaContrasena = new JPasswordField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelRegistrarAerolinea.add(registrarAerolineaContrasena, gbc);
        registrarAerolineaContrasena2 = new JPasswordField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelRegistrarAerolinea.add(registrarAerolineaContrasena2, gbc);
        final JLabel label11 = new JLabel();
        label11.setText("Archivo Seleccionado:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarAerolinea.add(label11, gbc);
        selectedFileAerolineaRegistrar = new JLabel();
        selectedFileAerolineaRegistrar.setText("No se ha seleccionado archivo.");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarAerolinea.add(selectedFileAerolineaRegistrar, gbc);
        jPanelRegistrarCliente = new JPanel();
        jPanelRegistrarCliente.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(jPanelRegistrarCliente, gbc);
        final JLabel label12 = new JLabel();
        label12.setText("Nickname:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarCliente.add(label12, gbc);
        final JLabel label13 = new JLabel();
        label13.setText("Nombre:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarCliente.add(label13, gbc);
        nombreRegistrarCliente = new JTextField();
        nombreRegistrarCliente.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelRegistrarCliente.add(nombreRegistrarCliente, gbc);
        final JLabel label14 = new JLabel();
        label14.setText("Tipo de documento:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarCliente.add(label14, gbc);
        final JLabel label15 = new JLabel();
        label15.setText("Fecha de nacimiento:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarCliente.add(label15, gbc);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelRegistrarCliente.add(panel5, gbc);
        fechaMesRegistrarCliente = new JSpinner();
        fechaMesRegistrarCliente.setName("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(fechaMesRegistrarCliente, gbc);
        fechaAnioRegistrarCliente = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(fechaAnioRegistrarCliente, gbc);
        fechaDiaRegistrarCliente = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(fechaDiaRegistrarCliente, gbc);
        nicknameRegistrarCliente = new JTextField();
        nicknameRegistrarCliente.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelRegistrarCliente.add(nicknameRegistrarCliente, gbc);
        final JLabel label16 = new JLabel();
        label16.setText("Correo electronico:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarCliente.add(label16, gbc);
        correoElectronicoRegistrarCliente = new JTextField();
        correoElectronicoRegistrarCliente.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelRegistrarCliente.add(correoElectronicoRegistrarCliente, gbc);
        tipoDocumentoRegistrarCliente = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Cedula");
        defaultComboBoxModel1.addElement("Pasaporte");
        tipoDocumentoRegistrarCliente.setModel(defaultComboBoxModel1);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelRegistrarCliente.add(tipoDocumentoRegistrarCliente, gbc);
        documentoRegistrarCliente = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelRegistrarCliente.add(documentoRegistrarCliente, gbc);
        final JLabel label17 = new JLabel();
        label17.setText("Apellido:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarCliente.add(label17, gbc);
        final JLabel label18 = new JLabel();
        label18.setText("Nacionalidad:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarCliente.add(label18, gbc);
        nacionalidadRegistrarCliente = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelRegistrarCliente.add(nacionalidadRegistrarCliente, gbc);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 12;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 0, 0);
        jPanelRegistrarCliente.add(panel6, gbc);
        cancelarAltaCliente = new JButton();
        cancelarAltaCliente.setText("CANCELAR");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(cancelarAltaCliente, gbc);
        confirmarAltaCliente = new JButton();
        confirmarAltaCliente.setText("CONFIRMAR");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(confirmarAltaCliente, gbc);
        apellidoRegistrarCliente = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelRegistrarCliente.add(apellidoRegistrarCliente, gbc);
        clienteSubirImagePanel = new JPanel();
        clienteSubirImagePanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 11;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        jPanelRegistrarCliente.add(clienteSubirImagePanel, gbc);
        subirImagenButton = new JButton();
        subirImagenButton.setText("⇪");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        clienteSubirImagePanel.add(subirImagenButton, gbc);
        final JLabel label19 = new JLabel();
        label19.setText("Subir Imagen:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarCliente.add(label19, gbc);
        registrarContrasenaCliente = new JPasswordField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelRegistrarCliente.add(registrarContrasenaCliente, gbc);
        final JLabel label20 = new JLabel();
        label20.setText("Contraseña:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarCliente.add(label20, gbc);
        registrarContrasenaCliente2 = new JPasswordField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelRegistrarCliente.add(registrarContrasenaCliente2, gbc);
        final JLabel label21 = new JLabel();
        label21.setText("Repetir contraseña:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarCliente.add(label21, gbc);
        final JLabel label22 = new JLabel();
        label22.setText("Archivo Seleccionado:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarCliente.add(label22, gbc);
        selectedFileClienteRegistrar = new JLabel();
        selectedFileClienteRegistrar.setText("No se ha seleccionado archivo.");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelRegistrarCliente.add(selectedFileClienteRegistrar, gbc);
        userType = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Cliente");
        defaultComboBoxModel2.addElement("Aerolinea");
        userType.setModel(defaultComboBoxModel2);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(userType, gbc);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridBagLayout());
        panel7.setAlignmentX(0.0f);
        panel7.setAlignmentY(0.0f);
        tabbedPane1.addTab("Consultar", panel7);
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(50, 20, 20, 50);
        panel7.add(panel8, gbc);
        final JLabel label23 = new JLabel();
        label23.setText("Seleccionar usuario");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel8.add(label23, gbc);
        jComboBoxSeleccionarUsuarioConsultar = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        jComboBoxSeleccionarUsuarioConsultar.setModel(defaultComboBoxModel3);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel8.add(jComboBoxSeleccionarUsuarioConsultar, gbc);
        consultarUsuarioButton = new JButton();
        consultarUsuarioButton.setText("Consultar Usuario");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel8.add(consultarUsuarioButton, gbc);
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridBagLayout());
        tabbedPane1.addTab("Modificar", panel9);
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(50, 20, 20, 50);
        panel9.add(panel10, gbc);
        final JLabel label24 = new JLabel();
        label24.setText("Seleccionar usuario");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel10.add(label24, gbc);
        jComboBoxSeleccionarUsuarioModificar = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel4 = new DefaultComboBoxModel();
        jComboBoxSeleccionarUsuarioModificar.setModel(defaultComboBoxModel4);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel10.add(jComboBoxSeleccionarUsuarioModificar, gbc);
        jPanelModificarAerolinea = new JPanel();
        jPanelModificarAerolinea.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel10.add(jPanelModificarAerolinea, gbc);
        final JLabel label25 = new JLabel();
        label25.setText("Nickname:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelModificarAerolinea.add(label25, gbc);
        final JLabel label26 = new JLabel();
        label26.setText("Nombre:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelModificarAerolinea.add(label26, gbc);
        nombreModificarAerolinea = new JTextField();
        nombreModificarAerolinea.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelModificarAerolinea.add(nombreModificarAerolinea, gbc);
        nicknameModificarAerolinea = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelModificarAerolinea.add(nicknameModificarAerolinea, gbc);
        final JLabel label27 = new JLabel();
        label27.setText("Correo electronico:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelModificarAerolinea.add(label27, gbc);
        correoModificarAerolinea = new JTextField();
        correoModificarAerolinea.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelModificarAerolinea.add(correoModificarAerolinea, gbc);
        descripcionModificarAerolinea = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelModificarAerolinea.add(descripcionModificarAerolinea, gbc);
        final JLabel label28 = new JLabel();
        label28.setText("Sitio web (link):");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelModificarAerolinea.add(label28, gbc);
        linkWebModificarAerolinea = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelModificarAerolinea.add(linkWebModificarAerolinea, gbc);
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 0, 0);
        jPanelModificarAerolinea.add(panel11, gbc);
        final JButton button3 = new JButton();
        button3.setText("CANCELAR");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel11.add(button3, gbc);
        jButtonModificarAerolinea = new JButton();
        jButtonModificarAerolinea.setText("CONFIRMAR");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel11.add(jButtonModificarAerolinea, gbc);
        final JLabel label29 = new JLabel();
        label29.setText("Descripcion:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelModificarAerolinea.add(label29, gbc);
        final JLabel label30 = new JLabel();
        label30.setText("Imagen de Perfil:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelModificarAerolinea.add(label30, gbc);
        modificarAerolineaImagenPanel = new JPanel();
        modificarAerolineaImagenPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        jPanelModificarAerolinea.add(modificarAerolineaImagenPanel, gbc);
        modificarAerolineaImagenButton = new JButton();
        modificarAerolineaImagenButton.setText("↺");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelModificarAerolinea.add(modificarAerolineaImagenButton, gbc);
        jPanelModificarCliente = new JPanel();
        jPanelModificarCliente.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel10.add(jPanelModificarCliente, gbc);
        final JLabel label31 = new JLabel();
        label31.setText("Nickname:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelModificarCliente.add(label31, gbc);
        final JLabel label32 = new JLabel();
        label32.setText("Nombre:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelModificarCliente.add(label32, gbc);
        nombreClienteModificar = new JTextField();
        nombreClienteModificar.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelModificarCliente.add(nombreClienteModificar, gbc);
        final JLabel label33 = new JLabel();
        label33.setText("Tipo de documento:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelModificarCliente.add(label33, gbc);
        final JLabel label34 = new JLabel();
        label34.setText("Fecha de nacimiento:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelModificarCliente.add(label34, gbc);
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelModificarCliente.add(panel12, gbc);
        fechaMesClienteModificar = new JSpinner();
        fechaMesClienteModificar.setName("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel12.add(fechaMesClienteModificar, gbc);
        fechaAnioClienteModificar = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel12.add(fechaAnioClienteModificar, gbc);
        fechaDiaClienteModificar = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel12.add(fechaDiaClienteModificar, gbc);
        nicknameModificarCliente = new JTextField();
        nicknameModificarCliente.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelModificarCliente.add(nicknameModificarCliente, gbc);
        correoClienteModificar = new JTextField();
        correoClienteModificar.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelModificarCliente.add(correoClienteModificar, gbc);
        tipoDocumentoClienteModificar = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel5 = new DefaultComboBoxModel();
        defaultComboBoxModel5.addElement("Cedula");
        defaultComboBoxModel5.addElement("Pasaporte");
        tipoDocumentoClienteModificar.setModel(defaultComboBoxModel5);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelModificarCliente.add(tipoDocumentoClienteModificar, gbc);
        final JLabel label35 = new JLabel();
        label35.setText("Nacionalidad:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelModificarCliente.add(label35, gbc);
        nacionalidadClienteModificar = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelModificarCliente.add(nacionalidadClienteModificar, gbc);
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 0, 0);
        jPanelModificarCliente.add(panel13, gbc);
        final JButton button4 = new JButton();
        button4.setText("CANCELAR");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel13.add(button4, gbc);
        confirmarModificarCliente = new JButton();
        confirmarModificarCliente.setText("CONFIRMAR");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel13.add(confirmarModificarCliente, gbc);
        final JLabel label36 = new JLabel();
        label36.setText("Apellido:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelModificarCliente.add(label36, gbc);
        apellidoClienteModificar = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelModificarCliente.add(apellidoClienteModificar, gbc);
        final JLabel label37 = new JLabel();
        label37.setText("Correo electronico:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelModificarCliente.add(label37, gbc);
        numeroDocumentoModificarCliente = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelModificarCliente.add(numeroDocumentoModificarCliente, gbc);
        final JLabel label38 = new JLabel();
        label38.setText("Imagen de Perfil:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        jPanelModificarCliente.add(label38, gbc);
        modificarClienteImagenPanel = new JPanel();
        modificarClienteImagenPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        jPanelModificarCliente.add(modificarClienteImagenPanel, gbc);
        modificarClienteImagen = new JButton();
        modificarClienteImagen.setText("↺");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanelModificarCliente.add(modificarClienteImagen, gbc);
        final JPanel panel14 = new JPanel();
        panel14.setLayout(new GridBagLayout());
        gestionUsuarios.addTab("Categoria", panel14);
        final JPanel panel15 = new JPanel();
        panel15.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(50, 20, 20, 50);
        panel14.add(panel15, gbc);
        final JLabel label39 = new JLabel();
        label39.setText("Nombre:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel15.add(label39, gbc);
        final JPanel panel16 = new JPanel();
        panel16.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel15.add(panel16, gbc);
        cancelarAltaCategori = new JButton();
        cancelarAltaCategori.setText("CANCELAR");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel16.add(cancelarAltaCategori, gbc);
        confirmarAltaCategoria = new JButton();
        confirmarAltaCategoria.setText("CONFIRMAR");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel16.add(confirmarAltaCategoria, gbc);
        final JLabel label40 = new JLabel();
        label40.setText("AGREGAR CATEGORÍA");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panel15.add(label40, gbc);
        nombreAltaCategoria = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel15.add(nombreAltaCategoria, gbc);
        final JPanel panel17 = new JPanel();
        panel17.setLayout(new GridBagLayout());
        gestionUsuarios.addTab("Paquetes", panel17);
        tabbedPane3 = new JTabbedPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel17.add(tabbedPane3, gbc);
        final JPanel panel18 = new JPanel();
        panel18.setLayout(new GridBagLayout());
        tabbedPane3.addTab("Crear", panel18);
        final JPanel panel19 = new JPanel();
        panel19.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(50, 20, 20, 50);
        panel18.add(panel19, gbc);
        nombreAltaPaquete = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel19.add(nombreAltaPaquete, gbc);
        jSpinnerDescuentoAltaPaquete = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel19.add(jSpinnerDescuentoAltaPaquete, gbc);
        final JLabel label41 = new JLabel();
        label41.setText("Nombre : ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel19.add(label41, gbc);
        final JLabel label42 = new JLabel();
        label42.setText("Descripcion :");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel19.add(label42, gbc);
        descripcionAltaPaquete = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel19.add(descripcionAltaPaquete, gbc);
        final JLabel label43 = new JLabel();
        label43.setText("Descuento :");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel19.add(label43, gbc);
        final JLabel label44 = new JLabel();
        label44.setText("Período (dias) :");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel19.add(label44, gbc);
        jSpinnerPeriodoAltaPaquete = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel19.add(jSpinnerPeriodoAltaPaquete, gbc);
        final JPanel panel20 = new JPanel();
        panel20.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel19.add(panel20, gbc);
        cANCELARButton = new JButton();
        cANCELARButton.setText("CANCELAR");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel20.add(cANCELARButton, gbc);
        cONFIRMARButton = new JButton();
        cONFIRMARButton.setText("CONFIRMAR");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel20.add(cONFIRMARButton, gbc);
        final JPanel panel21 = new JPanel();
        panel21.setLayout(new GridBagLayout());
        tabbedPane3.addTab("Comprar", panel21);
        final JPanel panel22 = new JPanel();
        panel22.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(50, 20, 20, 50);
        panel21.add(panel22, gbc);
        final JLabel label45 = new JLabel();
        label45.setText("Paquetes:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel22.add(label45, gbc);
        jComboBoxPaqueteComprarPaquete = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel22.add(jComboBoxPaqueteComprarPaquete, gbc);
        final JLabel label46 = new JLabel();
        label46.setText("Clientes:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel22.add(label46, gbc);
        jComboBoxClienteCompraPaquete = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel22.add(jComboBoxClienteCompraPaquete, gbc);
        final JPanel panel23 = new JPanel();
        panel23.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel22.add(panel23, gbc);
        cANCELARButton1 = new JButton();
        cANCELARButton1.setText("CANCELAR");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel23.add(cANCELARButton1, gbc);
        buttonConfirmarCompraPaquete = new JButton();
        buttonConfirmarCompraPaquete.setText("CONFIRMAR");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel23.add(buttonConfirmarCompraPaquete, gbc);
        final JPanel panel24 = new JPanel();
        panel24.setLayout(new GridBagLayout());
        tabbedPane3.addTab("Consultar", panel24);
        final JPanel panel25 = new JPanel();
        panel25.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(50, 20, 20, 50);
        panel24.add(panel25, gbc);
        final JLabel label47 = new JLabel();
        label47.setText("Paquetes:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel25.add(label47, gbc);
        jComboBoxPaqueteConsultaPaqueteRutaVuelo = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel25.add(jComboBoxPaqueteConsultaPaqueteRutaVuelo, gbc);
        jButtonConsultarPaquete = new JButton();
        jButtonConsultarPaquete.setText("CONSULTAR");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel25.add(jButtonConsultarPaquete, gbc);
        final JPanel panel26 = new JPanel();
        panel26.setLayout(new GridBagLayout());
        tabbedPane3.addTab("Agregar ruta", panel26);
        final JPanel panel27 = new JPanel();
        panel27.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(50, 20, 20, 50);
        panel26.add(panel27, gbc);
        jComboBoxRutaVueloAgregarRuta = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel27.add(jComboBoxRutaVueloAgregarRuta, gbc);
        final JLabel label48 = new JLabel();
        label48.setText("Ruta de vuelo:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel27.add(label48, gbc);
        buttonConfirmarAgregarRutaPaquete = new JButton();
        buttonConfirmarAgregarRutaPaquete.setText("CONFIRMAR");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel27.add(buttonConfirmarAgregarRutaPaquete, gbc);
        final JLabel label49 = new JLabel();
        label49.setText("Cantidad:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel27.add(label49, gbc);
        jSpinnerCantidadAgregarRuta = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel27.add(jSpinnerCantidadAgregarRuta, gbc);
        final JLabel label50 = new JLabel();
        label50.setText("Tipo de asiento:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel27.add(label50, gbc);
        jComboBoxTipoAsientoAgregarRutaPaquete = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel6 = new DefaultComboBoxModel();
        defaultComboBoxModel6.addElement("EJECUTIVO");
        defaultComboBoxModel6.addElement("TURISTA");
        jComboBoxTipoAsientoAgregarRutaPaquete.setModel(defaultComboBoxModel6);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel27.add(jComboBoxTipoAsientoAgregarRutaPaquete, gbc);
        final JLabel label51 = new JLabel();
        label51.setText("Paquete:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel27.add(label51, gbc);
        jComboBoxPaqueteAgregarRuta = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel27.add(jComboBoxPaqueteAgregarRuta, gbc);
        jComboBoxAerolineaAgregarRuta = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel27.add(jComboBoxAerolineaAgregarRuta, gbc);
        final JLabel label52 = new JLabel();
        label52.setText("Aerolinea:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel27.add(label52, gbc);
        final JPanel panel28 = new JPanel();
        panel28.setLayout(new GridBagLayout());
        gestionUsuarios.addTab("Vuelos", panel28);
        tabbedPane2 = new JTabbedPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel28.add(tabbedPane2, gbc);
        final JPanel panel29 = new JPanel();
        panel29.setLayout(new GridBagLayout());
        tabbedPane2.addTab("Crear", panel29);
        formVuelo = new JPanel();
        formVuelo.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(50, 20, 20, 50);
        panel29.add(formVuelo, gbc);
        final JLabel label53 = new JLabel();
        label53.setText("Seleccionar Aerolínea");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        formVuelo.add(label53, gbc);
        jComboBoxAerolineaAltaVuelo = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formVuelo.add(jComboBoxAerolineaAltaVuelo, gbc);
        final JLabel label54 = new JLabel();
        label54.setText("Seleccionar Ruta de Vuelo");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formVuelo.add(label54, gbc);
        final JLabel label55 = new JLabel();
        label55.setText("Nombre:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        formVuelo.add(label55, gbc);
        final JLabel label56 = new JLabel();
        label56.setText("Max. Turistas:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        formVuelo.add(label56, gbc);
        final JLabel label57 = new JLabel();
        label57.setText("Max. Ejecutivos:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        formVuelo.add(label57, gbc);
        jSpinnerEjecutivosAltaVuelo = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formVuelo.add(jSpinnerEjecutivosAltaVuelo, gbc);
        jSpinnerTuristasAltaVuelo = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formVuelo.add(jSpinnerTuristasAltaVuelo, gbc);
        nombreAltaVuelo = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formVuelo.add(nombreAltaVuelo, gbc);
        jComboBoxRutaVueloAltaVuelo = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formVuelo.add(jComboBoxRutaVueloAltaVuelo, gbc);
        final JLabel label58 = new JLabel();
        label58.setText("Fecha:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        formVuelo.add(label58, gbc);
        fechaField = new JPanel();
        fechaField.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formVuelo.add(fechaField, gbc);
        jSpinnerMesAltaVuelo = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        fechaField.add(jSpinnerMesAltaVuelo, gbc);
        jSpinnerAnioAltaVuelo = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        fechaField.add(jSpinnerAnioAltaVuelo, gbc);
        jSpinnerDiaAltaVuelo = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        fechaField.add(jSpinnerDiaAltaVuelo, gbc);
        button1 = new JButton();
        button1.setText("⇪");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formVuelo.add(button1, gbc);
        final JLabel label59 = new JLabel();
        label59.setText("Subir Imagen:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        formVuelo.add(label59, gbc);
        final JLabel label60 = new JLabel();
        label60.setText("Archivo Seleccionado:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        formVuelo.add(label60, gbc);
        final JPanel panel30 = new JPanel();
        panel30.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 0, 0);
        formVuelo.add(panel30, gbc);
        crearVueloButton = new JButton();
        crearVueloButton.setText("Crear Vuelo");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel30.add(crearVueloButton, gbc);
        selectedFileVueloCrear = new JLabel();
        selectedFileVueloCrear.setText("No se ha seleccionado Archivo");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 8;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        formVuelo.add(selectedFileVueloCrear, gbc);
        final JLabel label61 = new JLabel();
        label61.setText("Duración (hora, minutos): ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        formVuelo.add(label61, gbc);
        final JPanel panel31 = new JPanel();
        panel31.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        formVuelo.add(panel31, gbc);
        jSpinnerDuracionAltaVueloHora = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel31.add(jSpinnerDuracionAltaVueloHora, gbc);
        jSpinnerDuracionAltaVueloMinuto = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel31.add(jSpinnerDuracionAltaVueloMinuto, gbc);
        final JPanel panel32 = new JPanel();
        panel32.setLayout(new GridBagLayout());
        tabbedPane2.addTab("Consulta", panel32);
        vueloPanel = new JPanel();
        vueloPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(50, 20, 20, 50);
        panel32.add(vueloPanel, gbc);
        final JLabel label62 = new JLabel();
        label62.setText("Seleccionar Aerolínea");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        vueloPanel.add(label62, gbc);
        jComboBoxConsultaVueloAerolinea = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        vueloPanel.add(jComboBoxConsultaVueloAerolinea, gbc);
        final JLabel label63 = new JLabel();
        label63.setText("Seleccionar Ruta de Vuelo");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        vueloPanel.add(label63, gbc);
        final JLabel label64 = new JLabel();
        label64.setText("Seleccionar Vuelo");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        vueloPanel.add(label64, gbc);
        jComboBoxConsultaVueloRutaDeVuelo = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        vueloPanel.add(jComboBoxConsultaVueloRutaDeVuelo, gbc);
        jComboBoxConsultaVueloVuelo = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        vueloPanel.add(jComboBoxConsultaVueloVuelo, gbc);
        consultarVueloButton = new JButton();
        consultarVueloButton.setText("Consultar Vuelo");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        vueloPanel.add(consultarVueloButton, gbc);
        final JPanel panel33 = new JPanel();
        panel33.setLayout(new GridBagLayout());
        panel33.setEnabled(true);
        tabbedPane2.addTab("Reservar", panel33);
        reservaPanel = new JPanel();
        reservaPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(50, 20, 20, 50);
        panel33.add(reservaPanel, gbc);
        final JLabel label65 = new JLabel();
        label65.setText("Seleccionar Aerolinea");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        reservaPanel.add(label65, gbc);
        jComboBoxseleccionarAerolineaReserva = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel7 = new DefaultComboBoxModel();
        jComboBoxseleccionarAerolineaReserva.setModel(defaultComboBoxModel7);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        reservaPanel.add(jComboBoxseleccionarAerolineaReserva, gbc);
        final JLabel label66 = new JLabel();
        label66.setText("Seleccionar Ruta de Vuelo");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        reservaPanel.add(label66, gbc);
        final JLabel label67 = new JLabel();
        label67.setText("Seleccionar Vuelo");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        reservaPanel.add(label67, gbc);
        jComboBoxrutaDeVueloReserva = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel8 = new DefaultComboBoxModel();
        jComboBoxrutaDeVueloReserva.setModel(defaultComboBoxModel8);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        reservaPanel.add(jComboBoxrutaDeVueloReserva, gbc);
        jComboBoxvueloReserva = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel9 = new DefaultComboBoxModel();
        jComboBoxvueloReserva.setModel(defaultComboBoxModel9);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        reservaPanel.add(jComboBoxvueloReserva, gbc);
        jButtonVerVueloReservaButton = new JButton();
        jButtonVerVueloReservaButton.setText("Ver Vuelo");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        reservaPanel.add(jButtonVerVueloReservaButton, gbc);
        final JLabel label68 = new JLabel();
        label68.setText("Seleccionar Cliente");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        reservaPanel.add(label68, gbc);
        jComboBoxSeleccionarClienteReserva = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel10 = new DefaultComboBoxModel();
        jComboBoxSeleccionarClienteReserva.setModel(defaultComboBoxModel10);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        reservaPanel.add(jComboBoxSeleccionarClienteReserva, gbc);
        final JLabel label69 = new JLabel();
        label69.setText("Tipo Asiento");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        reservaPanel.add(label69, gbc);
        jComboBoxtipoAsientoReserva = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel11 = new DefaultComboBoxModel();
        defaultComboBoxModel11.addElement("Turista");
        defaultComboBoxModel11.addElement("Ejecutivo");
        jComboBoxtipoAsientoReserva.setModel(defaultComboBoxModel11);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        reservaPanel.add(jComboBoxtipoAsientoReserva, gbc);
        final JLabel label70 = new JLabel();
        label70.setText("Cantidad de Pasajes");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        reservaPanel.add(label70, gbc);
        jSpinnerCantPasajesReserva = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        reservaPanel.add(jSpinnerCantPasajesReserva, gbc);
        final JLabel label71 = new JLabel();
        label71.setText("Cantidad de Equipaje Extra");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        reservaPanel.add(label71, gbc);
        jSpinnerCantEquipajeExtraReserva = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        reservaPanel.add(jSpinnerCantEquipajeExtraReserva, gbc);
        hacerReservaButton = new JButton();
        hacerReservaButton.setText("Hacer Reserva");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        reservaPanel.add(hacerReservaButton, gbc);
        pagoGeneralRadioButton = new JRadioButton();
        pagoGeneralRadioButton.setText("Pago general");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        reservaPanel.add(pagoGeneralRadioButton, gbc);
        pagoConPaqueteRadioButton = new JRadioButton();
        pagoConPaqueteRadioButton.setText("Pago con paquete");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        reservaPanel.add(pagoConPaqueteRadioButton, gbc);
        jComboBoxSeleccionarPaqueteReserva = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel12 = new DefaultComboBoxModel();
        jComboBoxSeleccionarPaqueteReserva.setModel(defaultComboBoxModel12);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        reservaPanel.add(jComboBoxSeleccionarPaqueteReserva, gbc);
        final JLabel label72 = new JLabel();
        label72.setText("Seleccionar Paquete");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        reservaPanel.add(label72, gbc);
        final JPanel panel34 = new JPanel();
        panel34.setLayout(new GridBagLayout());
        gestionUsuarios.addTab("Rutas", panel34);
        tabbedPane4 = new JTabbedPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel34.add(tabbedPane4, gbc);
        final JPanel panel35 = new JPanel();
        panel35.setLayout(new GridBagLayout());
        tabbedPane4.addTab("Ingresar", panel35);
        final JPanel panel36 = new JPanel();
        panel36.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(50, 20, 20, 50);
        panel35.add(panel36, gbc);
        final JLabel label73 = new JLabel();
        label73.setText("Aerolínea:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel36.add(label73, gbc);
        final JLabel label74 = new JLabel();
        label74.setText("Nombre:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel36.add(label74, gbc);
        final JLabel label75 = new JLabel();
        label75.setText("Ciudad Origen:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        panel36.add(label75, gbc);
        final JLabel label76 = new JLabel();
        label76.setText("Ciudad destino:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        panel36.add(label76, gbc);
        nombreAltaRutaDeVuelo = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel36.add(nombreAltaRutaDeVuelo, gbc);
        final JLabel label77 = new JLabel();
        label77.setText("Categoria:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        panel36.add(label77, gbc);
        final JLabel label78 = new JLabel();
        label78.setText("Descripcion:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel36.add(label78, gbc);
        descripcionAltaRutaDeVuelo = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel36.add(descripcionAltaRutaDeVuelo, gbc);
        jComboBoxCiudadOrigen = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel36.add(jComboBoxCiudadOrigen, gbc);
        jComboBoxCiudadDestino = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel36.add(jComboBoxCiudadDestino, gbc);
        final JLabel label79 = new JLabel();
        label79.setText("Costo turista:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel36.add(label79, gbc);
        spinnerCostoTurista = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel36.add(spinnerCostoTurista, gbc);
        spinnerCostoEjecutivo = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel36.add(spinnerCostoEjecutivo, gbc);
        final JLabel label80 = new JLabel();
        label80.setText("Costo ejecutivo:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        panel36.add(label80, gbc);
        final JLabel label81 = new JLabel();
        label81.setText("Costo equipaje extra:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        panel36.add(label81, gbc);
        spinnerCostoEquipaje = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel36.add(spinnerCostoEquipaje, gbc);
        jComboBoxAerolineaAltaRutaVuelo = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel36.add(jComboBoxAerolineaAltaRutaVuelo, gbc);
        final JLabel label82 = new JLabel();
        label82.setText("Duracion (horas,minutos):");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel36.add(label82, gbc);
        final JPanel panel37 = new JPanel();
        panel37.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel36.add(panel37, gbc);
        spinnerHoraAltaRutaDeVuelo = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel37.add(spinnerHoraAltaRutaDeVuelo, gbc);
        spinnerMinutoAltaRutaDeVuelo = new JSpinner();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel37.add(spinnerMinutoAltaRutaDeVuelo, gbc);
        final JPanel panel38 = new JPanel();
        panel38.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.gridwidth = 3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel36.add(panel38, gbc);
        crearRutaDeVuelo = new JButton();
        crearRutaDeVuelo.setText("Crear Ruta");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel38.add(crearRutaDeVuelo, gbc);
        final JLabel label83 = new JLabel();
        label83.setText("Subir Imagen:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.WEST;
        panel36.add(label83, gbc);
        final JLabel label84 = new JLabel();
        label84.setText("Archivo Seleccionado:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.WEST;
        panel36.add(label84, gbc);
        selectedFileRutaCrear = new JLabel();
        selectedFileRutaCrear.setText("No se ha seleccionado archivo");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 12;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel36.add(selectedFileRutaCrear, gbc);
        button2 = new JButton();
        button2.setText("⇪");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 11;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel36.add(button2, gbc);
        final JLabel label85 = new JLabel();
        label85.setText("Descripcion Corta:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel36.add(label85, gbc);
        descripcionCortaAltaRutaDeVuelo = new JTextField();
        descripcionCortaAltaRutaDeVuelo.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel36.add(descripcionCortaAltaRutaDeVuelo, gbc);
        jPanelCategorias = new JPanel();
        jPanelCategorias.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 10, 0);
        panel36.add(jPanelCategorias, gbc);
        listaCategorias = new JList();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 10, 0);
        jPanelCategorias.add(listaCategorias, gbc);
        final JLabel label86 = new JLabel();
        label86.setText("URL Video");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.anchor = GridBagConstraints.WEST;
        panel36.add(label86, gbc);
        videoUrlAltaRutaDeVuelo = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 13;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel36.add(videoUrlAltaRutaDeVuelo, gbc);
        final JPanel panel39 = new JPanel();
        panel39.setLayout(new GridBagLayout());
        tabbedPane4.addTab("Aceptar/Rechazar", panel39);
        final JPanel panel40 = new JPanel();
        panel40.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(50, 20, 20, 50);
        panel39.add(panel40, gbc);
        final JLabel label87 = new JLabel();
        label87.setText("Seleccionar Aerolinea:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel40.add(label87, gbc);
        seleccionarAerolineaAceptarRechazarRuta = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel40.add(seleccionarAerolineaAceptarRechazarRuta, gbc);
        final JLabel label88 = new JLabel();
        label88.setText("Seleccionar Ruta:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel40.add(label88, gbc);
        seleccionarRutaAceptarRechazarRuta = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel40.add(seleccionarRutaAceptarRechazarRuta, gbc);
        aprobarButtonAceptarRechazarRuta = new JButton();
        aprobarButtonAceptarRechazarRuta.setText("Aprobar");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel40.add(aprobarButtonAceptarRechazarRuta, gbc);
        rechazarButtonAceptarRechazarRuta = new JButton();
        rechazarButtonAceptarRechazarRuta.setText("Rechazar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel40.add(rechazarButtonAceptarRechazarRuta, gbc);
        final JLabel label89 = new JLabel();
        label89.setText("Estado:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel40.add(label89, gbc);
        estadoRutaText = new JLabel();
        estadoRutaText.setText("No se ha seleccionado Ruta.");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel40.add(estadoRutaText, gbc);
        final JPanel panel41 = new JPanel();
        panel41.setLayout(new GridBagLayout());
        tabbedPane4.addTab("Consultar", panel41);
        final JPanel panel42 = new JPanel();
        panel42.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(50, 20, 20, 50);
        panel41.add(panel42, gbc);
        final JLabel label90 = new JLabel();
        label90.setText("Seleccionar Aerolínea");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel42.add(label90, gbc);
        jComboBoxAerolineaConsultaRuta = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel42.add(jComboBoxAerolineaConsultaRuta, gbc);
        final JLabel label91 = new JLabel();
        label91.setText("Seleccionar Ruta de Vuelo");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel42.add(label91, gbc);
        jComboBoxRutaVueloConsultaRuta = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel42.add(jComboBoxRutaVueloConsultaRuta, gbc);
        buttonConsultarRutaVuelo = new JButton();
        buttonConsultarRutaVuelo.setText("Consultar Ruta de Vuelo");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel42.add(buttonConsultarRutaVuelo, gbc);
        final JPanel panel43 = new JPanel();
        panel43.setLayout(new GridBagLayout());
        tabbedPane4.addTab("Top5", panel43);
        final JPanel panel44 = new JPanel();
        panel44.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(50, 20, 20, 50);
        panel43.add(panel44, gbc);
        jPanelTop5Rutas = new JPanel();
        jPanelTop5Rutas.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel44.add(jPanelTop5Rutas, gbc);
        final JPanel panel45 = new JPanel();
        panel45.setLayout(new GridBagLayout());
        gestionUsuarios.addTab("Ciudad", panel45);
        final JPanel panel46 = new JPanel();
        panel46.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(50, 20, 20, 50);
        panel45.add(panel46, gbc);
        final JLabel label92 = new JLabel();
        label92.setText("Nombre:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel46.add(label92, gbc);
        final JLabel label93 = new JLabel();
        label93.setText("Pais:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel46.add(label93, gbc);
        paisAltaCiudad = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel46.add(paisAltaCiudad, gbc);
        final JPanel panel47 = new JPanel();
        panel47.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel46.add(panel47, gbc);
        nombreAltaCiudad = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel46.add(nombreAltaCiudad, gbc);
        final JLabel label94 = new JLabel();
        label94.setText("Aeropuerto:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel46.add(label94, gbc);
        aeropuertoAltaCiudad = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel46.add(aeropuertoAltaCiudad, gbc);
        final JLabel label95 = new JLabel();
        label95.setText("Descripcion:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel46.add(label95, gbc);
        descripcionAltaCiudad = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel46.add(descripcionAltaCiudad, gbc);
        final JPanel panel48 = new JPanel();
        panel48.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel46.add(panel48, gbc);
        final JButton button5 = new JButton();
        button5.setText("CANCELAR");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel48.add(button5, gbc);
        btnConfirmarAltaCiudad = new JButton();
        btnConfirmarAltaCiudad.setText("CONFIRMAR");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel48.add(btnConfirmarAltaCiudad, gbc);
        final JLabel label96 = new JLabel();
        label96.setText("AGREGAR CIUDAD");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panel46.add(label96, gbc);
        final JLabel label97 = new JLabel();
        label97.setText("Sitio web:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        panel46.add(label97, gbc);
        webAltaCiudad = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel46.add(webAltaCiudad, gbc);
        label23.setLabelFor(jComboBoxSeleccionarUsuarioConsultar);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return menuPrincipal;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}