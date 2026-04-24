import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;

/**
 * Ejemplo de JPanel con título
 */
public class Example_JPanel {
  public static void main(String[] args) {
    JFrame jf = new JFrame("Título de ventana");
    
    //Crear JPanel y añadir elementos
    JPanel jp = new JPanel();
    jp.setLayout(new FlowLayout());
    jp.setBorder(new TitledBorder("JPanel"));
    jp.add(new JButton("Botón"));

    //Añadir panel al JFrame
    jf.add(jp);

    jf.setSize(400, 300);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setVisible(true);
  }
}