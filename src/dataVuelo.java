import datatypes.DtVuelo;

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


    }

}


