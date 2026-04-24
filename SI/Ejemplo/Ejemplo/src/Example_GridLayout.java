import javax.swing.*;
import java.awt.*;

/**
 * GridLayout en Java Swing
 */
public class Example_GridLayout {
  public static void main(String[] args) {
    JFrame jf = new JFrame("Título de ventana");
    
    GridLayout gl = new GridLayout(3, 2);

    //Espaciado entre elementos
    gl.setHgap(5);
    gl.setVgap(5);
    
    //Crear JPanel con la estructura de GridLayout
    JPanel jp = new JPanel(gl);
    jp.add(new JButton("Botón 1"));
    jp.add(new JButton("Botón 2"));
    jp.add(new JButton("Botón 3"));
    jp.add(new JButton("Botón 4"));
    jp.add(new JButton("Botón 5"));
    jp.add(new JButton("Botón 6"));
    //jp.add(new JButton("Botón7"));
    
    jf.add(jp);
        
    jf.setSize(400, 300);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setVisible(true);
  }
}