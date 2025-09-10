package com.app;

import com.app.clases.RutaDeVuelo;
import com.app.clases.RutaEnPaquete;
import com.app.datatypes.DtPaquete;
import com.app.datatypes.DtRuta;
import com.app.datatypes.DtVuelo;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class dataPaquete extends JFrame {
    private auxiliarFunctions a;

    private JLabel nombre;
    private JLabel descripcion;
    private JLabel validezDias;
    private JLabel descuento;
    private JLabel costo;
    private JLabel cantidadRutas;
    private JComboBox JComboBoxRutasVuelo;
    private JPanel dataPaquetePanel;
    private JButton ButtonVerRutaDeVuelo;
    private JPanel dataPaqueteDisplay;

    public dataPaquete(DtPaquete paquete, auxiliarFunctions auxiliar) {
            setTitle("Datos del paquete: " + paquete.getNombre());
            setResizable(false);
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            setSize(500, 300);
            setLocationRelativeTo(null);
            setVisible(true);
            add(dataPaquetePanel);

            this.a = auxiliar;

            nombre.setText(paquete.getNombre());
            descripcion.setText(paquete.getDescripcion());
            validezDias.setText(Integer.toString(paquete.getValidezDias()));
            descuento.setText(Float.toString(paquete.getDescuento()));
            costo.setText(Float.toString(paquete.getCosto()));
            cantidadRutas.setText(Integer.toString(paquete.getRutaEnPaquete().size()));

            JComboBoxRutasVuelo.setModel(a.getComboRutaPaquete());
            a.cargarRutasPaquete(paquete);


        ButtonVerRutaDeVuelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JComboBoxRutasVuelo.getSelectedItem() == null || JComboBoxRutasVuelo.getSelectedItem().toString().equals("N/A")){
                    new dialogMessage("Debe seleccionar una ruta de vuelo para ver su información");
                    return;
                }
                try{
                    DtRuta ruta = (DtRuta) JComboBoxRutasVuelo.getSelectedItem();
                    dataRutaDeVuelo ventanaRuta = new dataRutaDeVuelo(ruta, a);
                    setEnabled(false);

                    ventanaRuta.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e){setEnabled(true);
                        };
                    });
                } catch (Exception ex) {
                    new dialogMessage(ex.getMessage());
                }

            }
        });
    }
}
