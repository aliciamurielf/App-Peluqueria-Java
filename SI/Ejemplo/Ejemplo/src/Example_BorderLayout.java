import javax.swing.*;
import java.awt.*;

/**
 * BorderLayout en Java Swing
 */
public class Example_BorderLayout {
  public static void main(String[] args) {
    JFrame jf = new JFrame("Título de ventana");
    
    BorderLayout bl = new BorderLayout();

    //Espaciado entre elementos
    bl.setHgap(5);
    bl.setVgap(5);
    
    //Crear JPanel con la estructura de BorderLayout
    JPanel jp = new JPanel(bl);
    // Al añadir el elemento, indicar su posición
    jp.add(new JButton("East"), BorderLayout.EAST);
    jp.add(new JButton("West"), BorderLayout.WEST);
    jp.add(new JButton("North"), BorderLayout.NORTH);
    jp.add(new JButton("South"), BorderLayout.SOUTH);
    jp.add(new JButton("Center"), BorderLayout.CENTER);
    
    jf.add(jp);
        
    jf.setSize(400, 300);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setVisible(true);
  }
}