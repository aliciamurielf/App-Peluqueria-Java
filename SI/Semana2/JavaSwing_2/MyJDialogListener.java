import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Listener para el ejemplo de JDialog
 */
public class MyJDialogListener implements ActionListener{
    JFrame jf;
    
    public MyJDialogListener(JFrame cp) {
        this.jf = cp;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog dialog = new JDialog(jf, "Ventana secundaria");
        
        JPanel jp = new JPanel(new FlowLayout());
        jp.add(new JLabel("Esta ventana es secundaria."));
        jp.add(new JButton("OK"));
        jp.setVisible(true);
        
        dialog.setContentPane(jp);
        dialog.setSize(400, 150);
        dialog.setVisible(true);
        
    }
}