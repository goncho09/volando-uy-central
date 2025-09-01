package com.app;

import com.app.clases.Categoria;
import com.app.datatypes.DtRuta;

import javax.swing.*;
import java.awt.*;

public class dataRutaDeVuelo extends JFrame{
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

    public dataRutaDeVuelo(DtRuta ruta){
        setTitle("Datos de la ruta: " + ruta.getNombre());
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(275, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        add(dataRutaPanel);

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


    }
}
