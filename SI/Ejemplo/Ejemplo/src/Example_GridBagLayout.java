import javax.swing.*;
import java.awt.*;

/**
 * GridBagLayout en Java Swing
 */
public class Example_GridBagLayout {
  public static void main(String[] args) {
    JFrame jf = new JFrame("Ejemplo de GridBagLayout");
    
    //Crear JPanel con estructura GridBagLayout, y añadir al JFrame
    JPanel panel = new JPanel(new GridBagLayout());
    jf.setContentPane(panel);
    
    //Crear objeto GridBagConstrainsts para ir utilizando posteriormente
    GridBagConstraints c = new GridBagConstraints();

    // Valores que vamos a considerar comunes
    c.fill = GridBagConstraints.BOTH;
    c.insets = new Insets(1, 2, 1, 2);
    
    c.gridx = 0;
    c.gridy = 0;
    c.gridheight = 2;
    c.gridwidth = 1;
    panel.add(new JButton("<html>gridx 0, gridy 0 <br /> gridheight 2</html>"), c);
    
    c.gridx = 1;
    c.gridy = 0;
    c.gridheight = 1;
    c.gridwidth = 1;
    panel.add(new JButton("gridx 1, gridy 0"), c);
    
    c.gridx = 2;
    c.gridy = 0;
    c.gridheight = 1;
    c.gridwidth = 1;
    panel.add(new JButton("gridx 2, gridy 0"), c);
    
    c.gridx = 3;
    c.gridy = 0;
    c.gridheight = 1;
    c.gridwidth = 2;
    panel.add(new JButton("gridx 3, gridy 0; gwidth 2"), c);
    
    c.gridx = 1;
    c.gridy = 1;
    c.gridheight = 1;
    c.gridwidth = 4;
    panel.add(new JButton("gridx 1, gridy 1; gridwidth 4"), c);
        
    // Ajustar el tamaño del JFrame automáticamente
    jf.pack();
    
    // Establecer una altura fija
    jf.setSize(jf.getWidth(), 200);

    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setVisible(true);
  }
}