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





}
