package com.app.interfaces;

import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import com.app.datatypes.DtUsuario;
import com.app.datatypes.DtCliente;
import com.app.datatypes.DtAerolinea;
import com.app.datatypes.DtReserva;
import com.app.datatypes.DtPaquete;
import com.app.datatypes.DtRuta;

import com.app.enums.TipoImagen;

import com.app.utils.AuxiliarFunctions;

public class InfoUsuario extends JFrame {

    private AuxiliarFunctions a;

    private JPanel panelGlobal;
    private JLabel nicknameConsultaUsuario;
    private JLabel nombreConsultaUsuario;
    private JLabel emailConsultaUsuario;
    private JPanel panelAerolinea;
    private JButton cancelarButton;
    private JLabel apellidoConsultaUsuario;
    private JLabel fechaNacConsultaUsuario;
    private JLabel tipoDocConsultaUsuario;
    private JLabel numDocumentoConsultaCliente;
    private JLabel nacionalidadConsultaUsuario;
    private JLabel descripcionAerolineaConsultaUsuario;
    private JLabel sitioAerolineaConsultaUsuario;
    private JPanel panelUsuario;
    private JPanel panelCliente;
    private JComboBox jComboBoxRutasDeVuelo;
    private JButton verRuta;
    private JComboBox jComboBoxReservas;
    private JComboBox jComboBoxPaquetes;
    private JButton verReserva;
    private JButton verPaquete;
    private JPanel imagenPanel;
    private ImageIcon profileImage;

    public InfoUsuario(DtUsuario usuario, AuxiliarFunctions auxiliar) {
        setContentPane(panelGlobal);
        setTitle("Datos de Usuario: " + usuario.getNickname());
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.a = auxiliar;

        cancelarButton.addActionListener(e -> dispose());

        nicknameConsultaUsuario.setText(usuario.getNickname());
        nombreConsultaUsuario.setText(usuario.getNombre());
        emailConsultaUsuario.setText(usuario.getEmail());

        try {
            Path userImg = AuxiliarFunctions.getImagePath(usuario.getUrlImage(), TipoImagen.USUARIO);
            if (!Files.exists(userImg)) {
                throw new IllegalArgumentException("No se encontro la imagen");
            }
            profileImage = new ImageIcon(userImg.toAbsolutePath().toString());
        } catch (IllegalArgumentException ex) {
            profileImage = new ImageIcon(getClass().getResource("/pictures/users/default.png"));
        }

        AuxiliarFunctions.mostrarFoto(imagenPanel, profileImage, 175, 175, TipoImagen.USUARIO);

        if (usuario instanceof DtCliente) {
            DtCliente cliente = (DtCliente) usuario;
            panelCliente.setVisible(true);
            panelAerolinea.setVisible(false);
            apellidoConsultaUsuario.setText(cliente.getApellido());
            nacionalidadConsultaUsuario.setText(cliente.getNacionalidad());
            tipoDocConsultaUsuario.setText(cliente.getTipoDocumento().toString());
            numDocumentoConsultaCliente.setText(String.valueOf(cliente.getNumeroDocumento()));
            fechaNacConsultaUsuario.setText(cliente.getFechaNacimiento().toString());

            jComboBoxReservas.setModel(a.getComboReservaVueloClienteModel());
            a.cargarDatosReservaClienteComboBox(cliente.getNickname());

            jComboBoxPaquetes.setModel(a.getComboPaqueteClienteModel());
            a.cargarPaqueteClienteComboBox(cliente.getNickname());

        } else if (usuario instanceof DtAerolinea) {
            DtAerolinea aerolinea = (DtAerolinea) usuario;
            panelAerolinea.setVisible(true);
            panelCliente.setVisible(false);

            descripcionAerolineaConsultaUsuario.setText(aerolinea.getDescripcion());
            sitioAerolineaConsultaUsuario.setText(aerolinea.getLinkWeb());

            jComboBoxRutasDeVuelo.setModel(a.getComboRutaDeVueloAerolineaModel());
            a.cargarRutasDeVueloComboBoxAerolinea(aerolinea.getNickname());
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        verReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jComboBoxReservas.getSelectedItem() == null || jComboBoxReservas.getSelectedItem().toString().equals("N/A")){
                    new VentanaMensaje("Seleccione una reserva");
                    return;
                }
                DtReserva r = (DtReserva) jComboBoxReservas.getSelectedItem();
                InfoReserva ventanaReserva = new InfoReserva(r, a);
                setEnabled(false);

                ventanaReserva.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e){
                        setEnabled(true);
                    };
                });
            }
        });

        verPaquete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jComboBoxPaquetes.getSelectedItem() == null || jComboBoxPaquetes.getSelectedItem().toString().equals("N/A")){
                    new VentanaMensaje("Seleccione un paquete");
                    return;
                }
                DtPaquete p = (DtPaquete) jComboBoxPaquetes.getSelectedItem();
                InfoPaquete ventanaPaquete = new InfoPaquete(p, a);
                setEnabled(false);

                ventanaPaquete.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e){
                        setEnabled(true);
                    };
                });
            }
        });
        verRuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jComboBoxRutasDeVuelo.getSelectedItem() == null || jComboBoxRutasDeVuelo.getSelectedItem().toString().equals("N/A")){
                    new VentanaMensaje("Seleccione una ruta");
                    return;
                }
                DtRuta ruta =  (DtRuta) jComboBoxRutasDeVuelo.getSelectedItem();
                InfoRutaDeVuelo ventanaRuta = new InfoRutaDeVuelo(ruta, a);
                setEnabled(false);

                ventanaRuta.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e){
                        setEnabled(true);
                    }
                });
            }
        });
    }


    private void createUIComponents() {
    }
}
