import java.awt.event.*;
import javax.swing.*;

/**
 * Listener para volver al JFrame principal al cerrar el JDialog
 */
public class FocusFrameListener extends WindowAdapter{
    
    JFrame main_frame;
    JDialog dialog;
    
    public FocusFrameListener(JFrame main_frame, JDialog dialog){
        this.main_frame = main_frame;
        this.dialog = dialog;
    }
    
    public void windowClosing(WindowEvent e) {
        main_frame.setVisible(true);
        dialog.setVisible(false);
    }
    
    public void windowClosed(WindowEvent e) {
        main_frame.setVisible(true);
        dialog.setVisible(false);
    }
}