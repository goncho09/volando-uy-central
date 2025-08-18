import datatypes.DtFecha;
import clases.ISistema;
import clases.Factory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JPanel menuPrincipal;
    private JTabbedPane tabbedPane1;
    private JPanel opcionesMenu;
    private JButton panel1Button;
    private JButton panel2Button;
    private JButton panel4Button;
    private JButton panel3Button;

    public Main(){

        add(menuPrincipal);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);



        panel1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(0);
            }
        });
        panel2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(1);
            }
        });
        panel3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(2);
            }
        });
        panel4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(3);
            }
        });
    }

    public static void main(String[] args) {

        new Main();

        System.out.println("hgoaññ");
        DtFecha fecha = new DtFecha(1,4,2003);
        DtFecha fecha2 = new DtFecha(2,4,2003);

        System.out.println(fecha.equals(fecha2));

        ISistema s1 = Factory.getSistema();

        s1.ejecutar();

    }
}