package com.app.interfaces;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.*;

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
                if (jComboBoxReservas.getSelectedItem() == null || jComboBoxReservas.getSelectedItem().toString().equals("N/A")) {
                    new VentanaMensaje("Seleccione una reserva");
                    return;
                }
                DtReserva r = (DtReserva) jComboBoxReservas.getSelectedItem();
                InfoReserva ventanaReserva = new InfoReserva(r, a);
                setEnabled(false);

                ventanaReserva.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        setEnabled(true);
                    }

                    ;
                });
            }
        });

        verPaquete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jComboBoxPaquetes.getSelectedItem() == null || jComboBoxPaquetes.getSelectedItem().toString().equals("N/A")) {
                    new VentanaMensaje("Seleccione un paquete");
                    return;
                }
                DtPaquete p = (DtPaquete) jComboBoxPaquetes.getSelectedItem();
                InfoPaquete ventanaPaquete = new InfoPaquete(p, a);
                setEnabled(false);

                ventanaPaquete.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        setEnabled(true);
                    }

                    ;
                });
            }
        });
        verRuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jComboBoxRutasDeVuelo.getSelectedItem() == null || jComboBoxRutasDeVuelo.getSelectedItem().toString().equals("N/A")) {
                    new VentanaMensaje("Seleccione una ruta");
                    return;
                }
                DtRuta ruta = (DtRuta) jComboBoxRutasDeVuelo.getSelectedItem();
                InfoRutaDeVuelo ventanaRuta = new InfoRutaDeVuelo(ruta, a);
                setEnabled(false);

                ventanaRuta.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        setEnabled(true);
                    }
                });
            }
        });
    }


    private void createUIComponents() {
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelGlobal = new JPanel();
        panelGlobal.setLayout(new GridBagLayout());
        panelGlobal.setEnabled(true);
        panelUsuario = new JPanel();
        panelUsuario.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelGlobal.add(panelUsuario, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Nickname:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelUsuario.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Nombre:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelUsuario.add(label2, gbc);
        nombreConsultaUsuario = new JLabel();
        nombreConsultaUsuario.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelUsuario.add(nombreConsultaUsuario, gbc);
        nicknameConsultaUsuario = new JLabel();
        nicknameConsultaUsuario.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelUsuario.add(nicknameConsultaUsuario, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Email:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panelUsuario.add(label3, gbc);
        emailConsultaUsuario = new JLabel();
        emailConsultaUsuario.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panelUsuario.add(emailConsultaUsuario, gbc);
        panelAerolinea = new JPanel();
        panelAerolinea.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelUsuario.add(panelAerolinea, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Sitio Web:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelAerolinea.add(label4, gbc);
        sitioAerolineaConsultaUsuario = new JLabel();
        sitioAerolineaConsultaUsuario.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelAerolinea.add(sitioAerolineaConsultaUsuario, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("Descripción:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelAerolinea.add(label5, gbc);
        descripcionAerolineaConsultaUsuario = new JLabel();
        descripcionAerolineaConsultaUsuario.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelAerolinea.add(descripcionAerolineaConsultaUsuario, gbc);
        final JLabel label6 = new JLabel();
        label6.setText("Rutas de Vuelo:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelAerolinea.add(label6, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelAerolinea.add(panel1, gbc);
        jComboBoxRutasDeVuelo = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(jComboBoxRutasDeVuelo, gbc);
        verRuta = new JButton();
        verRuta.setText("\uD83D\uDD0D");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(verRuta, gbc);
        panelCliente = new JPanel();
        panelCliente.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelUsuario.add(panelCliente, gbc);
        final JLabel label7 = new JLabel();
        label7.setText("Apeliido:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelCliente.add(label7, gbc);
        apellidoConsultaUsuario = new JLabel();
        apellidoConsultaUsuario.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelCliente.add(apellidoConsultaUsuario, gbc);
        final JLabel label8 = new JLabel();
        label8.setText("Nacionalidad:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelCliente.add(label8, gbc);
        nacionalidadConsultaUsuario = new JLabel();
        nacionalidadConsultaUsuario.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelCliente.add(nacionalidadConsultaUsuario, gbc);
        final JLabel label9 = new JLabel();
        label9.setText("Fecha de Nacimiento:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelCliente.add(label9, gbc);
        fechaNacConsultaUsuario = new JLabel();
        fechaNacConsultaUsuario.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelCliente.add(fechaNacConsultaUsuario, gbc);
        final JLabel label10 = new JLabel();
        label10.setText("Tipo de documento:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panelCliente.add(label10, gbc);
        tipoDocConsultaUsuario = new JLabel();
        tipoDocConsultaUsuario.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelCliente.add(tipoDocConsultaUsuario, gbc);
        final JLabel label11 = new JLabel();
        label11.setText("Numero de Documento:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panelCliente.add(label11, gbc);
        numDocumentoConsultaCliente = new JLabel();
        numDocumentoConsultaCliente.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelCliente.add(numDocumentoConsultaCliente, gbc);
        final JLabel label12 = new JLabel();
        label12.setText("Reservas:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelCliente.add(label12, gbc);
        final JLabel label13 = new JLabel();
        label13.setText("Paquetes:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelCliente.add(label13, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelCliente.add(panel2, gbc);
        jComboBoxReservas = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(jComboBoxReservas, gbc);
        verReserva = new JButton();
        verReserva.setText("\uD83D\uDD0D");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(verReserva, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelCliente.add(panel3, gbc);
        jComboBoxPaquetes = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(jComboBoxPaquetes, gbc);
        verPaquete = new JButton();
        verPaquete.setText("\uD83D\uDD0D");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(verPaquete, gbc);
        cancelarButton = new JButton();
        cancelarButton.setText("Cancelar");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelUsuario.add(cancelarButton, gbc);
        imagenPanel = new JPanel();
        imagenPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelUsuario.add(imagenPanel, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelGlobal;
    }

}
