package com.app;

import com.app.datatypes.DtReserva;

import javax.swing.*;
import java.util.List;

public class dataReserva extends JFrame{

    private auxiliarFunctions a;

    private JPanel dataReservaPanel;
    private JPanel dataReservaDisplay;
    private JComboBox JComboBoxPasajeros;
    private JLabel clienteLabel;
    private JLabel fechaLabel;
    private JLabel asientosLabel;
    private JLabel tipoAsientoLabel;
    private JLabel equipajeLabel;
    private JLabel costoLabel;

    public dataReserva(DtReserva dataReserva, auxiliarFunctions auxiliar){
        setTitle("Reservas del vuelo: " + dataReserva.getVuelo().getNombre());
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(275, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        add(dataReservaPanel);

        this.a = auxiliar;

        clienteLabel.setText(dataReserva.getCliente().getNombre());
        fechaLabel.setText(dataReserva.getFecha().toString());
        asientosLabel.setText(Integer.toString(dataReserva.getCantPasajes()));
        tipoAsientoLabel.setText(dataReserva.getTipoAsiento().toString());
        equipajeLabel.setText(Integer.toString(dataReserva.getEquipajeExtra()));
        costoLabel.setText(Float.toString(dataReserva.getCosto()));

        JComboBoxPasajeros.setModel(a.getComboPasajerosReserva());
        a.cargarPasajeros(dataReserva);

    }

}
