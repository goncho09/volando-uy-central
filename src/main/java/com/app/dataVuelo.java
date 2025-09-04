package com.app;

import com.app.clases.Factory;
import com.app.datatypes.DtVuelo;
import com.app.datatypes.DtReserva;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

public class dataVuelo extends JFrame {

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

    public dataVuelo(DtVuelo dataVuelo) {
        setTitle("Datos del Vuelo: " + dataVuelo.getNombre());
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(275, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        add(dataVueloPanel);

        int cantidadReservas = 178; // Hay que hacer una variable en "Vuelos" para conseguir las reservas uwu
        int capacidadMaxima = dataVuelo.getMaxEjecutivos() + dataVuelo.getMaxTuristas();

        nombreVuelo.setText(dataVuelo.getNombre());
        fechaDespegue.setText(dataVuelo.getFecha().toString());
        flyDuration.setText(dataVuelo.getDuracion().toString());
        cantMaxTuristas.setText(Integer.toString(dataVuelo.getMaxTuristas()));
        cantMaxEjecutivos.setText(Integer.toString(dataVuelo.getMaxEjecutivos()));
        fechaAlta.setText(dataVuelo.getFechaAlta().toString());
        cantReservas.setText(Integer.toString(cantidadReservas));
        capacidadRestante.setText(Integer.toString(capacidadMaxima - cantidadReservas));


        consultarReservasButton.addActionListener(e -> {
            try {
                List<DtReserva> reservas = Factory.getSistema().listarReservaDeVuelo(dataVuelo.getNombre());

                if (reservas.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Este vuelo no tiene reservas.");
                    return;
                }

                StringBuilder sb = new StringBuilder("Reservas del vuelo:\n\n");
                for (DtReserva r : reservas) {
                    sb.append("- Cliente: ").append(r.getCliente().getNickname())
                            .append(" - Asientos: ").append(r.getCantPasajes())
                            .append(" - Tipo: ").append(r.getTipoAsiento())
                            .append(" - Costo: ").append(r.getCosto())
                            .append("\n");
                }

                JOptionPane.showMessageDialog(
                        this,
                        sb.toString(),
                        "Reservas",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al consultar reservas: " + ex.getMessage());
            }
        });



    }

}


