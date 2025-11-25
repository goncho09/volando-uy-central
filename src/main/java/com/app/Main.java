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

    //Declaració de JavaSwing
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

        // Configuración del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setResizable(true);
        setLocationRelativeTo(null);
        setTitle("Admin Dashboard");
        add(menuPrincipal);
        setVisible(true);

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
        jPanelCategorias.setLayout(new BorderLayout());
        jPanelCategorias.add(new JScrollPane(listaCategorias), BorderLayout.CENTER);

        jPanelCategorias.revalidate();
        jPanelCategorias.repaint();
    }

    public static void main(String[] args) {
        s = Factory.getSistema();
        SwingUtilities.invokeLater(() -> new Main());
        Publicador.publicar();
    }
}