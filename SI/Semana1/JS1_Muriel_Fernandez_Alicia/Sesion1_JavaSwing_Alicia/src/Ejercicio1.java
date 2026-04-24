import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;

public class Ejercicio1 {
    public static void main(String[] args) {
        JFrame jf = new JFrame("Ejercicio1");

        JPanel jp = new JPanel();
        jp.setBorder(new TitledBorder("Ejercicio1"));
        jp.setLayout(new GridLayout(2, 1));

        jp.add(new JLabel("Etiqueta: "));
        jp.add(new JButton("Botón"));
        jp.add(new JLabel("Check box: "));
        jp.add(new JCheckBox());

        jf.add(jp);

        jf.setSize(350, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}