import javax.swing.*;
import java.awt.*;

/**
 * Ejemplo de una interfaz con varios JPanels que van cambiando
 * Se modifica el container completo de la ventana
 */
public class Example_ChangePanels {
  public static void main(String[] args) {
    JFrame jf = new JFrame("Ejemplo de modificación del foco de JPanel");
    
    //Crear JPanel 1
    FlowLayout fl = new FlowLayout();
    fl.setAlignment(FlowLayout.CENTER);
    fl.setHgap(5);
    fl.setVgap(5);
    JPanel jp1 = new JPanel(fl);
    JLabel label = new JLabel("JPanel 1");
    jp1.add(label);
    
    //Crear JPanel 2
    GridLayout gl = new GridLayout();
    gl.setHgap(5);
    gl.setVgap(5);
    JPanel jp2 = new JPanel(gl);
    JLabel label2 = new JLabel("JPanel 2");
    jp2.add(label2);
    
    //Boton para ir de JP1 a JP2
    //  Incrustado en el panel JP1
    JButton button = new JButton("Go To JP2");
    button.addActionListener(new FocusPanelButtonListener(jp2, jf));
    jp1.add(button);
    
    //Boton para ir de JP2 a JP1
    //  Incrustado en el panel JP2
    JButton button2 = new JButton("Go To JP1");
    button2.addActionListener(new FocusPanelButtonListener(jp1, jf));
    jp2.add(button2);

    //Indicar el JP1 como visible y como panel principal
    jp1.setVisible(true);
    jf.setContentPane(jp1);
    
    //Parametros del JFrame
    jf.setSize(300, 200);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setVisible(true);
  }
}