import javax.swing.*;
import java.awt.*;

public class Ejercicio2 {
    public static void main(String[] args) {
        JFrame jf = new JFrame("Ejercicio2");

        jf.setLayout(new BorderLayout());

        FlowLayout flArriba = new FlowLayout();
        flArriba.setAlignment(FlowLayout.CENTER); 
        JPanel jpArriba = new JPanel(flArriba);
        jpArriba.add(new JLabel("Ejercicio2")); 
        jf.add(jpArriba, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(1, 2, 1, 2);
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1; 
        c.gridwidth = 1;
        panel.add(new JButton("A"), c);

        c.gridx = 1; 
        c.gridy = 0;
        c.gridheight = 1; 
        c.gridwidth = 1;
        panel.add(new JButton("B"), c);

        c.gridx = 2; 
        c.gridy = 0;
        c.gridheight = 2; 
        c.gridwidth = 1;
        panel.add(new JButton("D"), c);

        c.gridx = 0; 
        c.gridy = 1;
        c.gridheight = 1; 
        c.gridwidth = 2;
        panel.add(new JButton("C"), c);

        jf.add(panel, BorderLayout.CENTER);

        FlowLayout flAbajo = new FlowLayout();
        flAbajo.setAlignment(FlowLayout.RIGHT); 
        JPanel jpAbajo = new JPanel(flAbajo);
        jpAbajo.add(new JButton("Cancelar"));
        jpAbajo.add(new JButton("Aceptar"));

        jf.add(jpAbajo, BorderLayout.SOUTH);

        jf.setSize(400, 250);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}