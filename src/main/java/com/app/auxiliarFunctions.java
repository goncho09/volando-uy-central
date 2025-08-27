package com.app;

import com.app.DAOs.UserDao;
import com.app.clases.Usuario;

import javax.swing.*;
import java.util.List;

public class auxiliarFunctions {

    private DefaultComboBoxModel<Usuario> comboUser = new DefaultComboBoxModel<>();
    private UserDao userDao;

    public auxiliarFunctions(UserDao userDao) {
        this.userDao = userDao;
    }

    //Getters de los modelos
    public DefaultComboBoxModel<Usuario> getComboUserModel() {
        return comboUser;
    }


    //Función para validar 1 o más "JComboBox"
    public boolean estanVaciosJComboBox(JComboBox<?>... combos) {
        for (JComboBox<?> combo : combos) {
            Object item = combo.getSelectedItem();
            if (item == null || item.toString().trim().isEmpty()) {
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

}
