import javax.swing.*;
import java.awt.*;

/**
 * Ejemplo de JDialog. Se muestra junto con el JFrame
 */
public class Example_JDialog {
  public static void main(String[] args) {
    JFrame jf = new JFrame("Ejemplo de showMessageDialog");
    
    JPanel jp = new JPanel();
    jp.setLayout(new FlowLayout());
    
    JButton jb = new JButton("Botón");
    jb.addActionListener(new MyJDialogListener(jf));
    jp.add(jb);

    //Añadir panel al container
    Container cp = jf.getContentPane();
    cp.add(jp);
    
    jf.setSize(350, 120);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setVisible(true);
  }
}