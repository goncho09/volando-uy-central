package com.app;

import javax.swing.*;

public class auxiliarFunctions {

    //Función para validar 1 o más "JComboBox"
    public static boolean estanVaciosJComboBox(JComboBox<?>... combos) {
        for (JComboBox<?> combo : combos) {
            Object item = combo.getSelectedItem();
            if (item == null || item.toString().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean estanVaciosJTextField(JTextField... fields) {
        for (JTextField field : fields) {
            if (field.getText() == null || field.getText().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }



}
