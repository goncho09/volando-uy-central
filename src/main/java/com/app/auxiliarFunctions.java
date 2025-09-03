package com.app;

import com.app.clases.*;
import com.app.datatypes.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class auxiliarFunctions {

    private ISistema sistema;
    private DefaultComboBoxModel<DtUsuario> comboUser = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtAerolinea> comboAerolinea = new DefaultComboBoxModel<>(); // Solo muestra aerolineas
    private DefaultComboBoxModel<DtCliente> comboCliente = new DefaultComboBoxModel<>(); // Solo muestra aerolineas
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
    public DefaultComboBoxModel<DtUsuario> getComboUserModel() { return comboUser; }
    public DefaultComboBoxModel<DtAerolinea> getComboAerolineaModel() { return comboAerolinea; }
    public DefaultComboBoxModel<DtCliente> getComboClienteModel() { return comboCliente; }
    public DefaultComboBoxModel<DtRuta> getComboRutaDeVueloAerolineaModel() { return comboRutaDeVueloAerolinea; }
    public DefaultComboBoxModel<DtRuta> getComboRutaDeVueloModel() { return comboRutaDeVuelo; }
    public DefaultComboBoxModel<DtCiudad> getComboCiudadOrigenModel() { return comboCiudadOrigen; }
    public DefaultComboBoxModel<DtCiudad> getComboCiudadDestinoModel() { return comboCiudadDestino; }
    public DefaultComboBoxModel<DtPaquete> getComboPaqueteModel() { return comboPaquete; }
    public DefaultComboBoxModel<DtPaquete> getComboPaqueteNoCompradoModel() { return comboPaqueteNoComprado; }
    public DefaultComboBoxModel<DtVuelo> getComboVueloRutaDeVueloModel() { return  comboVueloRutaDeVuelo; }

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

    public void cargarTodosLosDatos(){
        cargarUsuariosComboBox();
        cargarAerolineasComboBox();
        cargarClientesComboBox();
        cargarCiudadesComboBox();
        cargarRutasDeVueloComboBox();
        cargarPaqueteComboBox();
        cargarPaqueteNoCompradoComboBox();
    }

    public void cargarUsuariosComboBox() {
        comboUser.removeAllElements();
        List<DtUsuario> usuarios = sistema.listarUsuarios();
        if(usuarios != null && !usuarios.isEmpty()){
            for (DtUsuario u : usuarios) {
                comboUser.addElement(u);
            }
        }else{
            comboUser.addElement(new DtUsuario() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarUsuariosComboBox(DtUsuario usuario) {
        comboUser.removeAllElements();
        List<DtUsuario> usuarios = sistema.listarUsuarios();
        if(usuarios != null  && !usuarios.isEmpty()){
            for (DtUsuario u : usuarios) {
                comboUser.addElement(u);
                if(u.getNombre().equals(usuario.getNombre())){
                    comboUser.setSelectedItem(u);
                }
            }
        }else{
            comboUser.addElement(new DtUsuario() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarAerolineasComboBox() {
        comboAerolinea.removeAllElements();
            List<DtAerolinea> aerolineas = sistema.listarAerolineas();
            if(aerolineas != null && !aerolineas.isEmpty()){
                for (DtAerolinea a : aerolineas) {
                    comboAerolinea.addElement(a);
                }
            }
            else {
                comboAerolinea.addElement(new DtAerolinea() {
                @Override
                public String toString() { return "N/A"; }
            });
            }
    }

    public void cargarClientesComboBox() {
        comboCliente.removeAllElements();
        List<DtCliente> clientes = sistema.listarClientes();
        if(clientes != null && !clientes.isEmpty()){
            for (DtCliente c : clientes) {
                comboCliente.addElement(c);
            }
        }
        else {
            comboCliente.addElement(new DtCliente() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarRutasDeVueloComboBox() {
        comboRutaDeVuelo.removeAllElements();
        List<DtRuta> rt = sistema.listarRutasDeVuelo();
        if(rt != null && !rt.isEmpty()) {
            for (DtRuta rtv : rt) {
                comboRutaDeVuelo.addElement(rtv);
            }
        }else{
            comboRutaDeVuelo.addElement(new DtRuta() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarRutasDeVueloComboBoxAerolinea(String nickname) {
        comboRutaDeVueloAerolinea.removeAllElements();
        List<DtRuta> rutas = sistema.listarRutasDeVuelo(nickname);
        if(rutas != null && !rutas.isEmpty()) {
            for (DtRuta r : rutas) {
                comboRutaDeVueloAerolinea.addElement(r);
            }
        }else{
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
        if(c != null && !c.isEmpty()) {
            for (DtCiudad ciudad : c) {
                comboCiudadOrigen.addElement(ciudad);
                comboCiudadDestino.addElement(ciudad);
            }
        }else{
            comboCiudadOrigen.addElement(new DtCiudad() {
                @Override
                public String toString() { return "N/A"; }
            });
            comboCiudadDestino.addElement(new DtCiudad() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarPaqueteComboBox() {
        comboPaquete.removeAllElements();
        List<DtPaquete> p = sistema.listarPaquetes();
        if(p != null && !p.isEmpty()) {
            for (DtPaquete pqt : p) {
                comboPaquete.addElement(pqt);
            }
        }else{
            comboPaquete.addElement(new DtPaquete() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarPaqueteNoCompradoComboBox() {
        comboPaqueteNoComprado.removeAllElements();
        List<DtPaquete> p = sistema.listarPaquetesNoComprados();
        if(p != null && !p.isEmpty()) {
            for (DtPaquete pqt : p) {
                comboPaqueteNoComprado.addElement(pqt);
            }
        }else{
            comboPaqueteNoComprado.addElement(new DtPaquete() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarVuelosComboBoxRuta(String nombre){
        comboVueloRutaDeVuelo.removeAllElements();
        List<DtVuelo> vuelos = sistema.listarVuelos(nombre);
        if(vuelos != null && !vuelos.isEmpty()) {
            for(DtVuelo v : vuelos){
                comboVueloRutaDeVuelo.addElement(v);
            }
        }else{
            comboVueloRutaDeVuelo.addElement(new DtVuelo() {
                @Override
                public String toString() { return "N/A"; }
            });
        }

    }

    public boolean esFechaValida(LocalDate fecha) {
        if (fecha == null) {
            return false;
        }

        LocalDate hoy = LocalDate.now();
        LocalDate limitePasado = hoy.minusYears(200);

        return !fecha.isAfter(hoy) && !fecha.isBefore(limitePasado);
    }

}
