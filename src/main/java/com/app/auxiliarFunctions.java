package com.app;

import com.app.DAOs.*;
import com.app.clases.*;

import javax.swing.*;
import java.util.List;

public class auxiliarFunctions {

    private DefaultComboBoxModel<Usuario> comboUser = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<Aerolinea> comboAerolinea = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<RutaDeVuelo> comboRutaDeVuelo = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<Ciudad> comboCiudadOrigen = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<Ciudad> comboCiudadDestino = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<Categoria> comboCategoria = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<Paquete> comboPaquete = new DefaultComboBoxModel<>();
    private UserDao userDao;
    private RutaDeVueloDao rutaDeVueloDao;
    private CiudadDao ciudadDao;
    private CategoriaDao categoriaDao;
    private PaqueteDao paqueteDao;

    public auxiliarFunctions(UserDao userDao, RutaDeVueloDao rutaDeVueloDao, CiudadDao ciudadDao, CategoriaDao categoriaDao, PaqueteDao paqueteDao) {
        this.userDao = userDao;
        this.rutaDeVueloDao = rutaDeVueloDao;
        this.ciudadDao = ciudadDao;
        this.categoriaDao = categoriaDao;
        this.paqueteDao = paqueteDao;
    }

    //Getters de los modelos
    public DefaultComboBoxModel<Usuario> getComboUserModel() {
        return comboUser;
    }

    public DefaultComboBoxModel<Aerolinea> getComboAerolineaModel() {
        return comboAerolinea;
    }

    public DefaultComboBoxModel<RutaDeVuelo> getComboRutaDeVueloModel() {
        return comboRutaDeVuelo;
    }

    public DefaultComboBoxModel<Ciudad> getComboCiudadOrigenModel() {
        return comboCiudadOrigen;
    }

    public DefaultComboBoxModel<Ciudad> getComboCiudadDestinoModel() {
        return comboCiudadDestino;
    }

    public DefaultComboBoxModel<Categoria> getComboCategoriaModel() {
        return comboCategoria;
    }

    public DefaultComboBoxModel<Paquete> getComboPaqueteModel() {
        return comboPaquete;
    }

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
        try {
            List<Usuario> usuarios = userDao.listarUsuarios();
            for (Usuario u : usuarios) {
                comboUser.addElement(u);
            }
        } catch (Exception e) {
            comboUser.removeAllElements();
            comboUser.addElement(new Usuario() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarAerolineasComboBox() {
        comboAerolinea.removeAllElements();
        try {
            List<Aerolinea> aerolineas = userDao.listarAerolineas();
            for (Aerolinea a : aerolineas) {
                comboAerolinea.addElement(a);
            }
        } catch (Exception e) {
            comboAerolinea.removeAllElements();
            comboAerolinea.addElement(new Aerolinea() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarRutasDeVueloComboBox() {
        comboRutaDeVuelo.removeAllElements();
        try {
            List<RutaDeVuelo> rt = rutaDeVueloDao.listarRutasDeVuelo();
            for (RutaDeVuelo rtv : rt) {
                comboRutaDeVuelo.addElement(rtv);
            }
        } catch (Exception e) {
            comboRutaDeVuelo.removeAllElements();
            comboRutaDeVuelo.addElement(new RutaDeVuelo() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarCiudadesComboBox() {
        comboCiudadOrigen.removeAllElements();
        comboCiudadDestino.removeAllElements();
        try {
            List<Ciudad> c= ciudadDao.listarCiudades();
            for (Ciudad ciudad : c) {
                comboCiudadOrigen.addElement(ciudad);
                comboCiudadDestino.addElement(ciudad);
            }
        } catch (Exception e) {
            comboCiudadOrigen.removeAllElements();
            comboCiudadOrigen.addElement(new Ciudad() {
                @Override
                public String toString() { return "N/A"; }
            });
            comboCiudadDestino.removeAllElements();
            comboCiudadDestino.addElement(new Ciudad() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }



    public void cargarPaqueteComboBox() {
        comboPaquete.removeAllElements();
        try {
            List<Paquete> p= paqueteDao.listarPaquetes();
            for (Paquete paquete : p) {
                comboPaquete.addElement(paquete);
            }
        } catch (Exception e) {
            comboPaquete.removeAllElements();
            comboPaquete.addElement(new Paquete() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }



}
