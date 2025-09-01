package com.app;

import com.app.datatypes.DtUsuario;
import com.app.datatypes.DtAerolinea;
import com.app.datatypes.DtCliente;
import com.app.datatypes.DtUsuario;

import javax.swing.*;

public class dataUsuario extends JFrame {
    private JPanel panel1;
    private JLabel nicknameConsultaUsuario;
    private JLabel nombreConsultaUsuario;
    private JLabel emailConsultaUsuario;
    private JPanel PanelAerolinea;
    private JButton cancelarButton;
    private JLabel apellidoConsultaUsuario;
    private JLabel fechaNacConsultaUsuario;
    private JLabel tipoDocConsultaUsuario;
    private JLabel numDocumentoConsultaCliente;
    private JLabel nacionalidadConsultaUsuario;
    private JLabel descripcionAerolineaConsultaUsuario;
    private JLabel nombreAerlineaConsultaUsuario;
    private JLabel nicknameAerlineaConsultaUsuario;
    private JLabel sitioAerolineaConsultaUsuario;

    public dataUsuario(DtUsuario usuario) {
        setTitle("Datos de Usuario: " + usuario.getNickname());
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(275, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        cancelarButton.addActionListener(e -> dispose());

        setVisible(true);

        nicknameConsultaUsuario.setText(usuario.getNickname());
        nombreConsultaUsuario.setText(usuario.getNombre());
        emailConsultaUsuario.setText(usuario.getEmail());

        if (usuario instanceof DtCliente) {
            DtCliente cliente = (DtCliente) usuario;
            PanelAerolinea.setVisible(false);
            apellidoConsultaUsuario.setText(cliente.getApellido());
            nacionalidadConsultaUsuario.setText(cliente.getNacionalidad());
            tipoDocConsultaUsuario.setText(cliente.getTipoDocumento().toString());
            numDocumentoConsultaCliente.setText(String.valueOf(cliente.getNumeroDocumento()));
            fechaNacConsultaUsuario.setText(cliente.getFechaNacimiento().toString());

        } else if (usuario instanceof DtAerolinea) {
            DtAerolinea aerolinea = (DtAerolinea) usuario;
            PanelAerolinea.setVisible(true);

            apellidoConsultaUsuario.setVisible(false);
            nacionalidadConsultaUsuario.setVisible(false);
            tipoDocConsultaUsuario.setVisible(false);
            numDocumentoConsultaCliente.setVisible(false);
            fechaNacConsultaUsuario.setVisible(false);

            descripcionAerolineaConsultaUsuario.setText(aerolinea.getDescripcion());
            sitioAerolineaConsultaUsuario.setText(aerolinea.getLinkWeb());
            nombreAerlineaConsultaUsuario.setText(usuario.getNombre());
            nicknameAerlineaConsultaUsuario.setText(usuario.getNickname());
        }
    }


    private void createUIComponents() {
    }
}
