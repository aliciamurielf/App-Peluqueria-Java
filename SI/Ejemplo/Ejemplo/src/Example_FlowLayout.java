import javax.swing.*;
import java.awt.*;

/**
 * FlowLayout en Java Swing
 */
public class Example_FlowLayout {
  public static void main(String[] args) {
    JFrame jf = new JFrame("Título de ventana");
    
    //Creamos un objeto de la estructura que deseemos (FlowLayout)
    FlowLayout fl = new FlowLayout();

    //Alineación de los elementos
    fl.setAlignment(FlowLayout.CENTER);

    //Espaciado entre elementos
    fl.setHgap(5);
    fl.setVgap(5);
    
    //Creamos un JPanel, indicandole el layout a utilizar, y añadimos los 
    // elementos
    JPanel jp = new JPanel(fl);
    jp.add(new JButton("Botón 1"));
    jp.add(new JButton("Botón 2"));
    jp.add(new JButton("Botón 3"));
    jp.add(new JButton("Botón 4"));
    
    // Añadimos el JPanel en nuestro JFrame
    jf.add(jp);
        
    jf.setSize(400, 300);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setVisible(true);
  }
}