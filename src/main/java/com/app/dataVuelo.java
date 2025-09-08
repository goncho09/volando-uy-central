package com.app;

import com.app.clases.Factory;
import com.app.datatypes.DtVuelo;
import com.app.datatypes.DtReserva;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class dataVuelo extends JFrame {

    private auxiliarFunctions a;

    private JPanel dataVueloPanel;
    private JButton consultarReservasButton;
    private JLabel nombreVuelo;
    private JLabel fechaDespegue;
    private JLabel flyDuration;
    private JLabel cantMaxTuristas;
    private JLabel cantMaxEjecutivos;
    private JLabel fechaAlta;
    private JLabel cantReservas;
    private JLabel capacidadRestante;
    private JPanel dataVueloDisplay;
    private JComboBox JComboBoxReservas;

    public dataVuelo(DtVuelo dataVuelo, auxiliarFunctions auxiliar) {
        setTitle("Datos del Vuelo: " + dataVuelo.getNombre());
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(550, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        add(dataVueloPanel);

        a = auxiliar;
        int cantidadReservas = dataVuelo.getCantReservas();
        int capacidadMaxima = dataVuelo.getMaxEjecutivos() + dataVuelo.getMaxTuristas();

        nombreVuelo.setText(dataVuelo.getNombre());
        fechaDespegue.setText(dataVuelo.getFecha().toString());
        flyDuration.setText(dataVuelo.getDuracion().toString());
        cantMaxTuristas.setText(Integer.toString(dataVuelo.getMaxTuristas()));
        cantMaxEjecutivos.setText(Integer.toString(dataVuelo.getMaxEjecutivos()));
        fechaAlta.setText(dataVuelo.getFechaAlta().toString());
        cantReservas.setText(Integer.toString(cantidadReservas));
        capacidadRestante.setText(Integer.toString(capacidadMaxima - cantidadReservas));

        JComboBoxReservas.setModel(a.getComboReservaVueloModel());

        a.cargarDatosReservaComboBox(dataVuelo);

        consultarReservasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DtReserva reserva = (DtReserva) JComboBoxReservas.getSelectedItem();
                if(reserva == null){
                    new dialogMessage("Esta reserva no existe");
                }
                dataReserva ventanaReserva = new dataReserva(reserva, a);
                setEnabled(false);

                ventanaReserva.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e){
                        setEnabled(true);
                    }
                });
            }
        });
    }

}


