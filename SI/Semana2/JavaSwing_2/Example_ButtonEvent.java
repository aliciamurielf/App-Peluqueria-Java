import javax.swing.*;
import java.awt.*;

/**
 * Evento al pulsar un botón
 * Mirar también la clase MyButtonListener
 */
public class Example_ButtonEvent {
  public static void main(String[] args) {
    JFrame jf = new JFrame("Manejo de eventos");
    
    GridLayout gl = new GridLayout(1,2);
    gl.setHgap(5);
    gl.setVgap(5);
    
    JPanel jp = new JPanel();
    jp.setLayout(gl);    
    
    // Crear 2 botones, ambos con el mismo ActionListener
    JButton button = new JButton("Pulsar");
    button.addActionListener(new MyButtonListener());
    jp.add(button);
    
    JButton button2 = new JButton("Puede pulsarme también");
    button2.addActionListener(new MyButtonListener());
    jp.add(button2);
    
    //Añadir panel al container
    Container cp = jf.getContentPane();
    cp.add(jp);

        
    jf.setSize(400, 200);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setVisible(true);
  }
}