import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Listener para mostrar un JDialog por defecto
 */
public class MessageDialogListener implements ActionListener{
    Container cp;
    
    public MessageDialogListener(Container cp) {
        this.cp = cp;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(cp, "Ha ocurrido un error", "Error!",
                JOptionPane.WARNING_MESSAGE);
    }
}