import java.awt.event.*;
import javax.swing.*;

/**
 * Listener para botón que mueve el foco a un nuevo JPanel dentro de un JFrame
 */
public class FocusPanelButtonListener implements ActionListener{
    
    //JPanel que se visualizará
    JPanel panel;
    
    //JFrame donde está contenido el JPanel
    JFrame frame;
    
    public FocusPanelButtonListener(JPanel panel, JFrame frame) {
        this.panel = panel;
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Ponemos el panel visible (si no lo estaba)
        panel.setVisible(true);
        
        //Indicamos que será el panel principal ahora
        frame.setContentPane(panel);
        
        //Revalidar el frame para pintarlo correctamente
        frame.revalidate();
    }
}
