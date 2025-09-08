package com.app;

import com.app.clases.Categoria;
import com.app.datatypes.DtRuta;
import com.app.datatypes.DtVuelo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class dataRutaDeVuelo extends JFrame{
    private auxiliarFunctions a;

    private JPanel panel1;
    private JLabel nombre;
    private JLabel descripcion;
    private JLabel duracion;
    private JLabel costoTurista;
    private JLabel costoEjecutivo;
    private JLabel costoEquipaje;
    private JLabel fechaAlta;
    private JLabel ciudadOrigen;
    private JLabel ciudadDestino;
    private JPanel dataRutaPanel;
    private JPanel categoriasPanel;
    private JComboBox JComboBoxVuelos;
    private JButton ButtonVerVuelo;

    public dataRutaDeVuelo(DtRuta ruta, List<DtVuelo> vuelosAsociados, auxiliarFunctions auxiliar){
        setTitle("Datos de la ruta: " + ruta.getNombre());
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        add(dataRutaPanel);

        a = auxiliar;

        DefaultComboBoxModel<DtVuelo> model = new DefaultComboBoxModel<>();
        for (DtVuelo v : vuelosAsociados) {
            if(v.getRutaDeVuelo().getNombre().equals(ruta.getNombre())){
             model.addElement(v);

            }
        }

        JComboBoxVuelos.setModel(a.getComboVueloRutaDeVueloModel());
        a.cargarVuelosComboBoxRuta(ruta.getNombre());

        nombre.setText(ruta.getNombre());
        descripcion.setText(ruta.getDescripcion());
        duracion.setText(ruta.getDuracion().toString());
        costoTurista.setText(Float.toString(ruta.getCostoTurista()));
        costoEjecutivo.setText(Float.toString(ruta.getCostoEjecutivo()));
        costoEquipaje.setText(Float.toString(ruta.getEquipajeExtra()));
        fechaAlta.setText(ruta.getFechaAlta().toString());
        ciudadOrigen.setText(ruta.getCiudadOrigen().getNombre());
        ciudadDestino.setText(ruta.getCiudadDestino().getNombre());

        categoriasPanel.setLayout(new GridLayout(0, 2, 10, 5));

        if (ruta.getCategorias().isEmpty()){
            categoriasPanel.add(new JLabel("No hay categorías."));
        }else{
            for (Categoria cat : ruta.getCategorias()) {
                JLabel label = new JLabel(cat.getNombre());
                categoriasPanel.add(label);
            }
        }


        ButtonVerVuelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JComboBoxVuelos.getSelectedItem() == null){
                    new dialogMessage("Seleccione un vuelo para poder verlo.");
                }

                for (DtVuelo v : vuelosAsociados) {
                    if(v.getNombre().equals(JComboBoxVuelos.getSelectedItem().toString())){
                        dataVuelo ventanaVuelo = new dataVuelo(v.getDatos(), a);
                        setEnabled(false);

                        ventanaVuelo.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e){
                                setEnabled(true);
                            };
                        });
                        break;
                    }
                }

            }
        });
    }
}
