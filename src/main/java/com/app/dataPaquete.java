package com.app;

import com.app.datatypes.DtPaquete;

import javax.swing.*;

public class dataPaquete extends JFrame {
    private JLabel nombre;
    private JLabel descripcion;
    private JLabel validezDias;
    private JLabel descuento;
    private JLabel costo;
    private JLabel cantidadRutas;
    private JComboBox ComboBoxRutas;
    private JPanel dataPaquetePanel;
    private JPanel dataPaqueteDisplay;

    public dataPaquete(DtPaquete paquete) {
            setTitle("Datos del paquete: " + paquete.getNombre());
            setResizable(false);
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            setSize(275, 300);
            setLocationRelativeTo(null);
            setVisible(true);
            add(dataPaquetePanel);

            nombre.setText(paquete.getNombre());
            descripcion.setText(paquete.getDescripcion());
            validezDias.setText(Integer.toString(paquete.getValidezDias()));
            descuento.setText(Float.toString(paquete.getDescuento()));
            costo.setText(Float.toString(paquete.getCosto()));
            cantidadRutas.setText(Integer.toString(paquete.getRutaEnPaquete().size()));
        }
}
