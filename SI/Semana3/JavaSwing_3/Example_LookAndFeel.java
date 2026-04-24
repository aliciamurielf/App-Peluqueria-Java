import javax.swing.*;
import java.awt.*;

/**
 * Selección de Java Look and Feel
 */
public class Example_LookAndFeel {
  public static void main(String[] args) {
    try{
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e){
        System.out.println("An error ocurred! Using default look and feel.");
        JFrame.setDefaultLookAndFeelDecorated(false);
        JDialog.setDefaultLookAndFeelDecorated(false);
    }
      
    JFrame jf = new JFrame("Look and Feel");
    
    JPanel jp = new JPanel();
    jp.setLayout(new FlowLayout());
    
    JButton jb = new JButton("Botón");
    jb.addActionListener(e -> {
      JDialog dialog = new JDialog(jf, "Ventana secundaria");

      JPanel panelDialogo = new JPanel(new FlowLayout());
      panelDialogo.add(new JLabel("Esta ventana es secundaria."));
      panelDialogo.add(new JButton("OK"));

      dialog.setContentPane(panelDialogo);
      dialog.setSize(400, 150);
      dialog.setVisible(true);
    });
    jp.add(jb);

    //Añadir panel al container
    Container cp = jf.getContentPane();
    cp.add(jp);
        
    jf.setSize(350, 120);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setVisible(true);
  }
}