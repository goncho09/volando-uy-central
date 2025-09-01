package com.app;

import com.app.clases.*;
import com.app.datatypes.*;

import javax.swing.*;
import java.util.List;

public class auxiliarFunctions {

    private ISistema sistema;
    private DefaultComboBoxModel<DtUsuario> comboUser = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtAerolinea> comboAerolinea = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtRuta> comboRutaDeVuelo = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtCiudad> comboCiudadOrigen = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtCiudad> comboCiudadDestino = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtPaquete> comboPaquete = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtPaquete> comboPaqueteNoComprado = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtRuta> comboRutaDeVueloAerolinea = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtVuelo> comboVueloRutaDeVuelo = new DefaultComboBoxModel<>();

    public auxiliarFunctions(ISistema s) {
        this.sistema = s;
    }

    //Getters de los modelos
    public DefaultComboBoxModel<DtUsuario> getComboUserModel() {
        return comboUser;
    }

    public DefaultComboBoxModel<DtAerolinea> getComboAerolineaModel() {
        return comboAerolinea;
    }

    public DefaultComboBoxModel<DtRuta> getComboRutaDeVueloAerolineaModel() {
        return comboRutaDeVueloAerolinea;
    }

    public DefaultComboBoxModel<DtRuta> getComboRutaDeVueloModel() {
        return comboRutaDeVuelo;
    }

    public DefaultComboBoxModel<DtCiudad> getComboCiudadOrigenModel() {
        return comboCiudadOrigen;
    }

    public DefaultComboBoxModel<DtCiudad> getComboCiudadDestinoModel() {
        return comboCiudadDestino;
    }

    public DefaultComboBoxModel<DtPaquete> getComboPaqueteModel() {
        return comboPaquete;
    }

    public DefaultComboBoxModel<DtPaquete> getComboPaqueteNoCompradoModel() {
        return comboPaqueteNoComprado;
    }

    public DefaultComboBoxModel<DtVuelo> getComboVueloRutaDeVueloModel() {return  comboVueloRutaDeVuelo;}

    //Función para validar 1 o más "JComboBox"
    public boolean estanVaciosJComboBox(JComboBox<?>... combos) {
        for (JComboBox<?> combo : combos) {
            Object item = combo.getSelectedItem();
            if (item == null || item.toString().trim().isEmpty() || item.toString().equals("N/A")) {
                return true;
            }
        }
        return false;
    }

    public boolean estanVaciosJTextField(JTextField... fields) {
        for (JTextField field : fields) {
            if (field.getText() == null || field.getText().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void limpiarJTextField(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    public void cargarUsuariosComboBox() {
        comboUser.removeAllElements();
        List<DtUsuario> usuarios = sistema.listarUsuarios();
        if(usuarios != null){
            for (DtUsuario u : usuarios) {
                comboUser.addElement(u);
            }
        }else{
            comboUser.removeAllElements();
            comboUser.addElement(new DtUsuario() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarAerolineasComboBox() {
        comboAerolinea.removeAllElements();
            List<DtAerolinea> aerolineas = sistema.listarAerolineas();
            if(aerolineas != null){
                for (DtAerolinea a : aerolineas) {
                    comboAerolinea.addElement(a);
                }
            }
            else {
                comboAerolinea.removeAllElements();
                comboAerolinea.addElement(new DtAerolinea() {
                @Override
                public String toString() { return "N/A"; }
            });
            }
    }

    public void cargarRutasDeVueloComboBox() {
        comboRutaDeVuelo.removeAllElements();
        List<DtRuta> rt = sistema.listarRutasDeVuelo();
        if(rt != null) {
            for (DtRuta rtv : rt) {
                comboRutaDeVuelo.addElement(rtv);
            }
        }else{
            comboRutaDeVuelo.removeAllElements();
            comboRutaDeVuelo.addElement(new DtRuta() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarRutasDeVueloComboBoxAerolinea(String nickname) {
        comboRutaDeVueloAerolinea.removeAllElements();
        List<DtRuta> rt = sistema.listarRutasDeVuelo(nickname);
        if(rt != null) {
            for (DtRuta rtv : rt) {
                comboRutaDeVueloAerolinea.addElement(rtv);
            }
        }else{
            comboRutaDeVueloAerolinea.removeAllElements();
            comboRutaDeVueloAerolinea.addElement(new DtRuta() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarCiudadesComboBox() {
        comboCiudadOrigen.removeAllElements();
        comboCiudadDestino.removeAllElements();

        List<DtCiudad> c = sistema.listarCiudades();
        if(c != null) {
            for (DtCiudad ciudad : c) {
                comboCiudadOrigen.addElement(ciudad);
                comboCiudadDestino.addElement(ciudad);
            }
        }else{
            comboCiudadOrigen.removeAllElements();
            comboCiudadOrigen.addElement(new DtCiudad() {
                @Override
                public String toString() { return "N/A"; }
            });
            comboCiudadDestino.removeAllElements();
            comboCiudadDestino.addElement(new DtCiudad() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }



    public void cargarPaqueteComboBox() {
        comboPaquete.removeAllElements();
        List<DtPaquete> p = sistema.listarPaquetes();
        if(p != null) {
            for (DtPaquete pqt : p) {
                comboPaquete.addElement(pqt);
            }
        }else{
            comboPaquete.removeAllElements();
            comboPaquete.addElement(new DtPaquete() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarPaqueteNoCompradoComboBox() {
        comboPaqueteNoComprado.removeAllElements();
        List<DtPaquete> p = sistema.listarPaquetesNoComprados();
        if(p != null) {
            for (DtPaquete pqt : p) {
                comboPaqueteNoComprado.addElement(pqt);
            }
        }else{
            comboPaqueteNoComprado.removeAllElements();
            comboPaqueteNoComprado.addElement(new DtPaquete() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

}
