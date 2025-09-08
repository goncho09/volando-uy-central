package com.app;

import com.app.clases.RutaDeVuelo;
import com.app.clases.RutaEnPaquete;
import com.app.datatypes.DtPaquete;
import com.app.datatypes.DtVuelo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class dataPaquete extends JFrame {
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

    public dataPaquete(DtPaquete paquete, List<DtVuelo> vuelos) {
            setTitle("Datos del paquete: " + paquete.getNombre());
            setResizable(false);
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            setSize(500, 300);
            setLocationRelativeTo(null);
            setVisible(true);
            add(dataPaquetePanel);

            nombre.setText(paquete.getNombre());
            descripcion.setText(paquete.getDescripcion());
            validezDias.setText(Integer.toString(paquete.getValidezDias()));
            descuento.setText(Float.toString(paquete.getDescuento()));
            costo.setText(Float.toString(paquete.getCosto()));


            DefaultComboBoxModel<RutaEnPaquete> model = new DefaultComboBoxModel<>();
            List <RutaEnPaquete> rutasTemp = new ArrayList<>();

            for (RutaEnPaquete rp : paquete.getRutaEnPaquete()) {
                if(!rutasTemp.equals(rp)){
                    rutasTemp.add(rp);
                    model.addElement(rp);
                }
            }

            cantidadRutas.setText(Integer.toString(rutasTemp.size()));

            JComboBoxRutasVuelo.setModel(model);
            JComboBoxRutasVuelo.setSelectedIndex(-1);


        ButtonVerRutaDeVuelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JComboBoxRutasVuelo.getSelectedItem() == null){
                    new dialogMessage("Debe seleccionar una ruta de vuelo para ver su información");
                }
                try{
                    new dataRutaDeVuelo(paquete.getRutaDeVuelo(JComboBoxRutasVuelo.getSelectedItem().toString()).getDatos(),vuelos);
                } catch (Exception ex) {
                    new dialogMessage(ex.getMessage());
                }

            }
        });
    }
}
