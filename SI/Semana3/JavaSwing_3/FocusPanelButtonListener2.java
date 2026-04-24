import java.awt.event.*;
import javax.swing.*;

/**
 * Listener para botón que mueve el foco a un JPanel dentro de otro JPanel
 */
public class FocusPanelButtonListener2 implements ActionListener{
    //Panel en el que incluir el nuevo panel
    JPanel parent_panel;
    
    //JPanel que se eliminará
    JPanel old_panel;
    
    //JPanel que se incluirá
    JPanel new_panel;
    
    //Posicion para el JPanel
    String position ;
    
    //JFrame donde está contenido el JPanel
    JFrame frame;
    
    public FocusPanelButtonListener2(JPanel parent_panel, JPanel old_panel, 
            JPanel new_panel, String position, JFrame frame) {
        this.parent_panel = parent_panel;
        this.old_panel = old_panel;
        this.new_panel = new_panel;
        this.position = position;
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Eliminar panel anterior
        old_panel.setVisible(false);
        parent_panel.remove(old_panel);

        //Incluir nuevo panel
        new_panel.setVisible(true);
        parent_panel.add(new_panel, position);
        
        //Revalidar el frame para pintarlo correctamente
        frame.revalidate();
    }
}