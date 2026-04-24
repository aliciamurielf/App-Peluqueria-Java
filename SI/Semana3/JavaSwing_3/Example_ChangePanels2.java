import javax.swing.*;
import java.awt.*;

/**
 * Ejemplo de una interfaz con varios JPanels que van cambiando
 * Se modifica unicamente uno de los JPanels de la ventana, sin alterar el resto
 */
public class Example_ChangePanels2 {
  public static void main(String[] args) {
    JFrame jf = new JFrame("Ejemplo de modificación del foco de JPanel 2");
    
    //JPanel principal
    BorderLayout bl = new BorderLayout();
    JPanel main_jp = new JPanel(bl);
    main_jp.add(new JLabel("Panel principal"), BorderLayout.NORTH);
    
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
    button.addActionListener(new FocusPanelButtonListener2(main_jp, jp1, jp2, 
            BorderLayout.CENTER, jf));
    jp1.add(button);
    
    //Boton para ir de JP2 a JP1
    //  Incrustado en el panel JP2
    JButton button2 = new JButton("Go To JP1");
    button2.addActionListener(new FocusPanelButtonListener2(main_jp, jp2, jp1, 
            BorderLayout.CENTER, jf));
    jp2.add(button2);

    //Indicar el JP1 como visible y como panel principal
    jp1.setVisible(true);
    main_jp.add(jp1, BorderLayout.CENTER);
    jf.setContentPane(main_jp);
    
    //Parametros del JFrame
    jf.setSize(300, 200);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setVisible(true);
  }
}